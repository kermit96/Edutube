
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
	<!--  sha256 script  -->
<script type="text/javascript" src="../resources/JS/sha256.js"> </script>
<!--   memebr 관리  -->
 	<script src="../resources/JS/member.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
 	
	<!--스크립트-->
	<script>
	
	
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
	            		 $("#checknickname").html("${LANGDATA.memberchecknickfail}");
	            		 $("#mem_nick").focus();		 
	            	 } else {            	
	            		 $("#checknickname").html("${LANGDATA.memberchecknicksuccess}");
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

	
	 function daumaddrfind()
	 {
		
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	   // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	        	 try { 
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
	            document.getElementById('mem_addrCode').value = data.zonecode; //5자리 새우편번호 사용
	            document.getElementById('mem_addr').value = fullAddr;

	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById('mem_detailaddr').focus();
	        	 } catch (ex) {
	        		 
	        		 alert(ex);
	        		 
	        	 }
	            
	        }
	    }).open();
	    
	  }
	 

	$( document ).ready(function() {
		
		 init();
	});
	
	
	function changepassword()
	{

		var oldpassword = $("#oldpassword").val();
		var newpassword = $("#newpassword").val();
		var verifypassword = $("#repassword").val();
		
		if (oldpassword != "") {
            alert("${LANGDATA.member_inputpassword}");
            $("#oldpassword").focus();
		    return;
		}
		
		if (oldpassword != "") {
            alert("${LANGDATA.member_inputpassword}");		              
		    return;
		}
		
		
		if (newpassword != "") {
            alert("${LANGDATA.samepassword}");		              
		    return;
		}

	
		
		
		if (newpassword != verifypassword) {
            alert("${LANGDATA.samepassword}");		              
		    return;
		}
		 
		if (!checkpassword(newpassword))
		{						
			alert("${LANGDATA.member_badpassword}");
			return;
		}

		
		oldpassword = Sha256.hash(oldpassword);
		newpassword = Sha256.hash(newpassword);
		
		 try {
	 	        $.ajax({
	 	            url:"../member/ajax/changepassword.do",
	 	            async:false,
	 	            type:'post',
	 	            dataType:'html',
	 	            cache:false,
	 	            data:{oldpassword:oldpassword,
	 	            	newpassword:newpassword	 	            	
	 	            },
	 	            
	 	            success:function(data){	 	            	    
	 	            	 alert("${LANGDATA.member_changepassword}");	 	            	             	              
	 	            },
	 	            error:function(request,status,error){
	 	                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 	               }	 	            	                                    
	 	        });
	 	        
		 } catch (ex) {
			 
			 alert(ex);
		 }
		 
	}
		
	
	function init()
	{

		 $("#mem_nick").change(function(){
			  
			 checknickname();			 
			 
			});
		 
			
		 $("#addrfind").click(function() {
		 		
		 	 	daumaddrfind();
	 	});
		 
		 

		 $("#changepasswordbutton").click(function() {
		 		
		 	 	changepassword();
		 	});
		 
		 $("#changemember").click(function() {
		 		

		 		try {

		 		  var name = $("#mem_name").val().trim();
		
		 		  var email = $("#mem_email").val().trim();
		 		  
	
		 		  var nickname = $("#mem_nick").val().trim();
		 		  
		 		  var tel = $("#mem_tel").val().trim();
		 		  

		 		 var mem_addrCode = $("#mem_addrCode").val().trim();
		 		 var mem_addr = $("#mem_addr").val().trim();
		 		 var mem_detailaddr = $("#mem_detailaddr").val().trim();
		 		
		 		  if (name == "") 
		 		  {
		 			     // alert("이름을 입력해 주시기 바랍니다");
		 			     alert("${LANGDATA.inputname}");
		 				 return;		 				 
		 		  }
		 		  
		 		  if (nickname == "") 
		 		  {
		 			   //  alert("닉네임을 입력해 주시기 바랍니다");
		 			   alert("${LANGDATA.member_inputnickname}");
		 				 return;			  			  
		 		  }
		 		  
		 		  if (email == "") 
		 		  {
		 			     // alert("이 메일을 입력해 주시기 바랍니다.");
		 			     alert("${LANGDATA.member_inputemail}");
		 				 return;			  			  
		 		  }
		 		  
		 		  if (tel == "") 
		 		  {
		 			//    alert("전화 번호를 입력해 주시기 바랍니다.");
		 			alert("${LANGDATA.member_inputtel}");
		 			   return;			  			  		  
		 		  }
 		  
		 		 try {
		 	        $.ajax({
		 	            url:"../member/ajax/updatemember.do",
		 	            async:false,
		 	            type:'post',
		 	            dataType:'html',
		 	            cache:false,
		 	            data:{mem_nick:nickname,
		 	            	mem_name:name,
		 	            	mem_email:email,
		 	            	mem_tel:tel,
		 	            	mem_addrCode:mem_addrCode,
		 	            	mem_addr:mem_addr,
		 	            	mem_detailaddr:mem_detailaddr
		 	            },
		 	            
		 	            success:function(data){
		 	            	    
		 	            	 // alert("수정 했습니다.");
		 	            	alert("${LANGDATA.member_changemsg}");
		 	            	             	              
		 	            },
		 	            error:function(request,status,error){
		 	                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		 	               }
		 	            	                                    
		 	        });
		 			
		 	        } catch (ex) {        	
		 	        	alert("ajax="+ex);
		 	        }
		 		  
		 		} catch (ex) {
		 			
		 			alert(ex);
		 		}
		 		  		 			 		
		 	});

	}
	
	</script>
	

</head>
<body>

	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	

<div class="container">
  <h2>${LANGDATA.member_usermodify}</h2>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#changeuserinfo">${LANGDATA.member_personalmodify}</a></li>
    <li><a data-toggle="tab" href="#changepassword">${LANGDATA.member_changepassword}<</a></li>
  </ul>

  <div class="tab-content">
    <div id="changeuserinfo" class="tab-pane fade in active">
      <h3>${LANGDATA.member_personalmodify}</h3>
      <table>      
      <tr> 
        <td>${LANGDATA.member_nickname}</td>
        <td><input  type="text"   id="mem_nick"  value="${member.mem_nick}"> <div id="checknickname"> </div> </td>
      </tr>       
      <tr> 
        <td>${LANGDATA.member_nickname} </td>
        <td><input  type="text"   id="mem_name"   value="${member.mem_name}" > </td>
      </tr> 
      <tr>
        <td>e-mail </td>
        <td><input  type="text"   id="mem_email"  value="${member.mem_email}" > </td>
       </tr>
       
       <tr>
        <td>${LANGDATA.member_tel}</td>
        <td> <input  type="text"   id="mem_tel"   value="${member.mem_tel}"  > </td>
       </tr>
       
       <tr>
       <td>${LANGDATA.member_addr}</td>
       <td>
        <input type="text" id="mem_addrCode"   name="mem_addrCode"  value=""   placeholder="${LANGDATA.member_post}">
			    <input type="button" id="addrfind"     value="${LANGDATA.member_postfind}"><br>
			    <input type="text" id="mem_addr"  name="mem_addr"   id="mem_addr"  value="${member.mem_addr}"   placeholder="${LANGDATA.member_addr}">
			    <input type="text" id="mem_detailaddr"  name="mem_detailaddr"  id="mem_detailaddr"  value="${member.mem_detailaddr}"   placeholder="${LANGDATA.member_addrmore}">
	    </td>		    			 
       
       </tr>       
              
       <tr>
       <td colspan="2"><button id="changemember">${LANGDATA.member_change} </button> </td>
       </tr>
      </table>
      
    </div>
    <div id="changepassword" class="tab-pane fade">
      <h3>${LANGDATA.member_passwordchange}</h3>
             <table>
      <tr> 
        <td>old password </td>
        <td><input  type="text"   id="oldpassword"> </td>
      </tr> 
      <tr>
        <td> ${LANGDATA.member_newpassword} </td>
        <td><input  type="text"   id="newpassword">  </td>
       </tr>
       
       <tr>
        <td>${LANGDATA.member_verifypassword}</td>
        <td> <input  type="text"   id="repassword">    </td>
       </tr>       
       <tr>
       <td colspan="2"><button id="changepasswordbutton">${LANGDATA.member_change}    </button> </td>
       </tr>
      </table>      
    </div>
  </div>
</div>
 
</body>
</html>