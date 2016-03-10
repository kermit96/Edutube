<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script type="text/javascript" src="/edutube/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<!--스크립트-->
<script>
	function goClassList(){
		location.href="../ClassList/ClassList.do?nowPage=${nowPage}&code=${CODE}";
	}
	

	$(document).ready(	function() {

			$("#save").click(function() {

					$title = $("#title").val();
						if ($title == "") {
							alert("제목을 입력해 주세요");
							return;
					}

					$subcode = $("#subcode").val();
					if ($subcode == 0) {
							alert("과목을 선택해 주세요");
						return;
					}

					$body = $("#body").val();
					if ($body == "") {
							alert("본문을 입력해 주세요");
						return;
					}					

					oEditors.getById["body"].exec(
									"UPDATE_CONTENTS_FIELD",[]);

					$("#classForm").attr("action",
									"../ClassList/ClassWrite.do?nowPage=${nowPage}&code=${CODE}");
					$("#classForm").submit();
				});
	});
</script>

<!--  스타일 -->
<style>
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

#main {
	width: 1200px;
}

#centerPage {
	float: right;
	position: relative;
	width: 980px;
	height: 100%;
	padding: 10px;
	font-size: 16px;
}

#sideBarDiv {
	float: left;
	border-top: solid 1px white;
	clear: both;
	width: 150px;
}
div#formMain{
	width:800px;
	height:600px;
	margin-left: auto;
	margin-right: auto;
}
	
	textarea#body{
		width:750px;
		height:400px;
		resize:none;
	}

div#writeUtilD{
	width:800px;	
	margin-left: auto;
	margin-right: auto;
}

div#formTop {
	width:800px;
	margin-top:50px;
	margin-bottom:20px;
}
div#selectD{
	float:left;
	width:150px;
}
div#mediaDIV{
	width:800px;
	margin-top:20px;
}
p#mediaURL{
	float:left;
	width:150px;
}
input#mediaURL{
	width:500px;
}

input#title{
	width:500px;
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
			<div id="sideBarDiv">
				<jsp:include page="/MenuBar/ClassSide.jsp" flush="false" />
			</div>

			<div id="centerPage">
				<div id="formMain">
				<form class="form-horizontal" id="classForm" name="classForm">
					<fieldset>
					<!--  숨겨놓을것 -->
					<input id="id" name="id" value="${sessionScope.ID}" type="hidden" />
					<input id="nick" name="nick" value="${sessionScope.NICKNAME}" type="hidden" />
					
					
						<!-- Text input-->
						<div class="control-group" id="formTop">
							<div id="selectD">
							<select id="subcode" name="subcode" class="input-large">
									<option value="0" >Select Subject</option>
										<c:forEach var="sublist" items="${SUBLIST}">
											<option value="${sublist.subcode}">${sublist.subname}</option>
										</c:forEach>
							</select>
							</div>
							<div class="controls" id="titleD">
								<input id="title" name="title" type="text" 
									class="input-xlarge" required>
							</div>
						</div>

						<!-- Textarea -->
						<div class="control-group">							
							<div>
								<textarea id="body" name="body" required></textarea>
							</div>
						</div>
						
						<div id="mediaDIV">
							<p id="mediaURL">동영상 주소</p>													
							<input id="mediaURL" name="mediaURL" type="text" >	
						</div>

					</fieldset>
				</form>
				</div><!--  글쓰기 폼 끝 -->
				<div id="writeUtilD">
					<a class="button button-purple"  id="save" ><i class="fa fa-rocket"></i>
					글올리기</a>
					<a class="button button-yellow" onClick="JavaScript:goClassList();" id="ListBtn" >
					목록으로</a>					
				</div>
			</div>
		</div>

	</div>

	<!--  글쓰기 -->
	<script>
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "body",
			sSkinURI : "../resources/smarteditor/SmartEditor2Skin.html",
			fCreator : "createSEditor2"
		});		
	</script>

</body>
</html>