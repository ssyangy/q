<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>活动索引</title>
</head>
<body>
<div id="content">
	新活动:<br />
	<c:forEach items="${newEvents}" var="event" varStatus="status">
		<a href="<c:out value="${urlPrefix}/event/${event.id}"/>"> <c:out
			value="${event.name}" /> </a>
		<br />
	</c:forEach>
</div>
</body>
</html>