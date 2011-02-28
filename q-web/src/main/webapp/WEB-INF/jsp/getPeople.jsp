<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的主页</title>
</head>
<body>
	<div id="content">
		<c:out value="${people.realName}" /><br />
		<%--  c:out value="${people.district.level1}" /--%>
		<%--  c:out value="${people.district.level2}" /--%>
		<c:out value="${people.intro}" /><br />
		生日<c:out value="${people.birthdayString}"></c:out><br />
		关注数<a href="<c:out value="${urlPrefix}/people/${people.id}/following"/>"><c:out value="${people.followingNum}" /></a>
		粉丝数<a href="<c:out value="${urlPrefix}/people/${people.id}/follower"/>"><c:out value="${people.followerNum}" /></a>
		微博数<c:out value="${people.weiboNum}" />
		<br />
		<c:if test="${isMe == false}">
			<c:choose>
				<c:when test="${isFollowing == false}">
					<form
						action="<c:out value="${urlPrefix}/people/${people.id}/following"/>"
						method="post">
						<button>关注<c:out value="${people.gender.cncall}"/></button>
					</form>
				</c:when>
				<c:otherwise>
					<form
						action="<c:out value="${urlPrefix}/people/${people.id}/following"/>"
						method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button>取消关注</button>
					</form>
				</c:otherwise>
			</c:choose>
			<a href="<c:out value="${urlPrefix}/message/new?receiverId=${people.id}" />">私信<c:out value="${people.gender.cncall}" /></a>
			<a href="<c:out value="${urlPrefix}/weibo/new?receiverId=${people.id}&from=${contextPath}/people/${people.id}" />">@<c:out value="${people.gender.cncall}" /></a>
		</c:if>
		
		<div>发言 | 关注</div>
		<div>
			<c:forEach items="${weibos}" var="weibo" varStatus="status">
				<br/><c:out value="${weibo.content}" /><br/>
				<c:if test="${weibo.quoteWeiboId >0}">
					<a href="<c:out value="${urlPrefix}/weibo/${weibo.quoteWeiboId}"/>">原文</a>&nbsp;
				</c:if>				
				<a href="<c:out value="${urlPrefix}/weibo/${weibo.id}/retweet?from=${contextPath}/people/${people.id}"/>">
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
		<div>
			<c:out value="${people.gender.cncall}" />加入的圈子: <br />
			<c:forEach items="${groups}" var="group" varStatus="status">
			<a href="<c:out value="${urlPrefix}/group/${group.id}"/>">
				<c:out value="${group.name}" />
			</a><br />
			</c:forEach>
		</div>
		<div>
			<c:out value="${people.gender.cncall}" />参与的活动: <br />
			<c:forEach items="${events}" var="event" varStatus="status">
				<a href="<c:out value="${urlPrefix}/event/${event.id}"/>">
					<c:out value="${event.name}" /> 
				</a>
				<br />
			</c:forEach>
		</div>
	</div>
</body>
</html>