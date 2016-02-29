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
		인기별 목록
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
			<td>${temp.no}</td>
			<td>${temp.code}</td>
			<td>${temp.title}</td>
			<td>${temp.id}</td>
			<td>${temp.date}</td>
			<td>${temp.good}</td>
		</tr>
		</c:forEach>
	</table>
	
<!-- 	페이지 이동 기능 -->
	<table border="1" align="center" width="800">
		<tr>
			<td align="center">
			<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
				<a href="../classlist/classSearch.do?nowPage=1">[처  음]</a>
				<c:if test="${PINFO.startPage eq 1}">
					[이 전]
				</c:if>
				<c:if test="${PINFO.startPage ne 1}">
					<a href="../classlist/classSearch.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
				</c:if>
				<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
					<a href="../classlist/classSearch.do?nowPage=${temp}">[ ${temp} ]</a>
				</c:forEach>
				<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다 음]
				</c:if>
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="../classlist/classSearch.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
				</c:if>
				<a href="../classlist/classSearch.do?nowPage=${PINFO.totalPage}">[마지막]</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>