<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>创建圈子分类</title>
</head>
<body>
申请创建圈子分类
<form action="<c:out value="${contextPath}" />/category" method="post" >
圈子分类名称:<input   type=text   name= "name"  size="20"  maxlength="20" > <br/>
圈子分类介绍:<textarea   name="intro"     cols="50" rows="10"  > 
</textarea>
<br/>
<input type="submit"  value="提交" />
</form>
</body>
</html>