<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>圈子新鲜事</title>
</head>
<body>
<div>我的圈子</div>
<div> 讨论区 | <a href="#">活动</a> | <a href="#">相册</a> </div>
<div>上海 <a href="#">切换所在地</a> <a href="<c:out value="${contextPath}/category"/>">逛更多圈子</a></div>
<div>新帖 | 热贴 | 我发起的 | 我回复的 | 我关注的</div>
</body>
</html>