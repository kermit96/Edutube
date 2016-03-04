<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->

	<!--JS-->
	
	<!--CustomScript-->
	<script>
		function goSCH(){
			location.href="/edutube/Sch/SchList.do";
		}
	</script>
	
	<!--Never Delete "EduContainer" style tag-->
	<style>
	
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}	
		#testList{
			float:right;
			width:1000px;
			height:500px;
			margin-top:100px;
			font-size:16px;
			border:solid 1px orange;
		}
		#side{
			width:200px;
			float:left;
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
	
		
		<div id="testList">
			<a href="JavaScript:goSCH();">스케쥴</a>
		</div>
	</div>
	 
</div>

</body>
</html>



