<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
    <link href="${staticUrlPrefix}/content/main/jquery.ui.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/qcomcn.css")" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/signup.css")" rel="stylesheet" type="text/css" />
    <script src="${staticUrlPrefix}/scripts/sea.js")" type="text/javascript"></script>
</head>
<style type="text/css">
ul.pssyao li{width:233px;}
</style>
<body>
	<div class="signup">
		<h1 class="logo2"></h1>
		
		<ul class='pssyao clear'>
            <li class="done">填写邮箱</li>
            <li class="">接收邮件</li>
			<li class="end">重置密码</li>
        </ul>
		
			<table class='qform'>
				<tr>
					<td align="right">忘记密码</td>
				</tr>
				<tr>
					<td>请到<%=request.getParameter("email")%>查阅来自Q.com.cn的邮件，点击邮件中的链接重设你的密码。</td>
				</tr>
			</table>
	</div>
</body>
</html>