<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/sideBar.css">
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script src="/edutube/resources/JS/sideJS.js"></script>

<!--  여기부터 자신이 원하는 사이드 메뉴링크 걸기? -->
<script>
</script>

<div id='cssmenu2'>
<ul>
   <li><a href='#'>과목별 검색</a></li>
   <li class='active has-sub'><a href='#'>언어</a>
      <ul>
         <li class='has-sub'><a href="../ClassList/ClassList.do?code=langkor">한국어</a></li>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=langjap'>일본어</a></li>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=langchn'>중국어</a></li>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=langeng'>영어</a></li>
      </ul>
   </li>
   <li class='active has-sub'><a href='#'>프로그래밍</a>
      <ul>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=progjava'>자바</a></li>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=progclang'>C언어</a></li>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=progphp'>PHP</a></li>
      </ul>
   </li>
   <li class='active has-sub'><a href='#'>요리</a>
      <ul>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=cookkor'>한식</a></li>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=cookchn'>중식</a></li>
         <li class='has-sub'><a href='../ClassList/ClassList.do?code=cookjap'>일식</a></li>
       	 <li class='has-sub'><a href='../ClassList/ClassList.do?code=cookwest'>양식</a></li>
      </ul>
   </li>

</ul>
</div>
