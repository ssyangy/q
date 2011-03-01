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
			<br/>
		</c:forEach>
	</div>
</div>
</body>
</html>