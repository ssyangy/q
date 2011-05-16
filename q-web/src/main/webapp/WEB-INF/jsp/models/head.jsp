<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html>
<html>
<head>
    <title>${param['title']}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="${staticUrlPrefix}/content-q/qcomcn.css" rel="stylesheet" type="text/css" />
<script src="${staticUrlPrefix}/scripts-q/sea.js" type="text/javascript"></script>
<script type="text/javascript">
    window.loginCookie = '${loginCookie.peopleId}';
    window.urlprefix = '${urlPrefix}';
</script>
<jsp:include page="js-common.jsp" />
</head>
<body>
<div class="body">
    <div id="toper"><div class="wapper">
        <a id='logo'>Q.com.cn</a>
        <div id="msg">
            <ul class="list">
                <li><a href='${urlPrefix}/category'>首页</a></li>
                <li class='rel'><a class="lk" href='${urlPrefix}/people/${loginCookie.peopleId}'>${loginCookie.realName}</a>
                <span target='minelist' class='in_bk tlistarr'></span>
		        <div id="minelist" class='tgtbox'>
		            <ul class="dlist">
		                <li><a class='lk' href='${urlPrefix}/people/${loginCookie.peopleId}'>我的主页</a></li>
		                <li><a class="lk" href='${urlPrefix}/at'>&#64提到我的</a></li>
		                <li><a class="lk" href='${urlPrefix}/reply'>我的回复</a></li>
		                <li class="end"><a class="lk" href='${urlPrefix}/favorite'>我的收藏</a></li>
		            </ul>
		        </div>
                </li>
                <li><a class="lk" href='${urlPrefix}/message'>私信</a></li>
                <li><a class="lk" href='${urlPrefix}/setting/basic'>设置</a></li>
                <li class="end"><a class="lk" href='${urlPrefix}/login/delete'>退出</a></li>
            </ul>
        </div>
        <div class="search">
            <input class="search_inp mttext_val" type="text" name="name" value="搜索圈子、信息" />
            <input type="submit" class="search_btn" title="搜索" value="" />
        </div>

    </div></div>

    <div id="main"><div class="wapper">
