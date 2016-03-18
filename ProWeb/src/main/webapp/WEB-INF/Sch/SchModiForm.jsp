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
	<link rel="stylesheet" href="/edutube/resources/Picker/bootstrap-material-datetimepicker.css">
	
	<!--JS-->
	<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
	<script src="/edutube/resources/JS/bootstrap.min.js"></script>
	<script src="/edutube/resources/JS/moment-with-locales.min.js"></script>
	<script src="/edutube/resources/JS/bootstrap-material-datetimepicker.js"></script>
	
	
		
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
									여기다 뭘 넣지
				</div>
				<div id="eventForm" class="eventD">
				<c:forEach var="events" items="${eventList}">
					${events.no}
					${events.id}
					${events.name}
					${events.sdate1}
					${events.edate1}
				</c:forEach>
				</div>
				<div id="eventAdd" class="eventD">
					<input id="wdate"  name="wdate" type="text"  class="input-medium" placeholder="Date" readonly required>
				</div>
			</div>
		</div>
		
	</div>
	 
</div>
<!--  피커 -->

	<script type="text/javascript">
	$(document).ready(function()
			{
				$('#wdate').bootstrapMaterialDatePicker
				({
					time: false
				});
			});
	</script>

</body>
</html>








