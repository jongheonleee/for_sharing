package com.codechobo.quiz05;

import javax.servlet.http.HttpServletRequest;

// ✅ request 객체에서 정보 추해줌, Singleton 패턴 적용
public final class Parser {

	private static final Parser singleton = new Parser();
	
	private Parser() {}
	
	public synchronized String parseClickedPage(String str) {
		int count = 0;
		
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i) == '/') {
				count++;
			}
			
			if (count == 2) {
				return str.substring(i+1);
			}
		}
		
		return "";
	}
	
	public synchronized MovingData parseMovingDataFromRequest(HttpServletRequest request) {
		String method = request.getMethod();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String from = request.getParameter("from");
		return MovingData.newInstance(method, id, pwd, from);
	}
	
	public static Parser getInstance() {
		return singleton;
	}
	
	
}
