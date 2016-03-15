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
		
	</style>
	<style type="text/css">
	    #Table{
			background-color: MistyRose;<!--Joon -->
		}
		#tr_top{
			background:rgb(114, 235, 125);
			text-align:center;
		}
		#page{
			background-color: MistyRose;
		}
		h3 {
			text-align:center;
		}
		
		table, th, td {
   		border: 1px solid black text-align:center;
		}
				
	</style>	
	<script>		    
			
			$(document).ready(function(){
				$("#mBtn").click(function(){					
					$(location).attr("href", "../Notice/NoticeModifyForm.do?oriNo=${DATA.notice_no}&nowPage=${NOWPAGE}");
				});
			});
			$(document).ready(function(){
				$("#dBtn").click(function(){
					$(location).attr("href", "../Notice/NoticeDelete.do?oriNo=${DATA.notice_no}&nowPage=${NOWPAGE}");
				});
				
			});
			$(document).ready(function(){
				$("#lBtn").click(function(){
					$(location).attr("href", "../Notice/NoticeList.do?nowPage=${NOWPAGE}");
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
			<jsp:include page="/MenuBar/NoticeSide.jsp" flush="false" />
		</div>
		
		<div id="centerPage"> 
		<h4 align="center" ><strong>공지 상세 보기 </strong></h4>
	<!-- 	
		수정하기 폼 요청에 필요한 데이터를 POST 방식으로 보내기 위한 임시 폼이다. 
		이 안에 요청에 필요한 모든 데이터를 hidden으로 만들어 놓으면 된다.
-->
		<form method="POST" id="mfrm">
			<input type="hidden" id="oriNo" name="oriNo" value="${DATA.mem_id}">
			<input type="hidden" id="nowPage" name="nowPage" value="${NOWPAGE}">
		</form>
		 <!-- 	목록 보여주기 -->
		<table border="1" width="80%" align="center">
				<tr id="tr_top">
					<th id="tr_top" class="text-center">번호</th>
					<th id="tr_top" class="text-center">제목</th>
					<th id="tr_top" class="text-center">글내용</th>
					<th id="tr_top" class="text-center">글쓴이</th>
					<th id="tr_top" class="text-center">작성일</th>
				</tr>
	    	   	<tr>
					<td class="text-center">${DATA.notice_no}</td>
					<td class="text-center">${DATA.notice_title}</td>
					<td class="text-center">${DATA.notice_body}</td>
					<td class="text-center">${DATA.mem_id}</td>
					<td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${DATA.notice_date}"/></td>							
			   </tr>		
		</table>
			<table border="1" width="80%" align="center">
				<tr id="tr_bot">
					<td align="center">					
<c:if test="${sessionScope.ID eq DATA.mem_id}">
					<input type="button" value="수정하기" id="mBtn" class="btn btn-primary btn-sm">
					<input type="button" value="삭제하기" id="dBtn" class="btn btn-primary btn-sm">					
</c:if>
					<input type="button" value="목록보기" id="lBtn" class="btn btn-primary btn-sm">					
					</td>
				</tr>
			</table>

		</div>
	</div>		 
 </div>
</body>
</html>
