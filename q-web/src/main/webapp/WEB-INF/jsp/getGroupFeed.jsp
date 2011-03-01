<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>圈子新鲜事</title>
</head>
<body>
<div id="content">
	<div>讨论区 | <a
		href="<c:out value="${urlPrefix}/group/event"/>">活动</a> | <a
		href="<c:out value="${urlPrefix}/group/photo"/>">相册</a>
	</div>
	<div>上海 <a href="#">切换所在地</a> 
		<a href="<c:out value="${contextPath}/group"/>">逛更多圈子</a>
	</div>
	<div>新帖 | 热贴 | 我发起的 | 我回复的 | 我关注的</div>
	<div>
	<c:forEach items="${weibos}" var="weibo" varStatus="status">
		<br/><c:out value="${weibo.content}" /><br/>
		<c:if test="${weibo.quoteWeiboId >0}">
			<a href="<c:out value="${urlPrefix}/weibo/${weibo.quoteWeiboId}"/>">原文</a>&nbsp;
		</c:if>				
		<a href="<c:out value="${urlPrefix}/weibo/${weibo.id}/retweet?from=${contextPath}/group/feed"/>">
		<c:choose>
			<c:when test="${weibo.inGroup}">分享给好友</c:when>
			<c:otherwise>转发</c:otherwise>
		</c:choose>
		</a>&nbsp;
		<a href="<c:out value="${urlPrefix}/weibo/${weibo.id}"/>">回复</a>&nbsp;
		<a href="<c:out value="${urlPrefix}/people/${weibo.senderId}"/>">
			<c:out value="${weibo.senderRealName}" />
		</a>&nbsp;
		<c:out value="${weibo.time}" />&nbsp;
		<a href="<c:out value="${urlPrefix}${weibo.fromUrl}" />">
			<c:out value="${weibo.fromName}" />
		</a>
		<br />
	</c:forEach>
	</div>	
</div>
<div id="content2">
	<div>我加入的圈子:</div>
	<div>
	<c:forEach items="${groups}" var="group" varStatus="status">
	<a href="${urlPrefix}/group/${group.id}"">${group.name}</a><br />
	</c:forEach>
	</div>
	<div>圈子活动:<br/></div>
	<div>热图:<br/></div>
</div>
</body>
</html>