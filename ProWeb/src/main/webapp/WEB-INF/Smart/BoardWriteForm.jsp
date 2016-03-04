<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script type="text/javascript" src="../se2/js/HuskyEZCreator.js" charset="utf-8">
		</script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$count = 0;	//	���ݱ��� �����ϴ� ÷�� ������ ������ ������ ����
				$("#dBtn").click(function(){
					//	�׻� �Ѱ��� ���ܵξ�� �ϹǷ�
					if($count == 0) {
						return;
					}
					//	������ �׻� �������� ���� ������� ���쵵�� �������.
					$("#upload" + $count).remove();
					//	������ �������� ÷�������� ������ �پ����Ƿ�
					$count = $count - 1;
				});
				$("#aBtn").click(function(){
					//	���� 	�߰��� <tr>�� �����.
					$count = $count + 1;
					$html = "<tr id='upload"+$count+"'>";
					$html += "<td>÷������</td>";
					$html += "<td colspan='2'><input type='file' id='up"+ $count +"' name='upfile'></td>";
					$html += "</tr>";
					
					$("#upload").after($html);
				});
				$("#wBtn").click(function(){
					//	���� ��Ų�� ������ textarea�� �ű��.
					oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	���� ���Ἲ �˻��ϰ�...
					var	body = $("#body").val();
					
					$("#frm").attr("action", "../Smart/BoardWrite.dol");
					$("#frm").submit();
				});
			});
		</script>
	</head>
	<body>
		<form method="POST" id="frm" action="" enctype="multipart/form-data">
			<table width="70%" border="1" align="center">
				<tr>
					<td>�۾���</td>
					<td colspan="2"><input type="text" name="writer" id="writer" value="${sessionScope.NICK}" disabled>
				</tr>
				<tr>
					<td>����</td>
					<td colspan="2"><input type="text" name="title" id="title">
				</tr>
				<tr>
					<td colspan="3">
						<textarea id="body" name="body" style="width:100%; height:200px"></textarea>
					</td>
				</tr>
				<tr>
					<td>��й�ȣ</td>
					<td colspan="2"><input type="password" name="pw" id="pw">
				</tr>			
<!-- 	�������� ÷�������� ������ ������ �� �ֵ��� �ϰ��� �Ѵ�. 
		�׷��� �������� ÷�������� �Է��� input�� ���� ��ġ�� ����־���Ѵ�.
-->
				<tr id="upload">
					<td>÷������</td>
					<td><input type="file" name="upfile" id="up0"></td>
					<td>
						<input type="button" id="aBtn" value="�߰�">
						<input type="button" id="dBtn" value="����">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" value="�۾���" id="wBtn">
					</td>
				</tr>
			</table>		
		</form>
	</body>
<!-- 	body��� textarea�� ��������� ���� �� ��ũ��Ʈ�� ����Ǹ�
		��Ų�� �������� ���� �� �ִ�.
 -->
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
