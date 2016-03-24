<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
</head>
<style>
#EduContainer {
   width: 1200px;
   position: absolute;
   left: 50%;
   margin-left: -600px;
}
body { background-image: url("../resources/img/bg.jpg");}

table {
   border-collapse: collapse;
   width: 950px;
}

th, td {
   /* text-align: center; */
   padding: 8px;
   height: 24px;
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

.button:hover {
   background-color: #3e8e41
}

.button:active {
   background-color: #3e8e41;
   box-shadow: 0 5px #666;
   transform: translateY(4px);
}

.page {
   text-align: right;
   background-color: white;
}

textarea#body {
   width: 750px;
   height: 400px;
   resize: none;
   border: none;
}

.line {
   border: none;
}
</style>
<body>
   <div id='EduContainer'>

      <div id='top'>
         <jsp:include page="/MenuBar/Top.jsp" flush="false" />
      </div>

      <div id="Main">
         <br>
         <br>
         <form method="POST" id="wfrm" name="wfrm"
            enctype="multipart/form-data">
            <table width="70%" border="1" align="center">
               <tr>
                  <th>id</th>
                  <td colspan="2"><input type="text" id="id"
                     value="${sessionScope.NICKNAME}" name="id" readonly="readonly" class="text"></td>
               </tr>
               <tr>
                  <th>제목</th>
                  <td colspan="2"><input type="text" id="title" name="title" class="text"></td>
               </tr>
               <tr>
                  <th>본문</th>
                  <td colspan="2"><textarea id="body" name="body" class="text"></textarea></td>
               </tr>
               <tr id="upload">
                  <th>첨부파일</th>
                  <td><input type="file" name="upfile" id="upfile"></td>
                  <td width="10.25%"><input type="button" id="aBtn" value="추가"
                     class="button"> <input type="button" id="dBtn" value="삭제"
                     class="button"></td>
               </tr>
               <tr>
                  <td colspan="3" align="center" class="line"><input type="button"
                     value="글쓰기" id="write" class="button"></td>
               </tr>
            </table>
         </form>
      </div>
   </div>
</body>
<script>
   $(document)
         .ready(
               function() {
                  $count = 0; //   지금까지 존재하는 첨부 파일의 개수를 지정할 변수
                  $("#dBtn").click(function() {
                     //   항상 한개는 남겨두어야 하므로
                     if ($count == 0) {
                        return;
                     }
                     //   삭제는 항상 마지막에 만든 순서대로 지우도록 약속하자.
                     $("#upload" + $count).remove();
                     //   삭제가 끝났으면 첨부파일의 개수가 줄었으므로
                     $count = $count - 1;
                  });
                  $("#aBtn")
                        .click(
                              function() {
                                 //   할일    추가할 <tr>을 만든다.
                                 $count = $count + 1;
                                 $html = "<tr id='upload"+$count+"'>";
                                 $html += "<td>첨부파일</td>";
                                 $html += "<td colspan='2'><input type='file' id='up"+ $count +"' name='upfile'></td>";
                                 $html += "</tr>";

                                 $("#upload").after($html);
                              });
                  $("#write").click(
                        function() {
                           $title = $("#title").val();
                           if ($title == "") {
                              alert("제목을 입력해 주세요.");
                              return;
                           }
                           $body = $("#body").val();
                           if ($body == "") {
                              alert("본문을 입력해 주세요.");
                              return;
                           }
                           var body = $("#body").val();
                           $frm = $("#wfrm");
                           $frm.attr("action",
                                 "../DownLoad/DownWriteProc.do");
                           $frm.submit();
                        });
               });
</script>

</html>