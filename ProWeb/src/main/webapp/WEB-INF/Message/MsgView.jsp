<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  ������ �ֱ� -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  �˻��� -->

<!--JS-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/edutube/resources/smarteditor/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script>
	$(document).ready(function() {
		$("#send").click(function() {
			$("#sfrm").attr("action","../Message/NewForm.do");
			$("#sfrm").submit();
		});
		$("#cancel").click(function() {
			window.close();
		});
		$("#delM").click(function(){
			$("#sfrm").attr("action","../Message/delM.do");
			$("#sfrm").submit();
		});
	});
</script>

<!--Never Delete "EduContainer" style tag-->
<style>
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

textarea#body {
	width: 250px;
	height: 150px;
	resize: none;
}
</style>

</head>
<body>

	<div id='EduContainer'>

		<div id="Main">
			<!-- This area is Body Part -->
			<form method="POST" action="" id="sfrm">
			<input type="hidden" id="fromid" name="fromid" value="${sessionScope.ID}">
			<input type="hidden" id="no" name="no" value="${no}">
				<table width="20%" border="1" align="center">
					<tr>
						<td>보낸사람</td>
						<td><input type="text" id="toid" name="toid" value="${LIST.fromid}"
							readonly="readonly"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea id="body" name="body" readonly="readonly">${LIST.body}</textarea></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input type="button"
							value="답장" id="send"> <input type="button" value="취소"
							id="cancel"><input type="button" value="삭제" id="delM"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>

</body>
</html>