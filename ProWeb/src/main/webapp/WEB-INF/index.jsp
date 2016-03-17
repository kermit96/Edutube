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
#centerPage {
   				float: right;
   				position: relative;
   				width: 980px;
   				height: 100%;
   				padding: 10px;
   				font-size: 16px;
}
#EduFooter{
				width:100%px;
				margin:20px auto;
				padding:20px;
				background-color:#DDD;
				text-align:right;
				clear:both;
		}
textarea#body {
	width: 200px;
	height: 200px;
	resize: none;
}
/* #title {
	float: left;
	padding-top: 5px;
	width: 475px;
}
 */
body { background-image: url("resources/img/bg.jpg");} 

.board { position:relative; display:inline-block; padding: 5px 100px 5px 100px;}

.notice { float:left; padding-top:5px; width:475px; }
.notice h3 {  clear:both; width:100%; display:inline-block; margin-bottom:10px; }
.notice h3 span {  float:left; }
.notice span.more {position:relative; left:425px; top:-155px;}
.notice .title { clear:both; width:100%; display:inline-block; color:#6b6b6b; line-height:24px; }
.notice .title dt { float:left; width:380px; }
.notice .title dt a { padding-left:10px;}
.notice .title dd { float:right; width:50px; font-size:11px; letter-spacing:-0.02em; }

.class { float:right; padding-top:5px; width:475px; }
.class h3 {  clear:both; width:100%; display:inline-block; margin-bottom:10px; }
.class h3 span {  float:left; }
.class span.more {position:relative; left:425px; top:-155px;}
.class .title { clear:both; width:100%; display:inline-block; color:#6b6b6b; line-height:24px; }
.class .title dt { float:left; width:380px; }
.class .title dt a { padding-left:10px;}
.class .title dd { float:right; width:50px; font-size:11px; letter-spacing:-0.02em; }

.qa { float:left; padding-top:5px; width:475px;}
.qa h3 {  clear:both; width:100%; display:inline-block; margin-bottom:10px; }
.qa h3 span {  float:left; }
.qa span.more {position:relative; left:425px; top:-155px;}
.qa .title { clear:both; width:100%; display:inline-block; color:#6b6b6b; line-height:24px; }
.qa .title dt { float:left; width:380px; }
.qa .title dt a { padding-left:10px;}
.qa .title dd { float:right; width:50px; font-size:11px; letter-spacing:-0.02em; }

.data { float:right; padding-top:5px; width:475px; }
.data h3 {  clear:both; width:100%; display:inline-block; margin-bottom:10px; }
.data h3 span {  float:left; }
.data span.more {position:relative; left:425px; top:-155px;}
.data .title { clear:both; width:100%; display:inline-block; color:#6b6b6b; line-height:24px; }
.data .title dt { float:left; width:380px; }
.data .title dt a { padding-left:10px;}
.data .title dd { float:right; width:50px; font-size:11px; letter-spacing:-0.02em; }

.rank { float:center; padding: 5px 20px 5px 20px; width:600px;}
.rank h3 {  clear:both; width:100%; display:inline-block; margin-bottom:10px; }
.rank h3 span {  float:left; }
.rank .title table { clear:both; padding-left:20%; width:100%; display:inline-block; line-height:15px; }
.rank .title tr { width:100%; }
.rank .title td { padding-left:10px;padding-top:10px; padding-right:30px; width:50px; font-size:13px; letter-spacing:-0.02em; text-align:center;}

</style>

</head>
<body>

	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

	<div id="centerPage">
	<br>
	
	<table width="100%" align="center">
		<tr>
			<td><img src="resources/img/mainback.png"></td>
		</tr>
	</table>

	<!-- 추천강사 -->
	<div class="rank">
	<h3><span><img src="resources/img/rank.jpg" alt="추천강사" /></span></h3>
		<div class="title">
		<table width="50%" align="center">
  			<tr>
    			<td><img src="resources/img/gg.jpg"></td>
    			<td><img src="resources/img/ss.jpg"></td>
    			<td><img src="resources/img/bb.jpg"></td>
  			</tr>
  			<tr>
    			<td><img src="resources/img/yu.jpg"></td>
    			<td><img src="resources/img/gu.jpg"></td>
    			<td><img src="resources/img/oh.jpg"></td>
  			</tr>
			  <tr>
			    <td>윤아입니당</td>
			    <td>구혜선을구혜선안되</td>
			    <td>천만의요정</td>
			  </tr>
			  <tr>
			    <td>20367</td>
			    <td>17623</td>
			    <td>13027</td>
			  </tr>
			</table>
		</div>
	</div>
		<br>
		</div>
	
	<div class="board">
	<!-- 공지사항 -->
	<div class="notice">
	<h3><span><img src="resources/img/notice.jpg" alt="공지사항" /></span></h3>
		<div class="title">
		<dl>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
		</dl>
		</div>
		<span class="more"><a href="/edutube/Notice/NoticeList.do"><img src="resources/img/more.jpg" alt="더보기(공지사항)" /></a></span>
	</div>
	
	<!-- 강의목록 -->
	<div class="class">
	<h3><span><img src="resources/img/class.jpg" alt="강의목록" /></span></h3>
		<div class="title">
		<dl>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
		</dl>
		</div>
		<span class="more"><a href="/edutube/ClassList/ClassList.do"><img src="resources/img/more.jpg" alt="더보기(강의게시물)" /></a></span>
	</div>
	
	<!-- 자료실 -->
	<div class="data">
	<h3><span><img src="resources/img/data.jpg" alt="자료실" /></span></h3>
		<div class="title">
		<dl>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
		</dl>
		</div>
		<span class="more"><a href="/edutube/DownLoad/DownMain.do"><img src="resources/img/more.jpg" alt="더보기(자료실)" /></a></span>
	</div>
	
	<!-- 질문게시판 -->
	<div class="qa">
	<h3><span><img src="resources/img/qa.jpg" alt="묻고답하기" /></span></h3>
		<div class="title">
		<dl>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
			<dt><a href="#">글제목</a></dt><dd>작성일</dd>
		</dl>
		</div>
		<span class="more"><a href="/edutube/CustomerBoard/QABoardList.do"><img src="resources/img/more.jpg" alt="더보기(묻고답하기)" /></a></span>
	</div>
	
	</div>
	
	
		
	<div id="EduFooter">
		<jsp:include page="/MenuBar/footer.jsp" flush="true"/>
	</div>
	</div>
</body>
</html>