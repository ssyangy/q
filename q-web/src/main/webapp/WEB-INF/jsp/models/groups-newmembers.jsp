<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<ul class="slist">
<c:forEach items="${newPeoples}" var="people" varStatus='stat'>
<c:choose>
	<c:when test="${stat.count % 3 == 0}"><li class='end'></c:when>
	<c:otherwise><li></c:otherwise>
</c:choose>
	<a href="${urlPrefix}/people/${people.id}">
	<img src="${people.avatarPath}-48" class="wh48" alt="img"></a>
	<div class="gray">
		<a href="${urlPrefix}/people/${people.id}" class="lk wb" >${people.realName}</a>
	</div>
	</li>
</c:forEach>
</ul>
