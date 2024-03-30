package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// ✅ 역할 : 로그인 실패시 처리해야하는 작업 및 해당 페이지 보여줌
@WebServlet("/loginFalse/*")
public class LoginFalseServlet05  extends HttpServlet {
	
	private static final String pathFormat = "	> loginFalseServlet : %s \n\n";
	private static final String urlFormat = "/quiz/quiz05/loginForm05.jsp?from=%s";
	private final Parser parser;
	
	public LoginFalseServlet05() {
		parser = Parser.getInstance();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		MovingData movingData = parser.parseMovingDataFromRequest(request); // 모든 서블릿에서 사용하는 공통 데이터 MovingData로 묶어서 관리
		HttpSession session = request.getSession(); // 세션 조회 
		showPathAndMovingData(movingData); // 현재 경로와 이동하는 공통  데이터 정보 확인 
		
		// 0. 세션 삭제
		// 1. 쿠키 삭제 
		// 2. 로그인 폼페이지로 리다이렉트
			// 이전 페이지 정보, 파라미터로 전달
		deleteSession(session);
		deleteCookie(response);
		goToLoginForm(response, movingData.getFrom());
	}
	
	
	private void deleteSession(HttpSession session) {
		session.invalidate();
	}
	
	private void deleteCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("id", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	private void goToLoginForm(HttpServletResponse response, String from) throws IOException {
		response.sendRedirect(String.format(urlFormat, from));
	}
	
	private void showPathAndMovingData(MovingData movingData) {
		System.out.printf(pathFormat, movingData);
	}
	
}
