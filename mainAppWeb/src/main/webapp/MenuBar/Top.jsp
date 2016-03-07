<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<title>에듀 튜브</title>

<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/menustyles.css">
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">


<!--JS-->	    
 <script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 <script src="/edutube/resources/JS/bootstrap.min.js"></script>
 <script src="/edutube/resources/JS/Link.js"></script>
  
<!--스크립트-->
<script>

</script>

<!--  Style -->
<Style>
	@font-face { 
	    font-family:'NanumGothic';
	    src: url('/edutube/resources/fonts/NanumGothic.eot');
	    src: url('/edutube/resources/fonts/NanumGothic.eot?#iefix') format('embedded-opentype'),
	         url('/edutube/resources/fonts/NanumGothic.woff') format('woff');     
	    font-weight: normal;
	    font-style: normal;
	}

	html,body{
		width:100%;
		height:100%; 
	}
	#topcontainer{
	 		 width:1200px;	 	 	 
	}
	#topbar{
			width:1200px;
	}
	#logo{
			width:20%;
			float:left; 			
	}
	#searchcontainer{
			width:45%;			
			height:60px;					
			float:left;			
			padding-top:15px;
			
	}	 
	#utilBar {
			width:30%;
			height:60px;
			float:right;
			padding-top:20px;				
	}
	#cssmenu{
			clear:both
	}
	<%-- 안쪽의 div --%>
	#searchBar {
			width:70%;
			height:80%;
			padding-top:10px;
			margin:0 auto;				
	}
	#inBar  {
			font-family:나눔고딕, 'NanumGothic';
			font-size: 18px;
			font
			color:black;
			font-weight:plane;
			
			width:70%;
			height:80%;
			margin:0 auto;					
	}
	a.logC:link{
		text-decoration:none;
		color:black;
	}
	a.logC:visited{
		text-decoration:none;
		color:black;
	}
	a.logC:active{
		text-decoration:none;
		color:black;
	}
	a.logC:hover{
		text-decoration:none;
		color:black;
	}			
	
	#searchBox{
			border:3px solid #ff8000;
			outline-style:none;
			width:350px;
	}

	
</Style>
<!-- JOON drop down -->
<style>
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #FC0000; <!-- 배경색 -->
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}
</style>

<div id="topcontainer">

	<div id="topbar">
		<div id="logo">
		<a href="JavaScript:goHome()"><img id="toplogo" src="/edutube/resources/img/logo.png"/></a>
		</div>
		
		<div id="searchcontainer">
			<div id="searchBar" >
				<form id="searchFrm" name="searchFrm">
			    <input type="text" id="searchBox">
			    <a href="JavaScript:goSearch()"><img src="/edutube/resources/img/searchBtn.png"></a>
				</form>
			</div>
		</div>
		
		<div id="utilBar">
			<div id="inBar">
							
					<!-- 비로그인시 -->
					<div id="state_login">
					 	<a href="" class="logC">로그인</a>&nbsp;&nbsp;<a href="" class="logC">회원가입</a>
							<select id="lang" name="lang">
								<option value="0" disabled selected>Language</option>
								<option>Kor</option>
								<option>Eng</option>
								<option>Japan</option>
							</select>						
					</div>
					
			<!-- 		로그인시 (로그인 완성되면)				
					<div id="state_logout">
						 	<a href="" class="logC">로그아웃</a>&nbsp;&nbsp;<a href="" class="logC">마이페이지</a>						
					</div>	 -->		

			</div>
		</div>
	</div>

	<div id='cssmenu'>
		<ul>
		   <li><a href="JavaScript:goHomeIntro()"><span>에듀튜브 소개</span></a></li>
		   <li><a href="JavaScript:goNotice()"><span>공지 사항</span></a></li>
		   <li><a href="JavaScript:goLecIntro()"><span>강사 소개</span></a></li> 
			  <!-- <li class="dropdown">
				  <button class="dropbtn">강사 소개<span class="caret"></span></button>
				  <div class="dropdown-content">
				    <a href="JavaScript:goKor()">한국어</a>
				    <a href="JavaScript:goEng()">영어</a>
				    <a href="JavaScript:goJapna()">일본어</a>
			 </div>
				</li> -->
		   <li><a href="JavaScript:goClasses()"><span>강의 목록</span></a></li>
		   <li><a href="JavaScript:goDownload()"><span>자료실</span></a></li>
		   <li><a href="#"><span>FAQ/QA</span></a></li>		   
		   <li><a href="JavaScript:goCS()"><span>고객센터</span></a></li>	   
		   <li class='last'><a href="JavaScript:goTest()"><span>테스트</span></a></li>
		</ul>
	</div>

</div>

<script src="/edutube/resources/JS/classie.js"></script>
<script src="/edutube/resources/JS/selectFx.js"></script>
<script>
		(function() {
			[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
				new SelectFx(el);
			} );
		})();
</script>
