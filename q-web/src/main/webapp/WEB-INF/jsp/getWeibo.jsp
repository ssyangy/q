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
		<c:out value="${weibo.content}" />
	</div>
	<div>
		<form action="<c:out value="${contextPath}/weibo/${weibo.id}/reply"/>"
			method="post"><textarea name="content"></textarea>
		<button>回复</button>
		</form>
	</div>
	<div>
		<c:forEach items="${replies}" var="reply" varStatus="status">
			<c:out value="${reply.content}" />
			<a href="<c:out value="${contextPath}/people/${reply.senderId}"/>"><c:out
				value="${reply.senderRealName}" /></a>
			<c:out value="${reply.created}" />
			<br />
		</c:forEach>
	</div>
</div>
</body>
</html>