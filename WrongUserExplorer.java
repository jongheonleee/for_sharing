package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ✅ 비회원인지 확인하고 LoginFalseServlet으로 포워딩 
public class WrongUserExplorer extends Explorer {
	
	public WrongUserExplorer() {
		super();
	}

	@Override
	public boolean found(HttpServletRequest request, HttpServletResponse response, MovingData movingData,
			String clickedLogout) throws ServletException, IOException {
		if (isWrongUser(movingData.getId(), movingData.getPwd())) {
			goToLoginFalseServlet(request, response);
			return true;
		}
		
		return false;
	}
	
	private boolean isWrongUser(String id, String pwd) {
		if (id.length() > 0 && pwd.length() > 0) {
			return !isValidUser(id, pwd);
		}
		
		return false;
	}
	
	// 데이터가 들어왔고 해당 데이터가 회원 정보가 맞는지 판단
	private boolean isValidUser(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
	
	// 로그인 실패, 로그인 실패 서블릿으로 포워딩 
	private void goToLoginFalseServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher resDis = request.getRequestDispatcher("/loginFalse/");
		resDis.forward(request, response);
	}

}
