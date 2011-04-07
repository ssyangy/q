<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>收藏</title>
</head>
<body onResize="ReSet()" onLoad="ReSet()">
	<div id="body">
	<jsp:include page="top.jsp"/>	
	<div id="page-outer">
		<div id="page-container">
			<div class="main-content" style="min-height:400px">
				<jsp:include page="people-head.jsp"/>
				<div class="stream-manager">
					<div id="tabs" class="ui-tabs ui-widget">
						<jsp:include page="people-tag.jsp">
							<jsp:param value="favorite" name="tab"/>
						</jsp:include>
						<div>
						<jsp:include page="weibo-list.jsp">
							<jsp:param name="feedUrl" value="${urlPrefix}/favorite"/>
							<jsp:param name="from" value="${urlPrefix}/favorite"/>
						</jsp:include>
						</div>
					</div>
				</div>				
			</div>
		</div>
		<jsp:include page="people-dashboard.jsp"/>
	</div>
</div>
</html>