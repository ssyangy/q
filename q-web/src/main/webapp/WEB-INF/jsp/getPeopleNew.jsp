<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登陆注册</title>
</head>
<%
//String wrong=request.getAttribute ("Wrong").toString();
request.setCharacterEncoding( "gb2312"); 
 %>
<body>
<div id="content">
	帐号信息
	<form action="<c:out value="${contextPath}" />/people" method="post" >
	登陆邮箱:<input   type=text   name= "email"  > <br/>
	设置密码<input   type=password name= "password"  > <br/>
	再输入一次密码:<input   type=password   name= "confirm_password" ></input><br/>
	圈子帐号:<input   type=text  name= "username"   ></input><br/>
	姓名:<input   type=text   name= "real_name"    ></input><br>性别: 
	<input type="radio" name="gender" value="1" checked="true">男 
	<input type="radio" name="gender" value="2">女 
	<br/>
	<input type="reset"  value="修改"  />
	<input type="submit"  value="提交" />
	</form>
</div>
</body>
</html>