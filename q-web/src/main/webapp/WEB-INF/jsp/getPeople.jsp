<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的主页</title>
</head>
<body>
<c:out value="${people.realName}" />
<%--  c:out value="${people.district.level1}" /--%>
<%--  c:out value="${people.district.level2}" /--%>
<br />
<c:out value="${people.intro}" />
<br />
生日
<c:out value="${people.birthdayString}"></c:out>
<br />
好友数
<a href="<c:out value="${urlPrefix}/people/${people.id}/friend"/>">
<c:out value="${people.friendNum}" /></a>
关注数
<a href="<c:out value="${urlPrefix}/people/${people.id}/follow"/>"><c:out
	value="${people.followNum}" /></a>
粉丝数
<a href="<c:out value="${urlPrefix}/people/${people.id}/following"/>">
<c:out value="${people.followingNum}" /></a>
微博数
<c:out value="${people.weiboNum}" />
<br />
关注
<c:out value="${people.gender.cncall}" />
私信
<c:out value="${people.gender.cncall}" />
@
<c:out value="${people.gender.cncall}" />

<div>发言 | 关注</div>
<div><c:forEach items="${weibos}" var="weibo" varStatus="status">
	<c:out value="${weibo.content}" />
	<a href="<c:out value="${urlPrefix}/weibo/${weibo.id}"/>"> 回复 </a>&nbsp;
	<a href="<c:out value="${contextPath}/people/${weibo.senderId}"/>"><c:out
		value="${weibo.senderRealName}" /></a>
	<c:out value="${weibo.created}" />
	<br />
</c:forEach></div>
</body>
</html>