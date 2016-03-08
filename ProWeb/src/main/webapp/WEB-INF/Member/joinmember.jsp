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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>

 function daumaddrfind()
 {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
        }
    }).open();
    
  }
</script>
	
</head>
<body>
<form   action="../member/joinmember.do" >
<div id='EduContainer'  align="center">
         <h2>EduTube LOGIN    </h2>
	     <br>
	      <table>
	      <tr> 
	      <td>  ID    </td>
	        <td><input type="text" id="userid"   placeholder="userid" >  <div id="checkid"> </div>  </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  비밀 번호    </td>
	        <td><input type="password" id="password" placeholder="password">     </td> 	        
	      </tr>
	      
	      <td>  비밀 번호 확인   </td>
	        <td><input type="password" id="repassword" placeholder="repassword">    </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  이름    </td>
	        <td><input type="text" id="name"   placeholder="이름" >    </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  닉네임    </td>
	        <td><input type="text" id="nickname"   placeholder="닉네임" > <div id="checknickname"> </div>   </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  전화 번호    </td>
	        <td><input type="tel" id="email"   placeholder="전화 번호" >    </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  E-MAIl    </td>
	        <td><input type="email" id="email"   placeholder="e-mail" >    </td> 	        
	      </tr>
	      
	      <tr>
	      
	      <td>  주소    </td>
	        <td>
	            <input type="text" id="mem_addrcode"  name="mem_addrcode" placeholder="우편번호">
			    <input type="button" id="addrfind"     value="우편번호 찾기"><br>
			    <input type="text" id="sample6_address"  name=""   placeholder="주소">
			    <input type="text" id="sample6_address2"  name=""  placeholder="상세주소">
			    
			    			  	         
	        </td> 	        
	      </tr>
	      
	      <tr> 
	         <td colspan="2" align="center"> <button id="register"  >    <button id="cancel"> 취소</button>  </td>
	      </tr>
	      	      
	      </table>   
	     
	     
</div>
</form>
</body>
</html>