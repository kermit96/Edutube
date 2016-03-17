<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		$("#smsg").click(function(){
			$("#sfrm").attr("action","../Message/MsgList.do?id=${sessionScope.ID}");
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
<body style='overflow-X:hidden'>
	<div id='EduContainer'>
		<div id="Main">
			<!-- This area is Body Part -->
			<form method="POST" action="" id="sfrm">
			<input type="hidden" id="fromid" name="fromid" value="${sessionScope.ID}">
				<table width="20%" border="1" align="center">
					<tr align="center">
						<td width="30%">받은사람</td>
						<td>내       용</td>
						<td>확인여부</td>
					</tr>
					<c:forEach var="temp" items="${LIST}">
					<tr>
						<td align="center">${temp.toid}</td>
						<td><a href="../Message/MsgView.do?no=${temp.no}">${temp.bodys}</a></td>
						<td>${temp.coms}</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="3" align="center"><input type="button" value="받은메일함으로" id="smsg"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>

</body>
</html>



