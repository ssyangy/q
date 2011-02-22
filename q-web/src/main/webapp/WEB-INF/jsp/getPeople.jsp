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
<c:out value="${people.realName}" />
私信
<c:out value="${people.realName}" />
@
<c:out value="${people.realName}" />
邀请
<c:out value="${people.realName}" />
加入圈子
</body>
</html>