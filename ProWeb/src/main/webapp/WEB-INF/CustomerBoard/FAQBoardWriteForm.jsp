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
				$("#wfrm").attr("action","../CustomerBoard/FAQBoardWrite.do");
				$("#wfrm").submit();
			});
		});
	</script>
</head>
	<body>
		<form method="post" id="wfrm">
		<table border="1" align="center" width="50%">
			<tr>
				<td align="center">
					<select id="qno" name="qno">
						<option value="1">가입방법</option>
						<option value="2">사이트 이용</option>
						<option value="3">동영상 수강</option>
					</select>
				</td>
			</tr>
		</table>
		<table border="1" align="center" width="50%">
			<tr>
				<td>글쓴이</td>
				<td>
					<input type="text" value="${sessionScope.NICK}" disabled>
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
					<input type="button" value="등록" id="wBtn">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>