<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/MenuBar/Top.jsp" flush="false" />

<!-- CSS -->
<link rel="stylesheet" href="/MyCorp/Util/CSS/bootstrap.min.css">
<link rel="stylesheet" href="/MyCorp/Util/Picker/bootstrap-material-datetimepicker.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<!--  JS -->
<script src="/MyCorp/Util/JQuery/jquery-2.1.4.min.js"></script>
<script src="/MyCorp/Util/JS/bootstrap.min.js"></script>
<script src="/MyCorp/Util/Picker/moment-with-locales.min.js"></script>
<script src="/MyCorp/Util/Picker/bootstrap-material-datetimepicker.js"></script>
<!-- 스마트에디터 JS -->
<script type="text/javascript" src="/MyCorp/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>


<script>
$(document).ready(function() {
	/* 날짜 스크립트 준비 */
	$('#wdate').bootstrapMaterialDatePicker ({
		time: false		
	});
	
});
</script>

<style> 
	div#mainForm{
		width:750px;
		height:600px;
		margin-left:auto;
		margin-right:auto;
	}
	
	textarea#ir1{
		width:700px;
		height:300px;
		resize:none;
	}
	input#title{
		width:300px;
	}
</style>		

		
<!--  비정상 접근 처리 -->  
<c:if test="${sessionScope.ADMIN ne 'Y'}">
 	<c:redirect url="/LoginForm.jsp" />
</c:if>
		
<p>&nbsp;</p>	

<div id="mainForm">
<!--  글쓰기 폼 -->	

<form class="form-horizontal"  method="POST" id="frm" name="frm">
<fieldset>
<input id="writer" name="writer" value="${sessionScope.ID}" type="hidden" />

<!-- Form Name -->
<legend>공고 작성</legend>

<!-- Select Basic -->
<div class="control-group">
  <label class="control-label" for="depG">부서그룹선택</label>
  <div class="controls">
    <select id="depG" name="depG" class="input-large" required>
      <option value="">선택하세요</option>
      <c:forEach var="group" items="${Glist}">
      <option>${group}</option>
      </c:forEach>
    </select>
  </div>
</div>

<!--  접수 날짜  -->
<div class="control-group">
<label class="control-label" for="title">모집 종료 날짜</label>
	<div class="controls">			
		<input id="wdate"  name="wdate" type="text"  class="input-medium" placeholder="Date" readonly required>	
	</div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="title">제목</label>
  <div class="controls">
    <input id="title" name="title" type="text" placeholder="제목 입력" class="input-xlarge" required>    
  </div>
</div>

<!-- Textarea -->
<div class="control-group">
  <label class="control-label" for="body">본문</label>
  <div class="controls">                     
    <textarea id="ir1" name="ir1" required></textarea>
  </div>
</div>

<!-- Button (Double) -->
<div class="control-group">
  <label class="control-label" for="wBtn"></label>
  <div class="controls">
    <button id="save" name="save" class="btn btn-primary">공고올리기</button>
    <button id="rBtn" name="rBtn" class="btn btn-info">다시쓰기</button>  	
  </div>
</div>

</fieldset>

</form>
</div>

<script>
 var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "ir1",
	    sSkinURI: "/MyCorp/smarteditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	}); 
	
	$(document).ready(function(){
		
		$("#save").click(function(){
			
			$title =$("#title").val();
			if($title==""){
				alertify.alert("제목을 입력해 주세요");
				return;
			}
			
			$group =$("#depG").val();
			if($group==0){
				alertify.alert("그룹을 선택해 주세요");
				return;
			}
			
			$body =$("#body").val();
			if($body==""){
				alertify.alert("본문을 입력해 주세요");
				return;
			}
			
			$wdate =$("#wdate").val();
			if($wdate==""){
				alertify.alert("날짜를 선택해 주세요");
				return;
			}
			
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			
			$("#frm").attr("action", "/MyCorp/Recruit/EmNotice/EmNoticeWrite.cop?nowPage=${nowPage}");
			$("#frm").submit();
		});
	});
</script>
