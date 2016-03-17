<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  비정상 접근 처리 -->  
<c:if test="${sessionScope.NAL ne 'L'}">
 	<c:redirect url="../Member/login.do" />
</c:if>
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
		location.href="../ClassList/ClassList.do?nowPage=${nowPage}&code=${listCode}";
	}
	

	$(document).ready(	function() {

			$('#title').val('${DATA.title}');
					
			$("#modifyClass").click(function() {
						
					$title = $("#title").val();
						if ($title == "") {
							alert("제목을 입력해 주세요");
							return;
					}

					$code = $("#code").val();
					if ($code == 0) {
							alert("과목을 선택해 주세요");
						return;
					}						

					oEditors.getById["body"].exec(
									"UPDATE_CONTENTS_FIELD",[]);

					$("#classForm").attr("action",
									"../ClassList/ClassModify.do?nowPage=${nowPage}&listCode=${listCode}&oriNO=${oriNO}");
					$("#classForm").submit();
				});
	});
	
	/*동영상 삭제&수정시 실행할 스크립트*/
	/* 추가 */
	function MediaAdd(){		
		$('#mKind').val('Ad');		
		var DivLocation = document.getElementById("mediaBox");
		var TextBox = "<input id='mediaSp' name='mediaSp' type='text' required>";
		DivLocation.innerHTML  = TextBox;		
	}	
	/* 수정*/
	function MediaModi(){		
		$('#mKind').val('Mo');		
		var DivLocation = document.getElementById("mediaBox");
		var TextBox = "<input id='mediaSp' name='mediaSp' type='text' required>";
		DivLocation.innerHTML  = TextBox;
	}
	/* 삭제*/
	function MediaDelete(){		
		$('#mKind').val('De');		
		$('#mediaBox').empty();		
	}

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
	height:650px;
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
	margin-top:10px;	
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
div#mediaURLp{
	float:left;
	width:100px;
}
div#mediaBox{
	float:left;
	width:350px;
}
div#mediaUtil{
	float:left;
	width:250px;
}

input#mediaURL{
	width:350px;
}

input#title{
	width:500px;
}

input#mediaSp{
	width:350px;
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
				<form class="form-horizontal" id="classForm" name="classForm" method="POST">
					<fieldset>
					<!--  숨겨놓을것 -->
					<input id="id" name="id" value="${sessionScope.ID}" type="hidden" />
					<input id="nick" name="nick" value="${sessionScope.NICKNAME}" type="hidden" />
					<input id="mKind" name="mKind" value="No" type="hidden" />
					
						<!-- Text input-->
						<div class="control-group" id="formTop">
							<div id="selectD">
							<select id="code" name="code" class="input-large">
									<option value="0" >Select Subject</option>
										<c:forEach var="sublist" items="${SUBLIST}">											
											<c:if test="${sublist.subcode eq DATA.code}">
												<option value="${sublist.subcode}" selected>${sublist.subname}</option>
											</c:if>
											<c:if test="${sublist.subcode ne DATA.code}">
												<option value="${sublist.subcode}">${sublist.subname}</option>
											</c:if>											
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
								<textarea id="body" name="body" required>${DATA.body}</textarea>
							</div>
						</div>
						
						<div id="mediaDIV">							
							<div id="mediaURLp">동영상 주소</div>	
								<c:if test="${isExist==true}">	
									<c:forEach var="mList" items="${mList}">
									<input id="VideoNo" name="VideoNo" value="${mList.mediaNO}" type="hidden" />
										<div id="mediaBox">																																								
										${mList.mediaURL}
										</div>
										<div id="mediaUtil">
										&nbsp;<a href="JavaScript:MediaModi()"><i class="fa fa-wrench"></i>수정하기</a>&nbsp;
										<a href="JavaScript:MediaDelete()"><i class="fa fa-trash"></i>삭제하기</a>
										</div>								
									</c:forEach>
								</c:if>
								<c:if test="${isExist==false}">
									<div id="mediaBox"></div>
									<div id="mediaUtil">&nbsp;<a href="JavaScript:MediaAdd();"><i class="fa fa-plus"></i>추가하기</a></div>		
								</c:if>	
						</div>

					</fieldset>
				</form>
				</div><!--  글쓰기 폼 끝 -->
				<div id="writeUtilD">
					<a class="button button-purple"  id="modifyClass" ><i class="fa fa-rocket"></i>
					수정하기</a>
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