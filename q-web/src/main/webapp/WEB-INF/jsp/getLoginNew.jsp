<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head-unsign.jsp">
	<jsp:param name="title" value="登陆" />
</jsp:include>
<body>
<div class="wapper">
    <h1 id="logo">Q.com.cn</h1>
    <div class="content">
    	<form action="${urlPrefix}/login" method="post">
        <table class='qform'>
			<tr>
				<td align="right">邮箱：</td>
				<td><input name="email" type='text' class='mttext' size='23'></td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td><input name="password" type='password' class='mttext' size='23'><a href="${urlPrefix}/password/forget" class='lk ml10'>忘记密码</a></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="checkbox"> 记住我</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<inut type="submit" class='btnr' value="登 录" />
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