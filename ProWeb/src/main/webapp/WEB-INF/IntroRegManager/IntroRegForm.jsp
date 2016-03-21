<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	 
	
	<!--CSS-->

	<!--JS-->
	
	<!--CustomScript-->
	
	<!--Never Delete "EduContainer" style tag-->
	<style>
	
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
		#main{
				width:1200px;
		}	
		#centerPage{
			float:right;
			position: relative;					
			width:980px;
			height:100%;
			padding:10px;
			font-size:16px;					
		}
		#sideBarDiv{
			float:left;
			border-top:solid 1px white;
			clear:both;
			width:150px;			
		}
		<!--JOON CSS-->
		
		table, th, td {
   		border: 1px solid black text-align:center;
		}
		#tr_top{
			background:rgb(114, 235, 125);/*배경색*/
		}
		table {
			width:100%;
			border-collapse:collapse;
			font-size:16px; /*글꼴 크기*/
			line-height:24px;/*줄 간격*/
		}		
		a{
			text-decoration:none; /* 링크 밑줄 없애기 */
			color:black; /*글 색상*/
		}
		a:HOVER {
			text-decoration:underline; /* 밑줄 
			color:green;			/*글 색상*/
		}
		
	</style>
	
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					
					//	이제 무결성 검사하고...
									
					$("#frm").attr("action", "../IntroRegManager/IntroReg.do");
					$("#frm").submit();
				});
				$("#lBtn").click(function(){
					//	먼저 스킨에 내용을 textarea로 옮긴다.
					//oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	이제 무결성 검사하고...
										
					$("#frm").attr("action", "../IntroRegManager/IntroList.do");
					$("#frm").submit();
				});				
			});
		</script>
		
</head>
<body>
	<div id='EduContainer'>
	<%-- <c:if test="${sessionScope.ADMIN ne 'Y'}">
 	<c:redirect url="/LoginForm.jsp" />
</c:if> --%>

	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
		<!-- This area is Body Part -->
		<div id="sideBarDiv">
			<jsp:include page="/MenuBar/IntroSide.jsp" flush="false" />
		</div>
		
		<div id="centerPage"> 
		<h4 align="center" ><strong>강사 소개 등록 </strong></h4>
		<!--  강사 소개글 등록 폼 -->
		<form method="POST" id="frm" enctype="multipart/form-data">


			<table border="1" align="center">
				<tr>
					<th id=#tr_top class="text-center">강사 ID</th>
					<th ><input type="text" id="mem_id" name="mem_id" value="${sessionScope.ID}" disabled ></th>
				</tr>
				<tr>
					<th id=#tr_top class="text-center">제목</th>
					<th><input type="text" id="intro_title" name="intro_title"></th>
				</tr>
				<tr>
					<th id=#tr_top class="text-center">소개 본문</th>
					<th><textarea cols="80" rows="10" id="intro_body" name="intro_body"></textarea></th>
				</tr>
				<tr>
					<th id=#tr_top class="text-center">사진</th>
					<th><input type="file" id="gimg" name="gimg"></th>
				</tr>
				
				<tr align="center">
					<td id=#tr_top colspan="2" align="center">
						<input type="button" value="소개 글 등록" id="wBtn" class="btn btn-primary btn-sm">
						<input type="reset" value="다시작성"  class="btn btn-primary btn-sm">
						<input type="button" value="목록" id="lBtn"   class="btn btn-primary btn-sm">
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>		 
 </div>
	</body>
</html>
