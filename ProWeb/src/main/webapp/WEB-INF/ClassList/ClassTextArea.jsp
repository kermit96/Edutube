<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<form id="remodiFrm" name="remodiFrm" method="POST">
	<input id="renom" name="renom" value="${reno}" type="hidden" />
	<textarea class="modiFFF" rows="4" id="relplymodi"  name="relplymodi" required>${rebody}	</textarea>
</form>
<a class="btn btn-warning btn-flat btn-xs" onClick="JavaScript:modiReply();">수정하기</a>	