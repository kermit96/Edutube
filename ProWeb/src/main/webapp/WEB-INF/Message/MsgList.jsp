<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="/edutube/resources/JS/MessageScript.js"></script>
<script>
	$(document)
			.ready(
					function() {
						self.resizeTo(200, 400);
						$("#smsg")
								.click(
										function() {
											window
													.open(
															"../Message/MsgSeList.do?id=${sessionScope.ID}",
															"Edutube2",
															"width=270, height=260, resizable=no, scrollbars=yes");
											//$("#sfrm").attr("action","../Message/MsgSeList.do?id=${sessionScope.ID}");
											//$("#sfrm").submit();
										});
					});

	function showDetail(no) {
		window.open("../Message/MsgView.do?no=" + no + "&id=${sessionScope.ID}", "Edutube2",
				"width=270, height=260, resizable=no");
	}
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
	margine: 10px, 10px;
}
</style>

</head>
<body style='overflow-X: hidden'>

	<div id='EduContainer'>

		<div id="Main">
			<!-- This area is Body Part -->
			<form method="POST" action="" id="sfrm">
				<input type="hidden" id="toid" name="toid"
					value="${sessionScope.ID}">
				<table width="20%" border="1" align="center">
					<tr align="center">
						<th width="30%">보낸사람</th>
						<th>내 용</th>
					</tr>
					<c:forEach var="temp2" items="${LISTN}">
						<tr>
							<td align="center">${temp2.fromid}</td>
							<!-- 
						<td><a href="../Message/MsgView.do?no=${temp.no}">${temp.bodys}</a></td>
 -->
							<td><a href="JavaScript:showDetail('${temp2.no}')"><img src="/edutube/resources/img/F.jpg" width="10%">  ${temp2.bodys}</a></td>
						</tr>
					</c:forEach>

					<c:forEach var="temp" items="${LIST}">
						<tr>
							<td align="center">${temp.fromid}</td>
							<!-- 
						<td><a href="../Message/MsgView.do?no=${temp.no}">${temp.bodys}</a></td>
 -->
							<td><a href="JavaScript:showDetail('${temp.no}')">${temp.bodys}</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="bottom">
					<input type="button" value="보낸메일함으로" id="smsg" class="button">
				</div>

			</form>
		</div>

	</div>

</body>

</html>



