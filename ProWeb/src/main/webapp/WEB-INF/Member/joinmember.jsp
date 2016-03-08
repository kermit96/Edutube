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
	<!--  sha256 script  -->
<script type="text/javascript" src="../resources/JS/sha256.js"> </script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
<script>  




$( document ).ready(function() {	
	 init();
});
	

function checkuserid()
{
	
	var id  =   $("#mem_id").val();		
    try {
        $.ajax({
            url:"../member/ajax/sameuserid.do",
            async:false,
            type:'post',
            dataType:'json',
            cache:false,
            data:{userid:id},
            success:function(data){
            	
            	 if (data.result)
                 {   
            		 $("#checkid").html("사용할수 없는 ID 입니다. ");
            		 $("#mem_id").focus();		 
            	 } else {
            	
            		 $("#checkid").html("사용할수 있는 ID 입니다. ");
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



function checknickname()
{
	var nick  =   $("#mem_nick").val();
		
    try {
        $.ajax({
            url:"../member/ajax/samenickname.do",
            async:false,
            type:'post',
            dataType:'json',
            cache:false,
            data:{nickname:nick},
            
            success:function(data){
            	 if (data.result)
                 {   
            		 $("#checknickname").html("사용할수 없는 nickname 입니다. ");
            		 $("#mem_nick").focus();		 
            	 } else {            	
            		 $("#checknickname").html("사용할수 있는 nickname 입니다. ");
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


function init() {

	
	 try {
		 
		 $("#mem_id").blur(function(){
				  
              checkuserid();			 
			 
			});
		 
		 $("#mem_nick").blur(function(){
			  
			 checknickname();			 
			 
			});
		 
		 
	$("#addrfind").click(function() {
		
	 	daumaddrfind();
	});
	
	$("#register").click(function() {
		

		try {

		  var password = $("#password").val().trim();
		  var repassword = $("#repassword").val().trim();
		  
		  var name = $("#mem_name").val().trim();
		  var email = $("#mem_email").val().trim();
		  
		  var id = $("#mem_id").val().trim();
		  var nickname = $("#mem_nick").val().trim();
		  
		  var tel = $("#mem_tel").val().trim();
		  
		  if (name == "") 
		  {
			     alert("이름을 입력해 주시기 바랍니다");	  
				 return;
				 
		  }
		  
			 
		  
		  
		  if (nickname == "") 
		  {
			     alert("닉네임을 입력해 주시기 바랍니다");	  
				 return;			  			  
		  }
		  
		  if (email == "") 
		  {
			     alert("이 메일을 입력해 주시기 바랍니다.");	  
				 return;			  			  
		  }
		  
		  if (tel == "") 
		  {
			   alert("전화 번호를 입력해 주시기 바랍니다.");	  
			   return;			  			  		  
		  }
		  
		  if (id == "") 
		  {
			   alert("아이디를 입력해 주시기 바랍니다.");	  
			   return;			  			  
		  }
		  
       		  
		  if (password != repassword) 
		  {
		      alert("password 는 일치 해야 합니다. ")	  
			  return; 
		  }
		  
		  $("#mem_pass").val(Sha256.hash(password));
		  
		  $( "#target" ).submit();
		} catch (ex) {
			
			alert(ex);
		}
		  
		
		
		  
	});
	
	
   $("#cancel").click(function() {		
		self.close();
	});
	

	 } catch (ex) {
		 alert(ex);
		 
	 }
}	


function checkpassword(input)
{
	
/*	
	var decimal=  /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(%$?!.*()\s).{6,15}$/;
	
	if(inputmatch(decimal))   
	{   
	  
	 return true;  
	}
	
	else  
	{   
	  
	return false;  
	  
	}   
	
	
	return false;
	*/
}

  

 function daumaddrfind()
 {
	
    new daum.Postcode({
        oncomplete: function(data) {
        	   // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            	if (data.userLanguageType == 'K') 
                  fullAddr = data.roadAddress;
            	else 
            		fullAddr = data.roadAddressEnglish;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            	if (data.userLanguageType == 'E') 
            		fullAddr = data.jibunAddressEnglish;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType == 'R' &&  data.userLanguageType == 'K' ){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('mem_addrcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('mem_addr').value = fullAddr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('mem_detailaddr').focus();                        
        }
    }).open();
    
  }
 

</script>
	
</head>
<body>
<div id='EduContainer'  align="center">
         <h2>EduTube 회원 가입    </h2>
	     <br>
	     <form  id="target"   method=" post"  action="../member/joinmemberprocess.do" >
	     
	        <input type="hidden" id="mem_pass" name="mem_pass" / >
	      <table>
	      <tr> 
	      <td>  ID    </td>
	        <td><input type="text" id="mem_id"   name="mem_id"   placeholder="아이디 입력"  >  <div id="checkid"> </div>  </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  비밀 번호    </td>
	        <td><input type="password" id="password"  placeholder="비밀 번호"     
	         title="Password must contain at least 6 characters, including UPPER/lowercase and numbers" type="text" required pattern="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{7,40}$" onchange="
	   this.setCustomValidity(this.validity.patternMismatch ? this.title : '');  ">
	        
	        </td> 	        
	      </tr>

          <tr>	      
	      <td>  비밀 번호 확인   </td>
	        <td><input type="password" id="repassword" placeholder="비밀 번호 확인">    </td> 	        
	      </tr>
	      
	      
	      
	      <tr> 
	      <td>  이름    </td>
	        <td><input type="text" id="mem_name"  name="mem_name"  placeholder="이름" >    </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  닉네임    </td>
	        <td><input type="text" id="mem_nick"  name="mem_nick"  placeholder="닉네임" > <div id="checknickname"> </div>   </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  전화 번호    </td>
	        <td><input type="tel" id="mem_tel"   name="mem_tel"  placeholder="전화 번호" >    </td> 	        
	      </tr>
	      
	      <tr> 
	      <td>  E-MAIl    </td>
	        <td><input type="email" id="mem_email"  name="mem_email"   placeholder="e-mail" >    </td> 	        
	      </tr>
	      
	      <tr>
	      
	      <td>  주소    </td>
	        <td>
	            <input type="text" id="mem_addrCode"   name="mem_addrCode"  placeholder="우편번호">
			    <input type="button" id="addrfind"     value="우편번호 찾기"><br>
			    <input type="text" id="mem_addr"  name="mem_addr"   id="mem_addr"   placeholder="주소">
			    <input type="text" id="mem_detailaddr"  name="mem_detailaddr"  id="mem_detailaddr"   placeholder="상세주소">
			    
			    			  	         
	        </td> 	        
	      </tr>
	      
	      <tr> 
	         <td colspan="2" align="center"> 
	         <input type="button" id="register"  value="등록"> 
             <input type="button"  id="cancel" value="취소">  </td>
	      </tr>
	      	      
	      </table>   
</form>	     
	     
</div>
 
</body>
</html>