<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="models/head-unsign.jsp">
	<jsp:param name="title" value="登陆" />
</jsp:include>
<body>
	<div class="wapper">
		<h1 id="logo">Q.com.cn</h1>
		<div class="content">
			<table class='qform'>
				<tr>
					<td align="right">忘记密码</td>
				</tr>
				<tr>
					<td>请到<%=request.getParameter("email")%>查阅来自Q.com.cn的邮件，点击邮件中的链接重设你的密码。</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>