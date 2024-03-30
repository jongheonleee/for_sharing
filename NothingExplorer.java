package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//✅ 마지막 탐색자, 무조건 LoginFalseServlet으로 포워딩   
public class NothingExplorer extends Explorer {
	
	public NothingExplorer() {
		super();
	}

	@Override
	public boolean found(HttpServletRequest request, HttpServletResponse response, MovingData movingData,
			String clickedLogout) throws ServletException, IOException {
		goToLoginFalseServlet(request, response);
		return true;
	}
	
	// 로그인 실패, 로그인 실패 서블릿으로 포워딩 
	private void goToLoginFalseServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher resDis = request.getRequestDispatcher("/loginFalse/");
		resDis.forward(request, response);
	}

}
