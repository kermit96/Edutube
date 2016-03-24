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

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>

<!--CustomScript-->
<script>
	
</script>

<!--Never Delete "EduContainer" style tag-->
<style>
body { background-image: url("../resources/img/bg.jpg");}
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

#main {
	width: 1200px;
}

#centerPage {
	float: right;
	position: relative;
	width: 980px;
	height: 100%;
	padding: 10px;
	font-size: 16px;
}

#sideBarDiv {
	float: left;
	border-top: solid 1px white;
	clear: both;
	width: 150px;
}

#introBoard {
	border: 1px solid black;
	position: relative;
	width: 600px;
	margin: 0 auto;
	position: relative;
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
				<jsp:include page="/MenuBar/IntroSide.jsp" flush="false" />
			</div>
			<div id="centerPage">

				<div id="introBoard">
					<table class="table table-bordered table-hover">
						<colgroup>
							<col style="width: 120px">
							<col style="width: 120px">
							<col style="width: 120px">
							<col style="width: 120px">
							<col style="width: 120px">
						</colgroup>

						<tbody>
							<tr>
								<td><a href="/edutube/Intro/IntroDetail.do"><img
										src="/edutube/resources/img/intros.jpg"></a></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
							</tr>

							<tr>
								<td><a href=""><img
										src="/edutube/resources/img/intros.jpg"></a></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
							</tr>
							<tr>
								<td><a href=""><img
										src="/edutube/resources/img/intros.jpg"></a></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
							</tr>
							<tr>
								<td><a href=""><img
										src="/edutube/resources/img/intros.jpg"></a></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
							</tr>
							<tr>
								<td><a href=""><img
										src="/edutube/resources/img/intros.jpg"></a></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
								<td><img src="/edutube/resources/img/intros.jpg"></td>
							</tr>



						</tbody>

					</table>
				</div>

			</div>
		</div>

	</div>

</body>
</html>



