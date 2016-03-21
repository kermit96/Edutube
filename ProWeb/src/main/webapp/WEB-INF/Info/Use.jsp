<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<!--  검색바 -->
	
	<!--JS-->
	 <script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 	<script src="/edutube/resources/JS/bootstrap.min.js"></script>
 	
	<!--스크립트-->
	<script>
	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
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
	</style>
</head>
<body>
	<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>

	<div id=sideBarDiv>
	 <jsp:include page="/MenuBar/InfoSide.jsp" flush="false" />
	</div>
	
	<div id="centerPage">
		<table width="100%" align="center">
			<tr>
				<td><img src="../resources/img/usetop.jpg"></td>
			</tr><br>
			<tr>
				<td><center><img src="../resources/img/usebody.jpg"></center></td>
			</tr>
		</table>
	</div>
	
	<div id="EduFooter">
		<jsp:include page="/MenuBar/footer.jsp" flush="true"/>
	</div>
</div>
</body>
</html>