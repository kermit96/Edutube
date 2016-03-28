<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>EduTube</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->
		<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
		<!--  ������ �ֱ� -->
		<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<!--  �˻��� -->
		
		<!--JS-->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 		<script src="/edutube/resources/JS/bootstrap.min.js"></script>
		<script type="text/javascript" src="/edutube/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
	</script>
	
	<!--Never Delete "EduContainer" style tag-->
	<style>
	body { background-image: url("../resources/img/bg.jpg");} 
	
			#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
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
		<br><br>
		<h3 align="center">승인 대기중..</h3>
		<table width="80%" align="center">
			<tr>
				<td><img src="../resources/img/sungin.png"></td>
			
			</tr>
		</table>
	</div>
	 
</div>

</body>
</html>



