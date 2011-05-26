<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="个人主页" />
</jsp:include>
<style type="text/css">
ul.ulist{border-bottom:1px solid #ddd;border-top:1px solid #ddd;padding:10px;margin:10px 0;}
ul.ulist li{float:left;padding:0 10px;}
</style>
<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">
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
<ul class='ulist clear'>
<c:forEach items="${groups}" var="group" varStatus="status">
	<li>
	<a href="${urlPrefix}/group/${group.id}" class='lk'>${group.name}</a>
	</li>
</c:forEach>
</ul>
<jsp:include page="models/weibo-list.jsp">
	<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/weibo" />
</jsp:include>
</div></div>
    <div class="col-sub pt20">
    
    </div>
</div>
<jsp:include page="models/foot.jsp" />