package com.codechobo.quiz05;


// ✅ 모든 서블릿에서 공통으로 필요한 데이터 묶음
public class MovingData {
	
	private static StringBuilder sb = new StringBuilder();
	
	private final String method;
	private final String id;
	private final String pwd;
	private final String from;
	
	private MovingData(String method, String id, String pwd, String from) {
		this.method = method;
		this.id = id;
		this.pwd = pwd;
		this.from = from;
	}
	
	public String getId() {
		return id != null ? id : "";
	}
	
	public String getPwd() {
		return pwd != null ? pwd : "";
	}
	
	public String getFrom() {
		return from != null ? from : "";
	}
	
	
	public static MovingData newInstance(String method, String id, String pwd, String from) {
		return new MovingData(method, id, pwd, from);
	}
	

	@Override
	public String toString() {
		sb.setLength(0);
		
		sb.append("[ METHOD : ").append(method).append("]").append(" -> ")
		  .append("id : ").append(id).append(", ")
		  .append("pwd : ").append(pwd).append(", ")
		  .append("from : ").append(from).append(" ");
		
		return sb.toString();
	}
}
