<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>자료실</title>
		<!--CSS-->
		<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
		<!--  파피콘 넣기 -->
		<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<!--  검색바 -->
		
		<!--JS-->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 		<script src="/edutube/resources/JS/bootstrap.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#lBtn").click(function(){
					$(location).attr("href","../../edutube");
				});
			});
			 $(document).ready(function(){
				$("#wBtn").click(function(){
					$(location).attr("href", "./DownWrite.do");
				});
			}); 
		</script>
	</head>
	<body>
		<div id='EduContainer'>
			<div id='top'>	
				<jsp:include page="/MenuBar/Top.jsp" flush="false" />
			</div>
			<div>
				a
			</div>
			<div id="main">	
				<table width="80%" border="1" align="center">
					<tr>
						<th width="10%">번호</th>
						<th width="50%">제목</th>
						<th width="15%">글쓴이</th>
						<th width="15%">날짜</th>
						<th width="10%">조회수</th>
					</tr>
				<c:forEach var="temp" items="${LIST}">
					<tr align="center">
						<td>${temp.no}</td>
						<td>
							<a href="#">${temp.title}</a>
						</td>
						<td>${temp.id}</td>
						<td>${temp.wdate}</td>
						<td>0</td>
					</tr>
				</c:forEach>
					<tr>
						<td colspan="5" align="center"><input type="button" value="write" id="wBtn"><input type="button" value="back" id="lBtn"></td>	
					</tr>
				</table>
			</div>
		</div>
		
	</body>
</html>