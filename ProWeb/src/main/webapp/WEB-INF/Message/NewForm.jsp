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
			$("#sfrm").attr("action","../Message/Messagego.do");
			$("#sfrm").submit();
		});
		$("#cancel").click(function() {
			window.close();
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

table {
	border-collapse: collapse;
	width: 20%;
}

th, td {
	text-align: center;
	padding: 2px;
	height: 5px;
}

tr:nth-child(even) {
	background-color: #f3f3f3
}

th {
	background-color: #f96e5b;
	color: white;
	border: 1px solid white;
}

tr>th {
	text-align: center;
	border: 1px solid white;
}

#rcorners2 {
	border: 1px solid white;
	padding: 50px;
	width: 1000px;
}

.button {
	border-radius: 15px;
	background-color: #f96e5b;
	border: none;
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 2px 1px;
	cursor: pointer;
}

.page {
	text-align: right;
	background-color: white;
}

textarea#body {
	width: 250px;
	height: 150px;
	resize: none;
}

.bottom {
	border-bottom-color: white;
	width: 100px;
	position: absolute;
	left: 44.5%;
}
.round{
	border: 1px solid white;
}
</style>

</head>
<body>

	<div id='EduContainer'>

		<div id="Main">
			<!-- This area is Body Part -->
			<form method="POST" action="" id="sfrm">
			<input type="hidden" id="fromid" name="fromid" value="${sessionScope.ID}">
				<table width="20%" border="1" align="center">
					<tr>
						<th>받는사람</th>
						<td class="round"><input type="text" id="toid" name="toid" value="${toid}"
							readonly="readonly"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="round"><textarea id="body" name="body"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center" class="round"><input type="button"
							value="전송" id="send" class="button"> <input type="button" value="취소"
							id="cancel" class="button"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>

</body>
</html>



