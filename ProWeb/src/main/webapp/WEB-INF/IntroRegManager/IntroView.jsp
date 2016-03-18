<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>강사 소개</title>
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
	<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<link rel="stylesheet" href="/edutube/resources/CSS/monthly.css">

	<!--JS-->
	<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
	<script src="/edutube/resources/JS/bootstrap.min.js"></script>
	<script type="text/javascript"	src="/edutube/resources/JS/monthly.js"></script>
	
	<!--CustomScript-->
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
			$(document).ready(function(){
				$("#modiSCH").click(function(){
					
					$(location).attr("href","../Sch/SchModiForm.do?mid=${DATA.mem_id}");
					
				}); 
			});
	</script>
		
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
		#realContent{
			width:700px;
			margin: 0 auto;			
		}
		/* JOON CSS */

		a{
			text-decoration:none; /* 링크 밑줄 없애기 */
			color:black; /*글 색상*/
		}
		a:HOVER {
			text-decoration:underline; /* 밑줄 
			color:green;			/*글 색상*/
		}
	
		#imgD{
			float:left;
			width:200px;
		}
		#nickD{
			width:100px;
		}
		#topWrapper{
			width:700px;
			margin:0 auto;
		}
		#titleD{
			clear:both;
			width:700px;
			border-top:2px solid silver;
			border-bottom:2px solid silver;
			padding:20px;
		}
		#bodyD{
			width:700px;
			height:500px;
			padding:20px;
		}
		#schD{
			width:700px;
		}
		#temp{
			margin-top:50px;
		}

.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:20px 10px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:20px 10px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
.tg .tg-i7wz{font-size:20px}
.tg .tg-yw4l{vertical-align:top}

		</style>

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
		<div id="realContent">
			<div id="topWrapper">
				<div id="imgD">
					
				<table class="tg" style="undefined;table-layout: fixed; width: 407px">
				<colgroup>
				<col style="width: 194px">
				<col style="width: 213px">
				</colgroup>
				  <tr>
				    <th class="tg-i7wz"><img src="../gimgs/${DATA.gimg2}" width="200" height="200" ></th>
				    <th class="tg-031e">닉네임 : ${DATA.mem_nick}</th>
				  </tr>
				
				</table>														
				</div>				
			</div>
		
			<div style="width: 100%; max-width: 600px; display: inline-block;">
					<div class="monthly" id="mycalendar"></div>
				</div>
				<script type="text/javascript"
					src="/edutube/resources/JS/monthly.js"></script>
				<script>
					$(window)
							.load(
									function() {

										$('#mycalendar').monthly({
											mode : 'event',
											xmlUrl : '/edutube/Sch/loadEvents.do?lecMid=${ID}'
										});

								 		switch (window.location.protocol) {
										case 'http:':
										case 'https:':
											// running on a server, should be good.
											break;
										case 'file:':
											alert('Just a heads-up, events will not work when run locally.');
										} 

									});
				</script>
				
			<table align="center">
				<tr id="tr_top">
					<td align="center">					
						<c:if test="${sessionScope.ID eq DATA.mem_id}">
							<a class="button button-blue" id = "modiSCH" ><i class="fa fa-globe"></i>일정 수정하기</a>
							<input type="hidden" value="${DATA.mem_id}"	id="mid" name="mid">						
						</c:if>	
					</td>
				</tr>
			</table>		
				
			<div id="titleD">
				${DATA.intro_title}
			</div>
			<div id="bodyD">
				${DATA.realbody}
			</div>
			
			
			
			<table align="center">
				<tr id="tr_top">
					<td align="center">					
					<c:if test="${sessionScope.ID eq DATA.mem_id}">
						<input type="button" value="수정하기" id="mBtn" class="btn btn-primary btn-sm">
						<input type="button" value="삭제하기" id="dBtn" class="btn btn-primary btn-sm">
					</c:if>	
						<input type="button" value="목록보기" id="lBtn" class="btn btn-primary btn-sm">
										
					</td>
				</tr>
			</table>	
			
			<div id="temp">
			&nbsp;
			</div>
		
		</div>

		</div>
	</div>		 
 </div>
</body>
</html>
