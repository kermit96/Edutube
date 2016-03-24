<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실</title>
<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script>
	function goDetail(orino) {
		$(location).attr(
				"href",
				"../DownLoad/DownView.do?nowPage=${PINFO.nowPage}&oriNo="
						+ orino);
	}
	$(document).ready(function() {
		$("#lBtn").click(function() {
			$(location).attr("href", "../../edutube");
		});
	});
	$(document).ready(function() {
		$("#wBtn").click(function() {
			$(location).attr("href", "./DownWrite.do");
		});
	});
</script>
<style>
body { background-image: url("../resources/img/bg.jpg");} 
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: center;
	padding: 8px;
	height : 50px;
}

tr:nth-child(even) {
	background-color: #f3f3f3
}

th {
	background-color: #f96e5b;
	color: white;
}

tr>th {
	text-align: center;
}

#rcorners2 {
	border: 0px solid white;
	padding: 50px;
	width: 1000px;
}

.button {
	border-radius: 15px;
	background-color: #f96e5b;
	border: none;
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 2px 1px;
	cursor: pointer;
}
.page {
	text-align : right;
	background-color: white;
}
</style>
</head>
<body>
	<div id='EduContainer'>
		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>
		<div align="center">
			<h1><img src="../resources/img/자료실.png"></h1>
		</div>
		<div id="main">
			<table width="80%" border="1" align="center" id="rcorners2">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
				</tr>
				<c:forEach var="temp" items="${LIST}">
					<tr>
						<td>${temp.no}</td>
						<td><a href="JavaScript:goDetail(${temp.no})">${temp.title}</a>
						</td>
						<td>${temp.id}</td>
						<td>${temp.wdate}</td>
					</tr>
				</c:forEach>
				
				<!-- 페이지 나눔 -->
				<tr>
					<td class="page" colspan="4">
						<c:if test="${PINFO.startPage eq 1}">
						[<]
						</c:if> 
						<c:if test="${PINFO.startPage ne 1}">
						<a href="../DownLoad/DownMain.do?nowPage=${PINFO.startPage - 1}">
						[<]</a>
						</c:if> 
						<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
							<a href="../DownLoad/DownMain.do?nowPage=${temp}">[ ${temp} ]</a>
						</c:forEach> 
						<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[>]
						</c:if> 
						<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../DownLoad/DownMain.do?nowPage=${PINFO.endPage + 1}">
						[>]</a>
						</c:if>
					</td>
				</tr>
				
				<!-- 버튼 -->
				<tr>
					<td colspan="4"><input type="button" value="write" id="wBtn"
						class="button">&nbsp;&nbsp;&nbsp;<input type="button"
						value="back" id="lBtn" class="button"></td>
				</tr>
			</table>

			<div>
				<!-- sideBar -->
			</div>
			<div id="asd"></div>
		</div>
	</div>

</body>

</html>