package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// ✅ 역할 : 회원이 접근하면, 세션이나 쿠키 관련 작업 처리 및 이전 페이지 포워딩
@WebServlet("/loginSuccess/*")
public class LoginSuccessServlet05 extends HttpServlet {
	
	
	private static final String format = "	> loginSuccessServlet : %s \n\n";
	private final Parser parser; // 요청 객체(request) 파서해서 정보 추출 
	
	public LoginSuccessServlet05() {
		this.parser = Parser.getInstance();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		MovingData movingData = parser.parseMovingDataFromRequest(request); // 모든 서블릿에서 사용하는 공통 데이터 MovingData로 묶어서 관리
		String remember = request.getParameter("remember"); // "remember me" 체크 조회 
		HttpSession session = request.getSession(); // 세션 조회 

		showPathAndMovingData(movingData); // 현재 경로와 이동하는 공통 데이터 정보 확인 
		
		// 0. 세션 확인
			// 0-0. 세션 있음, 넘어감
			// 0-1. 세션 없음, 세션 생성
			
		// 1. "remember me" 체크 확인
			// 1-0. 체크됨, 쿠키 생성
			// 1-1. 체크되지 않음, 쿠키 삭제 
		
		// 2. 이전 페이지로 이동
			// 이전 페이지 파라미터로 넘김
			// 없으면, 기본 페이지(index05.jsp)
		
		if (!isSession(session, movingData.getId())) {
			createSession(session, movingData.getId());
		}
	
		if (isClickRemember(remember)) {
			createCookie(response, movingData.getId());
		} else {
			deleteCookie(response);
		}
		
		goToFrom(request, response, movingData.getFrom());
			
	}
	
	private boolean isSession(HttpSession session, String id) {
		if (id != null) {
			return session.getAttribute("id") != null && session.getAttribute("id").equals(id);
		}
		
		return session.getAttribute("id") != null && session.getAttribute("id").equals("asdf");
	}
	
	private boolean isClickRemember(String remember) {
		return remember != null;
	}
	
	private void createCookie(HttpServletResponse response, String id) {
		Cookie cookie = new Cookie("id", id);
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);
	}
	
	private void deleteCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("id", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	private void createSession(HttpSession session, String id) {
		session.setAttribute("id", id);
	}
	
	private void goToFrom(HttpServletRequest request, HttpServletResponse response, String from) throws ServletException, IOException {
		RequestDispatcher resDis = null;
		if (!"null".equals(from) && from != null && from.length() > 0) {
			resDis = request.getRequestDispatcher("/quiz/quiz05/"+from);
		} else {
			resDis = request.getRequestDispatcher("/quiz/quiz05/index05.jsp");
		}
		resDis.forward(request, response);
		
	}
	
	private void showPathAndMovingData(MovingData movingData) {
		System.out.printf(format, movingData);
	}
	

}
