<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  비정상 접근 처리 -->  
<c:if test="${sessionScope.NAL ne 'L'}">
 	<c:redirect url="../Member/login.do" />
</c:if>

<c:redirect url="./ClassView.do">
	<c:param name="nowPage" value="${nowPage}" />
	<c:param name="oriNO" value="${oriNO}" />
	<c:param name="code" value="${CODE}" />
</c:redirect>