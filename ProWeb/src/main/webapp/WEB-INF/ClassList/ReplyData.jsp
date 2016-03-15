<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
							<ul class="timeline" id="timeline">
														
							    <!-- timeline items -->
							    <c:forEach var="recontent" items="${reDATA}">
							    <li>
							        <!-- timeline icon -->
							        <i class="fa fa-comments bg-yellow"></i>
							        <div class="timeline-item">
							            <span class="time"><i class="fa fa-clock-o"></i> ${recontent.realdate}</span>							
							            <h3 class="timeline-header"><i class="fa fa-android"></i>No : ${recontent.reno}&nbsp;<a href="#">${recontent.renick}</a></h3>
							
							            <div class="timeline-body" id="modiDiv">
							               ${recontent.realbody}
							            </div>
							
							            <div class="timeline-footer">
							            	<c:if test="${sessionScope.ID eq recontent.reid}">
							                <a class="btn btn-warning btn-flat btn-xs">수정하기</a>&nbsp;
							                <a class="btn btn-danger btn-xs" onClick="JavaScript:deleteReply(${recontent.reno});">삭제하기</a>
							                </c:if>
							            </div>
							        </div>
							    </li>
							    </c:forEach>
							    <!-- END timeline items --> 
							</ul> <!-- 댓글 끝 -->	
							
						<div id="replyPagingDiv">
							<ul class="pagination">
		
								<c:if test="${PINFO.startPage ne 1}">
									<li><a href="JavaScript:getReList(${PINFO.startPage-1})">이전</a></li>
								</c:if>		
							
								<c:forEach var="imsi" begin="${PINFO.startPage}" end="${PINFO.endPage}">									
									<c:if test="${imsi ne PINFO.nowPage}">
										<li><a href="JavaScript:getReList(${imsi})">${imsi}</a></li>
									</c:if>
									<c:if test="${imsi eq PINFO.nowPage}">
										<li class="active"><a href="#">${imsi}</a></li>			
									</c:if>										
								</c:forEach>
								
								<c:if test="${PINFO.endPage ne PINFO.pageNum}">
									<li><a href="JavaScript:getReList(${PINFO.endPage + 1})">다음</a></li>		
								</c:if>								
								
							</ul>		
						</div>						
					
						
						
						
						