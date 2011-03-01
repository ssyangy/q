<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发表微博</title>
</head>
<body>
<div id="content">
	<div>
		<form action="${urlPrefix}/weibo" method="post">
			<input type="hidden" name="from" value="${from}"/>
			<textarea name="content" rows="5" cols="50">@${receiver.realName}</textarea>
			<button>发言</button>
		</form>
	</div>
</div>
</body>
</html>