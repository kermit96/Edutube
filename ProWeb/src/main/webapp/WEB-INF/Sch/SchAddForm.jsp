<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>EduTube</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->
 	<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
	<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	 <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="../resources/Picker/bootstrap-material-datetimepicker.css">
	
	<!--JS-->
	<script src="../resources/JS/jquery-2.1.4.min.js"></script>
	<script src="../resources/JS/bootstrap.min.js"></script>
	
	
	
		
	<!--CustomScript-->

	
	<!--Never Delete "EduContainer" style tag-->
	<style>
	
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
		#main{
				width:1200px;
		}	
		#centerPage{
			float:right;
			position: relative;					
			width:980px;
			height:100%;
			padding:10px;
			font-size:16px;					
		}
		#sideBarDiv{
			float:left;
			border-top:solid 1px white;
			clear:both;
			width:150px;			
		}
		#realContent{
			width:700px;
			padding-top:50px;
			padding-bottom:100px;
			padding-right:100px;
			padding-left:50px;
			
			margin: 0 auto;			
		}	
		#clock{			
			height:200px;
			margin: 0 auto;
		}
		.eventD{
			width:600px;
			border:1px solid black;
		}
		#eventTop{
			padding:10px;
		}
	</style>
	
</head>
<body>

<div id='EduContainer'>

	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>		
		
	<div id="main">
		<div id="sideBarDiv">
			<jsp:include page="/MenuBar/IntroSide.jsp" flush="false" />
		</div>
	
		<!-- This area is Body Part -->
		<div id="centerPage">
			<div id="realContent">
				<div id="eventTop" class="eventD">
						종료된 일정은 불러오지 않습니다 :)
				</div>
				<div id="eventForm" class="eventD">
			
				</div>
				<div id="eventAdd" class="eventD">
					<input type="text" id="wdate" class="form-control floating-label" readonly placeholder="Date">
				</div>
			</div>
		</div>		
	</div>
	 
</div>

<!--  피커 -->
<script src="../resources/JS/moment-with-locales.min.js"></script>
<script src="../resources/JS/bootstrap-material-datetimepicker.js"></script>
	<script type="text/javascript">
	$(function()
			{				
				$('#wdate').bootstrapMaterialDatePicker				
				({						
					weakStart:0,
					time: false
				});
			});
	</script>

</body>
</html>





