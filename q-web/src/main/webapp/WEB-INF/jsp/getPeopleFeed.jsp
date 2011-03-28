<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>好友新鲜事</title>
	<script type="text/javascript">
		$(document).ready(function(){
			//$('#tabs').tabs();
			//$tabs.tabs('select', 0);
		});
	</script>	
</head>
<body>
	<div id="doc">
		<jsp:include page="top.jsp" />
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<jsp:include page="people-feed-head.jsp"></jsp:include>
					<div class="stream-manager">
						<div id="tabs" class="ui-tabs ui-widget">
							<jsp:include page="people-feed-tag.jsp">
								<jsp:param value="${param['tab']}" name="tab"/>
							</jsp:include>
							<div id="tabs-1">
								<c:choose>
								<c:when test="${'replied' == param['tab']}">
									<jsp:include page="reply-list.jsp" />
								</c:when>
								<c:otherwise>
									<jsp:include page="weibo-list.jsp" />
								</c:otherwise>
								</c:choose>
							</div>
						</div>					
					</div>
				</div>
				<jsp:include page="people-feed-dashboard.jsp"/>
			</div>
		</div>
	</div>
</body>
</html>