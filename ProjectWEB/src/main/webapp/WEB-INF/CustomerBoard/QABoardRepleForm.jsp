<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#wBtn").click(function(){
			//	무결성 검사를 반드시 진행하시고
				$("#wfrm").attr("action", "../CustomerBoard/QABoardReple.do");
				$("#wfrm").submit();
			});
		});
	</script>
</head>
	<body>
		<form method="post" id="wfrm" >
		<input type="hidden" name ="no" id="no" value="${ORINO}"/>
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
 		<table width="50%" border="1" align="center">
 			<tr>
				<td>글쓴이</td>
				<td>
					<input type="text" name="id" id="id" value="id"  readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<td>본문</td>
				<td><input type="text" name="body" id="body"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="글쓰기" id="wBtn">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>