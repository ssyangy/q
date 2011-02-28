<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微博</title>
</head>
<body>
<div id="content">
	<div>
		<c:out value="${weibo.content}" /><br/>
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
			<br/><c:out value="${reply.content}" /><br/>
			<a href="<c:out value="${urlPrefix}/reply/${reply.id}/retweet?from=${contextPath}/weibo/${weibo.id}"/>">转发</a>&nbsp;
			<a href="#">回复</a>&nbsp;
			<a href="<c:out value="${urlPrefix}/people/${reply.senderId}"/>">
				<c:out value="${reply.senderRealName}" />
			</a>&nbsp;${reply.time}&nbsp;
			<a href="${urlPrefix}${reply.fromUrl}">
				${reply.fromName}
			</a>
			<br />
		</c:forEach>
	</div>
</div>
</body>
</html>