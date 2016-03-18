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
 	
	<!--스크립트-->
	<script>

	$( document ).ready(function() {	
		 init();
	});

	
	function findid()
	{
		
		var name=$("#name").val().trim();
		var email =$("#email").val().trim();
		
		if (name == "") {
<<<<<<< HEAD
		    // alert(" 이름을 입력해 주시기 바랍니다.")
		    alert("${LANGDATA.member_inputname}")
			return;
		}
		
		if (email == "") {
		    // alert("메일을 입력해 주시기 바랍니다.")	
		    alert("${LANGDATA.member_inputemail}")
			return;
		}
		
		 try {
	 	        $.ajax({
	 	            url:"../member/ajax/findid.do",
	 	            async:false,
	 	            type:'post',
	 	            dataType:'html',
	 	            cache:false,
	 	            data:{name:name,
	 	            	email:email	 	            	
	 	            },
	 	            
	 	            success:function(data){
	 	            	    

	 	             $("#findidresult").html(data);
	 	            	             	              
	 	            },
	 	            error:function(request,status,error){
	 	                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 	               }
	 	            	                                    
	 	        });
	 			
	 	        } catch (ex) {        	
	 	        	alert("ajax="+ex);
	 	        }

	}
	
	function findpassword()
	{
		
		var name=$("#pa_name").val().trim();
		var email =$("#pa_email").val().trim();
		var id =$("#id").val().trim();		
		
		if (name == "") {
		 //   alert(" 이름을 입력해 주시기 바랍니다.")
		     alert("${LANGDATA.member_inputname}")
			return;
		}
		
		if (email == "") {
	// 	    alert("메일을 입력해 주시기 바랍니다.")
	    alert("${LANGDATA.member_inputemail}")
			return;
		}
		
		if (id == "") {
		//    alert("아이디를 입력해 주시기 바랍니다.")
		    // alert("아이디를 입력해 주시기 바랍니다.")
		       alert("${LANGDATA.member_inputid}")
			return;
		}
		
		 try {
	 	        $.ajax({
	 	            url:"../member/ajax/findpassword.do",
	 	            async:false,
	 	            type:'post',
	 	            dataType:'html',
	 	            cache:false,
	 	            data:{userid:id,
	 	            	name:name,
	 	            	email:email	 	            	
	 	            },
	 	            
	 	            success:function(data){	 	            	    
	 	            	 alert(data);
	 	            	             	              
	 	            },
	 	            error:function(request,status,error){
	 	                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 	               }
	 	            	                                    
	 	        });
	 			
	 	        } catch (ex) {        	
	 	        	alert("ajax="+ex);
	 	        }
	 		  	      	
	}
		
	
   function init() 
   {
		 $("#findid").click(function(){
			  
			 findid();			 
			 
			});
		 
			
		 $("#findpassword").click(function() {
		 		
			 findpassword();
	 	});
		 	   
   }
	
	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
	</style>
</head>
<body>
<div class="container">
  <h2>${LANGDATA.member_idpasswordfind}</h2>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#findiddiv">${LANGDATA.member_idfind} </a></li>
    <li><a data-toggle="tab" href="#findpassworddiv">${LANGDATA.member_passwordfind}</a></li>
  </ul>

  <div class="tab-content">
    <div id="findiddiv" class="tab-pane fade in active">
      <h3>${LANGDATA.member_idfind} </h3>
      <table>      
      <tr> 
        <td>${LANGDATA.member_name} </td>
        <td><input  type="text"   id="name"  >  </td>
      </tr>       
      
      <tr>
        <td>e-mail </td>
        <td><input  type="text"   id="email" >  </td>
       </tr>
                     
       <tr>
       <td colspan="2"><button id="findid">${LANGDATA.member_find}</button> </td>
       </tr>
      </table>
      
      <div id="findidresult">     </div>
      
    </div>
    <div id="findpassworddiv" class="tab-pane fade">
      <h3>${LANGDATA.member_passwordfind}</h3>
             <table>
      <tr> 
        <td>ID </td>
        <td><input  type="text"   id="id"> </td>
      </tr> 
      <tr>
        <td>${LANGDATA.member_name}</td>
        <td><input  type="text"   id="pa_name">  </td>
       </tr>
       
       <tr>
        <td>e-mail</td>
        <td> <input  type="text"   id="pa_email">    </td>
       </tr>       
       <tr>
       <td colspan="2"><button id="findpassword">${LANGDATA.member_find}</button> </td>
=======
		    alert(" 이름을 입력해 주시기 바랍니다.")		    
			return;
		}
		
		if (email == "") {
		    alert("메일을 입력해 주시기 바랍니다.")		    
			return;
		}
		
		 try {
	 	        $.ajax({
	 	            url:"../member/ajax/findid.do",
	 	            async:false,
	 	            type:'post',
	 	            dataType:'html',
	 	            cache:false,
	 	            data:{name:name,
	 	            	email:email	 	            	
	 	            },
	 	            
	 	            success:function(data){
	 	            	    

	 	             $("#findidresult").html(data);
	 	            	             	              
	 	            },
	 	            error:function(request,status,error){
	 	                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 	               }
	 	            	                                    
	 	        });
	 			
	 	        } catch (ex) {        	
	 	        	alert("ajax="+ex);
	 	        }

	}
	
	function findpassword()
	{
		
		var name=$("#pa_name").val().trim();
		var email =$("#pa_email").val().trim();
		var id =$("#id").val().trim();		
		
		if (name == "") {
		    alert(" 이름을 입력해 주시기 바랍니다.")		    
			return;
		}
		
		if (email == "") {
		    alert("메일을 입력해 주시기 바랍니다.")		    
			return;
		}
		
		if (id == "") {
		    alert("아이디를 입력해 주시기 바랍니다.")		    
			return;
		}
		
		 try {
	 	        $.ajax({
	 	            url:"../member/ajax/findpassword.do",
	 	            async:false,
	 	            type:'post',
	 	            dataType:'html',
	 	            cache:false,
	 	            data:{userid:id,
	 	            	name:name,
	 	            	email:email	 	            	
	 	            },
	 	            
	 	            success:function(data){	 	            	    
	 	            	 alert(data);
	 	            	             	              
	 	            },
	 	            error:function(request,status,error){
	 	                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 	               }
	 	            	                                    
	 	        });
	 			
	 	        } catch (ex) {        	
	 	        	alert("ajax="+ex);
	 	        }
	 		  	      	
	}
		
	
   function init() 
   {
		 $("#findid").click(function(){
			  
			 findid();			 
			 
			});
		 
			
		 $("#findpassword").click(function() {
		 		
			 findpassword();
	 	});
		 	   
   }
	
	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
	</style>
</head>
<body>
<div class="container">
  <h2>ID/PASSWORD 찾기</h2>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#findiddiv">ID 찾기</a></li>
    <li><a data-toggle="tab" href="#findpassworddiv">PASSWORD 찾기</a></li>
  </ul>

  <div class="tab-content">
    <div id="findiddiv" class="tab-pane fade in active">
      <h3>ID 찾기</h3>
      <table>      
      <tr> 
        <td> 이름 </td>
        <td><input  type="text"   id="name"  >  </td>
      </tr>       
      
      <tr>
        <td>e-mail </td>
        <td><input  type="text"   id="email" >  </td>
       </tr>
                     
       <tr>
       <td colspan="2"><button id="findid">찾기</button> </td>
       </tr>
      </table>
      
      <div id="findidresult">     </div>
      
    </div>
    <div id="findpassworddiv" class="tab-pane fade">
      <h3>비밀 번호 찾기</h3>
             <table>
      <tr> 
        <td>ID </td>
        <td><input  type="text"   id="id"> </td>
      </tr> 
      <tr>
        <td>이름</td>
        <td><input  type="text"   id="pa_name">  </td>
       </tr>
       
       <tr>
        <td>e-mail</td>
        <td> <input  type="text"   id="pa_email">    </td>
       </tr>       
       <tr>
       <td colspan="2"><button id="findpassword">찾기</button> </td>
>>>>>>> branch 'master' of https://github.com/Nepro73/EduTube.git
       </tr>
      </table>      
    </div>
  </div>
</div>

</body>
</html>