<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html>
<html>
<head>
<title>${param['title']}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${staticUrlPrefix}/content/qcomcn.css" rel="stylesheet" type="text/css" />
<script src="${staticUrlPrefix}/scripts/sea.js"></script>
<script type="text/javascript">
	var mods = [];
	seajs.use("qcomcn",function(q){
		q.jq(function(){
			for(var i in mods) mods[i](q);
			q.init();
		});
	});
	window.loginCookie = '${loginCookie.peopleId}';
	window.urlprefix = '${urlPrefix}';
	function errorType(error){
		  var exist=error.indexOf(':');
		  if(exist>-1){
		    var errorkind=error.substring(0, exist);
		    return errorkind;
		  } else{
			return null;
		  }
		}
	function errorContext(error){
	 var exist=error.indexOf(':');
	  if(exist>-1){
	    var errorcontext=error.substring(exist+1, error.length);
	    return errorcontext;
	  } else{
		return null;
	  }
	}
</script>
</head>
<body>
<c:set var="servletPath" value="${pageContext.request.servletPath}" />
<div id="body">
<div id="toper">
<div class="wapper">
<a id='logo'></a>
<div id="searchTab" class="hm1"><div class="hm2"><div class="hm3">
<form action="${urlPrefix}/search/weibo" method="GET">
    <input class="input_yao" type="text" name="search" value="" />
    <input type="submit" class="btnb2" value="搜索" />
</form>
</div></div></div>

<div id="msg">
<ul class="list">
	<li>
	<c:choose>
		<c:when test="${servletPath=='/WEB-INF/jsp/getCategoryIndex.jsp'}">首页</c:when>
		<c:otherwise>
			<a class="lk" href='${urlPrefix}/category'>首页</a>
		</c:otherwise>
	</c:choose></li>
	<li>
	<c:choose>
	  <c:when test="${loginCookie.peopleId>0}">

	<c:choose>
		<c:when test="${servletPath=='/WEB-INF/jsp/getPeopleFollowing.jsp'}">好友</c:when>
		<c:otherwise>
			<a class="lk" href='${urlPrefix}/people/${loginCookie.peopleId}/following'>好友</a>
		</c:otherwise>
	</c:choose>
	</c:when>
	</c:choose>
	</li>
                <li class='rel pr20_im'>
                	<c:choose>
	  <c:when test="${loginCookie.peopleId>0}">
                 <c:choose>
                       <c:when test="${servletPath=='/WEB-INF/jsp/getPeople.jsp'}">
                               ${loginCookie.realName}
                       </c:when>
                       <c:otherwise>
                               <a class="lk" href='${urlPrefix}/people/${loginCookie.peopleId}'>${loginCookie.realName}</a>
                       </c:otherwise>
               </c:choose>
                <span tgtt='minelist' class='in_bk tlistarr'></span>
                       <div id="minelist" class='tgtbox'>
                           <ul class="dlist">
                               <li><a class='lk' href='${urlPrefix}/people/${loginCookie.peopleId}'>我的主页</a></li>
                               <li><a class="lk" href='${urlPrefix}/at'>&#64;提到我的</a></li>
                               <li><a class="lk" href='${urlPrefix}/reply/received'>我的回复</a></li>
                               <li class="end"><a class="lk" href='${urlPrefix}/favorite'>我的收藏</a></li>
                           </ul>
                       </div>
                       </c:when>
	</c:choose>
                </li>

	<li>
		<c:choose>
	  <c:when test="${loginCookie.peopleId>0}">
	<c:choose>
		<c:when test="${servletPath=='/WEB-INF/jsp/getMessageIndex.jsp'}">私信</c:when>
		<c:otherwise><a class="lk" href='${urlPrefix}/message'>私信</a></c:otherwise>
	</c:choose>
		</c:when>
	</c:choose>
	</li>
	<li>
	<c:choose>
	  <c:when test="${loginCookie.peopleId>0}">
	<c:choose>
		<c:when test="${servletPath=='/WEB-INF/jsp/getSettingBasic.jsp'}">设置</c:when>
		<c:otherwise><a class="lk" href='${urlPrefix}/setting/basic'>设置</a></c:otherwise>
	</c:choose>
	</c:when>
	</c:choose>
	</li>
	<li class="end">
	<c:choose>
	  <c:when test="${loginCookie.peopleId>0}">	<c:choose>
		<c:when test="${servletPath=='/WEB-INF/jsp/deleteLogin.jsp'}">退出</c:when>
		<c:otherwise><a class="lk" href='${urlPrefix}/login/delete'>退出</a></c:otherwise>
	</c:choose></c:when>
	</c:choose>
	</li>
</ul>
</div>

<div id="note">
	 <p>1条新的私信<a class="lk" href="${urlPrefix}/message">查看私信</a></p>
     <p>1条新的回复<a class="lk" href="${urlPrefix}/reply/received">查看回复</a></p>
     <p>3位新粉丝<a class="lk" href="${urlPrefix}/people/${people.id}/follower">查看我的粉丝</a></p>
     <p>6条发言提到我<a class="lk" href="${urlPrefix}/at">查看@我</a></p>
</div>
</div>
</div>

<div id="main">
<div class="wapper">