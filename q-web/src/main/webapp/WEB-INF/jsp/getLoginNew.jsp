<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登陆</title>
    <link href="${staticUrlPrefix}/content-q/qcomcn.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    .wapper{position:relative;height:600px;}
    #logo{top:200px;font-size:28px;}
    .content{position:absolute;top:260px;left:60px;}
    p{line-height:26px;}
    </style>
	<script src="${staticUrlPrefix}/scripts-q/sea.js" type="text/javascript"></script>
    <script type="text/javascript">
        seajs.use('qcomcn.js', function (q) {
            var $ = q.jq;
            $(function () {
                q.Init();
            });
        });
    </script>
</head>
<body>
<div class="wapper">
    <h1 id="logo">Q.com.cn</h1>
    <div class="content">
    	<form action="${urlPrefix}/login" method="post">
        <table class='qform'>
			<tr>
				<td align="right">邮箱：</td>
				<td><input name="username" type='text' class='mttext' size='23'></td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td><input name="password" type='password' class='mttext' size='23'><a href="#" class='lk ml10'>忘记密码</a></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="checkbox"> 记住我</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<input type="hidden" name="from" value="${from}">
				<button type="submit" class='ui_btn'>提 交</button>
				</td>
			</tr>
		</table>
		</form>
        <br />
        <a href="${urlPrefix}/people/new" class='lk f14'>还不是Q.com.cn用户？立即注册！</a>
    </div>
</div>
</body>
</html>