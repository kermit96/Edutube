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
@font-face {
	font-family: 'NanumGothic';
	src: url('/edutube/resources/fonts/NanumGothic.eot');
	src: url('/edutube/resources/fonts/NanumGothic.eot?#iefix')
		format('embedded-opentype'),
		url('/edutube/resources/fonts/NanumGothic.woff') format('woff');
	font-weight: normal;
	font-style: normal;
}

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
#viewMain{
	width:800px;
	margin:0 auto;	
}

#videoM{
	width:600px;
	height:100%;
	margin:0 auto;
}

#realContent{
	width:600px;
	height:100%;
	margin:0 auto;
	border:1px solid black;
}
p#title{
	font-family: 나눔고딕, 'NanumGothic';
	font-size: 18px;
	font color: black;
	font-weight: plane;
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
					<div class="videoM">
					      <c:forEach var="mList" items="${mList}">					      	
					      	<iframe width="600" height="300" src="https://www.youtube.com/embed/${mList.mediaURL}?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>  					
     					 </c:forEach>	
     					 <p>글번호&nbsp;:&nbsp;${DATA.id}&nbsp;|&nbsp;강사&nbsp;:&nbsp;${DATA.nick}</p>
					</div>
					
					<div id="realContent">
						<table>
							<tr>
								<td>
									<p id="title">[${DATA.code}]${DATA.title}&nbsp;|&nbsp;${DATA.realdate}</p>
								</td>
							</tr>
							<tr>
								<td>
										유틸바${DATA.good}${DATA.hit}
								</td>
							</tr>
							<tr>
								<td>
									${DATA.body}
								</td>
							</tr>
							${DATA.code}
						
						</table>					
					</div>
					                    
                        	
				
				</div>
				   
      
     
      
      
  
      
           
  
			</div><!--  실제로 들어갈 내용 -->
			
		</div><!-- 메뉴바 밑 감싸기 -->

	</div><!-- 전체 감싸기 -->
					 


</body>
</html>