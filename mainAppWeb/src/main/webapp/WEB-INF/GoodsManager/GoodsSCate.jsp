<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<cates>
	<c:forEach var="temp" items="${SCATE}">
		<cate>
			<code>${temp.code}</code>
			<name>${temp.name}</name>
		</cate>
	</c:forEach>
</cates>
<%--
	<cates>
		<cate>
			<code>???</code>
			<name>???</name>
		</cate>
		<cate>
			<code>???</code>
			<name>???</name>
		</cate>
		<cate>
			<code>???</code>
			<name>???</name>
		</cate>
		<cate>
			<code>???</code>
			<name>???</name>
		</cate>
	</cates>
--%>