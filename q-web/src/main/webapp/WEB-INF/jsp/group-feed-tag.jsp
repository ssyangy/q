<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix stream-tabs">
<c:choose>
<c:when test="${'' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text" >新话题</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/group/feed">新话题</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'hot' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">热议</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/group/feed?tab=hot">热议</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'created' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">我发起的</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/group/feed?tab=created">我发起的</a>
	</li>
</c:otherwise>
</c:choose>	
<%-- c:choose>
<c:when test="${'replied' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">我回复的</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/group/feed?tag=replied">我回复的</a>
	</li>
</c:otherwise>
</c:choose --%>
<%-- c:choose>
<c:when test="${'favorite' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">我收藏的</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/group/feed?tag=favorite">我收藏的</a>
	</li>
</c:otherwise>
</c:choose --%>
</ul>