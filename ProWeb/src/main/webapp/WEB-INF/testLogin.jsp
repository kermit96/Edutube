<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title</title>
	

	
</head>
<body>
	<%	
		session.setAttribute("ID", "1234");
		session.setAttribute("NICK", "자바마스터");
		
	%>
	<c:redirect url="/" />
</body>
</html>



