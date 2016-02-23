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

	<Script>
		function ReqWrite() {
			location.href = "./EmNoticeWriteForm.cop?nowPage=${nowPage}";
		};
	</Script>

<!--  짜잘한 스타일 -->

<style>
	#recBoard{
		width:750px;
		height:600px;
		margin-left:auto;
		margin-right:auto;
	}
	#paging{		
		text-align:center;
		margin: 0 auto;
	}
	tbody tr td a:link{
		text-decoration: none;
	}	
	tbody tr td a:hover{
		text-decoration: none;
	}	
	tbody tr td a:visited{
		text-decoration: none;
	}	
</style>

<body>

<div id="recBoard">	
			
<!-- 아래 부터 작성 -->
<p>&nbsp;</p>

<h3>채용 공고</h3><br>

<!--  선택박스 (그룹 선택) -->

<!-- 채용공고 게시판 -->
<table class="table table-bordered table-hover">

	<colgroup>
	<col style="width: 500px">
	<col style="width: 120px">
	<col style="width: 100px">
	</colgroup>

	<!-- 컬럼 -->
	<thead>	
		<tr>
			<th>공고명</th>
			<th>접수기한</th>
			<th>D-Day</th>
		</tr>		
	</thead>
	
	<!--  바디 -->
	<tbody>
		
	<!--  반복문 시작 -->
<c:forEach var="content" items="${LIST}">	
		<tr>
			<td>				
			 	<a href="../EmNotice/EmNoticeView.cop?nowPage=${PINFO.nowPage}&oriNO=${content.no}" ><span></span>[${content.group}]&nbsp;${content.title}</a>          
			</td>
			<td>${content.rdate}</td>
			<td>
				<c:if test="${content.dday >= 0}">
					<span class="glyphicon glyphicon-calendar"></span>&nbsp;${content.dday} 일
				</c:if>
				<c:if test="${content.dday < 0}">
					모집종료
				</c:if>
			</td>
		</tr>		
</c:forEach>	
		<!--  반복문 종료 -->
				
	</tbody>
	<!--  필요한 기능 -->
		
		<c:if test="${sessionScope.ADMIN eq 'Y' and sessionScope.ADMIN ne null}">								
			<tr>
				<td colspan=3 align="center">
					<button class="btn btn-success" type="button" onclick="JavaScript:ReqWrite();"><span class="glyphicon glyphicon-pencil"></span>글쓰기</button>
				</td>
			</tr>
		</c:if>		
			
	</table>
<!--  테이블 종료 -->
<!--  페이징 처리 -->
	<div id="paging">
		<ul class="pagination">
		
			<c:if test="${PINFO.startPage ne 1}">
				<li><a href="../EmNotice/EmNoticeList.cop?nowPage=${PINFO.startPage-1}">이전</a></li>
			</c:if>		
		
			<c:forEach var="imsi" begin="${PINFO.startPage}" end="${PINFO.endPage}">
				
				<c:if test="${imsi ne PINFO.nowPage}">
					<li><a href="../EmNotice/EmNoticeList.cop?nowPage=${imsi}">${imsi}</a></li>
				</c:if>
				<c:if test="${imsi eq PINFO.nowPage}">
					<li class="active"><a href="#">${imsi}</a></li>			
				</c:if>
					
			</c:forEach>
			
			<c:if test="${PINFO.endPage ne PINFO.pageNum}">
				<li><a href="../EmNotice/EmNoticeList.cop?nowPage=${PINFO.endPage + 1}">다음</a></li>		
			</c:if>
			
			<c:if test="${PINFO.endPage eq PINFO.pageNum}">
			</c:if>
					
		</ul>		
	</div>

</div>	
	

</body>





