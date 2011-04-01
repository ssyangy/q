<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix stream-tabs">
<c:choose>
<c:when test="${null == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text" >新微博</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/people/feed">新微博</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'at' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">@我的</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/people/feed?tab=at">@我的</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'replied' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">我回复的</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/people/feed?tab=replied">我回复的</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'favorite' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">我收藏的</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/people/feed?tab=favorite">我收藏的</a>
	</li>
</c:otherwise>
</c:choose>
</ul>