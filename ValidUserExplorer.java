package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ✅ 회원 탐색자, 회원인지 판단하고 이전 페이지로 포워딩 
public class ValidUserExplorer extends Explorer {

	@Override
	public boolean found(HttpServletRequest request, HttpServletResponse response, MovingData movingData,
			String clickedLogout) throws ServletException, IOException {
		if (isValidUser(movingData.getId(), movingData.getPwd())) {
			goToLoginSuccessServlet(request, response);
			return true;
		}
		
		return false;
	}
	
	// 데이터가 들어왔고 해당 데이터가 회원 정보가 맞는지 판단
	private boolean isValidUser(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
	
	// 로그인 성공, 로그인 성공 서블릿으로 포워딩 
	private void goToLoginSuccessServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher resDis = request.getRequestDispatcher("/loginSuccess/");
		resDis.forward(request, response);
	}
	
}
