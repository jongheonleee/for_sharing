package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// ✅ 세션 확인, 이미 세션 존재하면 클린한 버튼으로 리다이렉트
public class AlreadyLoginedUserExplorer extends Explorer {
	
	public AlreadyLoginedUserExplorer() {
		super();
	}

	@Override
	public boolean found(HttpServletRequest request, HttpServletResponse response, MovingData movingData, String clickedLogout) throws ServletException, IOException {
		if (isAlreadyLogined(request, movingData.getId(), movingData.getPwd())) {
			showClickedPage(request, response);
			return true;
		}
		return false;
	}
	
	private boolean isAlreadyLogined(HttpServletRequest request, String id, String pwd) {
		HttpSession session = request.getSession();
		return session.getAttribute("id") != null && session.getAttribute("id").equals("asdf");
	}
	
	private void showClickedPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String clickedPage = parseClickedPage(servletPath);
		
		if (clickedPage != null && clickedPage.length() > 0) {
			response.sendRedirect("/quiz/quiz05/"+clickedPage);
		} else {
			response.sendRedirect("/quiz/quiz05/index05.jsp");
		}
	}
	
	private String parseClickedPage(String servletPath) {
		int count = 0;
		
		for (int i=0; i<servletPath.length(); i++) {
			if (servletPath.charAt(i) == '/') {
				count++;
			}
			
			if (count == 2) {
				return servletPath.substring(i+1);
			}
		}
		
		return "";
	}

}
