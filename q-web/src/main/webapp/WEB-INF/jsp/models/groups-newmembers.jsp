<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<ul class="slist">
<c:forEach items="${newPeoples}" var="fo" varStatus='stat'>
<c:choose>
	<c:when test="${!stat.last}"><li></c:when>
	<c:otherwise><li class='end'></c:otherwise>
</c:choose>
	<a href="${urlPrefix}/people/${fo.id}">
	<img src="${fo.avatarPath}-48" class="wh48" alt="img"></a>
	<div class="gray">
		<a href="${urlPrefix}/people/${fo.id}" class="lk" >${fo.realName}</a>
	</div>
	</li>
</c:forEach>
</ul>
