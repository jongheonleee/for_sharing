<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String from = "";
	String current = "index05.jsp";
	String logout = "clicked";
	
	if (request.getParameter("from") != null) {
		from = request.getParameter("from");
	} else {
		from = "index05.jsp";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/* Style the body */
	body {
		font-family: Arial, Helvetica, sans-serif;
		margin: 0;
	}
	
	/* Header/logo Title */
	.header {
		 padding: 80px;
		 text-align: center;
		 background: #1abc9c;
		 color: white;
	}
		
	/* Increase the font size of the heading */
	.header h1 {
		 font-size: 40px;
	}
	
	.footer {
	  padding: 40px;
	  text-align: center;
	  background: #ddd;
	}
	/* Style the top navigation bar */
	.navbar {
	  overflow: hidden;
	  background-color: #333;
	}
	
	/* Style the navigation bar links */
	.navbar a {
	  float: left;
	  display: block;
	  color: white;
	  text-align: center;
	  padding: 14px 20px;
	  text-decoration: none;
	}
	
	/* Right-aligned link */
	.navbar a.right {
	  float: right;
	}
	
	/* Change color on hover */
	.navbar a:hover {
	  background-color: #ddd;
	  color: black;
	}
</style>
</head>
<body>
	<jsp:include page="header05.jsp" flush="false"/>
	
	<div class="navbar">
	  <a href="/quiz/quiz05/index05.jsp?from=<%=from%>">홈</a>
	  <a href="/filter05/board05.jsp?from=<%=from%>">글목록</a>
	  <a href="/filter05/loginForm05.jsp?from=<%=from%>">로그인</a>
	  <a href="/filter05?from=<%=current%>&&logout=<%=logout %>" class="right">로그아웃</a>
	</div>
	
	<img src="/quiz/img/img1.png">
	<jsp:include page="footer05.jsp" flush="false"/>
</body>
</html>