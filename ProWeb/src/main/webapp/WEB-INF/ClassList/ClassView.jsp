<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>EduTube</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->

	<!--JS-->
	
	<!--CustomScript-->
	<script>
	</script>
	
	<!--Never Delete "EduContainer" style tag-->
	<style>
	
			#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
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
		${DATA.id}
		${DATA.nick}
		${DATA.realdate}
		${DATA.title}
		${DATA.body}
		${DATA.hit}
		${DATA.good}
		${DATA.code}
		${DATA.lang}
		
		<c:forEach var="mList" items="${mList}">
		${mList.mediaNO}
		${mList.mediaURL}
		</c:forEach>
		
	</div>
	 
</div>

</body>
</html>



