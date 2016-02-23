<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!--  필요한 유틸 설정 -->   
<jsp:include page="/MenuBar/Top.jsp" flush="false" />

<!--  CSS -->
<link rel="stylesheet" href="/MyCorp/Util/CSS/bootstrap.min.css">

<!--  JS  -->
<script src="/MyCorp/Util/JQuery/jquery-2.1.4.min.js"></script>
<script src="/MyCorp/Util/JS/bootstrap.min.js"></script>

<!--  스크립트 -->
	<script>
		function apply(){
			
			location.href = "../EmNotice/CandidateForm.cop?${data.no}";
		};
		
		function EmNotice(){  		
			location.href="/MyCorp/Recruit/EmNotice/EmNoticeList.cop?nowPage=${nowPage}";  		
	  	};
	  	
 	   /* 본문 불러오기 
	  	function getmycontent(){
	  		var text = (본문);
	  		var ifr = document.createElement("iframe");
	  	  	ifr.setAttribute("frameborder", "0");
	  	  	ifr.setAttribute("id", "iframeResult");
	  	  document.getElementById("rec_content").innerHTML = "";
	  	  document.getElementById("rec_content").appendChild(ifr);
	  	  var ifrw = (ifr.contentWindow) ? ifr.contentWindow : (ifr.contentDocument.document) ? ifr.contentDocument.document : ifr.contentDocument;
	  	  ifrw.document.open();
	  	  ifrw.document.write(text);  
	  	  ifrw.document.close();
	  	}	  	 */
 
	</script>
<!-- Style -->
	<style>
	@font-face {
    font-family:'NanumGothic';
    src: url('/MyCorp/Util/fonts/NanumGothic.eot');
    src: url('/MyCorp/Util/fonts/NanumGothic.eot?#iefix') format('embedded-opentype'),
         url('/MyCorp/Util/fonts/NanumGothic.woff') format('woff');     
    font-weight: normal;
    font-style: normal;
}
		div#detail{
			position: relative;
			top:20px;			
			width:800px;			
			margin :0 auto;
			text-align:left;
			height:auto;
		}
		#rec_content{
			width:800px;
			height:auto;			
		}
		p#con_no{
			font-family:나눔고딕,'NanumGothic';
			font-size: 14px;
			color:black;
		}
		p#con_title{
			font-family:나눔고딕, 'NanumGothic';
			font-size: 18px;
			color:black;
			font-weight:bold;
		}
		p#con_ect{
			font-family:나눔고딕, 'NanumGothic';
			font-size: 16px;
			color:black;
		}
		p#con_date{
			font-family:나눔고딕,'NanumGothic';
			font-size: 16px;
			color:black;
		}			
		table#detail_table{
			width:800px;
			margin :0 auto;
			text-align:left;
		}		
	</style>

<!-- Body -->
<body>

<div id="detail">
	<table class="table table-bordered" id="detail_table">
		<tbody>
			<tr>
				<td><p id="con_no">No : ${data.no}</p></td>
			</tr>
			<tr>
				<td><p id="con_title">${data.title}</p></td>
			</tr>
			<tr>
				<td><p  id="con_ect">지원분야 : ${data.group}</p></td>
			</tr>
			<tr>
				<td><p id="con_date"><span class="glyphicon glyphicon-calendar"></span>&nbsp;${data.rdate}&nbsp;<span class="glyphicon glyphicon-time"></span>
					<c:if test="${data.dday > 0 || data.dday eq 0}">
					 ${data.dday}일
					 </c:if>
					 <c:if test="${data.dday<0}">
					 모집종료
					 </c:if>
				 </p></td>				 
			</tr>
			
			<!--  필요한 기능 -->
			<tr>
				<td>
					<div class="control-group">
  					<label class="control-label" for="apply" onClick="JavaScript:apply();"></label>
  					<div class="controls">
    <button id="apply" name="apply" class="btn btn-success">지원하기</button>
    <button id="reclist" name="reclist" class="btn btn-primary" onClick="JavaScript:EmNotice();">목록보기</button>
 					 </div>
					</div>
				</td>
			</tr>
			
			<!--  에디터 쓴거 들어가는 부분 -->
			<tr>
				<td>
					<!-- <div class="iframe"> -->
						<div id="rec_content" >
							${data.body}
						</div>
				<!-- 	</div> -->
				</td>
			</tr>
		
		</tbody>
	</table>
</div>

<div	style="margin-top:100px;">
</div>

</body>



