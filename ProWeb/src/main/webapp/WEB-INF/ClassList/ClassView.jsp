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
<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

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
	function moContent(){
		location.href="../ClassList/ClassModifyForm.do?code=${CODE}&nowPage=${nowPage}&oriNo=${DATA.no}";
	}
	function delContent(){
		location.href="../ClassList/ClassDelete.do?nowPage=${nowPage}&code=${CODE}&oriNo=${DATA.no}";
	}
	function goClassList(){
		location.href="../ClassList/ClassList.do?nowPage=${nowPage}&code=${CODE}";
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
	margin-bottom:100px;
}

#videoM{
	width:800px;
	height:100%;
	margin:0 auto;	
}

#ContentTop{
	width:800px;
	height:100%;
	margin:0 auto;
}
#bodycontent{
	width:800px;
	min-height:300px;
	margin:0 auto;
}
#goodUtil{
	width:800px;
	height:100%;
	margin:0 auto;
}
#utilBar00{
	width:800px;
	height:100%;
	margin:0 auto;
}
#titleC{
	border-top:2px solid purple;
	border-bottom:2px solid purple;
	width:800px;
	height:100%;
	margin:0 auto;
}
p#title{
	font-family: 나눔고딕, 'NanumGothic';
	font-size: 24px;
	font color: black;
	font-weight: plane;
}
#utilT{
	border-bottom:1px solid black;
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
					      	<iframe width="600" height="300" src="https://www.youtube.com/embed/${mList.realURL}?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>  					
     					 </c:forEach>	
     					 <p>&nbsp;</p>
					</div>					

					<div id="titleC">
								<p id="title">[${DATA.code}]${DATA.title}</p>							
					</div>		
					<div id="ContentTop">
						<table id="utilT">
							<tr>
								<td	colspan="2" style="padding:20px">강사 : <a href="#">${DATA.nick}</a></td>								
							</tr>
							<tr>
								<td style="padding:20px">
										작성일 : ${DATA.realdate}
								</td>
								<td style="padding:20px">
										조회수 : ${DATA.hit}
								</td>
							</tr>
						</table>					
					</div><!--  realcontent1 끝 -->
					<div id="bodycontent">
						${DATA.body}
					</div>
					<div id="goodUtil">
							추천 : ${DATA.good}
					</div>
					<div id="utilBar00">
						<c:if test="${DATA.id eq sessionScope.ID}">
						<a class="button button-yellow" onClick="JavaScript:moContent();"><i class="fa fa-clock-o"></i>수정하기</a>
						<a class="button button-red" onClick="JavaScript:delContent();"><i class="fa fa-times"></i>삭제하기</a>
						</c:if>						
						<a class="button button-orange" onClick="JavaScript:goClassList();" id="ListBtn" >목록으로</a>	
					</div>				

				</div><!--  viewmain 끝 -->
			</div><!--  실제로 들어갈 내용 -->
			
		</div><!-- 메뉴바 밑 감싸기 -->

	</div><!-- 전체 감싸기 -->
					 


</body>
</html>