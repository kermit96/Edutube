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
	</script>
	

</head>
<body>
<div class="container">
  <h2>유저 정보 변경</h2>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#changeuserinfo">유저 정보 변경</a></li>
    <li><a data-toggle="tab" href="#changepassword">비밀 번호 변경</a></li>
  </ul>

  <div class="tab-content">
    <div id="changeuserinfo" class="tab-pane fade in active">
      <h3>개인 정보 변경</h3>
      <table>
      <tr> 
        <td>이름 </td>
        <td><input  type="text"   id="id_name"> </td>
      </tr> 
      <tr>
        <td>e-mail </td>
        <td><input  type="text"   id="id_email"> </td>
       </tr>
       
       <tr>
        <td>ID</td>
        <td> <input  type="text"  readonly="readonly"  id="findingip">    </td>
       </tr>       
       <tr>
       <td colspan="2"><button id="changeuserinfobutton">변경   </button> </td>
       </tr>
      </table>
      
    </div>
    <div id="changepassword" class="tab-pane fade">
      <h3>비밀 번호 변경</h3>
             <table>
      <tr> 
        <td>ID </td>
        <td><input  type="text"   id="password_id"> </td>
      </tr> 
      <tr>
        <td>이름 </td>
        <td><input  type="text"   id="password_name">  </td>
       </tr>
       
       <tr>
        <td>e-mail</td>
        <td> <input  type="text"   id="e_mailid">    </td>
       </tr>       
       <tr>
       <td colspan="2"><button id="changepasswordbutton">변경   </button> </td>
       </tr>
      </table>
      
    </div>
  </div>
</div>

</body>
</html>