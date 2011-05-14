<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微博转发</title>
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
		<form action="${urlPrefix}/weibo/${weibo.id}/retweet" method="post">
			<input type="hidden" name="from" value="${from}"/>
			<textarea name="content" rows="5" cols="50">//@${sender.realName}</textarea>
			<button>转发</button>
		</form>
	</div>
</div>
</body>
</html>