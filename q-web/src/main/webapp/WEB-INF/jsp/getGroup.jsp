<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>圈子:<c:out value="${group.name}" /></title>
</head>
<body>
<div id="content">
	<div><c:out value="${group.name}" /> <c:choose>
		<c:when test="${join == null}">
			<form action="<c:out value="${contextPath}/group/${group.id}/join"/>"
				method="post">
			<button>加入</button>
			</form>
		</c:when>
		<c:otherwise>我已加入这个圈子
			<form action="<c:out value="${contextPath}/group/${group.id}/join"/>"
				method="post"><input type="hidden" name="_method"
				value="delete" />
			<button>不参加了</button>
			</form>
		</c:otherwise>
	</c:choose></div>
	<div>成员数:上海9999/全站:199999</div>
	<div>上海 <a href="#">切换所在地</a></div>
	<div>讨论区 | 活动 | 图片 | 成员</div>
	<div>发言 | <a
		href="<c:out value="${urlPrefix}/event/new?groupId=${group.id}" />">发起活动</a>
	</div>
	<div>
	<form
		action="<c:out value="${contextPath}/weibo?from=${contextPath}/group/${group.id}"/>"
		method="post"><textarea name="content" rows="5" cols="50"></textarea> <input
		type="hidden" name="groupId" value="<c:out value="${group.id}"/>" />
	<button>发言</button>
	</form>
	</div>
	<div>新帖 | 热贴 | 我发起的 | 我回复的 | 我关注的</div>
	<div><c:forEach items="${weibos}" var="weibo" varStatus="status">
		<br/>${weibo.content}<br/>
		<form action="${urlPrefix}/weibo/${weibo.id}/favorite" method="post">
				<c:choose>
					<c:when test="${weibo.unFav}">
					<button>收藏</button>
					</c:when>
					<c:otherwise>
					<input type="hidden" name="_method"  value="delete"/>
					<button>取消收藏</button>
					</c:otherwise>
				</c:choose>	
		</form>				
		<a href="<c:out value="${urlPrefix}/weibo/${weibo.id}/retweet?from=${contextPath}/group/${group.id}"/>">
		<c:choose>
			<c:when test="${weibo.inGroup}">分享给好友</c:when>
			<c:otherwise>转发</c:otherwise>
		</c:choose>
		</a>&nbsp;
		<a href="<c:out value="${urlPrefix}/weibo/${weibo.id}"/>">回复</a>&nbsp;
		<a href="<c:out value="${contextPath}/people/${weibo.senderId}"/>">
			<c:out value="${weibo.senderRealName}" />
		</a>&nbsp;
		<c:out value="${weibo.time}" />&nbsp;
		<a href="<c:out value="${urlPrefix}${weibo.fromUrl}" />">
			<c:out value="${weibo.fromName}" />
		</a>
		<br />
	</c:forEach></div>
</div>
<div id="content2">
	<div>
		最新活动: <br />
		<c:forEach items="${events}" var="event" varStatus="status">
			<a href="<c:out value="${urlPrefix}/event/${event.id}"/>">
				<c:out value="${event.name}" /> 
			</a>
			<br />
		</c:forEach>
	</div>
</div>
</html>