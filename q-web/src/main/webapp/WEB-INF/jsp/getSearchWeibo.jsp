<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="搜索发言" />
</jsp:include>
<div class="layout grid-m0s7">
<div class="col-main"><div class="main-wrap pr10">
<h2 class='mt20'>搜索</h2>
<div id='searchpage' class="search">
	<form action="${urlPrefix}/search/weibo" method="GET">
    <input class="search_inp mttext_val" type="text" name="search" value="${param['search']}" />
    <input type="submit" class="search_btn" value="搜索" />
	</form>    
</div>
<div class="ui-tabs mt10">
    <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
        <li class="ui-state-default crt2 ui-state-active"><a href="${urlPrefix}/search/weibo">发言</a></li>
        <li class="ui-state-default crt2"><a href="${urlPrefix}/search/group">圈子</a></li>
        <li class="ui-state-default crt2"><a href="${urlPrefix}/search/people">成员</a></li>
    </ul>
</div>
<div class='tabscont'>
	<jsp:include page="models/weibo-list.jsp" >
		<jsp:param name="search" value="${param['search']}"/>
	</jsp:include>
</div>
</div></div>
<div class="col-sub pt20">
    <h3>热门搜索词</h3>
</div>
</div>