<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<ul class="stream-tabs">
	<li class="stream-tab"><a class="tab-text" href="#tabs-1">新微博</a></li>
	<c:if test="${loginCookie.peopleId == people.id }">
		<li class="stream-tab"><a class="tab-text" href="#tabs-2">收藏</a></li>
	</c:if>
	<li class="stream-tab"><a class="tab-text" href="#tabs-3">好友</a></li>
	<li class="stream-tab"><a class="tab-text" href="#tabs-4">关注</a></li>
	<li class="stream-tab"><a class="tab-text" href="#tabs-5">粉丝</a></li>
</ul>