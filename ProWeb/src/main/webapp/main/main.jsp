<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edu Tube</title>
	
	<!--CSS-->
	<link rel="stylesheet" href="../resources/CSS/bootstrap.min.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="../favicon.ico" type="image/x-icon"/> 
	<!--  검색바 -->
	
	<!--JS-->
	 <script src="../resources/JS/jquery-2.1.4.min.js"></script>
 	<script src="../resources/JS/bootstrap.min.js"></script>
 	
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
	</style>
	
</head>
<body>

<div id='EduContainer'>

	<div id='top'>	
		<jsp:include page="../MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
		<p>메인 페이지 입니다.</p>
	</div>
	 
</div>


</body>
</html>



