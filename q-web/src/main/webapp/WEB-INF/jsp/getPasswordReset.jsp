<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
    <link href="${staticUrlPrefix}/content/main/jquery.ui.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/qcomcn.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/signup.css")" rel="stylesheet" type="text/css" />
    <script src="${staticUrlPrefix}/scripts/sea.js")" type="text/javascript"></script>
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
</head>
<body>
	<div class="signup">
		<h1 class="logo2"></h1>
		<span class='info'><a class='lk mr5' href="${urlPrefix}/people/new">注册</a><a class='lk' href="${urlPrefix}/login/new">登录</a></span>
		<ul class='pssyao clear'>
            <li class="done3">填写邮箱</li>
            <li class="done2">接收邮件</li>
			<li class="done end">重置密码</li>
        </ul>
		
			<form action="${urlPrefix}/password/reset" method="post">
				<table class='qform'>
				<input type="hidden" name="key" value="${key}"></input>
					<tr>
						<td align="right">新密码：</td>
						<td><input type="password" name="newPassword" type='text' class='mttext' /></td>
					</tr>
					<tr>
						<td align="right">重复新密码：</td>
						<td><input type="password" name="newPassword_re" type='text' class='mttext' /></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" class='btnr' value="提交" /></td>
					</tr>
				</table>
			</form>
	</div>
</body>
</html>