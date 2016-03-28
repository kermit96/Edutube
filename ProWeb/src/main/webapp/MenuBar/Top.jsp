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
<script src="/edutube/resources/JS/MessageScript.js"></script>
<script language="javascript">
var id = "${sessionScope.ID}";
$(document).ready(function(){
	setInterval(msg,7000); 
});
function msg(){
	if(id==null|| id==""){
			
	}
		
	else if(id != null){
		window.open("/edutube/Message/isMsg.do?id="+id,"hiddenframe","width=20, height=20, resizable=no, toolbar=no, location=no,scrollbars=yes");
		/* location.href="/edutube/Message/isMsg.do?id="+id; */
//		 200, 260
	}
//	setTimeout(msg, 7000);
}
</script>


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
#idstate{
	width:200px;
	height:20px;
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
					<a href="JavaScript:goLogin();" class="logC">${LANGDATA.top_login}</a>&nbsp;&nbsp;<a href="JavaScript:memberJoin()" class="logC">${LANGDATA.top_member}</a>						
						<select id="lang" name="lang" onchange="goLanguage()">
							<option value="ko"> 한국어</option>
							<option value="en">English</option>
							<option value="ja">日本語</option>
							<option value="ch">中國(繁體)</option>
							<option value="ch_cn">中国(简体)</option>
						</select>
					</div>
				</c:if>

				<!--  로그인 시 -->
				<c:if test="${sessionScope.ID ne null}">

					<div id="state_logout">
						<a href="JavaScript:goLogout()" class="logC">${LANGDATA.top_logout}</a>&nbsp;&nbsp;<a href="JavaScript:goInfo()"
							class="logC">${LANGDATA.top_mypage}</a>
							<select id="lang" name="lang" onchange="goLanguage()">
							<option value="ko">한국어</option>
							<option value="en">English</option>
							<option value="ja">日本語</option>
							<option value="ch">中國(繁體)</option>
							<option value="ch_cn">中国(简体)</option>
						</select>
					</div>
					</c:if>	

			</div>
			<c:if test="${sessionScope.ID ne null}">
			<div id="idstate">
				${sessionScope.NICKNAME} 님 환영합니다.
			</div>
			</c:if>
		</div>
	</div>

	<div id='cssmenu'>
		<ul>		
			
			<li><a href="JavaScript:goHomeIntro()"><span>${LANGDATA.top_intro}</span></a></li>
			<li><a href="JavaScript:goNotice()"><span>${LANGDATA.top_notify}</span></a></li>
			<li><a href="JavaScript:goLecIntro()"><span>${LANGDATA.top_instructor}</span></a></li>
			<li><a href="JavaScript:goClasses()"><span>${LANGDATA.top_instructor_dir}</span></a></li>
			<li><a href="JavaScript:goDownload()"><span>${LANGDATA.top_file}</span></a></li>
			<li><a href="JavaScript:goFAQ()"><span>FAQ</span></a></li>
			<li class='last'><a href="JavaScript:goQA()"><span>QA</span></a></li>
		</ul>
	</div>

</div><!-- 이게 들어가야 03/15 노란색줄 수정 된거 -->

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
				//	alert('검색어를 입력해주세욜');
				alert('${LANGDATA.top_search}');	
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

<iframe width='1' height='1' name='hiddenframe' style="display:none"></iframe>
