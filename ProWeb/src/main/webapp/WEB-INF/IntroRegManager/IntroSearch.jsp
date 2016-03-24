<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			function goDetail(orino) {
			//	매개변수	선택한 글의 번호가 기억될 예정이다.
				$(location).attr("href", "../IntroRegManager/IntroHit.do?nowPage=${PINFO.nowPage}&oriNo=" + orino + "&flag=S");
			}
			$(document).ready(function(){
				$("#lBtn").click(function(){
					$(location).attr("href", "../IntroRegManager/IntroList.do");
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
		<h4 align="center" ><strong>강사 검색 결과 </strong></h4>
<!-- 	검색 폼 -->
<!-- 	목록 출력 -->
		<table border="1" align="center" >
			<tr id="tr_top">
				<th class="text-center">번호</th>
				<th class="text-center">제목</th>
				<th class="text-center">글쓴이</th>
				<th class="text-center">작성일</th>				
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
					<td class="text-center">${temp.intro_no}</td>
					<td class="text-center">
						 <a href="JavaScript:goDetail(${temp.intro_no})">${temp.intro_title}</a>
					</td>
					<td class="text-center">${temp.mem_id}</td>
					<td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${temp.intro_date}"/></td>					
				</tr>
			</c:forEach>
		</c:if>
		</table>
<!-- 	페이지 이동 기능 -->
		<table border="1" align="center">
			<tr>
				<td align="center">
				<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
					<a href="../IntroRegManager/IntroSearch.do?nowPage=1">[처  음]</a>
					<c:if test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../IntroRegManager/IntroSearch.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
					</c:if>
					<c:forEach var="temp1" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<c:if test="${temp1 eq PINFO.nowPage}">
								[${temp1}]
						</c:if>
						<c:if test="${temp1 ne PINFO.nowPage}">
							<a href="..//IntroRegManager/IntroSearch.do?nowPage=${temp1}">[ ${temp1} ]</a>
						</c:if>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../IntroRegManager/IntroSearch.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
					<a href="../IntroRegManager/IntroSearch.do?nowPage=${PINFO.totalPage}">[마지막]</a>
				</td>
			</tr>
		</table>
					<table border="1" align="center">
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