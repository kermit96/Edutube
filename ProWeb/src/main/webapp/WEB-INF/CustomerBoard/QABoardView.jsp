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
	function goReForm(orino) {
		$(location).attr("href",
				"../CustomerBoard/QABoardRepleForm.do?oriNo=${DATA.no}");
	}
	$(document).ready(function() {
		$("#dBtn").click(function() {
			$("#mfrm").attr("action", "../CustomerBoard/QABoardDelete.do");
			$("#mfrm").submit();
		});
		$("#mBtn").click(function() {
			$("#mfrm").attr("action", "../CustomerBoard/QABoardModifyForm.do");
			$("#mfrm").submit();
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
		#EduFooter{
				width:100%px;
				margin:20px auto;
				padding:20px;
				background-color:#DDD;
				text-align:right;
				clear:both;
		}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: center;
	padding: 8px;
	height: 50px;
	border: 1px solid white;
}

tr:nth-child(even) {
	background-color: #f3f3f3
}

th {
	background-color: #f96e5b;
	color: black;
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
	text-align: right;
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
		<br>
			<form action="post" id="mfrm">
				<input type="hidden" id="topNo" name="top" value="${DATA.top}">
				<input type="hidden" id="nowPage" name="nowPage" value="${NOWPAGE}">
				<input type="hidden" id="oriNo" name="oriNo" value="${DATA.no}">
			</form>
			<table width="50%" border="1" align="center">
				<tr>
					<td>글번호</td>
					<td>${DATA.no}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${DATA.datetime}</td>
				</tr>
				<tr>
					<td>글쓴이</td>
					<td>${DATA.id}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${DATA.title}</td>
				</tr>
				<tr>
					<td>본문</td>
					<td>${DATA.body}</td>
				</tr>
				<c:if test="${sessionScope.NAL eq 'A' && DATA.mid eq 0}">
					<tr>
						<td colspan="2" align="center"><a
							href="JavaScript:goReForm(${DATA.no})">답글달기</a></td>
					</tr>
				</c:if>
				<c:if test="${sessionScope.ID eq DATA.id}">
					<tr>
						<td colspan="2" align="right"><input type="button"
							value="삭제하기" id="dBtn" class="button"> <input type="button" value="수정하기"
							id="mBtn" class="button"></td>
					</tr>
				</c:if>
			</table>
			
				<div id="EduFooter">
				<jsp:include page="/MenuBar/footer.jsp" flush="true"/>
			</div>
	
			
		</div>
	</div>
		
	
</body>
</html>