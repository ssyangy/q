<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><decorator:title /></title>
</head>
<body>
<div id="header">

  <a href="${q.url.prefix}/groupFeed">圈子新鲜事</a> | 
  <a href="${q.url.prefix}/feed">好友新鲜事</a> | 
  <a href="${q.url.prefix}/people/1">我的主页</a>| 
  <a href="${q.url.prefix}/people">找人</a>
  <a href="${q.url.prefix}/login/new">登陆</a>
  <a href="${q.url.prefix}/people/new">注册</a>

</div>
<hr />
<div id="main"><decorator:body /></div>
</body>
</html>
