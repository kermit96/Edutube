<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->

<!--JS-->

<!--CustomScript-->
<script>
	
</script>

<!--Never Delete "EduContainer" style tag-->
<style>
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

#main {
	width: 1200px;
	height:800px;
}

#sideBarDiv {
	float: left;
	border-top: solid 1px white;
	clear: both;
	width: 220px;
	height:100%;
	background-color:#FFA76A; 
}

#centerPage {
	float: right;
	position: relative;
	width: 980px;
	height: 100%;
	padding: 10px;
	font-size: 16px;
}

</style>

</head>
<body>

	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
			<!-- This area is Body Part -->
			<div id="sideBarDiv">
				<jsp:include page="/MenuBar/Side.jsp" flush="false" />
			</div>		
			<div id="centerPage">
				나는 관리자 페이지
			</div>
		</div>

	</div>

</body>
</html>



