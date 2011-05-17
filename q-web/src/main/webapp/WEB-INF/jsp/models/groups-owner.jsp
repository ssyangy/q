<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<p>由${group.creator.realName}创建于${group.time}</p><br />
<c:choose>
	<c:when test="${join == null}">
			<a class="btn" href="#" onclick="joinGroup(this,'${group.id}')">加入</a>
	</c:when>
	<c:otherwise>
			<a class="btn btnw24" href="#" onclick="unJoinGroup(this,'${group.id}')">已加入,退出</a>
	</c:otherwise>
</c:choose>
<c:if test="${loginCookie.peopleId == group.creator.id}">			
	<a href="${urlPrefix}/group/${group.id}/edit" class='btna'>管理</a>
</c:if>
