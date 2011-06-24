<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Q.com.cn</title>
    <link href="${staticUrlPrefix}/content/main/jquery.ui.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/qcomcn.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/signup.css")" rel="stylesheet" type="text/css" />
    <script src="${staticUrlPrefix}/scripts/sea.js")" type="text/javascript"></script>
</head>
<style type="text/css">
ul.pssyao li{width:233px;}
</style>
<script type="text/javascript">
	seajs.use('qcomcn', function(q) {
		var $ = q.jq;
		$(function() {
			q.loader();
		});
	});
</script>
<body>
<div class="signup">
    <h1 class="logo2"></h1>
		<span class='info'><a class='lk mr5' href="${urlPrefix}/people/new">注册</a><a class='lk' href="${urlPrefix}/login/new">登录</a></span>
		<ul class='pssyao clear'>
            <li class="done">填写邮箱</li>
            <li class="">接收邮件</li>
			<li class="end">重置密码</li>
        </ul>
	
    	<form action="${urlPrefix}/password/forget" method="post">
		<div class='qform' style="padding:20px 280px;text-align:left;">
			<p>填写注册邮箱或者用户名：<p/>
			<input name="email" type='text' class='mttext'/>
			<input type="submit" class='btnb' value='提交' />
		</div>
		</form>
</div>
</body>
</html>