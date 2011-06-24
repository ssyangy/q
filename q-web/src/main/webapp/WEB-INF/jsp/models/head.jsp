<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html>
<html>
<head>
<title>${param['title']}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${staticUrlPrefix}/content/qcomcn.css" rel="stylesheet"
	type="text/css" />
<script src="${staticUrlPrefix}/scripts/sea.js" type="text/javascript"></script>
<script type="text/javascript">
	var mods = [];
	var Getpush = {};
	seajs.use("qcomcn",function(q){
		q.jq(function(){
			q.loader();
			for(var i in mods) mods[i](q);

			var $ = q.jq;

			var note = $("#note");
			var n_msg = $("p.n_msg");
			var n_weiborep = $("p.n_weiborep");
			var n_fo = $("p.n_fo");
			var n_at = $("p.n_at");
			$("a.clo", note).click(function(){
				note.hide();
				window.clearInterval(pushtime);
			});
	        Getpush = function (){
        		$.ajax({
        			global: false,
        	    	url: '${pushUrlPrefix}?ids=${loginCookie.peopleId}&cmd=mine&aliveTime=1000',
        	    	dataType: 'jsonp',
       	    	    success:function(data,text,xhqr){
				        $.each(data, function(i, item) {
				            switch (item.type) {
			                case "message":
			                	if(item.content > 0) {
			                		n_msg.show();
			                		note.show();
			                	}
			                    $("span.num", n_msg).text(item.content);
			                    break;
			                case "weiboReply":
			                	if(item.content > 0) {
			                		n_weiborep.show();
			                		note.show();
			                	}
			                    $("span.num", n_weiborep).text(item.content);
			                    break;
			                case "fo":
			                	if(item.content > 0) {
			                		n_fo.show();
			                		note.show();
			                	}
			                    $("span.num", n_fo).text(item.content);
			                    break;
			                case "at":
			                	if(item.content > 0) {
			                		n_at.show();
			                		note.show();
			                	}
			                    $("span.num", n_at).text(item.content);
			                    break;
			                default:
			                	break;
			            	}
				        });
				    }
        	   });
        	}

      		initget = function(){
		    	Getpush();
		    	pushtime = setInterval("Getpush()",30000);
		    }
	        <c:if test="${loginCookie!=null}">
	        var sss = setTimeout("initget()",5000);
        	</c:if>
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
<div id="toper">
<div class="wapper"><a id='logo' href="${urlPrefix}"></a>
<div id="searchTab" class="hm1">
<div class="hm2">
<div class="hm3">
<form action="${urlPrefix}/search/weibo" onsubmit="if(this.search.value=='') return false;" method="GET">
	<input class="input_yao" type="text" name="search" value="" />
	<input type="submit" class="btnb" value="搜索" /></form>
</div>
</div>
</div>

<div id="msg"><c:choose>
	<c:when test="${loginCookie.peopleId>0}">
		<ul class="list">
			<li><c:choose>
				<c:when test="${servletPath=='/WEB-INF/jsp/getCategoryIndex.jsp'}">首页</c:when>
				<c:otherwise>
					<a class="lk" href='${urlPrefix}/category'>首页</a>
				</c:otherwise>
			</c:choose></li>
			<li><c:choose>
				<c:when test="${servletPath=='/WEB-INF/jsp/getPeopleFollowing.jsp'}">好友</c:when>
				<c:otherwise>
					<a class="lk"
						href='${urlPrefix}/following'>好友</a>
				</c:otherwise>
			</c:choose></li>
			<li class='rel pr20_im'><c:choose>
				<c:when test="${servletPath=='/WEB-INF/jsp/getPeople.jsp'}">
                               ${loginCookie.realName}
                       </c:when>
				<c:otherwise>
					<a class="lk" href='${urlPrefix}/people/${loginCookie.peopleId}'>${loginCookie.realName}</a>
				</c:otherwise>
			</c:choose> <span tgtt='minelist' class='in_bk tlistarr'></span>
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
			</li>
			<li>
				<c:choose>
					<c:when test="${servletPath=='/WEB-INF/jsp/getMessageIndex.jsp'}">私信</c:when>
					<c:otherwise>
						<a class="lk" href='${urlPrefix}/message'>私信</a>
					</c:otherwise>
				</c:choose>
			</li>
			<li>
				<c:choose>
					<c:when test="${servletPath=='/WEB-INF/jsp/getSettingBasic.jsp'}">设置</c:when>
					<c:otherwise>
						<a class="lk" href='${urlPrefix}/setting/basic'>设置</a>
					</c:otherwise>
				</c:choose>
			</li>
			<li class="end">
				<c:choose>
					<c:when test="${servletPath=='/WEB-INF/jsp/deleteLogin.jsp'}">退出</c:when>
					<c:otherwise>
						<a class="lk" href='${urlPrefix}/login/delete'>退出</a>
					</c:otherwise>
				</c:choose>
			</li>
	</ul>
	</c:when>
</c:choose>
</div>

<div id="note">
<p class="n_msg hide"><span class='num'>0</span>条新的私信<a
	class="lk ml10" href="${urlPrefix}/message">查看私信</a></p>
<p class="n_weiborep hide"><span class='num'>0</span>条新的回复<a
	class="lk ml10" href="${urlPrefix}/reply/received">查看回复</a></p>
<p class="n_fo hide"><span class='num'>0</span>位新粉丝<a
	class="lk ml10"
	href="${urlPrefix}/people/${loginCookie.peopleId}/follower">查看我的粉丝</a>
</p>
<p class="n_at hide"><span class='num'>0</span>条发言提到我<a
	class="lk ml10" href="${urlPrefix}/at">查看@我</a></p>
<a class="clo lk">关闭</a></div>

</div>
</div>
<div id="main"><div class="wapper">
