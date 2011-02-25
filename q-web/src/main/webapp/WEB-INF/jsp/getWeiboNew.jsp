<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新微博</title>
</head>
<body>
<div id="content">
	<div>
		<form action="<c:out value="${urlPrefix}/weibo"/>" method="post">
			<input type="hidden" name="from" value='<c:out value="${from}"></c:out>'/>
			<textarea name="content" rows="5" cols="50">@<c:out value="${receiver.realName}"></c:out></textarea>
			<button>发言</button>
		</form>
	</div>
</div>
</body>
</html>