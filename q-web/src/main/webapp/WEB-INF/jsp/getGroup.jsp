<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>圈子:${group.name}</title>
</head>
<body>
	<div id="body">
	<jsp:include page="top.jsp" />
	<div id="page-outer">
		<div id="page-container">
			<div class="main-content" style="min-height:400px">
				<jsp:include page="group-head.jsp"  >
					<jsp:param name="tab" value="group" />
				</jsp:include>
				<jsp:include page="weibo-send.jsp" >
					<jsp:param name="from" value="${urlPrefix}/group/${group.id}"/>
				</jsp:include>
				<div class="stream-manager">
					<div id="tabs" class="ui-tabs ui-widget">
						<jsp:include page="group-tag.jsp"/>
						<jsp:include page="weibo-list.jsp">
							<jsp:param name="feedUrl" value="${urlPrefix}/group/${group.id}" />
						</jsp:include>
					</div>
				</div>
			</div>
			<jsp:include page="group-dashboard.jsp" />
			<div class="tweetexpand expand"></div>
			<br clear='all'/>
		</div>

	</div>
</div>
</body>
</html>