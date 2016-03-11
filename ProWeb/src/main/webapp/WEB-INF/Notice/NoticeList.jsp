<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
			//	상세보기 요청을 해줄 함수
			function goDetail(orino) {
				//	매개변수	선택한 글의 번호가 기억될 예정이다.
				$(location).attr("href", "../RBoard/BoardHit.dol?nowPage=${PINFO.nowPage}&oriNo=" + orino + "&flag=L");
			}
			$(document).ready(function(){
				$("#sBtn").click(function() {
					//	검색단어가 입력되었는지 무결성 검사하고....
					$("#sfrm").attr("action", "../Notice/NoticeSearch.do");
					$("#sfrm").submit();
				});
				$("#wBtn").click(function(){
					//	글쓰기 폼 요청을 한다.(GET방식)
					$(location).attr("href", "../Notice/NoticeRegForm.do");
					//$("#sfrm").submit();
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
	<h4 align="center" ><strong>공지 사항</strong></h4>
<!-- 	검색기능 -->
		<table border="1" align="center" >
			<tr>
				<td align="center">
					<form method="POST" id="sfrm">
						<select id="kind" name="kind">
							<option value="title">제목</option>
							<option value="body">본문</option>
							<option value="mem_id" >글쓴이</option>
							<option value="both">제목 + 본문</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="검색" id="sBtn" class="btn btn-primary btn-sm">
					</form>
				</td>
			</tr>
		</table>
<!-- 	목록 보여주기 -->
			<table border="1" align="center">
				<tr id="tr_top">
					<th class="text-center">번호</th>
					<th class="text-center">제목</th>
					<!-- <th class="text-center">글내용</th> -->
					<th class="text-center">글쓴이</th>
					<th class="text-center">작성일</th>
				</tr>
	    	<c:if test="${empty LIST}">
				<tr>
					<td colspan="5" align="center">
						<h4>아직 소개 글이 등록이 되지 않았습니다.</h4>
					</td>
				</tr>			   
	  		</c:if>			
	   		<c:if test="${not empty LIST}">
	   		 <tr>
			    	<td class="text-center" style="background-color:#00FFFF" ><strong>공지</strong></td>
					<td class="text-center">
							<a href="../Notice/NoticeView.do?nowPage=${PINFO.nowPage}&oriNo=${DATA.notice_no}">${DATA.notice_title}</a>
					</td>
					<!-- <td>${temp.notice_body}</td>  -->
					<td class="text-center">${DATA.mem_id}</td>
					<td class="text-center">${DATA.notice_date}</td>
			 </tr>
		   	<c:forEach	var="temp" items="${LIST}">
					<tr>
						<td class="text-center">${temp.notice_no}</td>
						<td class="text-center">
							<a href="../Notice/NoticeView.do?nowPage=${PINFO.nowPage}&oriNo=${temp.notice_no}">${temp.notice_title}</a>
						</td>
						<!-- <td>${temp.notice_body}</td>  -->
						<td class="text-center">${temp.mem_id}</td>
						<td class="text-center">${temp.notice_date}</td>				
					</tr>
			</c:forEach>
			</c:if>	
			</table>
	
		<!-- 	페이지 이동 기능 -->
	
			<table border="1" align="center" id="page">
				<tr>
					<td align="center">
					<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
					    <a href="../Notice/NoticeList.do?nowPage=1">[처  음]</a>
						<c:if test="${PINFO.startPage eq 1}">
							[이 전]
						</c:if>
						<c:if test="${PINFO.startPage ne 1}">
							<a href="../Notice/NoticeList.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
						</c:if>
						<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
							<a href="../Notice/NoticeList.do?nowPage=${temp}">[ ${temp} ]</a>
						</c:forEach>
						<c:if test="${PINFO.endPage eq PINFO.totalPage}">
							[다 음]
						</c:if>
						<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../Notice/NoticeList.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
						</c:if>
					    <a href="../Notice/NoticeList.do?nowPage=${PINFO.totalPage}">[마지막]</a> -->
					</td>
				</tr>
			</table>		
<!-- 	기타 부가 기능 -->
<c:if test="${sessionScope.ID eq DATA.mem_id}">
			<table border="1" >
				<tr id="tr_bot">
					<td align="center">
						<input type="button" id="wBtn" value="글쓰기" class="btn btn-primary btn-sm">
					</td>
				</tr>
			</table>
</c:if>
			</div>
		</div>
	</div>	
	</body>
</html>
