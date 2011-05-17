<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="微博" />
</jsp:include>
<script type="text/javascript">
seajs.use('qcomcn.js', function (q) {
	$ = q.jq;
	$(function () {
         q.Init();
	});
});
</script>

	<div>
		<c:out value="${weibo.content}" /><br/>
		<c:if test="${weibo.quoteWeiboId >0}">
			<a href="<c:out value="${urlPrefix}/weibo/${weibo.quoteWeiboId}"/>">原文</a>&nbsp;
		</c:if>	
		<a href="<c:out value="${urlPrefix}/people/${weibo.senderId}"/>">
			<c:out value="${weibo.senderRealName}" />
		</a>&nbsp;
		<c:out value="${weibo.time}" />&nbsp;
		<a href="<c:out value="${urlPrefix}${weibo.fromUrl}" />">
			<c:out value="${weibo.fromName}" />
		</a>
		<br />
	</div>
	<div>
		<form action="<c:out value="${urlPrefix}/weibo/${weibo.id}/reply"/>"
			method="post"><textarea name="content" rows="5" cols="50"></textarea>
		<button>回复</button>
		</form>
	</div>
	<div>
		<c:forEach items="${replies}" var="reply" varStatus="status">
			<br>${reply.content}<br/>
			<form action="${urlPrefix}/reply/${reply.id}/favorite" method="post">
			<c:choose>
				<c:when test="${reply.unFav}">
				<button>收藏</button>
				</c:when>
				<c:otherwise>
				<input type="hidden" name="_method"  value="delete"/>
				<button>取消收藏</button>
				</c:otherwise>
			</c:choose>
			</form>
			<a href="${urlPrefix}/reply/${reply.id}/retweet?from=${urlPrefix}/weibo/${weibo.id}">			
			<c:choose>
				<c:when test="${reply.fromGroup}">分享给好友</c:when>
				<c:otherwise>转发</c:otherwise>
			</c:choose>
			</a>
			<a href="#">回复</a>&nbsp;
			<a href="${urlPrefix}/people/${reply.senderId}">${reply.senderRealName}</a>&nbsp;${reply.time}&nbsp;
			<a href="${urlPrefix}${reply.fromUrl}">
				${reply.fromName}
			</a>
			<br />
		</c:forEach>
	</div>
</div><jsp:include page="models/foot.jsp" />