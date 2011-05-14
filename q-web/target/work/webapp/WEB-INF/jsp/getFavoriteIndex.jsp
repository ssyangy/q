<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="收藏" />
</jsp:include>
<script type="text/javascript">
    seajs.use('qcomcn.js', function (qcomcn) {
        var $ = qcomcn.jq;
        $(function () {
            qcomcn.Init();
        });
    });
</script>
<h2 class="mb20">我的收藏</h2>
<div style="border:1px solid #ddd;width:540px;margin-right:30px;float:left;">
	<jsp:include page="models/weibo-list.jsp">
		<jsp:param name="feedUrl" value="${urlPrefix}/favorite"/>
	</jsp:include>
</div>
<div style="width:240px;float:left;">
	<jsp:include page="models/profile.jsp"/>
</div>
<jsp:include page="models/foot.jsp" />