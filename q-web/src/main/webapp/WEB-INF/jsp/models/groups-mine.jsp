<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>

<link href='${staticUrlPrefix}/content/navleft.css' rel='stylesheet' type='text/css' />
<div class="navleft">

<c:if test="${loginCookie != null}">
<div class="avator">
    <a href="${urlPrefix}/people/${loginCookie.peopleId}">
    <img src="${loginCookie.avatarPath}-128" class="max-w120" alt="portraitt" /></a>
    <p><a href="${urlPrefix}/people/${loginCookie.peopleId}" class="lk">${loginCookie.realName}</a></p>
    <p><a href="${urlPrefix}/people/${loginCookie.peopleId}" class="lk">@${loginCookie.username}</a></p>
</div>
</c:if>

<div class="creat">
<a class="lk mr20" href="${urlPrefix}/category">找圈子</a><a class="lk" href="${urlPrefix}/group/new">建圈子</a>
</div>

<div class='unav_group'>
	<c:forEach items="${groups}" var="group" varStatus="status">
		<c:choose>
		<c:when test="${param['id'] == group.id}">
			<a href="${urlPrefix}/group/${group.id}" class='in_bk unav_item unav_action'>${group.name}</a>
		</c:when>
		<c:otherwise>
			<a href="${urlPrefix}/group/${group.id}" class='in_bk unav_item'>${group.name}</a>
		</c:otherwise>
		</c:choose>	
	</c:forEach>
</div>

</div>