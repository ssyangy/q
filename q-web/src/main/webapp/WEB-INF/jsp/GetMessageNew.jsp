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
	<form action="<c:out value="${urlPrefix}/message" />" method="post">
		<input type="hidden" name="receiverId" value='<c:out value="${receiver.id}"></c:out>'></input>
		<label>发私信给:<c:out value='${receiver.realName}'></c:out></label><br/>
		<textarea name="content"></textarea><br/>
		<button>发送</button>
	</form>
</div>
</body>
</html>