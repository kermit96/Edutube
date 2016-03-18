<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>EduTube</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->
 	<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
	<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	 <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="../resources/Picker/bootstrap-material-datetimepicker.css">
	<link rel="stylesheet" href="/edutube/resources/CSS/AdminLTE.min.css">
	
	<!--JS-->
	<script src="../resources/JS/jquery-2.1.4.min.js"></script>
	<script src="../resources/JS/bootstrap.min.js"></script>
	
	
	
		
	<!--CustomScript-->
	<script>
		function schAdd(){
			location.href="../Sch/SchAddForm.do?nowPage=${nowPage}&oriNo=${orino}&memid=${events.id}";
		}
		function gointroD(){
			location.href="/edutube/IntroRegManager/IntroView.do?nowPage=${nowPage}&oriNo=${orino}";
		}
		function delEvent(no){
			var eventNoo = no;			
			location.href="../Sch/SchDelete.do?nowPage=${nowPage}&oriNo=${orino}&eventNo="+eventNoo;
		}
	
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
			padding-top:50px;
			padding-bottom:100px;
			padding-right:100px;
			padding-left:50px;
			
			margin: 0 auto;			
		}	
		#clock{			
			height:200px;
			margin: 0 auto;
		}
		.eventD{
			width:700px;			
		}
				#eventTop{
			padding:10px;
		}
		
		
	tbody tr td a:link{
		text-decoration: none;
	}	
	tbody tr td a:hover{
		text-decoration: none;
	}	
	tbody tr td a:visited{
		text-decoration: none;
	}	
	#delB{
		width:100px;
	}
	</style>
	<!-- ${orino} ${nowPage} -->
	
</head>
<body>

<div id='EduContainer'>

	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>		
		
	<div id="main">
		<div id="sideBarDiv">
			<jsp:include page="/MenuBar/IntroSide.jsp" flush="false" />
		</div>
	
		<!-- This area is Body Part -->
		<div id="centerPage">
			<div id="realContent">
				<div id="eventTop" class="eventD">
						종료된 일정은 불러오지 않습니다 :)
				</div>
				<div id="eventForm" class="eventD">
					
					<table class="table table-bordered table-hover">

					<colgroup>
						<col style="width: 50px">
						<col style="width: 400px">
						<col style="width: 120px">
						<col style="width: 120px">
						<col style="width: 100px">
					</colgroup>

					<!-- 컬럼 -->
					<thead>
						<tr>
							<th>No</th>
							<th>일정</th>
							<th>시작날짜</th>
							<th>종료날짜</th>
							<th></th>							
						</tr>
					</thead>

					<!--  바디 -->
					<tbody>

						<!--  반복문 시작 -->
						<c:forEach var="events" items="${eventList}">
							<tr>
								<td>${events.no}</td>
								<td>
								<a href="../Sch/SchAddForm.do?nowPage=${nowPage}&oriNo=${orino}&evenNo=${events.no}"><span></span>${events.name}</a>
								</td>
								<td>${events.sdate1}</td>
								<td>	${events.edate1}</td>
								<td>								
								<button onClick="JavaScript:delEvent(${events.no});" id="delB">삭제하기</button>
								</td>														
							</tr>
						</c:forEach>
						<!--  반복문 종료 -->

					</tbody>
					<!--  필요한 기능 -->						
						<tr>
							<td colspan=6 align="center">
								<a class="button button-blue" onclick="JavaScript:schAdd();"><i class="fa fa-globe"></i>
								일정추가</a>&nbsp;
								<a class="button button-yellow" onclick="JavaScript:gointroD();">강사소개로</a>
							</td>
						</tr>										

				</table>
				</div>				
			</div>
		</div>		
	</div>
	 
</div>


</body>
</html>








