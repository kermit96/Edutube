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
				<td>�۹�ȣ</td>
				<td>${DATA.no}</td>
				<td>�ۼ���</td>
				<td>${DATA.datetime}</td>
			</tr>
			<tr>
				<td>�۾���</td>
				<td>${DATA.id}</td>
			</tr>
			<tr>
				<td>����</td>
				<td>${DATA.title}</td>
			</tr>
			<tr>
				<td>����</td>
				<td>${DATA.body}</td>
			</tr>
			<tr>
				<td>
					<a href="JavaScript:goReForm(${DATA.no})">��۴ޱ�</a>
				</td>
			</tr>
		</table>
	</body>
</html>