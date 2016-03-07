<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		</head>
		</head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="../se2/js/HuskyEZCreator.js" charset="utf-8"></script>
		<script>
			$(document).ready(function(){
				$count = 0;		//	�߰��� ���ε� ���� �Ϸù�ȣ�� �ο��� ����
				$("#aBtn").click(function(){
					//	����
					//		������ ���� ���ε� ���� ���� 
					$html = "<tr id='fpos"+ ($count + 1) +"'>";
					$html += "<td>����÷��</td>";
					$html += "<td><input type='file' name='upfile'></td>";
					$html += "</tr>";
					//		���� �߰��Ѵ�.
					$("#fpos" + $count).after($html);
					//		���̵�� ����� �Ϸù�ȣ�� �����Ѵ�.
					$count = $count + 1;
				});
				$("#dBtn").click(function(){
					if($count == 0) {
						return;
					}
					$("#fpos" + $count).remove();
					$count = $count - 1;
				});
				$("#mBtn").click(function(){
					//	��Ų�� �ִ� ������ �ؽ�Ʈ����� �ݿ��ؾ� �ϰ�.....				
					oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	���Ἲ �˻��ϰ�
					$("#frm").attr("action", "../Smart/BoardModify.do");
					$("#frm").submit();
				});
			});
		</script>
		<script>
			function DeleteFile(no) {
				//	Ajax�� ���ؼ� ������ ��û�Ѵ�.
				$.ajax({
					url : "../Smart/BoardFileDelete.do",
					data : "no=" + no + "&temp=" + new Date(),
					dataType : "json",
					type : "GET",
					success : function(data) {
						//	����
						//		�� ������ ǥ�õ� ���� �����.
						$("#files" + no).remove();
					},
					error : function(){
						alert("������");
					}
				});				
			}
		</script>
	<body>
<!-- 	����Ʈ �����͸� �����ϴ� ���� ó������ ���� ����� ��ó�� �����Ѵ�. 
		�ٸ� textarea �κп� ����Ʈ ������ ��Ų�� �����ϸ� �ȴ�.
		1.	���̹� ����Ʈ ������ ���̺귯���� �θ���.
		2.	textarea�� ��Ų�� ������.
-->
		<form method="POST" id="frm" action="" enctype="multipart/form-data">
			<input type="hidden" name="nowPage" value="${NOWPAGE}">
			<input type="hidden" name="kind" value="${KIND}">
			<input type="hidden" name="oriNo" value="${ORINO}">
			<table width="80%" border="1" align="center">
				<tr>
					<td>�۾���</td>
					<td><input type="text" id="id" name="id" value="${sessionScope.ID}" disabled></td>
				</tr>
				<tr>
					<td>����</td>
					<td><input type="text" id="title" name="title" value="${DATA.title}"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea id="body" name="body" style="width:100%; height:200px;">${DATA.body}</textarea></td>
				</tr>
<c:if test="${not empty FILES}">
	<c:forEach var="temp" items="${FILES}">
	<!-- 	���߿� �� ���� ������ �������� id���� �ο��Ѵ�. -->		
				<tr id="files${temp.no}">
					<td colspan="2">
						${temp.oriname} <input type="button" value="����" OnClick="JavaScript:DeleteFile(${temp.no})">
					</td>
				</tr>
	</c:forEach>
</c:if>
<!-- 	���ο� ���� ÷�θ� ���� ���� 
		�����̹Ƿ� ������ ���ε����� �𸣱� ������ ó������ ���߸� �����ְ�
		�ʿ��ϸ� ���� ���ε� ���� �߰��ؼ� ���ε尡 �����ϵ��� �ϰڴ�.
-->
				<tr id="fpos0">
					<td colspan="2" align="right">
						<input type="button" value="�߰�" id="aBtn">
						<input type="button" value="����" id="dBtn">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="�����ϱ�" id="mBtn">
					</td>
				</tr>
			</table>
		</form>
	</body>
		<script>
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "body",
			sSkinURI: "../se2/SmartEditor2Skin.html",
			fCreator: "createSEditor2"
		});
	</script>
</html>