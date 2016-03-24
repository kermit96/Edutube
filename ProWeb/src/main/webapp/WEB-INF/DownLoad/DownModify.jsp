<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
   type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
   type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script>
   $(document).ready(function() {
      $count = 0;
      $("#aBtn").click(function(){ 
         $html = "<tr id='fpos"+ ($count + 1) +"'>";
         $html += "<td>파일첨부</td>";
         $html += "<td><input type='file' name='upfile'></td>";
         $html += "</tr>";
         $("#fpos" + $count).after($html);
         $count = $count + 1;
      });
      // 글 수정해줘
      $("#mBtn").click(function() {
         $("#sfrm").attr("action", "../DownLoad/downModifyProc.do");
         $("#sfrm").submit();
      });
      // 글 취소할래
      $("#dBtn").click(function() {
         $("#sfrm").attr("action", "../DownLoad/downBack.do");
         $("#sfrm").submit();
      });
   });
</script>

<!--Never Delete "EduContainer" style tag-->
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
   width: 950px;
}

th, td {
   /* text-align: center; */
   padding: 8px;
   height : 24px;
}
tr:nth-child(even) {
   background-color: #f3f3f3
}

th {
   background-color: #f96e5b;
   color: white;
   width: 100px;
}

tr>th {
   text-align: center;
}

#rcorners2 {
   border: 0px solid white;
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
.text {
   border: none;
   text-align: left;
   width: 400px;
}
.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
.page {
   text-align : right;
   background-color: white;
}
 textarea#body{
      width:750px;
      height:400px;
      resize:none;
      border : none;
}
.line{
   border:none;
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
         <br><br>
         <form method="POST" id="sfrm">
         <table width="60%" border="1" align="center">
            <tr>
               <th>id</th>
               <td><input type="text" id="id"
                  value="${sessionScope.NICKNAME}" name="id" readonly="readonly" class="text"></td>
            </tr>
            <tr>
               <th>제목</th>
               <td><input type="text" id="title" name="title"
                  value="${DATA.title}" class="text"></td>
            </tr>
            <tr>
               <th>본문</th>
               <td><textarea id="body" name="body">${DATA.body}</textarea></td>
            </tr>
            <c:if test="${not empty FILE}">
               <c:forEach var="temp" items="${FILE}">
                  <!--    나중에 이 줄을 삭제할 목적으로 id값을 부여한다. -->
                  <tr id="sex${temp.no}">
                     <td colspan="2">${temp.oriname}<input
                              type="button" value="삭제"
                              OnClick="JavaScript:DeleteFile(${temp.no})">
                        </td>
                  </tr>
               </c:forEach>
            </c:if>
            <tr>
               <td align="center" class="line" colspan="2"><c:if test="${DATA.id eq sessionScope.ID}">
                     <input type="button" value="완료" id="mBtn" class="button">
                     <input type="button" value="취소" id="dBtn" class="button">
                  </c:if></td>
            </tr>
         </table>
            <input type="hidden" name="no" value="${ORINO}">
            <input type="hidden" name="nowPage" value="${NOWPAGE}">
         </form>
      </div>
   </div>
</body>
</html>