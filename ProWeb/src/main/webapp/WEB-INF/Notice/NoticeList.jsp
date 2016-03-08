<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
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
	
	</style>
	<style>
		footer {
    background-color:#eeeeee;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;	 	 
	}
	
	</style>
	<style>
		#Table{
			background-color: MistyRose;<!--Joon -->
		}
	</style>
	<style>
	aside   { padding: 30px;
    background: lightblue;padding-left:0px;margin-left:1px 1px 0px 1px;float:left;  width:16%;height:auto}
	
	section { 
	   float:none; width:82%; height:auto; padding:10px 10px 10x 10px;
	   }
	</style>
	<!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
	<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					$(location).attr("href", "../Notice/NoticeRegForm.do");
				});
			});
			$(document).ready(function(){
				$("#sBtn").click(function(){
					$(location).attr("action", "../Notice/NoticeRegForm.do");
				});
			});
		</script>	
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
 <div id='EduContainer'>
		<div id='top'>	
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>	
		<!-- 왼쪽 사이드 영역 -->
		<div id="side">  	
			<jsp:include page="/MenuBar/Side.jsp" flush="false" />
		</div>
	    <div id="Main">
        <h4 align="center" ><strong>공지 사항 보기</strong></h4>
	<!-- 	검색 기능 폼 -->
	<form method="POST" id="sFrm">
			<input type="hidden" name="kind" value="S">
			<table border="1" width="80%" align="center" id='Table' >
				<tr>
					<td>
						<select id="kind" name="kind">
							<option value="title">제목</option>
							<option value="body">본문</option>
							<option value="writer">글쓴이</option>
							<option value="all">제목 + 본문</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="검색" id="sBtn" class="btn btn-primary btn-sm">
					</td>
				</tr>
			</table>
	</form>
<!-- 	목록 보여주기 -->
		<table border="1" width="80%" align="center">
			<tr id="tr_top">
				<th class="text-center">번호</th>
				<th class="text-center">제목</th>
				<th class="text-center">글내용</th>
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
<c:forEach	var="temp" items="${LIST}">
			<tr>
				<td class="text-center">${temp.notice_no}</td>
				<td>
					<a href="../Notice/NoticeView.do">${temp.notice_title}</a>
				</td>
				<td>${temp.notice_body}</td>
				<td>${temp.mem_id}</td>
				<td>${temp.notice_date}</td>				
			</tr>
</c:forEach>
</c:if>	
		</table>

<!-- 	페이지 이동 기능 -->

<table border="1" align="center" width="80%" id="page">
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
		<table border="1" width="80%" align="center">
			<tr id="tr_bot">
				<td align="center">
					<input type="button" id="wBtn" value="글쓰기" class="btn btn-primary btn-sm">
				</td>
			</tr>
		</table>
	</div>
	<!-- 왼쪽 사이드 영역 -->
	<div id="side">  	
			<jsp:include page="/MenuBar/Foot.jsp" flush="false" />
	</div>	
	</div>
	</body>
</html>