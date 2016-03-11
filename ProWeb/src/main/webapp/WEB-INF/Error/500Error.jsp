<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>EduTube - 500 Error</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
	 <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<!--JS-->
	
	<!--CustomScript-->
	<script>
		function reHome(){
			location.href="/edutube/main.do";
		}
	</script>
	
	<!--Never Delete "EduContainer" style tag-->
	<style>
		@font-face { 
		    font-family:'NanumGothic';
		    src: url('/edutube/resources/fonts/NanumGothic.eot');
		    src: url('/edutube/resources/fonts/NanumGothic.eot?#iefix') format('embedded-opentype'),
		         url('/edutube/resources/fonts/NanumGothic.woff') format('woff');     
		    font-weight: normal;
		    font-style: normal;
		}	
		html,body{
			width:100%;
			height:100%;
		}
		#container{
		  	border-top:solid 2px orange;
  			border-bottom:solid 2px orange;
  			margin-top:300px;
		}
	
		#mainBox{
			width:800px;
			margin:0 auto;
		}
		#outer{			
  			width: 100%;
  			height:200px;  			
		}
		#logo{
		  	float:left;
		  	width:170px;
		  	height:135px;		  		  				
		}
	
		#errorMsg{
			width:630px;
			height:135px;				  	
		}		
		p		{			
			font-family:나눔고딕, 'NanumGothic';
			font-size:20px;
		}
		#utilB{
			text-align:center;
			margin:0 auto;
		}		
		#home{

		}
		
	</style>
	
</head>
<body>

	<div id="mainBox">
		<div id="container">
			<div id="outer">
				<div id="logo">
					<img src="/edutube/resources/img/logo.png">
				</div>
				<div id="errorMsg">				
					<p>500 Error -  서버에러 혹은 잘못된 요청</p>					
					<p>Request processing failed or internal server error.</p>
					<p>(개발용 메시지 삭제예정) 이클립스 에러메시지 보세여</p>
				</div>
				
				<div id="utilB">		
					<a class="button button-blue" onClick="JavaScript:reHome();"><i class="fa fa-globe"></i>Main <strong>Home</strong></a>
				</div>
							
			</div>
		</div>						
	</div>

</body>
</html>



