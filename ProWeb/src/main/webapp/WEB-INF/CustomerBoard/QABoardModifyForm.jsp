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
				$("#mBtn").click(function(){
					$("#mfrm").attr("action", "../CustomerBoard/QABoardModify.do");
					$("#mfrm").submit();
				});
			});
		</script>
</head>
	<body>
		<form method="POST" id="mfrm">
			<input type="hidden" name="no" value="${DATA.no}">
			<%-- <input type="hidden" name="nowPage" value="${NOWPAGE}"> --%>
			<table border="1" align="center" width="50%">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" value="${DATA.id}" disabled></td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" id="title" name="title" value="${DATA.title}">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea id="body" name="body">${DATA.body}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="mBtn" value="수정하기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>