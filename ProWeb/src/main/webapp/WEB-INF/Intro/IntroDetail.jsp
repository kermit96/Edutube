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

<!--JS-->

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
					<table>

						<colgroup>
							<col style="width: 300px">
							<col style="width: 400px">
						</colgroup>


						<tr rowspan="3">
							<td><img src="/edutube/resources/img/intros.jpg" size="50%;"></td>
						</tr>
						<tr>
							<td>이호진 강사</td>							
						</tr>
						<tr>
							<td>JAVA</td>
						</tr>
						<tr>
							<td><a href="/edutube/Sch/SchList.do">강사 일정</a></td>
						</tr>

					</table>

					<table border="1px solid black">
						<tr>
							<td>강사가 올린 게시물(강사게시판)</td>
						</tr>
						<tr>
							<td>No</td>
							<td>Title</td>
							<td>글쓴이</td>
							<td>날짜</td>
						<tr>
						<tr>
							<td>1</td>
							<td>이클립스 설치</td>
							<td>천재</td>
							<td>오늘</td>
						<tr>
					</table>



				</div>
			</div>
		</div>

	</div>

</body>
</html>



