<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${null != param['feedUrl']}">
	<script id="tweet" type="text/html"> 
    <div class="stream-item tweet waitSlideDown" weiboId="{{id}}">
        <div class="tweet-image">
			{{#people}}
			<a href="${urlPrefix}/people/{{id}}">
				<img height="48" width="48" src="${avatarUrlPrefix}/{{avatarPath}}-48">
			</a>
			{{/people}}
		</div>
        <div class="tweet-content">
            <div class="tweet-row">
				{{#people}}
                <span class="tweet-user-name">
					<a href="${urlPrefix}/people/{{id}}" class="tweet-screen-name user-profile-link">{{screenName}}</a>
				</span>
				{{/people}}
                <span class="tweet-group">发自<a href="${urlPrefix}{{fromUrl}}">{{fromName}}</a></span>
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
                <span class="tweet-actions">
					<!--<a href="">赞</a>
					<span class="link-sep">·</span>-->
					<a href="">收藏</a>
					<span class="link-sep">·</span><a href="#" class='btn_ret'>转发</a>
					<span class="link-sep">·</span><a href="#" class='btn_rep'>回复</a>
				</span>
            </div>
        </div>
    </div>
	</script>
	<script id="tweetexp" type="text/html">
					{{#weibo}}
					<div id='twrep' class='mb10' weiboid="{{id}}">
					<div class="tw_head">
					{{#people}}
					<a href="${urlPrefix}/people/{{id}}">
                        <img class="wh48" src="${avatarUrlPrefix}/{{avatarPath}}-48" alt="head" />
					</a>
                    <h4><a class='link' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a></h4>
					{{/people}}
                    </div>
                    <div class="twtxt mt10">
						<div class='rcontent'>{{text}}</div>
						<div class="mt10">
						<span class='gray mr10'>{{screenTime}}</span>
						<span class="gray">发自<a href="${urlPrefix}{{fromUrl}}">{{fromName}}</a></span>
						<a class="link ml10 FR btn_hrep" href="#">回复</a>
						<a class="link ml10 FR btn_hret" href="#">转发</a>
						<a class="link ml10 FR" href="#">收藏</a>
						</div>
                    </div>
					</div>
					{{/weibo}}
					{{#replies}}
                    <div class="tweet_rep" weiboid="{{quoteWeiboId}}" replyid="{{id}}">
						{{#people}}
						<a href="${urlPrefix}/people/{{id}}">
                        	<img class="wh48" src="${avatarUrlPrefix}/{{avatarPath}}-48" alt="head" />
						</a>
                    	<a class='link peop' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>
						{{/people}}
                        <span class="gray ml10">{{screenTime}}</span>
						<span class="gray ml10">发自<a href="${urlPrefix}{{fromUrl}}">{{fromName}}</a></span>
						<a class="link ml10 FR btn_rrep">回复</a>
						<a class="link ml10 FR btn_rret">转发</a>
						<a class="link FR" href="#">收藏</a>
                        <div class="mt10 twtxtr">{{text}}</div>
                    </div>
					{{/replies}}

	</script>
	<script type="text/javascript">
	var ajlock = true;
		$(function () {
			
			
	        $('div.tweet').live('click',function (e) {
	        	if($(e.target).get(0).tagName != 'A'){
	        		var twid = $(this).attr('weiboid');
	        		
	        		$.ajax({
					    url: '${urlPrefix}/weibo/' + twid,
					    type: 'GET',
					    dataType: 'json',
					    data: {size:8, startId:99999999999999999},
					    timeout: 5000,
					    msg:this,
					   	success: function(json){
			            	var o = $('div.tweetexpand');
			        		o.empty().append(ich.tweetexp(json));
			            	o.css('left', '0');
			            	o.animate({ left: 540 }, 500, 'swing');
					    }
	                });
	        	}
	        });
	        
	        window.onresize = window.onload = function () {
	            gWinHeight = $(window).height();
	            $("#body").height(gWinHeight);
	        };
	        var body = $('#body');
	        var o = body[0];
	        body.scroll(function () {
	            if (o.scrollTop + gWinHeight >= o.scrollHeight) {
	            	if(ajlock){
	            		ajlock = false;
					var item = $('div.stream-items .stream-item');
		            $.ajax(
			            {
						    url: "${param['feedUrl']}?tab=${param['tab']}",
						    type: 'GET',
						    dataType: 'json',
						    data: {size:8, startId:parseInt(item.last().attr('weiboId'))-1},
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
	        
	          $('#dia_ret').dialog({
	              resizable: false,
	              modal: true,
	              autoOpen: false,
	              hide: "drop",
	              width:350,
	              buttons: {
	                  "转发": function () {
	                	  $('img.ajaxload', this).show();
	                	  var dia = $('#dia_ret');
	                	  $.ajax({
							    url: $("input[type='hidden']",dia).val(),
							    type: 'POST',
							    dataType: 'json',
							    data: {content:$("textarea[name='content']",dia).val()},
							    timeout: 5000,
							    msg:this,
							   	success: function(m){
							   		$(this.msg).dialog("close");
							   		$('img.ajaxload', this.msg).hide();
								   	//...
							    }
	                	  });
	                  },
	                  "取消": function () {
	                      $(this).dialog("close");
	                  }
	              }
	          });
		      $("a.btn_ret").click(function () {
		    	  var dia = $('#dia_ret');
		    	  var tweet = $(this).closest('div.tweet');
		    	  var rows = tweet.find('div.tweet-row');
		    	  $('div.wcontent',dia).empty().html(rows.eq(1).html());
		    	  $('div.wpeople',dia).empty().html(rows.eq(0).html());
		    	  $("textarea[name='content']",dia).empty().val('//@'+$('span.tweet-user-name',rows.eq(0)).text().trim());
		    	  $("#ret_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/retweet');
		    	  dia.dialog("open");
		      });
		      $("a.btn_rret").live('click',function () {
		    	  var dia = $('#dia_ret');
		    	  var tweet = $(this).closest('div.tweet_rep');
		    	  $('div.wcontent',dia).empty().html($('div.twtxtr',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('a.peop',tweet).html());
		    	  $("textarea[name='content']",dia).empty().val('//@'+$('a.peop',tweet).text().trim());
		    	  $("#ret_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/retweet');
		    	  dia.dialog("open");
		      });
		      $("a.btn_hret").live('click',function () {
		    	  var dia = $('#dia_ret');
		    	  var tweet = $(this).closest('#twrep');
		    	  $('div.wcontent',dia).empty().html($('div.rcontent',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('h4',tweet).html());
		    	  $("textarea[name='content']",dia).empty().val('//@'+$('h4',tweet).text().trim());
		    	  $("#ret_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/retweet');
		    	  dia.dialog("open");
		      });
		      
	          $('#dia_rep').dialog({
	              resizable: false,
	              modal: true,
	              autoOpen: false,
	              hide: "drop",
	              width:350,
	              buttons: {
	                  "回复": function () {
	                	  $('img.ajaxload', this).show();
	                	  var dia = $('#dia_rep');
	                	  $.ajax({
							    url: $("input[type='hidden']",dia).val(),
							    type: 'POST',
							    dataType: 'json',
							    data: {content:$("textarea[name='content']",dia).val()},
							    timeout: 5000,
							    msg:this,
							   	success: function(m){
							   		$(this.msg).dialog("close");
							   		$('img.ajaxload', this.msg).hide();
								   	//...
							    }
	                	  });	                	  
	                  },
	                  "取消": function () {
	                      $(this).dialog("close");
	                  }
	              }
	          });
		      $("a.btn_rep").live("click",function () {
		    	  var dia = $('#dia_rep');
		    	  var tweet = $(this).closest('div.tweet');
		    	  var rows = tweet.find('div.tweet-row');
		    	  $('div.wcontent',dia).empty().html(rows.eq(1).html());
		    	  $('div.wpeople',dia).empty().html(rows.eq(0).html());
		    	  $("textarea[name='content']",dia).empty();
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply');
		    	  dia.dialog("open");
		      });
		      $("a.btn_rrep").live('click',function () {
		    	  var dia = $('#dia_rep');
		    	  var tweet = $(this).closest('div.tweet_rep');
		    	  $('div.wcontent',dia).empty().html($('div.twtxtr',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('a.peop',tweet).html());
		    	  $("textarea[name='content']",dia).empty();
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply?replyId=' + tweet.attr('replyid'));
		    	  dia.dialog("open");
		      });
		      $("a.btn_hrep").live('click',function () {
		    	  var dia = $('#dia_rep');
		    	  var tweet = $(this).closest('#twrep');
		    	  $('div.wcontent',dia).empty().html($('div.rcontent',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('h4',tweet).html());
		    	  $("textarea[name='content']",dia).empty();
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply');
		    	  dia.dialog("open");
		      });		      
	    });
	</script>
</c:if>
<div class="stream-items">
<c:forEach items="${weibos}" var="weibo" varStatus="status">
	<div class="stream-item tweet" weiboId=${weibo.id}>
		<div class="tweet-image">
			<a href="${urlPrefix}/people/${weibo.senderId}" >
				<img height="48" width="48" src="${avatarUrlPrefix}/${weibo.people.avatarPath}-48"/>
			</a>
		</div>
		<div class="tweet-content">
			<div class="tweet-row">
				<span class="tweet-user-name">
					<a class="tweet-screen-name user-profile-link" href="${urlPrefix}/people/${weibo.people.id}">
					${weibo.people.realName}
					</a>
				</span>
				<span class="tweet-group">发自<a href="${urlPrefix}${weibo.fromUrl}">${weibo.fromName}</a>
				</span>
			</div>
			<div class="tweet-row">
				<div class="tweet-text">${weibo.content}</div>
			</div>
			<c:if test="${weibo.quote.id > 0}">
			<div class="tweet-ori"> 
				<div class="tweet-ori-inner"> 
					<a href="${urlPrefix}/people/${weibo.quote.people.id}" class="tweet-ori-author">${weibo.quote.people.realName}</a>：
					${weibo.quote.content}
					<span class="">
						<a href="${urlPrefix}/weibo/${weibo.quote.id}">原文转发</a>
						<span class="link-sep">·</span>
						<a href="${urlPrefix}/weibo/${weibo.quote.id}">原文回复</a>
					</span> 
				</div> 
			</div>
			</c:if> 			
			<div class="tweet-row">
				<a href="" class="tweet-timestamp">${weibo.time}</a>
				<span class="tweet-actions">
					<!--<button onclick="">赞</button>
					<span class="link-sep">·</span>-->
					<c:choose>
						<c:when test="${weibo.unFav}">
						<a class='link' onclick="favWeibo(this,${weibo.id})">收藏</button>
						</c:when>
						<c:otherwise>
						<a class='link' onclick="unFavWeibo(this,${weibo.id})">取消收藏</button>
						</c:otherwise>
					</c:choose>
					<span class="link-sep">·</span>
					<a class="btn_ret link">转发</a>
					<span class="link-sep">·</span>
					<a class="btn_rep link">回复</a>
				</span>
			</div>
		</div>
	</div>							
</c:forEach>							
</div>
<div id="dia_ret" class="ui_dialog" title="转发">
		<div class="wcontent mb10"></div>
		<div class="wpeople mb10"></div>
		<input id='ret_url' type='hidden' ></input>
		<textarea name="content" rows="5" cols="50"></textarea>
		<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
    </div>
<div id="dia_rep" class="ui_dialog" title="回复">
		<div class="wcontent mb10"></div>
		<div class="wpeople mb10"></div>
		<input id='rep_url' type='hidden'></input>
		<textarea name="content" rows="5" cols="50"></textarea>
		<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
    </div>     