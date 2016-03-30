<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
 <%@page import="com.iedu.util.*" %>

   
<%
   boolean isview = false;
    //  local 에서  실행하지 않으면 실행되지 않게 한다.    
    String ip=  request.getRemoteAddr();      
    if (util.IsMyIp(ip) )
     	isview = true;     		
%>    
<!DOCTYPE html >

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"> </script>
<script>
$(function () {

    $(".tab_content").hide();
    $(".tab_content:first").show();

    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active").css("color", "#333");
        //$(this).addClass("active").css({"color": "darkred","font-weight": "bolder"});
        $(this).addClass("active").css("color", "darkred");
        $(".tab_content").hide()
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn()
    });
});
</script>

<style>
ul.tabs {
    margin: 0;
    padding: 0;
    float: left;
    list-style: none;
    height: 32px;
    border-bottom: 1px solid #eee;
    border-left: 1px solid #eee;
    width: 100%;
    font-family:"dotum";
    font-size:12px;
}
ul.tabs li {
    float: left;
    text-align:center;
    cursor: pointer;
    width:30%;
    height: 31px;
    line-height: 31px;
    border: 1px solid #eee;
    border-left: none;
    font-weight: bold;
    background: #fafafa;
    overflow: hidden;
    position: relative;
}
ul.tabs li.active {
    background: #FFFFFF;
    border-bottom: 1px solid #FFFFFF;
}
.tab_container {
    border: 1px solid #eee;
    border-top: none;
    clear: both;
    float: left;
    width: 100%;
    background: #FFFFFF;
}
.tab_content {
    padding: 5px;
    font-size: 12px;
    display: none;
}
.tab_container .tab_content ul {
    width:100%;
    margin:0px;
    padding:0px;
}
.tab_container .tab_content ul li {
    padding:5px;
    list-style:none
}
;
 #container {
    width: 100%;
    margin: 0 auto;
}

</style>


<%if (isview)  { %> 
<script>
$(document).ready(function() {
	

	  // 처음 설정 항목을 가져온다.
	  init();
	  
	   $("#test").click(test);
	   $("#dbsave").click(dbsave);
	   $("#smtpsave").click(smtpsave);
	   $("#etcsave").click(etcsave);
	   $("#end").click(myclose);	   
	   $("#smtptest").click(smtptest);
	  
});


function init()
{

    /*	
	  $("#stmtport").keydown(function (e) {
	        // Allow: backspace, delete, tab, escape, enter and .
	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	             // Allow: Ctrl+A, Command+A
	            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
	             // Allow: home, end, left, right, down, up
	            (e.keyCode >= 35 && e.keyCode <= 40)) {
	                 // let it happen, don't do anything
	                 return;
	        }
	        // Ensure that it is a number and stop the keypress
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))) 
	        {		  
	            e.preventDefault();
	        }
	    });
	
	*/
	
	 $("#stmtport").keydown(function (e) {
	        // Allow: backspace, delete, tab, escape, enter and .
	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	             // Allow: Ctrl+A, Command+A
	            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
	             // Allow: home, end, left, right, down, up
	            (e.keyCode >= 35 && e.keyCode <= 40)) {
	                 // let it happen, don't do anything
	                 return;
	        }
	        // Ensure that it is a number and stop the keypress
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	            e.preventDefault();
	        }
	    });
	
	
    $.ajax({
        url:'../Setup/getglobalconfig.do',
        async:false,
        type:'post',
        dataType:'json',
        success:function(data){
        	
       
          $("#dbname").val(data.dbname);
          $("#dbselect").val(data.dbtype);
          $("#dbuser").val(data.userid);
          $("#dbhost").val(data.host);
          $("#dbport").val(data.port);
          $("#dbpassword").val(data.password);
          
          $("#smtpport").val(data.smtpport);
          $("#smtphost").val(data.smtphost);
          $("#smtpuserid").val(data.smtpuserid);
          $("#smtppassword").val(data.smtppassword);
          
          $("#smtpemail").val(data.smtpemail);
          $("#smtpsender").val(data.smtpsender);
          
          
          switch(data.encryptedmethod )
          {
             case 0:
            	 $("#nosecurity").attr('checked', 'checked');
            	 nosecurity
            	   break;
             case 1:
            	 $("#tls").attr('checked', 'checked');
          	   break;
        
             case 2:
            	 $("#ssl").attr('checked', 'checked');
          	   break;
        
          }
          
          
          $("#savedir").val(data.savedir);
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }
        
    });
}

function smtptest()
{
	var encryted = $('input:radio[name="checkset"]:checked').val();
	$.ajax({
        url:'../Setup/smtptest.do',
        async:false,
        type:'post',
        dataType:'html',
        data:{smtpport:$("#smtpport").val(),
        	smtphost:$("#smtphost").val(),
        	smtpuserid:$("#smtpuserid").val(),
        	smtppassword:$("#smtppassword").val(),
        	smtpemail:$("#smtpemail").val(),
        	smtpsender:$("#smtpsender").val(),
        	encryptedmethod:encryted,
        },
        success:function(data){            	
            $("#smtpresult").html(data);
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }
        
    }); 
}



function test()
{
	$.ajax({
        url:'../Setup/dbtest.do',
        async:false,
        type:'post',
        dataType:'html',
        data:{dbtype:$("#dbselect").val(),
        	dbname:$("#dbname").val(),
        	dbuser:$("#dbuser").val(),
        	dbhost:$("#dbhost").val(),
        	dbport:$("#dbport").val(),
        	dbpassword:$("#dbpassword").val(),
        
        },
        success:function(data){            	
            $("#result").html(data);
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }
        
    }); 
}

function etcsave()
{
	
	var encryted = $('input:radio[name="checkset"]:checked').val();
    try {
	$.ajax({
        url:'../Setup/etcsave.do',
        async:false,
        type:'post',
        dataType:'html',
        data:{        	
        	savedir:$("#savedir").val()        
        },
        
        success:function(data){            	
            alert("저장했습니다.\n tomcat server 를 재 시작하기기 바랍니다.");
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }        
    }); 	
	
    } catch (ex) {
    	
    	alert(ex);
    }
 
}



function smtpsave()
{
	
	var encryted = $('input:radio[name="checkset"]:checked').val();
    try {
	$.ajax({
        url:'../Setup/smtpsave.do',
        async:false,
        type:'post',
        dataType:'html',
        data:{        	
        	smtphost:$("#smtphost").val(),        	
        	smtpport:$("#smtpport").val(),        	
        	smtpuserid:$("#smtpuserid").val(),
        	smtppassword:$("#smtppassword").val(),
        	smtpemail:$("#smtpemail").val(),
        	smtpsender:$("#smtpsender").val(),        	
        	encryptedmethod:encryted,        	

        	
        },
        
        success:function(data){            	
            alert("저장했습니다.");
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }        
    }); 	
	
    } catch (ex) {
    	
    	alert(ex);
    }
 
}



function dbsave()
{
	
	var encryted = $('input:radio[name="checkset"]:checked').val();
    try {
	$.ajax({
        url:'../Setup/dbsave.do',
        async:false,
        type:'post',
        dataType:'html',
        data:{dbtype:$("#dbselect").val(),
        	dbname:$("#dbname").val(),
        	dbuser:$("#dbuser").val(),
        	dbhost:$("#dbhost").val(),
        	dbport:$("#dbport").val(),
        	dbpassword:$("#dbpassword").val()  
        },
        
        success:function(data){            	
            alert("저장했습니다.\n tomcat server 를 재 시작하기기 바랍니다.");
        },
        error:function( xhr) {
        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
        }        
    }); 	
	
    } catch (ex) {
    	
    	alert(ex);
    }
 
}


function myclose()
{
//	opener=self;
//	self.close();
	
	window.opener = "";
	window.close();
//	self.opener = self;
//	window.close();

}
<%} %>

</script>
</head>

<body>
<%if (isview)  { %>
  <h1 align="center"> 환경 설정</h1>   
  
  <div id="container">
    <ul class="tabs">
        <li class="active" rel="dbsetting">DB 설정 </li>
        <li rel="smtpsetting">SMTP 설정</li>
        <li rel="ectsetting">기타 설정</li>
    </ul>
    <div class="tab_container">
        <div id="dbsetting" class="tab_content">
        <h2 align="center"> DB 설정 </h1>    <br> 
  
  <table align="center">
   <tr> 
      <td>
          DB 선택  
      </td>
      <td>
      
         <select id="dbselect" > 
  


<c:forEach var="list" items="${DBLIST}">
    <option value="${list.key}">${list.value.dbname}</option>
  </c:forEach>
  <!-- 
     <option value="0">Oracle</option>
     <option value="1">Ms-sql</option>
      <option value="2">Mysql</option>  -->
          </select>
      </td>
      
   </tr>
   
     <tr> 
      <td>
          DB Host  
      </td>
      <td>
       <input type="text"  id="dbhost" >   
      </td>     
   </tr>

     <tr> 
      <td>
          DB Port (0 이면  default )  
      </td>
      <td>
       <input type="text"  id="dbport"  value="0">   
      </td>     
   </tr>
   
    <tr> 
      <td>
          DB 명   
      </td>
      <td>
       <input type="text"  id="dbname"  value="">   
      </td>     
   </tr>   
  
    <tr> 
      <td>
          userid   
      </td>
      <td>
       <input type="text"  id="dbuser"  value="">   
      </td>     
   </tr>   
  
    <tr> 
      <td>
          password   
      </td>
      <td>
       <input type="password"  id="dbpassword"  value="">   
      </td>     
   </tr>   
  <tr>
  <td colspan="2"  align="center">
    <p>
      <button id="test"> DB 테스트</button>     <button  id="dbsave">적용</button>
  </td>
  </tr>    
  </table>    
   <div id="result"  style="color:blue"  align="center"> 
      
   </div>
  
          </div>
          
          
        <!-- #tab1 -->
        <div id="smtpsetting" class="tab_content">
   <h2 align="center"> SMTP  설정 </h1>    <p> 
   <table align="center">
      <tr> 
        <td>server</td>
        <td><input type="text"   id="smtphost"  /> </td>
      </tr>
      <tr> 
        <td>port</td>
        <td><input type="text"   id="smtpport"  /> </td>
      </tr>
      
      <tr>
      <td>보안 연결 설정  </td>
        <td>         
        <input type="radio" name="checkset" id="nosecurity" value="0" >없음 
        <input type="radio" name="checkset" id="ssl" value="2" />SSL
         <input type="radio" name="checkset" id="tls" value="1" />TLS        
        </td>
      </tr>
      
      <tr>
      <td>smtp userid </td>
        <td><input type="text"   id="smtpuserid"  />  </td>
      </tr>
      
      <tr>      
      <td>smtp password </td>
        <td><input type="password"   id="smtppassword"  /> </td>
      </tr>                
      
      
      <tr> 
        <td>보내는 사람 E-MAIL</td>
        <td><input type="input"   id="smtpemail"  /> </td>
      </tr>
      
         <tr> 
      <td>보내는 사람 이름 </td>
        <td><input type="input"   id="smtpsender"  /> </td>
      </tr>        


  <td colspan="2"  align="center">
    <p>
      <button id="smtptest"> SMTP TEST</button>        <button  id="smtpsave">적용</button>       
  </td>
           
   
   </table>
   
    <div id="smtpresult"  style="color:blue"  align="center"> 
      
   </div>
            
        
        </div>
        <!-- #tab2 -->
        <div id="ectsetting" class="tab_content">
             <table align="center">
    <tr> 
      <td>
          파일 저장 디렉토리   
      </td>
      <td>
       <input type="text"  id="savedir"  value="">   
      </td>
      
         <td colspan="2"  align="center">
               
               <button  id="etcsave">적용</button>       
    </td>
       
           
   </tr>     
</table>
                        
        </div>
        <!-- #tab3 -->
    </div>
    <!-- .tab_container -->
</div>
<!-- #container -->
  
    <div   align="center"> 
         <button id="end"> 끝내기</button>              
   </div>

   <% } else { %>  

<%} %>

</body>
  <script>

   </script>
</html>
