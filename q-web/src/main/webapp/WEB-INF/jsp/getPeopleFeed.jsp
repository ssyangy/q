<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>好友新鲜事</title>
</head>
<body>
<div id="content">
	<div>新动态 | @提到我 | 回复我</div>
	<div>
	<c:forEach items="${weibos}" var="weibo" varStatus="status">
		<br/><c:out value="${weibo.content}" /><br/>
		<c:if test="${weibo.quoteWeiboId >0}">
			<a href="<c:out value="${urlPrefix}/weibo/${weibo.quoteWeiboId}"/>">原文</a>&nbsp;
		</c:if>				
		<a href="<c:out value="${urlPrefix}/weibo/${weibo.id}/retweet?from=${contextPath}/people/feed"/>">
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
<div>好友地图 
	<div>附近好友 | 全部好友 | 附近陌生人</div> 
</div>
</div>
</body>
</html>