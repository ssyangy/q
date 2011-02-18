<%@ page language="java" import="java.util.*" import="q.domain.Category" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登陆</title>
</head>

<body>
<form action="<c:out value="${contextPath}" />/??" method="post" >
用户名:<input  type=text   name= "name"  > <br/>
密码:<input   type=password name= "password"  > <br/>
<input type="reset"  value="修改"  />
<input type="submit"  value="提交" />
</form>
</body>
</html>