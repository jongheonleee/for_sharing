package com.codechobo.quiz05;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



// ✅ 역할 : 모든 요청 받기, 흐름 제어 
@WebFilter(filterName = "LoginFilter05", urlPatterns = {"/filter05/*"})
public class FilterServlet05 implements Filter  {

	private static final String format = " > filterServlet : %s \n\n"; 
	private final Parser parser; // 요청 객체(request) 파서해서 정보 추출 
	private Explorer exploreStarter; // 흐름 제어 로직, 객체로 다루기 
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public FilterServlet05() {
		parser = Parser.getInstance(); //  Singleton 패턴 적용 
		exploreStarter = new ExploreStarter(); // Chain of Responsibility 패턴 적용 
		
		Explorer explorerLogout = new LogoutExplorer();
		Explorer explorerValidUser = new ValidUserExplorer();
		Explorer explorerWrongUser = new WrongUserExplorer();
		Explorer explorerAlreadyLoginedUser = new AlreadyLoginedUserExplorer();
		Explorer explorerNoting = new NothingExplorer();
		
		exploreStarter.setNext(explorerLogout)
					  .setNext(explorerValidUser)
					  .setNext(explorerWrongUser)
					  .setNext(explorerAlreadyLoginedUser)
					  .setNext(explorerNoting);
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fliterChain)
			throws IOException, ServletException {
		// 필요 데이터 조회 
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		MovingData movingData = parser.parseMovingDataFromRequest(httpRequest); // 모든 서블릿에서 사용하는 공통 데이터 MovingData로 묶어서 관리
		String check = httpRequest.getParameter("logout"); // 로그아웃 버튼 클릭 정보 조회 
		showPathAndMovingData(movingData); // 현재 경로와 이동하는 공통  데이터 정보 확인  
		// FilterServlet에서 흐름 제어하는 부분 exploreStarter가 탐색, 내부적으로 처리하는 과정
			// 0. 로그아웃 확인
				// 0-0. LogoutServlet으로 포워딩 
			// 1. 회원 확인
				// 1-0. LoginSuccessServlet으로 포워딩
			// 2. 세션 확인
				// 2-0. 원래 이동할 페이지로 리다이렉트
			// 3. 비회원 확인
				// 3-0. LoginFalseServlet으로 포워딩 
			// 4. 그 외의 경우
				// 4-0. LoginFalseServlet으로 포워딩 
		exploreStarter.check(httpRequest, httpResponse, movingData, check);
		
		
	}
	
	private void showPathAndMovingData(MovingData movingData) {
		System.out.printf(format, movingData);
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
