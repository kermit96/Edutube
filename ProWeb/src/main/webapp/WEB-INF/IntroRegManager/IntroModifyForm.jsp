<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
			background:yellowgreen;/*배경색*/
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
				
	</style>			

<script>
			$(document).ready(function(){
				$("#mBtn").click(function(){
					//할일
					//무결성 검사하고
					$writer = $("#mem_id").val();
					/*
					if($writer == ""){
						alert("글쓴이를 입력해 주세요");
						return;
					}
					*/
					$title = $("#intro_title").val();
					if($title == ""){
						alert("제목을 입력해 주세요");
						return;
					}
					$body = $("#intro_body").val();
					if($body == ""){
						alert("내용을 입력해 주세요");
						return;
					}
					$newphoto = $("#new_img").val();
					if($newphoto != ""){
						alert("새로운 사진으로 변경될 예정입니다.");
						$("#mfrm").attr("action", "../IntroRegManager/IntroModify.do?flag=1");
					}
					else{
						$oldphoto = $("#old_img").val();
						//if($oldphoto!=""){
						//	alert("이전  사진을 그대로 사용합니다.");
							//return;
							$("#mfrm").attr("action", "../IntroRegManager/IntroModify.do?flag=0");
						//}
						
					}
					
					$("#mfrm").submit();
					//서버에게 요청한다.
					//$("#mfrm").attr("action", "../IntroRegManager/IntroModify.do");
					//$("#mfrm").submit();
				});
				
				$("#lBtn").click(function(){
					//	먼저 스킨에 내용을 textarea로 옮긴다.
					//oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	이제 무결성 검사하고...
										
					$("#mfrm").attr("action", "../IntroRegManager/IntroList.do?nowPage=${NOWPAGE}");
					$("#mfrm").submit();
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
		<h4 align="center" ><strong>강사 수정 </strong></h4>	
		<form method="POST" id="mfrm" enctype="multipart/form-data">
			
			<input type="hidden" name="intro_no" value="${DATA.intro_no}">
			<input type="hidden" name="nowPage" value="${NOWPAGE}">
			<!--  강사 소개글 등록 폼 -->
			<table width="80%" border="1" align="center">
				<tr>
					<td class="text-center">강사 ID</td>
					<td><input type="text" id="mem_id" name="mem_id" value="${sessionScope.ID}" disabled ></td>
				</tr>
				<tr>
					<td class="text-center">제목</td>
					<td><input type="text" id="intro_title" name="intro_title" value="${DATA.intro_title}"></td>
				</tr>
				<tr>
					<td class="text-center">소개 본문</td>
					<td><textarea cols="80" rows="10" id="intro_body" name="intro_body">${DATA.intro_body}</textarea></td>
				</tr>
				<tr>
					<td class="text-center">기존 사진</td>
					<td id="old_img">${DATA.gimg2}</td>
				</tr>
				<tr>
					<td class="text-center">사진 변경</td>
					<td><input type="file" id="new_img" name="gimg"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="글 수정" id="mBtn" class="btn btn-primary btn-sm">
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





