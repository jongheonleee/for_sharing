package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ✅ 탐색자, filterServlet의 분기문 개선하기 위한 chain of responsibility 패턴 적용  
public abstract class Explorer {
	private Explorer next;
	
	public Explorer() {
		next = null;
	}
	
	public Explorer setNext(Explorer next) {
		this.next = next;
		return next;
	}
	
	public void check(HttpServletRequest request, HttpServletResponse response, MovingData movingData, String clickedLogout) throws ServletException, IOException {
		if (!found(request, response, movingData, clickedLogout)) {
			next.check(request, response, movingData, clickedLogout);
		}
	}
	
	public abstract boolean found(HttpServletRequest request, HttpServletResponse response, MovingData movingData, String clickedLogout) throws ServletException, IOException;

	
	
}
