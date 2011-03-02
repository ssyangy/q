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
			<br/>
			<a href="${urlPrefix}/people/${message.senderId}">
				${message.senderRealName}
			</a>发送给
			<a href="${urlPrefix}/people/${message.receiverId}">
				${message.receiverRealName}
			</a>&nbsp;
			${message.content}<br/>
			<c:if test="${loginId != message.senderId}">
				<a href="${urlPrefix}/message/new?receiverId=${message.senderId}&replyMessageId=${message.id}">回复</a>&nbsp;
			</c:if>			
			<c:out value="${message.time}" />
			<br />
		</c:forEach>
	</div>
</div>
</body>
</html>