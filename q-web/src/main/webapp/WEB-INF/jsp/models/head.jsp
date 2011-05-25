<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html>
<html>
<head>
<title>${param['title']}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${staticUrlPrefix}/content-v1/qcomcn.css" rel="stylesheet"
	type="text/css" />
<script src="${staticUrlPrefix}/scripts/sea.js"></script>
<script type="text/javascript">
	var mods = [];
	seajs.use("qcomcn",function(q){
		q.jq(function(){
			for(var i in mods) mods[i](q);
			q.init();
			var $ = q.jq;
	        var hasNewInformation = function (){
        		$.ajax({
        			//crossDomain:true,
        			global: false,
        	    	url: '${pushUrlPrefix}?ids=${loginCookie.peopleId}&cmd=mine&aliveTime=1000',
        	    	type: 'GET',
        	    	dataType: 'jsonp',
       	    	    complete:function(data,text,xhqr){
				    },
       	    	    success:function(data,text,xhqr){
				        $.each(data, function(i, item) {
				            console.log(item);
				        });
				    }				    
        	   });
        	}
		    <c:if test="${loginCookie!=null}">
        	hasNewInformation();
        	</c:if>
		});
	});
	window.loginCookie = '${loginCookie.peopleId}';
	window.urlprefix = '${urlPrefix}';


</script>
<jsp:include page="js-common.jsp" />
</head>
<body>
<c:set var="servletPath" value="${pageContext.request.servletPath}" />
<div id="body">
<div id="toper">
<div class="wapper"><a id='logo'>Q.com.cn</a>
<div id="msg">
<ul class="list">
	<li><c:choose>
		<c:when test="${servletPath=='/WEB-INF/jsp/getCategoryIndex.jsp'}">首页</c:when>
		<c:otherwise>
			<a class="lk" href='${urlPrefix}/category'>首页</a>
		</c:otherwise>
	</c:choose></li>
	<li><c:choose>
		<c:when test="${loginCookie.peopleId>0}">

			<c:choose>
				<c:when test="${servletPath=='/WEB-INF/jsp/getPeopleFollowing.jsp'}">好友</c:when>
				<c:otherwise>
					<a class="lk"
						href='${urlPrefix}/people/${loginCookie.peopleId}/following'>好友</a>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose></li>
	<li class='rel'><c:choose>
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
				<li><a class='lk'
					href='${urlPrefix}/people/${loginCookie.peopleId}'>我的主页</a></li>
				<li><a class="lk" href='${urlPrefix}/at'>&#64;提到我的</a></li>
				<li><a class="lk" href='${urlPrefix}/reply/received'>我的回复</a></li>
				<li class="end"><a class="lk" href='${urlPrefix}/favorite'>我的收藏</a>
				</li>
			</ul>
			</div>
		</c:when>
	</c:choose></li>

	<li><c:choose>
		<c:when test="${loginCookie.peopleId>0}">
			<c:choose>
				<c:when test="${servletPath=='/WEB-INF/jsp/getMessageIndex.jsp'}">私信</c:when>
				<c:otherwise>
					<a class="lk" href='${urlPrefix}/message'>私信</a>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose></li>
	<li><c:choose>
		<c:when test="${loginCookie.peopleId>0}">
			<c:choose>
				<c:when test="${servletPath=='/WEB-INF/jsp/getSettingBasic.jsp'}">设置</c:when>
				<c:otherwise>
					<a class="lk" href='${urlPrefix}/setting/basic'>设置</a>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose></li>
	<li class="end"><c:choose>
		<c:when test="${loginCookie.peopleId>0}">
			<c:choose>
				<c:when test="${servletPath=='/WEB-INF/jsp/deleteLogin.jsp'}">退出</c:when>
				<c:otherwise>
					<a class="lk" href='${urlPrefix}/login/delete'>退出</a>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose></li>
</ul>
</div>
<div class="search">
<form action="${urlPrefix}/search/weibo" method="GET"><input
	class="search_inp mttext_val" type="text" name="search" value="搜索圈子、信息" />
<input type="submit" class="search_btn" title="搜索" value="" />
</form>
</div>

<div id="note" style="display: block">
<p>1条新的私信<a class="lk" href="${urlPrefix}/message">查看私信</a></p>
<p>1条新的回复<a class="lk" href="${urlPrefix}/reply/received">查看回复</a></p>
<p>3位新粉丝<a class="lk"
	href="${urlPrefix}/people/${loginCookie.peopleId}/follower">查看我的粉丝</a></p>
<p>6条发言提到我<a class="lk" href="${urlPrefix}/at">查看@我</a></p>
</div>
</div>
</div>

<div id="main">
<div class="wapper">