<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="提到我的" />
</jsp:include>
<style>
li.streambox{padding:15px 20px;}
</style>
<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">
    <h2 style="margin-bottom:15px;">&#64提到我的</h2>
    <div style="border:1px solid #dcdcdc;">
	<jsp:include page="models/weibo-list.jsp">
		<jsp:param name="feedUrl" value="${urlPrefix}/at" />
	</jsp:include>
	</div>
</div></div>
    <div class="col-sub">
        <jsp:include page="models/profile.jsp"/>
    </div>
</div>

<jsp:include page="models/foot.jsp" />
