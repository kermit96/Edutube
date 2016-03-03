<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>강사 스케쥴</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->
		<link rel="stylesheet" href="/edutube/resources/CSS/monthly.css">
	<!--JS-->
		<script type="text/javascript" src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
	
	<!--CustomScript-->
	<script>
	$(window).load( function() {

			$('#mycalendar').monthly({
				mode: 'event',
				xmlUrl: 'events.jsp'
			});
		
			switch(window.location.protocol) {
			case 'http:':
			case 'https:':
			// running on a server, should be good.
			break;
			case 'file:':
			alert('Just a heads-up, events will not work when run locally.');
			}

		});
	</script>
	
	<!--Never Delete "EduContainer" style tag-->
	<style>
	
		#EduContainer{
			width:1200px;
			position: absolute;
			left: 50%;
			margin-left:-600px;				
		}
		
		#sch{
			width:900px;
			float:right;
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
		<div id="sideBar">
		
		</div>
		
		<div id="sch">
			
			<h2>강사 스케쥴</h2>
			<div style="width:100%; max-width:600px; display:inline-block;">
				<div class="monthly" id="mycalendar"></div>
			</div>
			<script type="text/javascript" src="/edutube/resources/JS/monthly.js"></script>
	
		</div>
		
	</div>
	 
</div>

</body>
</html>



