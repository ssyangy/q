<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" flush="true"/>
<title>圈子:${group.name}</title>
	<script type="text/javascript">
		$(function(){
			$( "#radio" ).buttonset();
			$('#tabs').tabs();
		});
	</script>	
</head>
<body>
<div id="doc">
<jsp:include page="top.jsp" flush="true"/>	
	<div id="page-outer">
		<div id="page-container">
			<div class="main-content" style="min-height:400px">
				<jsp:include page="group-head.jsp" flush="true" >
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
					<div id="tabs">
						<ul class="stream-tabs">
							<li class="stream-tab"><a class="tab-text" href="#tabs-1">新话题</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-2">热议</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-3">我发起的</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-4">我回复的</a></li>
							<li class="stream-tab"><a class="tab-text" href="#tabs-5">我收藏的</a></li>
						</ul>
						<div id="tabs-1">
						<jsp:include page="weiboList.jsp" flush="true"/>
						</div>
						<div id="tabs-2">tabs2</div>
						<div id="tabs-3">tabs3</div>
						<div id="tabs-4">tabs4</div>
						<div id="tabs-5">tabs5</div>
					</div>
				</div>	
			</div>
		</div>
		<jsp:include page="group-dashboard.jsp" flush="true"/>
	</div>
</div>
</html>