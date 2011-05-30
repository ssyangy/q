<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
    <link href="${staticUrlPrefix}/content/main/jquery.ui.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/qcomcn.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/signup.css")" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
ul.pssyao li{width:233px;}
</style>
<body>
	<div class="signup">
		<h1 class="logo2"></h1>
		<span class='info'><a class='lk mr5' href="${urlPrefix}/people/new">注册</a><a class='lk' href="${urlPrefix}/login/new">登录</a></span>
		<ul class='pssyao clear'>
            <li class="done">填写邮箱</li>
            <li class="">接收邮件</li>
			<li class="end">重置密码</li>
        </ul>
		<div class='qform' style="padding:20px;text-align:center;">
		请到<%=request.getParameter("email")%>查阅来自Q.com.cn的邮件，点击邮件中的链接重设你的密码。
		</div>
	</div>
</body>
</html>