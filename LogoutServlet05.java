package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// ✅ 역할 : 로그아웃 버튼 누르면, 로그아웃 처리 및 기본 페이지(index)로 이동 
@WebServlet("/logout/*")
public class LogoutServlet05 extends HttpServlet {
	
	
	private static final String format = "	> loginFalseServlet : %s \n\n";
	private final Parser parser; // 요청 객체(request) 파서해서 정보 추출 

	public LogoutServlet05() {
		parser = Parser.getInstance(); 
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		MovingData movingData = parser.parseMovingDataFromRequest(request); // 모든 서블릿에서 사용하는 공통 데이터 MovingData로 묶어서 관리
		HttpSession session = request.getSession();	// 세션 조회 
		showPathAndMovingData(movingData); // 현재 경로와 이동하는 공통 데이터 정보 확인 
		
		// 0. 세션 삭제
		// 1. 기본 페이지(index)로 이동 
		deleteSession(session);
		goToIndex(response, movingData.getFrom());
	}
	
	private void deleteSession(HttpSession session) {
		session.invalidate();
	}
	
	private void goToIndex(HttpServletResponse response, String from) throws IOException {
		response.sendRedirect("quiz/quiz05/index05.jsp?from="+from);
	}
	
	private void showPathAndMovingData(MovingData movingData) {
		System.out.printf(format, movingData);
	}
}
