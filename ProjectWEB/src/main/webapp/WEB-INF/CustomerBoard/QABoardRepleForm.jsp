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
			//	���Ἲ �˻縦 �ݵ�� �����Ͻð�
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
						<option value="1">���Թ��</option>
						<option value="2">����Ʈ �̿�</option>
						<option value="3">������ ����</option>
					</select>
				</td>
			</tr>
		</table>
 		<table width="50%" border="1" align="center">
 			<tr>
				<td>�۾���</td>
				<td>
					<input type="text" name="id" id="id" value="id"  readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>����</td>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<td>����</td>
				<td><input type="text" name="body" id="body"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="�۾���" id="wBtn">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>