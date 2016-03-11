<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		
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
		
	</style>
		<script>
			function goDetail(orino) {
			//	매개변수	선택한 글의 번호가 기억될 예정이다.
				$(location).attr("href", "../Notice/NoticeHit.do?nowPage=${PINFO.nowPage}&oriNo=" + orino + "&flag=S");
			}
			$(document).ready(function(){
				$("#lBtn").click(function(){
					$(location).attr("href", "../Notice/NoticeList.do");
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
		<h4 align="center" ><strong>공지 검색 </strong></h4>
<!-- 	검색 폼 -->
<!-- 	목록 출력 -->
		<table border="1" align="center" width="80%">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>				
			</tr>
		<c:if test="${empty LIST}">
			<tr>
				<td colspan="4" align="center">
					검색 결과가 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty LIST}">
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.notice_no}</td>
					<td>
						 <a href="JavaScript:goDetail(${temp.notice_no})">${temp.notice_title}</a>
						<!-- <a href="../Notice/NoticeView.do?nowPage=${PINFO.nowPage}&oriNo=${temp.notice_no}">${temp.notice_title}</a>  -->
					</td>
					<td>${temp.mem_id}</td>
					<td>${temp.notice_date}</td>					
				</tr>
			</c:forEach>
		</c:if>
		</table>
<!-- 	페이지 이동 기능 -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
				<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
					<a href="../Notice/NoticeSearch.do?nowPage=1">[처  음]</a>
					<c:if test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../Notice/NoticeSearch.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="../Notice/NoticeSearch.do?nowPage=${temp}">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../Notice/NoticeSearch.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
					<a href="../Notice/NoticeSearch.do?nowPage=${PINFO.totalPage}">[마지막]</a>
				</td>
			</tr>
		</table>
					<table border="1" width="80%" align="center">
				<tr id="tr_bot">
					<td align="center">		
					 <input type="hidden" value="이전 검색" id="bBtn" class="btn btn-primary btn-sm">					
					<input type="button" value="목록보기" id="lBtn" class="btn btn-primary btn-sm">					
					</td>
				</tr>
			</table>
		</div>
	</div>		 
 </div>
<!-- 	기타 부가기능 -->
	</body>
</html>