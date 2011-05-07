<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head.jsp">
	<jsp:param name="title" value="圈子新鲜事" />
</jsp:include>
<jsp:include page="weibo-list.jsp">
	<jsp:param name="feedUrl" value="${urlPrefix}/group/feed" />
</jsp:include>
<jsp:include page="foot.jsp" />