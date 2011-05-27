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
		
		<ul class='pssyao clear'>
            <li class="done2">填写邮箱</li>
            <li class="done2">接收邮件</li>
			<li class="done end">重置密码</li>
        </ul>
		
			<form action="${urlPrefix}/password/reset" method="post">
				<table class='qform'>
				<input type="hidden" name="key" value="${key}"></input>
					<tr>
						<td align="right">新密码：</td>
						<td><input type="password" name="newPassword" type='text' class='mttext'
							size='23'></td>
					</tr>
					<tr>
						<td align="right">重复新密码：</td>
						<td><input type="password" name="newPassword_re" type='text' class='mttext'
							size='23'></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<button type="submit" class='ui_btn'>提交</button></td>
					</tr>
				</table>
			</form>
	</div>
</body>
</html>