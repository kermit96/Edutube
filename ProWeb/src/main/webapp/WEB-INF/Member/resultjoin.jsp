<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

  <script> 
  
	$( document ).ready(function() {	
		 init();
	});

	function init()
	{		
		$("#ok").click(function() {			
			ok();
		});
		
	}
  
    function ok() 
    {
    	 window.close();    	
    }
  </script>

</head>
<body>
          <div  align="center" > 
                 가입 했습니다. <br>
                  <button id="ok" > 확인 </button>                         
          </div>
</body>
</html>