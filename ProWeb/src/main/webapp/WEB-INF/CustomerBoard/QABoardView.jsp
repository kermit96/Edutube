<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
function goReForm(orino) {
	$(location).attr("href", "../CustomerBoard/QABoardRepleForm.do?oriNo=${DATA.no}");
}
</script>
</head>
	<body>
		<table width="50%" border="1" align="center">
			<tr>
				<td>글번호</td>
				<td>${DATA.no}</td>
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
				<td>
					<a href="JavaScript:goReForm(${DATA.no})">댓글달기</a>
				</td>
			</tr>
		</table>
	</body>
</html>