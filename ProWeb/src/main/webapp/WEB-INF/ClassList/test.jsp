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
	<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

	<!--JS-->
	
	<!--CustomScript-->
	<script>
	var count = 1; 
	var num = 1; 

	function addBox (x) {
		$
		
	    var Commentext = '<p><textarea NAME="Comment' + count++ + '" rows="5" cols="50" style="background-color:#F5F5F5">textarea '+num++ +'<\/textarea>';
	    #bbb.insertAdjacentHTML('beforeEnd', Commentext)	   
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
		#modiDiv{
			width:300px;
			height:200px;
			margin:0 auto;
			border-top:1px solid black;			
		}
		#bbb{
			width:300px;
			margin:0 auto;
			border-top:1px solid black;
			padding:20px;
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
		<p>&nbsp;</p>
		<div id="modiDiv">
			여기에 있는 내용을 수정하자
		</div>
		<div id="bbb">
			<a class="btn btn-warning btn-flat btn-xs" onClick="JavaScript:addBox (x)">수정하기</a>
		</div>
		
	</div>
	 
</div>

</body>
</html>



