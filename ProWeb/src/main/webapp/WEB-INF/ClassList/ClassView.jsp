<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
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
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

#main {
	width: 1200px;
}

#centerPage {
	float: right;
	position: relative;
	width: 980px;
	height: 100%;
	padding: 10px;
	font-size: 16px;
}

#sideBarDiv {
	float: left;
	border-top: solid 1px white;
	clear: both;
	width: 150px;
}

</style>
</head>
<body>
	<div id='EduContainer'>
		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
			<!-- This area is Body Part -->
			<div id="sideBarDiv">
				<jsp:include page="/MenuBar/ClassSide.jsp" flush="false" />
			</div> <!--  사이드바 -->

			<div id="centerPage">
				<div id="viewMain">
					<div class="embed-responsive embed-responsive-16by9">
					      <c:forEach var="mList" items="${mList}">					      	
					      	<iframe class="embed-responsive-item" width="640" height="360" src="${mList.mediaURL}"  frameborder="0" allowfullscreen></iframe>     					
     					 </c:forEach>			
					</div>
					                    
                        	
				
				</div>
				   ${DATA.id}
      ${DATA.nick}
      ${DATA.realdate}
      ${DATA.title}
      ${DATA.body}
      ${DATA.hit}
      ${DATA.good}
      ${DATA.code}
      ${DATA.lang}      
  
			</div><!--  실제로 들어갈 내용 -->
			
		</div><!-- 메뉴바 밑 감싸기 -->

	</div><!-- 전체 감싸기 -->
					 


</body>
</html>