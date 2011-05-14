<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://127.0.0.1:8090/style/common/main.css" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><decorator:title /></title>
</head>
<body>
<div><a href="http://www.jin.q.net:9999/q">首页</a> <a
	href="http://www.jin.q.net:9999/q/group">圈子</a> <a href="http://www.jin.q.net:9999/q/event">活动</a>
	<a href="http://www.jin.q.net:9999/q/message">私信</a>
	<a href="http://www.jin.q.net:9999/q/favorite">收藏</a> <a
	href="http://www.jin.q.net:9999/q/setting">设置</a> <a
	href="http://www.jin.q.net:9999/q/people/new">注册</a> <a
	href="http://www.jin.q.net:9999/q/login/new">登陆</a> <a
	href="http://www.jin.q.net:9999/q/login/delete">�?��</a></div>
<div><a href="http://www.jin.q.net:9999/q/group/feed">圈子新鲜�?/a> | <a
	href="http://www.jin.q.net:9999/q/people/feed">好友新鲜�?/a> | <a
	href="http://www.jin.q.net:9999/q/mine">我的主页</a> | <a href="http://www.jin.q.net:9999/q/people">找人</a>
</div>
<hr />
<div id="main"><decorator:body /></div>
</body>
</html>
