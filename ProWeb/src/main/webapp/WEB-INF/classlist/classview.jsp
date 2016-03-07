<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<!--  검색바 -->
	
	<!--JS-->
	 <script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 	<script src="/edutube/resources/JS/bootstrap.min.js"></script>
 	
	<!--스크립트-->
	<script>
	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
		#nav {
    	line-height:20px;
    	float:left;
    	padding:5px;	      
		}
		tr > th {
			text-align:center;
		}
	</style>
</head>
<body>
<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>
		상세보기
	<div id=nav>
	<a href="teacherlist.do">강사별 목록</a><br>
	<a href="classlist.do">강의별 목록</a><br>
	<a href="bestlist.do">인기별 목록</a><br>
	</div>
	<table width="800" border="1" align="center">
		<tr>
			<th>강의번호</th>
			<th>강의코드</th>
			<th>제　  목</th>
			<th>작 성 자</th>
			<th>작 성 일</th>
			<th>추 천 수</th>
		</tr>
		<c:forEach var="temp" items="${LIST}">
		<tr>
			<td>${DATA.no}</td>
			<td>${DATA.code}</td>
			<td>${DATA.title}</td>
			<td>${DATA.id}</td>
			<td>${DATA.date}</td>
			<td>${DATA.good}</td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>