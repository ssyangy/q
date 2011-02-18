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
<form action="<c:out value="${contextPath}" />/event" method="post">活动主题:<input type=text
	name="name" size="20" maxlength="20"> <br />
活动介绍:<textarea name="intro" cols="50" rows="10"> 
</textarea> <br />
<input type="hidden" name="sender_id" value="1" /><br />
<input type="submit" value="提交" /></form>
</body>
</html>