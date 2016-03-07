<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id="charmvit";
String pwd="1234";
String name="차준호";

if(id.equals(request.getParameter("id")) &&
		pwd.equals(request.getParameter("pwd")) ){
	
	session.setAttribute("loginUser",name);
	session.setAttribute("loginId",id);
	//response.sendRedirect("home");
	System.out.println("session saved");
	//response.sendRedirect("/");
	
}
else{
	
	System.out.println("session not equal..");
	//response.sendRedirect("index.jsp");
	
}
//response.sendRedirect("../index.jsp");
//response.sendRedirect("../Smart/BoardList.do");
 response.sendRedirect("../Notice/NoticeList.do");
%>
</body>
</html>