<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>

<link href='${staticUrlPrefix}/content-q/navleft.css' rel='stylesheet' type='text/css' />
<div class='unav_group'>
	<c:choose>
	<c:when test="${param['id']} == 0">
		<a href='${urlPrefix}/category' class='in_bk unav_item unav_action'>找圈子</a>
	</c:when>
	<c:otherwise>
		<a href='${urlPrefix}/category' class='in_bk unav_item'>找圈子</a>
	</c:otherwise>
	</c:choose>	

	<c:forEach items="${groups}" var="group" varStatus="status">
		<c:choose>
		<c:when test="${param['id']} == ${group.id}">
			<a href="${urlPrefix}/group/${group.id}" class='in_bk unav_item unav_action'>${group.name}</a>
		</c:when>
		<c:otherwise>
			<a href="${urlPrefix}/group/${group.id}" class='in_bk unav_item'>${group.name}</a>
		</c:otherwise>
		</c:choose>	
	</c:forEach>
</div>