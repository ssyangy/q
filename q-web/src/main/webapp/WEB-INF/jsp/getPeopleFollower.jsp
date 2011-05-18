<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="我的粉丝" />
</jsp:include>
<jsp:include page="models/people-list.jsp"></jsp:include>
<jsp:include page="models/foot.jsp" />