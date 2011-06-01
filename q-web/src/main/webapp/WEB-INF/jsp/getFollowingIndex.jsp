<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="我的关注" />
</jsp:include>
<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">
        <p class='simptab mb10'>
        	<span class='fw'>
        	<c:choose><c:when test="${loginCookie.peopleId==people.id}">我</c:when><c:otherwise>${people.realName}</c:otherwise></c:choose>的关注（${people.followingNum}）</span>
        	<span class='split'>|</span>
        	<a class="lk" href="${urlPrefix}/follower">
        	<c:choose><c:when test="${loginCookie.peopleId==people.id}">我</c:when><c:otherwise>
        	${people.realName}</c:otherwise></c:choose>的粉丝（${people.followerNum}）</a>
       	</p>
        <jsp:include page="models/people-list.jsp">
			<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/following" />
			<jsp:param name="orderId" value="relation" />
		</jsp:include>
    </div></div>
    <div class="col-sub">
        <h3>可能感兴趣的</h3>
		<jsp:include page="models/peoples-recommend.jsp" />
    </div>
</div>
<jsp:include page="models/foot.jsp" />