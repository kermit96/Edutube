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
		$(document).ready(function(){
			$("#wBtn").click(function(){
				$title = $("#title").val();
				if($title == "") {
					alert("제목을 입력해주세요");
					return;
				}
				
				$body = $("#body").val();
				if($body == "") {
					alert("본문을 입력해주세요");
					return;
				}
				$("#wfrm").attr("action","../CustomerBoard/QABoardWrite.do");
				$("#wfrm").submit();
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
	text-align : right;
	background-color: white;
}
textarea#body{
      width:750px;
      height:400px;
      resize:none;
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
	<form method="post" id="wfrm">
	<table border="1" align="center" width="50%">
			<tr>
				<th align="center">
					<select id="qno" name="qno">
						<option value="1">가입방법</option>
						<option value="2">사이트 이용</option>
						<option value="3">동영상 수강</option>
						<option value="4">기타</option>
					</select>
				</th>
			</tr>
		</table>
		<table border="1" align="center" width="50%">
			<tr>
				<td>글쓴이</td>
				<td>
					<input type="text" value="${sessionScope.ID}"  disabled>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" id="title">
				</td>
			</tr>
			<tr>
				<td>본문</td>
				<td>
					<textarea name="body" id="body"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="등록" id="wBtn" class="button">
				</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</body>
</html>