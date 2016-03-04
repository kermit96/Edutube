<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
<!-- 	jquery ���̺귯�� ��ġ -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>		
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					$(location).attr("href", "../Smart/BoardWriteForm.dol");
				});
				$("#sBtn").click(function(){
					//	������ ������ �ȴ�.
					$("#sFrm").attr("action", "../Smart/BoardSearch.dol");
					$("#sFrm").submit();
				});
			});
		</script>
	</head>
	<body>
<!-- 	�˻� ��� �� -->
		<form method="POST" id="sFrm">
			<table border="1" width="80%" align="center">
				<tr>
					<td>
						<select id="kind" name="kind">
							<option value="title">����</option>
							<option value="body">����</option>
							<option value="writer">�۾���</option>
							<option value="all">���� + ����</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="�˻�" id="sBtn">
					</td>
				</tr>
			</table>
		</form>
<!-- 	��� �����ֱ� -->
		<table border="1" width="80%" align="center">
			<tr>
				<th>��ȣ</th>
				<th>����</th>
				<th>�۾���</th>
				<th>�ۼ���</th>
				<th>��ȸ��</th>
			</tr>
<c:forEach		var="temp" items="${LIST}">
			<tr>
				<td>${temp.no}</td>
				<td>
					<a href="../Smart/BoardHit.dol?nowPage=${PINFO.nowPage}&kind=L&oriNo=${temp.no}">${temp.title}</a>
				</td>
				<td>${temp.id}</td>
				<td>${temp.wdate}</td>
				<td>${temp.hit}</td>
			</tr>
</c:forEach>
		</table>
<!-- 	������ �̵� ��� -->
<!-- 	��Ÿ �ΰ� ��� -->
		<table border="1" width="80%" align="center">
			<tr>
				<td align="center">
					<input type="button" id="wBtn" value="�۾���">
				</td>
			</tr>
		</table>
	</body>
</html>
