<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>

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
	height: 100%;
}

#searchMain {
	width: 1200px;
	height: 100%;
}

#searchTop {
	width: 800px;
	height: 100px;
	line-height: 100px;
	margin: 0 auto;
}

#searchTop p {
	vertical-align: middle;
	font-size: 18px;
}

#searchUtil {
	clear: both;
	width: 800px;
	padding: 20px;
	border: 1px solid black;
	margin: 0 auto;
}

#searchList {
	width: 800px;
	height: 100%;
	border: 1px solid black;
	margin: 0 auto;
	margin-bottom: 100px; padding : 20px;
	overflow: auto;
	padding: 20px;
}

#searchBox {
	border: 3px solid #ff8000;
	outline-style: none;
	width: 350px;
}


</style>

<script>
	
</script>

</head>
<body>

	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
			<!-- This area is Body Part -->
			<div id="searchMain">
				<div id="searchTop">
					<p>"${keyword}" 에 대한 검색 결과</p>
				</div>

				<div id="searchUtil">
					<h3>상세 검색</h3>
					<form method="POST" name="detailSearch" id="detailSearch">
					<table id="utilTable">
						<tr>
							<td>과목 선택</td>
							<td><select id="subcodeL" name="subcodeL"
								class="input-medium">
									<option>선택</option>
									<option>Option two</option>
							</select></td>
							<td><select id="subcodeM" name="subcodeM"
								class="input-medium">
									<option>선택</option>
									<option>Option two</option>
							</select></td>
						</tr>

						<tr>
							<td colspan="3">
								&nbsp;
							</td>							
						</tr>
						
						<tr>
							<td  colspan="3">
							<input type="text" id="searchBox" name="searchBox">
								<a href="JavaScript:detailSearch()"><img
									src="/edutube/resources/img/searchBtn.png"></a>
							</td>
						</tr>

					</table>
					</form>
				</div>
				<!-- 검색 결과  -->
				<div id="searchList">검색결과
				</div>

			</div>
			<!-- 작은 메인 -->

		</div>
		<!-- big Main -->
	</div>
</body>
</html>



