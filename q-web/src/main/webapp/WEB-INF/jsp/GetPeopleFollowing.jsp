<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>关注者</title>
</head>
<body>
<div><c:forEach items="${relations}" var="relation">
	<a href="<c:out value="${urlPrefix}/people/${relation.toPeopleId}"/>"><c:out
		value="${relation.toPeopleRealName}" /></a>
	<br />
</c:forEach></div>
</body>
</html>