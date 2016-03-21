<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  ������ �ֱ� -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  �˻��� -->

<!--JS-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/edutube/resources/smarteditor/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script>
	function goDetail(orino) {
		$(location).attr(
				"href",
				"../CustomerBoard/QABoardView.do?nowPage=${PINFO.nowPage}&oriNo="
						+ orino + "&flag=L");
	}
	$(document).ready(function() {
		$("#sBtn").click(function() {
			$("#sfrm").attr("action", "../CustomerBoard/QABoardSearch.do");
			$("#sfrm").submit();
		});
		$("#wBtn").click(function() {
			$(location).attr("href", "../CustomerBoard/QABoardWriteForm.do");
		});
	});
</script>
<style>
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
	border: 1px solid white;
}

tr:nth-child(even) {
	background-color: #f3f3f3
}

th {
	background-color: #f96e5b;
	color: white;
	border: 1px solid white;
}

tr>th {
	text-align: center;
	border: 1px solid white;
}
#rcorners2 {
	border: 1px solid white;
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
	<div id="EduContainer">
		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />

		</div>

		<div id="Main">
		<br><h1>QA 게시판</h1>
			<table border="1" align="center" width="80%">
				<tr>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="temp" items="${LIST}">
					<tr>
						<td><c:if test="${temp.mid ne 0}">
								<c:forEach var="test" begin="1" end="${temp.mid}">
				&nbsp;&nbsp;
			</c:forEach>
				=>[답변]
		</c:if> <a href="JavaScript:goDetail(${temp.no})">[${temp.qname}]${temp.title}</a></td>
						<td>${temp.id}</td>
						<td>${temp.datetime}</td>
					</tr>
				</c:forEach>
			</table>
			<table border="1" align="center" width="80%">
				<tr>
					<td align="center"><input type="button" value="글쓰기" id="wBtn" class="button">
					</td>
				</tr>
			</table>
			<table border="1" align="center" width="80%">
				<tr>
					<td align="center">
						<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] --> <a
						href="../CustomerBoard/QABoardList.do?nowPage=1">[처 음]</a> <c:if
							test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if> <c:if test="${PINFO.startPage ne 1}">
							<a
								href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.startPage - 1}">[이
								전]</a>
						</c:if> <c:forEach var="temp" begin="${PINFO.startPage}"
							end="${PINFO.endPage}">
							<a href="../CustomerBoard/QABoardList.do?nowPage=${temp}">[
								${temp} ]</a>
						</c:forEach> <c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if> <c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a
								href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.endPage + 1}">[다
								음]</a>
						</c:if> <a
						href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.totalPage}">[마지막]</a>
					</td>
				</tr>
			</table>
			<table border="1" align="center" width="80%">
				<tr>
					<td align="center">
						<form method="POST" id="sfrm">
							<select id="kind" name="kind">
								<option value="title">제목</option>
								<option value="writer">글쓴이</option>
								<option value="both">제목 + 글쓴이</option>
							</select> <input type="text" id="content" name="content"> <input
								type="button" value="검색" id="sBtn" class="button">
						</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>