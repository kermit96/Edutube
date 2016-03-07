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
<!-- 	jquery 라이브러리 설치 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>		
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					$(location).attr("href", "../Smart/BoardWriteForm.do");
				});
			});
			$(document).ready(function(){
				$("#sBtn").click(function(){
					$(location).attr("action", "../Smart/BoardSearch.do");
				});
			});
		</script>
	</head>
	<body>
	<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
		<p><h3 align="center" >공지 사항</h3></p>
    <!-- 	검색 기능 폼 -->
	<form method="POST" id="sFrm">
		<!-- 	
				일반목록은 L로 주기로 했고
				검색 목록은 S로 주기로 했다.
		 -->
			<input type="hidden" name="kind" value="S">
			<table border="1" width="80%" align="center" id='Table'>
				<tr>
					<td>
						<select id="kind" name="kind">
							<option value="title">제목</option>
							<option value="body">본문</option>
							<option value="writer">글쓴이</option>
							<option value="all">제목 + 본문</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="검색" id="sBtn">
					</td>
				</tr>
			</table>
		</form>
<!-- 	목록 보여주기 -->
		<table border="1" width="80%" align="center">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
<c:forEach		var="temp" items="${LIST}">
			<tr>
				<td>${temp.no}</td>
				<td>
					<a href="../Smart/BoardHit.do?nowPage=${PINFO.nowPage}&kind=L&oriNo=${temp.no}">${temp.title}</a>
				</td>
				<td>${temp.id}</td>
				<td>${temp.wdate}</td>
				<td>${temp.hit}</td>
			</tr>
</c:forEach>
		</table>
<!-- 	페이지 이동 기능 -->
<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
				<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
				<!--	<a href="../Smart/BoardList.do?nowPage=1">[처  음]</a> -->
					<c:if test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../Smart/BoardList.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="../Smart/BoardList.do?nowPage=${temp}">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../Smart/BoardList.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
				<!--	<a href="../Smart/BoardList.do?nowPage=${PINFO.totalPage}">[마지막]</a> -->
				</td>
			</tr>
		</table>

<!-- 	기타 부가 기능 -->
		<table border="1" width="80%" align="center">
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
