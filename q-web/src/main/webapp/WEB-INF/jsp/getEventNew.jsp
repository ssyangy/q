<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发起活动</title>
</head>
<body>
<div id="content">
	发起活动<br />
	<form action="<c:out value="${contextPath}/event" />" method="post">
	主题:<input type=text name="name" size="20" maxlength="20"> <br />
	开始时间:<input type="text" name="start" size="20" maxlength="20"
		value="${day}">
		<input type="text" name="starttime" size="20" maxlength="20"
		value="${time}">
		<br />
	结束时间:<input type="text" name="end" size="20" maxlength="20" value="${day}">
	        <input type="text" name="end" size="20" maxlength="20" value="${time}">
	<br />
	所在地: <select name="city" id="city" onchange="">
	</select> <select name="gsub_type" id="region" onchange="">
	</select><br>
	<input type="text" name="location" size="10" maxlength="10" value="暂不支持"><br />
	简介:<textarea name="intro" cols="50" rows="10"> 
	</textarea> <br />
	费用:<input type="text" name="end" size="10" maxlength="10" value="暂不支持"><br />
	人数限制:<input type="radio" name="peoplenumber" value="1" checked="checked" />无 <input type="text" name="peoplenumber" size="10" maxlength="10" value="暂不支持"/><br />
	<input type="hidden" name="groupId" value="${groupId}" />
	<input type="submit" value="创建活动" /></form>
</div>
</body>
</html>