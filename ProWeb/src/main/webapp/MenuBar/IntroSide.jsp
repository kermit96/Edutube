<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/sideBar.css">  
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>

<!-- 아래 sideJS.js 제거해서 IntroSide.jsp의 자바스크립트 실행됨 -->
<!-- <script src="/edutube/resources/JS/sideJS.js"></script> -->
<!--CSS-->

<!--  여기부터 자신이 원하는 사이드 메뉴링크 걸기? -->
<script>
</script>

<div id='cssmenu2'>
<ul>
   <li><a href='#'>과목별 검색</a></li>
   <li class='active has-sub'><a href="#">컴퓨터</a>
      <ul>
         <li class='has-sub'><a href="JavaScript:goJavaLang();">JAVA</a></li>
         <li class='has-sub'><a href="JavaScript:goCLang();">C언어</a></li>
         <li class='has-sub'><a href="JavaScript:goPhpLang();">PHP언어</a></li>
      </ul>
   </li>
   <li class='active has-sub'><a href="#">언어</a>
      <ul>
         <li class='has-sub'><a href="JavaScript:goKorLang()">한국어</a></li>
         <li class='has-sub'><a href="JavaScript:goEngLang()">영어</a></li>
         <li class='has-sub'><a href="JavaScript:goJapLang()">일본어</a></li>
         <li class='has-sub'><a href="JavaScript:goChnLang()">중국어</a></li>
      </ul>
   </li>
    <li class='active has-sub'><a href="#">요리</a>
      <ul>
         <li class='has-sub'><a href="JavaScript:goKorCook()">한식</a></li>
         <li class='has-sub'><a href="JavaScript:goChnCook()">중식</a></li>
         <li class='has-sub'><a href="JavaScript:goJapCook()">일식</a></li>
         <li class='has-sub'><a href="JavaScript:goWestCook()">양식</a></li>
      </ul>
   </li>
   <li><a href='#'>About</a></li>
   <li><a href='#'>Contact</a></li>
</ul>
</div>
