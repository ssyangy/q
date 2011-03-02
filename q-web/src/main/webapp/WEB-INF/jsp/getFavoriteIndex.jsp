<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>收藏夹</title>
</head>
<body>
<div id="content">
	<div>
		<c:forEach items="${favorites}" var="fav" varStatus="status">
			<br/>收藏于:${fav.time}
			<br/>${fav.source.content}<br/>
			<c:if test="${fav.source.quoteWeiboId >0}">
				<a href="${urlPrefix}/${fav.source.viewName}/${fav.source.quoteWeiboId}">原文</a>&nbsp;
			</c:if>	
			<form action="${urlPrefix}/${fav.source.viewName}/${fav.source.id}/favorite" method="post">
			<c:choose>
				<c:when test="${fav.source.unFav}">
				<button>收藏</button>
				</c:when>
				<c:otherwise>
				<input type="hidden" name="_method"  value="delete"/>
				<button>取消收藏</button>
				</c:otherwise>
			</c:choose>	
			</form>		
			<a href="${urlPrefix}/${fav.source.viewName}/${fav.source.id}/retweet?from=${contextPath}/people/${people.id}">
			<c:choose>
				<c:when test="${fav.source.inGroup}">分享给好友</c:when>
				<c:otherwise>转发</c:otherwise>
			</c:choose>
			</a>&nbsp;
			<a href="${urlPrefix}/${fav.source.viewName}/${fav.source.id}">回复</a>&nbsp;
			<a href="${urlPrefix}/people/${fav.source.senderId}">
				${fav.source.senderRealName}
			</a>&nbsp;
			<c:out value="${fav.source.time}" />&nbsp;
			<a href="${urlPrefix}${fav.source.fromUrl}">
				${fav.source.fromName}
			</a><br/>
		</c:forEach>
	</div>
</div>
</body>
</html>