<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>

<!--스크립트-->
<script>
	
</script>

<!--  스타일 -->
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


<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
<script>
	$(document).ready(function() {
		$("#wBtn").click(function() {
			$(location).attr("href", "../IntroRegManager/IntroRegForm.do");
		});
	});
	$(document).ready(function() {
		$("#sBtn").click(function() {
			$(location).attr("action", "../IntroRegManager/IntroSearch.do");
		});
	});
</script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
			<!-- This area is Body Part -->
			<div id="sideBarDiv">
				<jsp:include page="/MenuBar/IntroSide.jsp" flush="false" />
			</div>
			<div id="centerPage">
				<h4 align="center" ><strong>과목별 리스트</strong></h4>
<!-- 	검색 기능 폼 -->
	    <form method="POST" id="sFrm">
		<!-- 	
				일반목록은 L로 주기로 했고
				검색 목록은 S로 주기로 했다.
		 -->
			<input type="hidden" name="kind" value="S">
			<table border="1" width="80%" align="center" id='Table'>
				<tr>
					<td>
						<select id="kind" name="kind">
							<option value="title">제목</option>
							<option value="body">본문</option>
							<option value="id">ID</option>
							<option value="all">제목 + 본문</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="검색" id="sBtn" class="btn btn-primary btn-sm">
					</td>
				</tr>
			</table>
		</form>
<%--	상품 목록 보여주기 	--%>
	    <table width="80%" border="1" align="center">
	    	<tr>
				<th class="text-center">번호</th>
				<th class="text-center">강사 사진</th>
				<th class="text-center">강사 ID</th>
				<!-- <th class="text-center">제목</th> -->
				<!-- <th class="text-center">제목</th> --> 
				
			</tr>
	   <c:if test="${empty LIST}">
			<tr>
				<td colspan="5" align="center">
					<h4>아직 소개 글이 등록이 되지 않았습니다.</h4>
				</td>
			</tr>
	   </c:if>			
	   <c:if test="${not empty LIST}">
	   <c:forEach var="temp" items="${LIST}" varStatus="status">
			<tr>
				<td class="text-center">
					<a href="../IntroRegManager/IntroView.do?intro_no=${temp.intro_no}">${temp.intro_no}</a>
				</td>
				<td class="text-center">
					<img src="../gimgs/${temp.gimg2}" width="100" height="100">
				</td>
				<td class="text-center">${temp.mem_id}</td>
				<!-- <td class="text-center">${temp.intro_title}</td> -->
				<!-- <td class="text-center">${temp.intro_body}</td>  -->
				
			</tr>
	  </c:forEach>
	  </c:if>	
	  </table>		
<!-- 	페이지 이동 기능 -->
	  <table border="1" align="center" width="80%">
			<tr>
				<td align="center">
				<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
					<c:if test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../IntroRegManager/IntroList.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<c:if test="${temp eq PINFO.nowPage}">
						[${temp}]
						</c:if>
						<c:if test="${temp ne PINFO.nowPage}">
						<a href="../IntroRegManager/IntroList.do?nowPage=${temp}">[ ${temp} ]</a>
						</c:if>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../IntroRegManager/Introist.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
				</td>
			</tr>
	  </table>

			</div>
		</div>

	</div>
</body>
</html>
