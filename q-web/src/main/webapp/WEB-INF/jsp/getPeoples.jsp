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
昵称:<c:out value="${nick}"></c:out>
粉丝数:<c:out value="${fansNum}"></c:out>
好友数<c:out value="${friendsNum}"></c:out>
UID<c:out value="${uid}"></c:out>
</body>
</html>