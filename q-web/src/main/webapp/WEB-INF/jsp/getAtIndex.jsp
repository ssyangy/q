<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="提到我的" />
</jsp:include>
<h2 class="mb20">&#64提到我的</h2>
<div style="border:1px solid #ddd;width:540px;margin-right:30px;float:left;">
	<jsp:include page="models/weibo-list.jsp">
		<jsp:param name="feedUrl" value="${urlPrefix}/at" />
	</jsp:include>
</div>
<div style="width:240px;float:left;">
    <jsp:include page="models/profile.jsp"/>
</div>
<jsp:include page="models/foot.jsp" />
