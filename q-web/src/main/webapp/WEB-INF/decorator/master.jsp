<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><decorator:title /> ::</title>
</head>
<body>
<div id="header">兴趣小组 | 好友动态 | 找朋友 | 我的主页</div>
<hr />
<div id="main"><decorator:body /></div>
</body>
</html>
