<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  관리자 판별 -->
<c:set var="isAdmin"  value="false" />
	<c:if test="${sessionScope.NAL eq 'A' }">
		<c:set var="isAdmin" value="true" />
	</c:if>

<!DOCTYPE html>
<title>에듀 튜브</title>

<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/menustyles.css">
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">


<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script src="/edutube/resources/JS/Link.js"></script>



<!--  Style -->
<Style>
@font-face {
	font-family: 'NanumGothic';
	src: url('/edutube/resources/fonts/NanumGothic.eot');
	src: url('/edutube/resources/fonts/NanumGothic.eot?#iefix')
		format('embedded-opentype'),
		url('/edutube/resources/fonts/NanumGothic.woff') format('woff');
	font-weight: normal;
	font-style: normal;
}

html, body {
	width: 100%;
	height: 100%;
}



#topcontainer {
	width: 1200px;
}

#topbar {
	width: 1200px;
}

#logo {
	width: 20%;
	float: left;
}

#searchcontainer {
	width: 45%;
	height: 60px;
	float: left;
	padding-top: 15px;
}

#utilBar {
	width: 30%;
	height: 60px;
	float: right;
	padding-top: 20px;
}

#cssmenu {
	clear: both
}

<%--안쪽의 div --%> 
#searchBar {
	width: 70%;
	height: 80%;
	padding-top: 10px;
	margin: 0 auto;
}

#inBar {
	font-family: 나눔고딕, 'NanumGothic';
	font-size: 18px;
	font color: black;
	font-weight: plane;
	width: 80%;
	height: 80%;
	margin: 0 auto; 
}

a.logC:link {
	text-decoration: none;
	color: black;
}

a.logC:visited {
	text-decoration: none;
	color: black;
}

a.logC:active {
	text-decoration: none;
	color: black;
}

a.logC:hover {
	text-decoration: none;
	color: black;
}

#searchBox {
	border: 3px solid #ff8000;
	outline-style: none;
	width: 350px;
} 
</Style>

<div id="topcontainer">

	<div id="topbar">
		<div id="logo">
			<a href="JavaScript:goHome()"><img id="toplogo"
				src="/edutube/resources/img/logo.png" /></a>
		</div>

		<div id="searchcontainer">
			<div id="searchBar">
				<form method="POST" id="searchFrm" name="searchFrm"   onKeyDown="JavaScript:SearchOnKey();">
					<input type="text" id="searchBox" name="searchBox"> <a
						href="JavaScript:goSearch()"><img
						src="/edutube/resources/img/searchBtn.png"></a>
				</form>
			</div>
		</div>

		<div id="utilBar">
			<div id="inBar">

				<!-- 비로그인시 -->
				<c:if test="${sessionScope.ID eq null}">

					<div id="state_login">
						<a href="JavaScript:goLogin();" class="logC">로그인</a>&nbsp;&nbsp;<a href="JavaScript:memberJoin()" class="logC">회원가입</a>
						
						<select id="lang" name="lang" onchange="goLanguage()">
							<option value="ko"> 한국어</option>
							<option value="en">English</option>
							<option value="ja">日本語</option>
							<option value="ch">中國(繁體)</option>
							<option value="ch_en">中国()简体)</option>
						</select>
						
					</div>
				</c:if>

				<!--  로그인 시 -->
				<c:if test="${sessionScope.ID ne null}">

					<div id="state_logout">
						<a href="JavaScript:goLogout()" class="logC">로그아웃</a>&nbsp;&nbsp;<a href="JavaScript:goInfo()"
							class="logC">마이페이지</a>
							<select id="lang" name="lang" onchange="goLanguage()">
							<option value="ko"> 한국어</option>
							<option value="en">English</option>
							<option value="ja">日本語</option>
							<option value="ch">中國(繁體)</option>
							<option value="ch_en">中国()简体)</option>
						</select>
					</div>
					</c:if>	

			</div>
		</div>
	</div>

	<div id='cssmenu'>
		<ul>
			<li><a href="JavaScript:goHomeIntro()"><span>에듀튜브 소개</span></a></li>
			<li><a href="JavaScript:goNotice()"><span>공지 사항</span></a></li>
			<li><a href="JavaScript:goLecIntro()"><span>강사 소개</span></a></li>
			<li><a href="JavaScript:goClasses()"><span>강의 목록</span></a></li>
			<li><a href="JavaScript:goDownload()"><span>자료실</span></a></li>
			<li><a href="JavaScript:goFAQ()"><span>FAQ</span></a></li>
			<li><a href="JavaScript:goQA()"><span>QA</span></a></li>			
			<li class='last'><a href="JavaScript:goTest()"><span>테스트</span></a></li>
			<c:if test="${isAdmin==true}">
 				<li class='last'><a href="/edutube/AdminPage/AdminMain.do"><span>관리자</span></a></li>	
			</c:if>
		</ul>
	</div>



<script src="/edutube/resources/JS/classie.js"></script>
<script src="/edutube/resources/JS/selectFx.js"></script>
<script>
	(function() {
		[].slice.call(document.querySelectorAll('select.cs-select')).forEach(
				function(el) {
					new SelectFx(el);
				});
	})();
</script>


<!--스크립트-->
<script>
	/* 엔터키 눌렀을때도 실행하게 */
	function SearchOnKey()
	{
	     if(event.keyCode == 13)
	     {	          
	          goSearch(); 
	     }	     
	}	
	/*검색*/
	function goSearch(){			
			
			$searchWord=$("#searchBox").val();
			$searchWord=$searchWord.replace(/^\s+|\s+$/g,"");

			if($searchWord==""){
				alert('검색어를 입력해주세욜');
				return;
			}			
			$("#searchFrm").attr("action", "/edutube/Search/SearchForm.do");
			$("#searchFrm").submit();
			
	}
	
	
	var lang = "${sessionScope.LANG}";
	
	$("#lang").val(lang);
	
	
	function goLanguage()
	{		
		var lang = $("#lang").val();
        try {
            $.ajax({
                url:"/edutube/lang/changelang.do",
                async:false,
                type:'post',
                dataType:'html',
                cache:false,
                data:{lang:lang},
                success:function(data){
                	// 재로딩
                	location.href=location.href;         	              
                },
                error:function(request,status,error){
                     alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                   }
                	                                    
            });
    		
            } catch (ex) {        	
            	alert("ajax="+ex);
            }
		    
	}

	
</script>

