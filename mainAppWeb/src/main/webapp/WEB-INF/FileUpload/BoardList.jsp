<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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
	</style>
	<style>
		#Table{
			background-color: MistyRose;<!--Joon -->
		}
	</style>	
</head>
<body>
<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
		<p><h3 align="center" >공지 사항</h3></p>
<!-- 	목록보기 만들고 -->
		<table border="1" align="center" width="80%">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>첨부파일</th>
			</tr>
<c:forEach var="temp" items="${LIST}">
			<tr>
				<td>${temp.no}</td>
				<td>${temp.title}</td>
				<td>${temp.writer}</td>
				<td align="center">
	<c:if test="${not empty temp.oriname}">
				<a href="../FileUpload/FileDownload.do?oriNo=${temp.no}"><img src="../images/down.gif"></a>	
	</c:if>			
				</td>
			</tr>
</c:forEach>			
		</table>
<!-- 	기타 기능 만들고 -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<input type="button" id="wBtn" value="글쓰기">
				</td>
			</tr>
		</table>
		</div>
	</div>
	</body>
</html>
