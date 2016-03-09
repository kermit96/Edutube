<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>테스트</title>
	
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
		function goIntro(){
			location.href="/edutube/Intro/IntroMain.do";
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
		
	</style>
	
</head>
<body>

<div id='EduContainer'>

<%-- <c:if test="${sessionScope.ADMIN ne 'Y'}">
 	<c:redirect url="/LoginForm.jsp" />
</c:if> --%>

	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
		<!-- This area is Body Part -->
		<div id="sideBarDiv">
			<jsp:include page="/MenuBar/AdminSide.jsp" flush="false" />
		</div>		
		<div id="centerPage">
			<a href="JavaScript:goSCH();">스케쥴</a>
			<a href="JavaScript:goIntro();">강사소개</a>
		</div>
	</div>
	 
</div>

</body>
</html>



