<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edu Tube</title>
	
	<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	<link rel="stylesheet" href="/edutube/resources/CSS/mypage.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<!--  검색바 -->
	
	<!--JS-->
	 <script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 	<script src="/edutube/resources/JS/bootstrap.min.js"></script>
 	
	<!--스크립트-->
	<script>
	
	/*회원정보수정*/
	function MchangeD(){
		location.href="../member/changeuserinfo.do";
	}
	
	/*회원신고*/	
	function MreportD(){
		location.href="../myPage/REPWriteForm.do";
	}
	
		
	/*강사신청*/	
	function LapplyD(){		
		location.href="../Apply/ApplyForm.do";
	}
		
	/*회원관리*/	
	function MemberMD (){
		 location.href="../myPage/REPList.do";	
	}
		
	/*강사승인*/	
	function LcogD(){
		location.href="../Apply/ApplyForm.do";		
	}
	

	
	</script>
	
	<!--  스타일 -->
	<style>
	@font-face {
	font-family: 'NanumGothic';
	src: url('/edutube/resources/fonts/NanumGothic.eot');
	src: url('/edutube/resources/fonts/NanumGothic.eot?#iefix')
		format('embedded-opentype'),
		url('/edutube/resources/fonts/NanumGothic.woff') format('woff');
	font-weight: normal;
	font-style: normal;
}
	
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
		#nalstate{
			width:800px;
			margin:0 auto;
			text-align:center;
			padding-top:50px;
			height:100px;
		}
		#hi{
			font-family: 나눔고딕, 'NanumGothic';
			font-size: 24px;
			font-color: black;
			font-weight: plane;
		}
		.container{
			width:1000px;
			margin-left:-400px;
			margin-top:50px;
			height:300px;			
		}
		.homeBox{
			width:1000px;			
			margin-left:150px;
			height:300px;		
		}
		.one_fourth{
			margin:0 auto;
		}
		#normalD{
			margin-left:180px;
		}
		#tempAdmin{
			width:600px;
			margin-left:180px;
		}
		
	</style>
	
</head>
<body>

<div id='EduContainer'>

	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
			
				<div id="nalstate">
					<c:if test="${sessionScope.NAL eq 'N'}">
						<p id="hi">일반 회원 페이지</p>
					</c:if>
					<c:if test="${sessionScope.NAL eq 'A'}">
						<p id="hi">관리자 마이 페이지</p>
					</c:if>
					<c:if test="${sessionScope.NAL eq 'L'}">
						<p id="hi">강사 마이 페이지</p>
					</c:if>
			</div>		
				

				
					
	
				<!--  일반 회원 (정보 수정, 신고, 강사 신청) -->
				<c:if test="${sessionScope.NAL eq 'N'}">
				<div class="container"> 	
				<div class="homeBox">
					<div class="one_fourth" id="MchangeD" onClick="JavaScript:MchangeD()">
						<div class = "boxImage"><img src = "/edutube/resources/img/change.png"></div>	
						<h2>Changing information</h2>			
						<div class = "boxDescription"></div>	
					</div>
		
					<div class="one_fourth" id="MreportD" onClick="JavaScript:MreportD()">
						<div class = "boxImage"><img src = "/edutube/resources/img/report.png"></div>	
						<h2>Member Report</h2>
						<div class = "boxDescription">Please report any manner without membership</div>				
					</div>
					
					<!-- 강사 신청 -->
					<div class="one_fourth" id="LapplyD" onClick="JavaScript:LapplyD()">
						<div class = "boxImage"><img src = "/edutube/resources/img/apply.png"></div>	
						<h2>Apply Lecturer</h2>
						<div class = "boxDescription"></div>				
					</div>
				</div>
				</div>
				</c:if>
			
				<!--  일반회원 아닌경우 (정보 수정, ) -->
				<c:if test="${sessionScope.NAL ne 'N'}">
				<div class="container">
				<div class="homeBox">
					<div id="normalD">
						<!-- 정보변경, 리폿 -->
						<div class="one_fourth" id="MchangeD" onClick="JavaScript:MchangeD()">
							<div class = "boxImage"><img src = "/edutube/resources/img/change.png"></div>	
							<h2>Changing information</h2>
							<div class = "boxDescription"></div>				
						</div>
		
						<div class="one_fourth last" id="MreportD" onClick="JavaScript:MreportD()">
							<div class = "boxImage"><img src = "/edutube/resources/img/report.png"></div>	
							<h2>Member Report</h2>
							<div class = "boxDescription">Please report any manner without membership</div>
						</div>
					</div>
					
					<c:if test="${sessionScope.NAL eq 'A'}">
					<!-- 회원 관리, 강사승인 -->
					<div id="tempAdmin">
						<div class="one_fourth last" id="MemberMD" onClick="JavaScript:MemberMD()">
							<div class = "boxImage"><img src = "/edutube/resources/img/member.png"></div>	
							<h2>Member Management</h2>
							<div class = "boxDescription"></div>
						</div>
						
						<div class="one_fourth last" id="LcogD" onClick="JavaScript:LcogD();">
							<div class = "boxImage"><img src = "/edutube/resources/img/recog.png"></div>	
							<h2>강사 승인</h2>
							<div class = "boxDescription"></div>
						</div>
					</div>
					</c:if>
				</div>
				</div>
				</c:if>
				<!--  박스 끝 -->
			
		</div>	<!-- main 끝 -->
	</div> <!--  EduTube 컨테이너 끝 -->
	 



</body>
</html>



