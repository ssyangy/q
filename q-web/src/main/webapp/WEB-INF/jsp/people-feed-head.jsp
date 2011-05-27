<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="home-header">
				<jsp:include page="weibo-send.jsp" >
					<jsp:param name="from" value="${urlPrefix}/people/feed"/>
					<jsp:param name="selectGroup" value="true"/>
				</jsp:include>
</div>