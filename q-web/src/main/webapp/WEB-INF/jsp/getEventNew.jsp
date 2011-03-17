<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<jsp:include page="head.jsp" flush="true"/>
	<title>发起活动</title>
	<jsp:include page="js-areas.jsp" flush="true"/>
	</head>
	<body>
		<div id="content">
			发起活动<br />
			<form action="${contextPath}/event" method="post">
			主题:<input type=text name="name" size="20" maxlength="20"> <br />
			开始时间:<input type="text" name="startDate" size="20" maxlength="20" value="${day}">
				    <input type="text" name="startTime" size="20" maxlength="20" value="${time}">
				<br />
			结束时间:<input type="text" name="endDate" size="20" maxlength="20" value="${day}">
			        <input type="text" name="endTime" size="20" maxlength="20" value="${time}">
			<br />
			所在地: 
			<select class='select' name="province" id="selProvince"  onchange="changeCity()">
			</select>
			<select class='select' name="city" id="selCity" onchange="changeCounty()">
			</select>
			<select class='select' name="county" id="selCounty">
			</select>
			<input type="text" name="location" size="10" maxlength="10" value=""><br />
			简介:
			<textarea name="intro" cols="50" rows="10"></textarea> <br />
			费用:
			<input type="text" name="cost" size="10" maxlength="10" value="暂不支持"><br />
			人数限制:
			<input type="radio" name="zeroNumber" value="0" checked="checked" />无 
			<input type="text" name="number" size="10" maxlength="10" value=""/><br />
			<input type="hidden" name="groupId" value="${groupId}" />
			<input type="submit" value="创建活动" /></form>
		</div>
	</body>
</html>