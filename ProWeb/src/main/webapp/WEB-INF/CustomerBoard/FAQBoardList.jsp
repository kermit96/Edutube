<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FAQ</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
   type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  ������ �ֱ� -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
   type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  �˻��� -->

<!--JS-->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script type="text/javascript"
   src="/edutube/resources/smarteditor/js/HuskyEZCreator.js"
   charset="utf-8"></script>
<script>
   function goDetail(orino) {
      $(location).attr("href",
            "../CustomerBoard/FAQBoardView.do?oriNo=" + orino);
   }
   $(document).ready(function(){
      $("#write").click(function(){
         location.href="../CustomerBoard/FAQBoardWriteForm.do";
      });
   });
</script>
<style>
body { background-image: url("../resources/img/bg.jpg");} 
#EduContainer {
   width: 1200px;
   position: absolute;
   left: 50%;
   margin-left: -600px;
}
table {
   border-collapse: collapse;
   width: 100%;
}

th, td {
   text-align: center;
   padding: 8px;
   height : 50px;
}

tr:nth-child(even) {
   background-color: #f3f3f3
}

th {
   background-color: #f96e5b;
   color: white;
   border: 1px solid white;
}

tr>th {
   text-align: center;
   border: 1px solid white;
}
#rcorners2 {
   border: 1px solid white;
   padding: 50px;
   width: 1000px;
}

.button {
   border-radius: 15px;
   background-color: #f96e5b;
   border: none;
   color: white;
   padding: 8px 16px;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   font-size: 14px;
   margin: 2px 1px;
   cursor: pointer;
}
.page {
   text-align : right;
   background-color: white;
}
</style>

</head>
<body>
   <div id="EduContainer">
      <div id='top'>
         <jsp:include page="/MenuBar/Top.jsp" flush="false" />
         
      </div>

      <div id="Main">
      <br>
      <h1>가입 방법 관련 FAQ</h1>
      <table width="80%" >
         <tr>
            <th width="10%">질문유형</th>
            <th width="40%">제목</th>
            <th width="50%">본문</th>
         </tr>
         <c:forEach var="temp" items="${LIST}">
            <c:if test="${temp.qno eq 1}">
               <tr>
                  <td>${temp.qname}</td>
                  <td>
                        <a href="JavaScript:goDetail(${temp.no})">
                      ${temp.title}</a></td>
                  <td>${temp.bodys}</td>
               </tr>
            </c:if>
         </c:forEach>
      </table>
      <br><br><br>
      <h1>사이트 이용관련 FAQ</h1>
      <table width="80%">
         <tr>
            <th width="10%">질문유형</th>
            <th width="40%">제목</th>
            <th width="50%">본문</th>
         </tr>
         <c:forEach var="temp" items="${LIST}">
            <c:if test="${temp.qno eq 2}">
               <tr>
                  <td>${temp.qname}</td>
                  <td><c:if test="${sessionScope.NAL eq 'A'}">
                        <a href="JavaScript:goDetail(${temp.no})">
                     </c:if> ${temp.title}</a></td>
                  <td>${temp.bodys}</td>
               </tr>
            </c:if>
         </c:forEach>
      </table>
         <br><br><br><h1><img src="../resources/img/동영상수강.png"></h1>
      <table width="80%">
         <tr>
            <th width="10%">질문유형</th>
            <th width="40%">제목</th>
            <th width="50%">본문</th>
         </tr>
         <c:forEach var="temp" items="${LIST}">
            <c:if test="${temp.qno eq 3}">
               <tr>
                  <td>${temp.qname}</td>
                  <td><c:if test="${sessionScope.NAL eq 'A'}">
                        <a href="JavaScript:goDetail(${temp.no})">
                     </c:if> ${temp.title}</a></td>
                  <td>${temp.bodys}</td>
               </tr>
            </c:if>
         </c:forEach>
      </table>
         <table>
            <tr>
               <c:if test="${NAL eq 'A'}">
               <td><input type="button" id="write" value="글쓰기" class="button"></td>
               </c:if>
            </tr>
         </table>
      </div>
   </div>
</body>
</html>