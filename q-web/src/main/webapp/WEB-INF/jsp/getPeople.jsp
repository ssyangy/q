<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="个人主页" />
</jsp:include>
<h2>${people.realName}的个人主页</h2>
<div class="profile mb10">
	<a href="${urlPrefix}/people/${people.id}">
    <img src="${people.avatarPath}-48" alt="portrait" class="FL mr10" /></a>
    <div class='proline'>
        <p><a href="${urlPrefix}/people/${people.id}" class="lk">${people.realName}</a></p>
        <p><span class="mr10">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</span>
        	<span>${people.time}加入</span></p>
        <p class="gray">${people.intro}</p>
    </div>
</div>
<div style="padding:10px;border:1px solid #ddd;">
<ul class='slist'>
<c:forEach items="${groups}" var="group" varStatus="status">
	<a href="${urlPrefix}/group/${group.id}" class='ik'>${group.name}</a>
</c:forEach>
</ul>
<jsp:include page="models/weibo-list.jsp">
	<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/weibo" />
</jsp:include>
</div>
<jsp:include page="models/foot.jsp" />