<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="../resources/JS/sha256.js"> </script>
<script>
	
	$( document ).ready(function() {
		
	      $("#login").click(
	    		  login
	      );
	      
	      $("#makeid").click(
	    		  make
	      );


	      $("#findmember").click(
	    		  findmember
	      );

	      
	});
	
	function make()
	{
		
		// location.href = ;
		 window.open('../member/joinmember.do', "_blank" );
	}
	
	
	function findmember()
	{
		
		 window.open('../member/findmember.do', "_blank" );
		
	}
	
	
	function login()
	{
		
		var userid = $("#userid").val().trim();
		var password = $("#password").val().trim();
		
		if (userid == "") {
			
<<<<<<< HEAD
		   	alert("${LANGDATA.member_inputid}");
			return;
		}
		
        if (password == "") {
			
		   	// alert("password 를 입력해 주시기 바랍니다.");
		   	alert("${LANGDATA.member_inputpassword}");
			return;
		}
		
        try {
          password = Sha256.hash(password);
        } catch (ex) {        	
        	alert(ex);	
        }
        

        try {
        $.ajax({
            url:"../member/ajax/login.do",
            async:false,
            type:'post',
            dataType:'json',
            cache:false,
            data:{userid:userid,password:password  },
            success:function(data){
            	 if (data.isSuccess)
                 {   
            		 location.href="${returnurl}";            		 
            	 } else {            		 
            		 alert(data.failreason);
            	 }            	              
            },
            error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
               }
            	                                    
        });
		
        } catch (ex) {        	
        	alert("ajax="+ex);
        }
	}
	
	</script>

	
</head>
<body>
<div id='EduContainer'  align="center">
         <h2>EduTube LOGIN    </h2>
	     <br>
	      <table>
	      <tr> 
	      <td>  ID    </td>
	        <td><input type="text" id="userid"   placeholder="userid" >    </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  PASSWORD    </td>
	        <td><input type="password" id="password" placeholder="password">    </td> 	        
	      </tr>
	      <tr> 
	         <td colspan="2" align="center"> <button id="login">
	         ${LANGDATA.member_login}</button>   
	         <button id="makeid"> ${LANGDATA.member_join} </button> 
	          <button id="findmember"> ${LANGDATA.member_idpasswordfind} </button>  </td>
=======
		   	alert("userid 를 입력해 주시기 바랍니다.");
			return;
		}
		
        if (password == "") {
			
		   	alert("password 를 입력해 주시기 바랍니다.");
			return;
		}
		
        try {
          password = Sha256.hash(password);
        } catch (ex) {        	
        	alert(ex);	
        }
        

        try {
        $.ajax({
            url:"../member/ajax/login.do",
            async:false,
            type:'post',
            dataType:'json',
            cache:false,
            data:{userid:userid,password:password  },
            success:function(data){
            	 if (data.isSuccess)
                 {   
            		 location.href="${returnurl}";            		 
            	 } else {            		 
            		 alert(data.failreason);
            	 }            	              
            },
            error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
               }
            	                                    
        });
		
        } catch (ex) {        	
        	alert("ajax="+ex);
        }
	}
	
	</script>

	
</head>
<body>
<div id='EduContainer'  align="center">
         <h2>EduTube LOGIN    </h2>
	     <br>
	      <table>
	      <tr> 
	      <td>  ID    </td>
	        <td><input type="text" id="userid"   placeholder="userid" >    </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  PASSWORD    </td>
	        <td><input type="password" id="password" placeholder="password">    </td> 	        
	      </tr>
	      <tr> 
	         <td colspan="2" align="center"> <button id="login"> 로그인 </button>   <button id="makeid"> 가입 </button>  <button id="findmember"> ID/PASSWORD 찾기 </button>  </td>
>>>>>>> branch 'master' of https://github.com/Nepro73/EduTube.git
	      </tr>
	      	      
	      </table>   
	     
	     
</div>

</body>
</html>