<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix stream-tabs">
<c:choose>
<c:when test="${'basic' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">密码</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/setting/basic">密码</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'avatar' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">手机绑定</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/setting/basic">手机绑定</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'interest' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">换肤</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/setting/basic">换肤</a>
	</li>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${'interest' == param['tab']}">
	<li class="tab-text ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
		<a class="tab-text">隐私设置</a>
	</li>
</c:when>
<c:otherwise>
	<li class="tab-text ui-state-default ui-corner-top">
		<a class="tab-text" href="${urlPrefix}/setting/basic">隐私设置</a>
	</li>
</c:otherwise>
</c:choose>


</ul>