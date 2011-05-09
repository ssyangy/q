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
</head>
<body>
<div class="body">
    <div id="toper"><div class="wapper">
        <a id='logo'>Q.com.cn</a>
        <div id="nav">
            <ul class="list">
                <li><a href='#'>首页</a></li>
                <li><a class="lk" href='#'>Q博</a></li>
                <li><a class="lk" href='/group'>圈子</a></li>
                <li><a class="lk" href='#'>应用</a></li>
                <li><a class="lk" href='/friends'>好友</a></li>
                <li class="end"><a class="lk" href='#'>我自己</a></li>
            </ul>
        </div>
        <div class="search">
            <input class="search_inp mttext_val" type="text" name="name" value="搜索圈子、信息" />
            <input type="submit" class="search_btn" title="搜索" value="" />
        </div>
        <div id="msg">
            <ul class="list">
                <li><a id="btnnote" class="lk" href="/qcomcn/MsgInbox">私信</a></li>
                <li class="end"><a class="lk" href="#">通知</a></li>
            </ul>
        </div>
        <div id="login">
            <ul class="list">
                <li><a class="lk" href="/qcomcn/Setting">设置</a></li>
                <li class="end"><a class="lk" href="/qcomcn/SignIn">退出</a></li>
            </ul>
        </div>
        <div id="note">
            <p>1条新的回应<a class="lk">查看回应</a></p>
            <p>3位新粉丝<a class="lk">查看我的粉丝</a></p>
            <p>6条发言提到我<a class="lk">查看&#64我</a></p>
        </div>
    </div></div>

    <div id="main"><div class="wapper">