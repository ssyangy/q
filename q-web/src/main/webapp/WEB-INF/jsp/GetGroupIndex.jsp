<%@ page language="java" import="java.util.*" import="q.domain.Category"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>圈子黄页</title>
</head>

<body>
圈子目录:
<br />
<c:forEach items="${categorys}" var="current" varStatus="status">
	<c:out value="${current.name}" />
	<br />
</c:forEach>
<br />
新圈子:
<br />
<c:forEach items="${newGroups}" var="group" varStatus="status">
	<a href="<c:out value="${urlPrefix}/group/${group.id}"/>"> <c:out
		value="${group.name}" /> </a>
	<br />
</c:forEach>
人气活动:
<br />
圈子热议:
<br />
<a href='<c:out value="${urlPrefix}/group/new"/>'>创建圈子</a>
</body>
</html>