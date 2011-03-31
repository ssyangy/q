<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>好友新鲜事</title>
	<style type="text/css"> 
	  #tabs{border:0 none;}
	  div.waitSlideDown{display:none;}
	  div.extend2{position:absolute;top:0;left:0;}
	  .tabs-widget{padding:0 10px;}
	</style> 
	<script id="tweet" type="text/html"> 
    <div class="stream-item tweet waitSlideDown" weiboId="{{id}}">
        <div class="tweet-image">
			<a href="${urlPrefix}/people/{{#people}}{{id}}{{/people}}">
				<img height="48" width="48" src="${avatarUrlPrefix}/{{#people}}{{avatarPath}}{{/people}}-48">
			</a>
		</div>
        <div class="tweet-content">
            <div class="tweet-row">
                <span class="tweet-user-name">
					<a href="${urlPrefix}/people/{{#people}}{{id}}{{/people}}" class="tweet-screen-name user-profile-link">{{#people}}{{screenName}}{{/people}}</a>
				</span>
                {{#soures}}<span class="tweet-group">发自 <a href="">{{soures}}</a></span>{{/soures}}
            </div>
            <div class="tweet-row">
				<div class="tweet-text">{{text}}</div>
			</div>
			{{#quote}}
			<div class="tweet-ori"> 
				<div class="tweet-ori-inner"> 
					<a href="${urlPrefix}/people/{{#people}}{{id}}{{/people}}" class="tweet-ori-author">{{#people}}{{screenName}}{{/people}}</a>：
					{{text}}
					<span class="">
						<a href="${urlPrefix}/weibo/{{id}}">原文转发</a>
						<span class="link-sep">·</span>
						<a href="${urlPrefix}/weibo/{{id}}">原文回复</a>
					</span> 
				</div> 
			</div>
			{{/quote}}
            <div class="tweet-row">
                <a href="" class="tweet-timestamp">{{screenTime}}</a>
                <span class="tweet-actions"><a href="">赞</a><a href="">转发</a><a href="">收藏</a><a href="">回复</a></span>
            </div>
        </div>
    </div>
	</script>	
	<script type="text/javascript">
	var ajlock = true;
		$(function () {
			//$('#tabs').tabs();
			//$tabs.tabs('select', 0);
	        var body = $('#body');
	        var o = body[0];
	        body.scroll(function () {
	            if (o.scrollTop + winHeight >= o.scrollHeight) {
	            	if(ajlock){
	            		ajlock = false;
					var item = $('div.stream-items .stream-item');
		            $.ajax(
			            {
						    url: '${urlPrefix}/people/feed',
						    type: 'GET',
						    dataType: 'json',
						    data: {size:2, startId:item.last().attr('weiboId')},
						    timeout: 5000,
						    error: function(msg){
						    },
						   	success: function(json){
							   	$(json).each(function(){
							   		$('div.stream-items').append(ich.tweet(this));
				                    $('div.waitSlideDown').slideDown("slow", function () {
				                        $(this).removeClass('waitSlideDown');
				                    });
							   	});
						    },
						    complete: function(){
						    	ajlock = true;
						    }
					    }
				   	);
				   	}
	            }
		    });
	    });
 
	    var winHeight;
	    var ReSet = function () {
	        winHeight = $(window).height() + 10;
	        $("#body").height(winHeight - 18);
	    }
	</script>	
</head>
<body onResize="ReSet()" onLoad="ReSet()">
	<div id="body" style="padding-bottom:0px;overflow-y:auto; overflow-x:hidden;"}>
		<jsp:include page="top.jsp" />
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<jsp:include page="people-feed-head.jsp"></jsp:include>
					<div class="stream-manager">
						<div id="tabs" class="ui-tabs ui-widget">
							<jsp:include page="people-feed-tag.jsp">
								<jsp:param value="${param['tab']}" name="tab"/>
							</jsp:include>
							<div id="tabs-1">
								<c:choose>
								<c:when test="${'replied' == param['tab']}">
									<jsp:include page="reply-list.jsp" />
								</c:when>
								<c:otherwise>
									<jsp:include page="weibo-list.jsp" />
								</c:otherwise>
								</c:choose>
							</div>
						</div>					
					</div>
				</div>
				<jsp:include page="people-feed-dashboard.jsp"/>
			</div>
		</div>
	</div>
</body>
</html>