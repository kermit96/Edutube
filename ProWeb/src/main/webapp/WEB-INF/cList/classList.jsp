<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
	<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					$("#wfrm").attr("action", "../cList/classList.do");
					$("#wfrm").submit();
				});
			});
	</script>
</head>
<body>
<form method="POST" id="wfrm">
	<table width="800" border="1" align="center">
		<tr>
			<th>강의번호</th>
			<td><input type="text" value="${sessionScope.NO}" disabled></td>
		</tr>
		<tr>
			<th>강의코드</th>
			<td><input type="text" value="${sessionScope.CODE}" disabled></td>
		</tr>
		<tr>
			<th>제    목</th>
			<td><input type="text" name="title" id="title"></td>
		</tr>
		<tr>
			<th>본　  문</th>
			<td><textarea name="body" id="body"></textarea></td>
		</tr>
		<tr>
			<th>작 성 자</th>
			<td><input type="text" value="${sessionScope.ID}" disabled></td>
		</tr>
		<tr>
			<th>작 성 일</th>
			<td><input type="text" value="${sessionScope.DATE}" diasabled></td>
		</tr>
		<tr>
			<th>추 천 수</th>
			<td><input type="text" value="${sessionScope.GOOD}" disabled></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글작성" id="wBtn">
			</td>
		</tr>
	</table>
</form>
</body>
</html>