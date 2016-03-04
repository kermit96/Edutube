<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#mBtn").click(function(){
					//	����
					//		1.	��й�ȣ�� �޴´�.
					$pw = window.prompt("��й�ȣ�� �Է��� �ּ���");
					if($pw == "") {
						alert("��й�ȣ�� �Է����� ������ ������ �� �����");
						return;
					}
					//		2.	���� ��й�ȣ�� ���迡 �ִ´�.
					$("#pw").val($pw);
					
					//		3.	������ ������.
					$("#imsiFrm").attr("action", "../Smart/BoardModifyForm.dol");
					$("#imsiFrm").submit();
				});
			});
		</script>				
	</head>
	<body>
<!-- 	�󼼺��� ��� -->
		<table width="80%" border="1" align="center">
			<tr>
				<td>�۹�ȣ</td>
				<td>${VIEW.no}</td>
				<td>��ȸ��</td>
				<td>${VIEW.hit}</td>
			</tr>
			<tr>
				<td>�ۼ���</td>
				<td>${VIEW.wdate}</td>
				<td>������</td>
				<td>${VIEW.mdate}</td>
			</tr>
			<tr>
				<td>�۾���</td>
				<td colspan="3">${VIEW.id}</td>
			</tr>
			<tr>
				<td>����</td>
				<td colspan="3">${VIEW.title}</td>
			</tr>
			<tr>
				<td colspan="4">${VIEW.body}</td>
			</tr>
		</table>
<!-- 	���� ��� 
		÷�� ������ ���� ���� �ִ�.
-->
		<c:if test="${not empty FILES}">
		<table width="80%" border="1" align="center">
			<c:forEach var="temp" items="${FILES}">
				<tr>
					<td>
						<a href="../Smart/BoardDown.dol?no=${temp.no}">${temp.oriname}</a> 
	( <fmt:formatNumber value="${temp.len}" pattern="#,###"  /> Bytes)
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>	
<!-- 	������ ������ 
		�������̳� �������� ���� ���� �ִ�.
-->
		<table width="80%" border="1" align="center">
			<tr>
				<td>������</td>
				<td>
					<c:if test="${MAP.PRENO eq 0}">
						������ ����
					</c:if>
					<c:if test="${MAP.PRENO ne 0}">
						<a href="../Smart/BoardHit.dol?nowPage=${NOWPAGE}&kind=${KIND}&oriNo=${MAP.PRENO}">${MAP.PRETITLE}</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>������</td>
				<td>
					<c:if test="${MAP.NEXTNO eq 0}">
						������ ����
					</c:if>
					<c:if test="${MAP.NEXTNO ne 0}">
						<a href="../Smart/BoardHit.dol?nowPage=${NOWPAGE}&kind=${KIND}&oriNo=${MAP.NEXTNO}">${MAP.NEXTTITLE}</a>
					</c:if>
				</td>
			</tr>
		</table>
<!-- 	��Ÿ��� -->
		<table border="1" width="80%" align="center">
			<tr>
				<td>
<c:if test="${VIEW.id eq sessionScope.ID}">
					<input type="button" value="�����ϱ�" id="mBtn">
</c:if>			
				</td>
			</tr>
		</table>
<!-- 	POST ������� ������ ������ ���ؼ��� ���� �̿��ؾ� �Ѵ�. -->
		<form method="POST" id="imsiFrm">
			<input type="hidden" name="oriNo" value="${VIEW.no}">
			<input type="hidden" name="nowPage" value="${NOWPAGE}">
			<input type="hidden" name="kind" value="${KIND}">
			<input type="hidden" name="pw" id="pw">
		</form>
	</body>
</html>
