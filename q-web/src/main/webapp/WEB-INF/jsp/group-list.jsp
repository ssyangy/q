<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="stream-items search members">
<c:forEach items="${groups}" var="group">
<div class="action">
		<a href="${urlPrefix}/group/${group.id}">
			<span class="display-name">${group.name}</span>
			<br></br>
			<span class="intro">${group.intro}</span>
		</a>
</div>
</c:forEach>
</div>
