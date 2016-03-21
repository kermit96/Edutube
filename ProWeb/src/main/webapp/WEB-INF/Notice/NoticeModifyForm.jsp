<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				$("#mBtn").click(function(){
					//할일
					//무결성 검사하고
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
					//서버에게 요청한다.
					$("#mfrm").attr("action", "../Notice/NoticeModify.do");
					$("#mfrm").submit();
				});
				$("#lBtn").click(function(){
					//	이제 무결성 검사하고...										
					$("#mfrm").attr("action", "../Notice/NoticeList.do?nowPage=${NOWPAGE}");
					$("#mfrm").submit();
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
		<div id="sideBarDiv">
			<jsp:include page="/MenuBar/NoticeSide.jsp" flush="false" />
		</div>
		
		<div id="centerPage"> 
		<h4 align="center" ><strong>공지 수정 등록 </strong></h4>	
		<form method="POST" id="mfrm">
			<input type="hidden" name="notice_no" value="${DATA.notice_no}">
			<input type="hidden" name="nowPage" value="${NOWPAGE}">
			<table border="1" align="center" >
				<tr>
					<th id="tr_top" class="text-center">글쓴이</th>
					<td colspan="3"><input type="text" id="writer" name="mem_id" value="${DATA.mem_id}" readonly></td>
				</tr>
				<tr>
					<th id="tr_top" class="text-center">제목</th>
					<td colspan="3">
						<input type="text" id="title" name="notice_title" value="${DATA.notice_title}">
					</td>
				</tr>
				<tr>
					<th id="tr_top" class="text-center">내용</th>
					<td colspan="3">
						<textarea cols="80" rows="10" style="width:100%; height:200px" id="body" name="notice_body">${DATA.notice_body}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center" id="lbtn">
						<input type="button" id="mBtn" value="수정하기" class="btn btn-primary btn-sm">
						<input type="reset" value="다시작성"  class="btn btn-primary btn-sm">
						<input type="button" value="목록" id="lBtn" class="btn btn-primary btn-sm">
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>		 
 </div>
</body>
</html>





