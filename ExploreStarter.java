package com.codechobo.quiz05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ✅ 시작 탐색자  
public class ExploreStarter extends Explorer {
	
	public ExploreStarter() {
		super();
	}

	@Override
	public boolean found(HttpServletRequest request, HttpServletResponse response, MovingData movingData,
			String clickedLogout) throws ServletException, IOException {
		return false;
	}

}
