<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>私信</title>
</head>
<body>
<div id="content">
	<div>
		<c:forEach items="${messages}" var="message" varStatus="status">
			
			<a href="<c:out value="${urlPrefix}/people/${message.senderId}"/>"><c:out
				value="${message.senderRealName}" /></a>发送给
			<a href="<c:out value="${urlPrefix}/people/${message.receiverId}"/>"><c:out
				value="${message.receiverRealName}" /></a>
			<c:out value="${message.content}" />
			<c:out value="${message.time}" />
			<br />
		</c:forEach>
	</div>
</div>
</body>
</html>