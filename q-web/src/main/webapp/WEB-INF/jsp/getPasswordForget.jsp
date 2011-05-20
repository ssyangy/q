<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="models/head-unsign.jsp">
	<jsp:param name="title" value="登陆" />
</jsp:include>
<body>
<div class="wapper">
    <h1 id="logo">Q.com.cn</h1>
    <div class="content">
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
				<button type="submit" class='ui_btn'>重置密码</button>
				</td>
			</tr>
		</table>
		</form>
    </div>
</div>
</body>
</html>