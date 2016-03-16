<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<form id="remodiFrm${reno}" name="remodiFrm${reno}" method="POST">
	<input id="renom" name="renom" value="${reno}" type="hidden" />
	<textarea class="modiFFF" rows="4" id="relplymodi${reno}"  name="relplymodi${reno}" required>${rebody}</textarea>
</form>
<a class="btn btn-warning btn-flat btn-xs" onClick="JavaScript:modiReply(${reno});">수정완료하기</a>	