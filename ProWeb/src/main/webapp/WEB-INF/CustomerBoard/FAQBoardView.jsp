<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
	$(document).ready(function(){
		$("#dBtn").click(function(){
			$("#mfrm").attr("action", "../CustomerBoard/FAQBoardDelete.do");
			$("#mfrm").submit();
		});
		$("#mBtn").click(function(){
			$("#mfrm").attr("action", "../CustomerBoard/FAQBoardModifyForm.do");
			$("#mfrm").submit();
		});	
	});
	</script>
</head>
	<body>
		<form action="post" id="mfrm">
		<input type="hidden" id="oriNo" name="oriNo" value="${DATA.no}">
		</form>
		<table width="50%" border="1" align="center">
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
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="삭제하기" id="dBtn">
					<input type="button" value="수정하기" id="mBtn">
				</td>
			</tr>
			</table>
	</body>
</html>