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
ul.pssyao li{width:370px;}
</style>
<body>
<div class="signup">
    <h1 class="logo2"></h1>
	
			<ul class='pssyao clear'>
            <li class="done2">填写邮箱</li>
            <li class="done">接收邮件</li>
			<li class="end">重置密码</li>
        </ul>
	
    	<form action="${urlPrefix}/password/forget" method="post">
        <table class='qform'>
			<tr>
				<td align="right">忘记密码：</td>
				<td><input name="email" type='text' class='mttext' size='23' onblur="if(this.value==''){this.value='填写注册邮箱或者用户名'};" onfocus="if(this.value=='填写注册邮箱或者用户名') this.value='';" value="填写注册邮箱或者用户名"/>
				</td>
			</tr>

			<tr>
				<td></td>
				<td>
				<button type="submit" class='btnb'>重置密码</button>
				</td>
			</tr>
		</table>
		</form>
</div>
</body>
</html>