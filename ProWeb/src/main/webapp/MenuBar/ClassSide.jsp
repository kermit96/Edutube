<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
   <li><a href='/edutube/ClassList/ClassList.do'>${LANGDATA.side_all}</a></li>
   <li class='active has-sub'><a href='#'>${LANGDATA.side_lang}</a>
      <ul>
         <li><a href="../ClassList/ClassList.do?code=langkor">${LANGDATA.side_kor}</a></li>
         <li><a href='../ClassList/ClassList.do?code=langjap'>${LANGDATA.side_jap}</a></li>
         <li><a href='../ClassList/ClassList.do?code=langchn'>${LANGDATA.side_chn}</a></li>
         <li><a href='../ClassList/ClassList.do?code=langeng'>${LANGDATA.side_eng}</a></li>
      </ul>
   </li>
   <li class='active has-sub'><a href='#'>${LANGDATA.side_prog}</a>
      <ul>
         <li><a href='../ClassList/ClassList.do?code=progjava'>${LANGDATA.side_java}</a></li>
         <li><a href='../ClassList/ClassList.do?code=progclang'>${LANGDATA.side_clang}</a></li>
         <li><a href='../ClassList/ClassList.do?code=progphp'>${LANGDATA.side_php}</a></li>
      </ul>
   </li>
   <li class='active has-sub'><a href='#'>${LANGDATA.side_cook}</a>
      <ul>
         <li><a href='../ClassList/ClassList.do?code=cookkor'>${LANGDATA.side_ckor}</a></li>
         <li><a href='../ClassList/ClassList.do?code=cookchn'>${LANGDATA.side_cchn}</a></li>
         <li><a href='../ClassList/ClassList.do?code=cookjap'>${LANGDATA.side_cjap}</a></li>
       	 <li><a href='../ClassList/ClassList.do?code=cookwest'>${LANGDATA.side_cwest}</a></li>
      </ul>
   </li>

</ul>
</div>
