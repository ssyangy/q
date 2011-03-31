<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>圈子:${group.name}</title>
</head>
<body onResize="ReSet()" onLoad="ReSet()">
	<div id="body" style="padding-bottom:0px;overflow-y:auto; overflow-x:hidden;">
	<jsp:include page="top.jsp" />	
	<div id="page-outer">
		<div id="page-container">
			<div class="main-content" style="min-height:400px">
				<jsp:include page="group-head.jsp"  >
					<jsp:param name="tab" value="group" /> 
				</jsp:include>	
				<div class="main-tweet-box group">
					<div class="tweet-box">
						<div class="bg">
							<form action="${contextPath}/weibo?from=${contextPath}/group/${group.id}" method="post">
							<div class="text-area">
								<textarea name="content" class="twitter-anywhere-tweet-box-editor" style="width: 470px; height: 56px; "></textarea>
							</div>
							<div class="tweet-button-container">
								<div class="submit"><button class="button">发表</button></div>
								<div class="bar">插入：<a href="">表情</a><a href="">图片</a><a href="">视频</a></div>
								<div class="clearfix2"></div>
							</div>
							<input type="hidden" name="groupId" value="${group.id}" />
							</form>
						</div>
					</div>
				</div>											
				<div class="stream-manager">
					<div id="tabs" class="ui-tabs ui-widget">
						<jsp:include page="group-tag.jsp"/>
						<jsp:include page="weibo-list.jsp">
							<jsp:param name="feedUrl" value="${urlPrefix}/group/${group.id}" />
						</jsp:include>
					</div>
				</div>	
			</div>
		</div>
		<jsp:include page="group-dashboard.jsp" />
	</div>
</div>
</html>