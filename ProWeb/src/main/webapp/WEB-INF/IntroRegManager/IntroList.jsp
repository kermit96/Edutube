<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
	name="viewport" content="width=device-width, initial-scale=0">
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
	$(document).ready(function() {
		$("#wBtn").click(function() {
			$(location).attr("href", "../IntroRegManager/IntroRegForm.do");
		});
		$("#lBtn").click(function() {
			$(location).attr("action", "../IntroRegManager/IntroList.do");
			//$("#sfrm").submit();
		});
		$("#sBtn").click(function() {
			//수정 다시할 것
			$content = $("#content").val();
			if($content == ""){
				alert("검색어를 입력해 주세요");
				return;
			}
			$("#sfrm").attr("action", "../IntroRegManager/IntroSearch.do");
			$("#sfrm").submit();
		});
		
	});
	
	</script>
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
				<h4 align="center" ><strong>강사 리스트</strong></h4>
<!-- 	검색 기능 폼 

	    <form method="POST" id="sFrm">
	 	
				일반목록은 L로 주기로 했고
				검색 목록은 S로 주기로 했다.
		
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
		</form> -->
<!-- 	검색기능 -->
		<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<form method="POST" id="sfrm">
						<select id="kind" name="kind">
							<option value="title">제목</option>
							<option value="body">본문</option>
							<option value="mem_nick" >강사 NICK</option>
							<option value="both">제목 + 본문</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="검색" id="sBtn" class="btn btn-primary btn-sm">
						<input type="button" id="wBtn" value="글 추가" class="btn btn-primary btn-sm">
					</form>
				</td>
			</tr>
		</table>
<%--	상품 목록 보여주기 	 --%>
		  <div class="row">
		    <c:if test="${not empty LIST}">
	  		<c:forEach var="temp" items="${LIST}" varStatus="status">
			    <div class="col-md-4">
			      <a href="../IntroRegManager/IntroView.do?nowPage=${PINFO.nowPage}&oriNo=${temp.intro_no}">
				  NICK :${temp.mem_nick}
				  <c:if test="${empty temp.gimg2}">
					<img src="../gimgs/noimgae.png" class="img-circle" width="100%" height="100">
				  </c:if>
				  <c:if test="${not empty temp.gimg2}">
			      <img src="../gimgs/${temp.gimg2}" class="img-circle" width="100" height="100">
			      </c:if>
			      <!-- <td class="text-center">${temp.intro_body}</td>  -->
			      </a>
			      <br>
			      <a href='#'>강의 일정</a>
			      <br> <br> <br>  <br>  <br>
			    </div>		
		     </c:forEach>
	   </c:if>		    
		  </div>	
	  <!-- 	페이지 이동 기능 -->
	  <table border="1" align="center" >
			<tr>
				<td align="center">
				<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
					<a href="../IntroRegManager/IntroList.do?nowPage=1">[처  음]</a>
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
						<a href="../IntroRegManager/IntroList.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
					 <a href="../IntroRegManager/IntroList.do?nowPage=${PINFO.totalPage}">[마지막]</a>
				</td>
			</tr>
	  </table>
<!-- 	기타 부가 기능 --> 
			</div>
		</div>
	</div>
	
</body>
</html>
