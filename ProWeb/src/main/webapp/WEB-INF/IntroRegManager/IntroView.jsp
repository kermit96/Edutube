<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
		</style>
	<script>		    
			
			$(document).ready(function(){
				$("#mBtn").click(function(){					
					$(location).attr("href", "../IntroRegManager/IntroModifyForm.do?oriNo=${DATA.intro_no}&nowPage=${NOWPAGE}");
				});
			});
			$(document).ready(function(){
				$("#dBtn").click(function(){
					$(location).attr("href", "../IntroRegManager/IntroDelete.do?oriNo=${DATA.intro_no}&nowPage=${NOWPAGE}");
				});
				
			});
			$(document).ready(function(){
				$("#lBtn").click(function(){
					$(location).attr("href", "../IntroRegManager/IntroList.do?nowPage=${NOWPAGE}");
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
		<h4 align="center" ><strong>소개 상세 보기 </strong></h4>
	<!-- 	
		수정하기 폼 요청에 필요한 데이터를 POST 방식으로 보내기 위한 임시 폼이다. 
		이 안에 요청에 필요한 모든 데이터를 hidden으로 만들어 놓으면 된다.
-->
		<form method="POST" id="mfrm">
			<input type="hidden" id="oriNo" name="oriNo" value="${DATA.mem_id}">
			<input type="hidden" id="nowPage" name="nowPage" value="${NOWPAGE}">
		</form>
		 <!-- 	목록 보여주기 -->
		<table border="1" align="center">
				<tr>
					<th  id="tr_top" class="text-center">번호</th>
					<th  id="tr_top" class="text-center">사진</th>
					<th  id="tr_top" class="text-center">제목</th>
					<th  id="tr_top" class="text-center">글내용</th>
					<th  id="tr_top" class="text-center">글쓴이</th>
					<th  id="tr_top" class="text-center">작성일</th>
				</tr>
	    	   	<tr>
					<td class="text-center">${DATA.intro_no}</td>
					<td class="text-center">
						<img src="../gimgs/${DATA.gimg2}" width="100" height="100"></td>
					<td class="text-center">${DATA.intro_title}</td>
					<th class="text-center">${DATA.intro_body}</th>
					<td class="text-center">${DATA.mem_id}</td>
					<td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${DATA.intro_date}"/></td>				
			   </tr>		
		</table>
			<table border="1" align="center">
				<tr id="tr_top">
					<td align="center">					

					<input type="button" value="수정하기" id="mBtn" class="btn btn-primary btn-sm">
					<input type="button" value="삭제하기" id="dBtn" class="btn btn-primary btn-sm">					

					<input type="button" value="목록보기" id="lBtn" class="btn btn-primary btn-sm">					
					</td>
				</tr>
			</table>

		</div>
	</div>		 
 </div>
</body>
</html>
