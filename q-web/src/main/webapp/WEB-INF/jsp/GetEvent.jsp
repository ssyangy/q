<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动</title>
</head>
<body>
活动名称:<c:out value="${event.name}" />
<br />
<form action="<c:out value="${contextPath}/event/${event.id}/join"/>" method="post" >

<input type="hidden" name="eventId" value="${event.intro}" /><br />
<button>我要参加</button>

</form>
<form action="<c:out value="${contextPath}/event/${event.id}/join"/>" method="post" ><button>我感兴趣</button></form>
<br>
时间:<c:out value="${event.intro}" />
<br />
地点:<c:out value="${event.intro}" />
<br />
费用:<c:out value="${event.intro}" />
<br />
人数限制:<c:out value="${event.intro}" />
<br />
简介:<c:out value="${event.intro}" />
<br />
发起人:<c:out value="${senderperson.username}" />
<br />
</body>
</html>