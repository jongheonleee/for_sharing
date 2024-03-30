<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 이전 페이지 정보 조회 
	String from = request.getParameter("from");
	if (from == null  || from == "") {
		from = "index05.jsp";
	}
	String userId = "";

	// 쿠키 조회
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("id")) {
			userId = cookie.getValue();
			request.setAttribute("checked", true);
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {font-family: Arial, Helvetica, sans-serif;}

	
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

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}


.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.createbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #04AA6D;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>
</head>
<body>
	<!-- 헤더 부분, 고정적인 부분 공통처리-->
	<jsp:include page="header05.jsp" flush="false"/>
	
	
	<form action="/filter05" method="post">
	  <div class="container">
	    <label><b>ID</b></label>
	    <input type="text" placeholder="Enter ID" name="id" id="id" value="<%= userId %>"  required>
	
	    <label><b>PASSWORD</b></label>
	    <input type="text" placeholder="Enter PASSWORD" name="pwd" id="pwd" required>
		<input type="hidden" name="from" id="from" value=<%= from %>>
	    <button type="submit" class="loginButton">login</button>
	    <br>
	    <label>
	      <input id = "remember" type="checkbox" name="remember" ${ checked ? 'checked' :''}> remember me
	    </label>
	  </div>
	</form>
	
	
	<!-- 푸터 부분, 고정적인 부분 공통처리 -->
	<jsp:include page="footer05.jsp" flush="false"/>
</body>
</html>