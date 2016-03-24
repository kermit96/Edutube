<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<monthly>
	<c:forEach var="temp" items="${eventL}">
		<event>
			<id>${temp.id}</id>
			<name>${temp.name}</name>
			<startdate>${temp.sdate1}</startdate>
			<enddate>${temp.edate1}</enddate>
			<starttime></starttime>
			<endtime></endtime>
			<color>#ffb128</color>
			<url></url>
		</event>
	</c:forEach>
</monthly>

