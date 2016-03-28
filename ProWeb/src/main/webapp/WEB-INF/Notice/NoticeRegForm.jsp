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
		th,td {
			background:white;/*배경색*/
		}
		table td, th {
			border:#d3d3d3 solid 1px;/*경계선 색상 스타일 굵기 */
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
		#tr_top{
			background:rgb(114, 235, 125);
			text-align:center;
		}
		</style>
	
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){					
					//	이제 무결성 검사
					$writer = $("#writer").val();
					if($writer == ""){
						alert("글쓴이를 입력해 주세요");
						return;
					}
					$title = $("#title").val();
					if($title == ""){
						alert("제목을 입력해 주세요");
						return;
					}
					$body = $("#body").val();
					if($body == ""){
						alert("내용을 입력해 주세요");
						return;
					}
					$("#frm").attr("action", "../Notice/NoticeReg.do");
					$("#frm").submit();
				});
				$("#lBtn").click(function(){
					//	먼저 스킨에 내용을 textarea로 옮긴다.
					//oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	이제 무결성 검사하고...
										
					$("#frm").attr("action", "../Notice/NoticeList.do");
					$("#frm").submit();
				});				
			});
		</script>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
		
		
		<div id="centerPage"> 
		<h4 align="center" ><strong>공지 등록 </strong></h4>
		<form method="POST" id="frm" action="" enctype="multipart/form-data">
			<table width="80%" border="1" align="center" id='Table'>
				<tr>
					<th id="tr_top" class="text-center">아이디</th>
					<td colspan="3"><input type="text" name="mem_id" id="writer" value="${sessionScope.ID}" disabled>
				</tr>
				<tr>
					<th id="tr_top" class="text-center">제목</th>
					<td colspan="3"><input type="text" name="notice_title" id="title" value="${DATA.notice_title}">
				</tr>
				<tr>
				    <th id="tr_top" class="text-center">내용</th>
					<td colspan="3">
						<textarea cols="80" rows="10" id="body" name="notice_body" style="width:100%; height:200px">${DATA.notice_body}</textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
					<!--  비정상 접근 처리 -->
					  
						<c:if test="${sessionScope.NAL eq 'A'}">	
						<input type="button" value="글쓰기" id="wBtn" class="btn btn-primary btn-sm">
						<input type="reset" value="다시쓰기" id="rfBtn" class="btn btn-primary btn-sm">
						<input type="button" value="목록" id="lBtn"   class="btn btn-primary btn-sm">
						</c:if>
					</td>
				</tr>
			</table>		
		</form>
		</div>
	</div>		 
 </div>
	</body>
</html>
