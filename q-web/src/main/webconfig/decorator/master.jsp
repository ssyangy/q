<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://www.w3cn.org/article/layout/2004/csslayout/css/02_2box_fluid_touch2.css" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><decorator:title /></title>
</head>
<body>
<div><a href="${q.url.prefix}">首页</a> <a
	href="${q.url.prefix}/group">圈子</a> <a href="${q.url.prefix}/event">活动</a>
<a href="${q.url.prefix}/message">私信</a> <a
	href="${q.url.prefix}/setting">设置</a> <a
	href="${q.url.prefix}/people/new">注册</a> <a
	href="${q.url.prefix}/login/new">登陆</a> <a
	href="${q.url.prefix}/login/delete">退出</a></div>
<div><a href="${q.url.prefix}/group/feed">圈子新鲜事</a> | <a
	href="${q.url.prefix}/people/feed">好友新鲜事</a> | <a
	href="${q.url.prefix}/mine">我的主页</a> | <a href="${q.url.prefix}/people">找人</a>
</div>
<hr />
<div id="main"><decorator:body /></div>
</body>
</html>
