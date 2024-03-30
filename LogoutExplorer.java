package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutExplorer extends Explorer {
	
	public LogoutExplorer() {
		super();
	}

	@Override
	public boolean found(HttpServletRequest request, HttpServletResponse response, MovingData movingData,
			String clickedLogout) throws ServletException, IOException {
		if (isTryingLogout(request)) {
			goToLogoutServlet(request, response);
			return true;
		}
		
		return false;
	}
	
	// 로그아웃 시도했는지 체크
	private boolean isTryingLogout(HttpServletRequest request) {
		String check = request.getParameter("logout");
		return "clicked".equals(check);
	}
	
	// 로그아웃, 로그아웃 서블릿으로 포워딩 
	private void goToLogoutServlet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		RequestDispatcher resDis = httpRequest.getRequestDispatcher("/logout/");
		resDis.forward(httpRequest, httpResponse);
	}

}
