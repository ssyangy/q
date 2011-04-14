<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>好友新鲜事</title>
</head>
<body onResize="ReSet()" onLoad="ReSet()">
	<div id="body">
		<jsp:include page="top.jsp" />
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<jsp:include page="people-feed-head.jsp"></jsp:include>
					<div class="stream-manager">
						<div id="tabs" class="ui-tabs ui-widget">
							<jsp:include page="people-feed-tag.jsp"/>
							<c:choose>
							<c:when test="${'replied' == param['tab']}">
								<jsp:include page="reply-list.jsp" />
							</c:when>
							<c:otherwise>
								<jsp:include page="weibo-list.jsp" >
									<jsp:param name="feedUrl" value="${urlPrefix}/people/feed"/>
								</jsp:include>
							</c:otherwise>
							</c:choose>
						</div>					
					</div>
				</div>
				<jsp:include page="people-feed-dashboard.jsp"/>
				<div class="tweetexpand expand"></div>
				<div class='pagebk'></div>
				<br clear='all'/>
			</div>
		</div>
	</div>
</body>
</html>