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
                 ${LANGDATA.member_joinstr } <br>
                  <button id="ok" > ${LANGDATA.member_ok } </button>                         
          </div>
</body>
</html>