<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

</head>
	<body>
		<table border="1" align="center" width="80%">
			<tr>
				<td>제목</td>
				<td>본문</td>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.title}</td>
					<td>${temp.body}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>