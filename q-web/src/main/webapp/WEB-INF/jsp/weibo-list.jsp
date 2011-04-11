<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${null != param['feedUrl']}">
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
                <span class="tweet-actions">
					<!--<a href="">赞</a>
					<span class="link-sep">·</span>-->
					<a href="">收藏</a>
					<span class="link-sep">·</span><a href="">转发</a>
					<span class="link-sep">·</span><a href="">回复</a>
				</span>
            </div>
        </div>
    </div>
	</script>
	<script id="tweetexp" type="text/html">
					<div id='twrep' class='mb10' weibo="{{id}}">
					<div class="tw_head">
                        <img class="wh48" src="${urlPrefix}/{{#people}}{{img}}{{/people}}" alt="head" />
                        <h4><a class='link' href='${urlPrefix}/{{#people}}{{id}}{{/people}}'>{{#people}}{{name}}{{/people}}</a></h4>
                    </div>
                    <div class="twtxt mt10">
						<div class='rcontent'>{{tweet}}</div>
						<div class="mt10">
						<span class='gray mr10'>{{time}}</span>
						{{#source}}<span class="gray">发自 <a href="">{{source}}</a></span>{{/source}}
						<a class="link ml10 FR btn_hrep" href="#">回复</a>
						<a class="link ml10 FR btn_hret" href="#">转发</a>
						<a class="link ml10 FR" href="#">收藏</a>
						<a class="link FR" href="#">攒</a>
						</div>
                    </div>
					</div>
					{{#reps}}
                    <div class="tweet_rep" weibo={{id}}>
                        <img class="wh48" src="${urlPrefix}/{{#people}}{{img}}{{/people}}" alt="head" />
                        <a class="link peop">{{#people}}{{name}}{{/people}}</a><span class="gray ml10">{{time}}</span>
						<a class="link ml10 FR btn_rrep">回复</a>
						<a class="link FR btn_rret">转发</a>
                        <div class="mt10 twtxtr">{{tweet}}</div>
                    </div>
					{{/reps}}

	</script>
	<script type="text/javascript">
	
        var tweetexp = {
			id:'123214',
			time:'10秒前',
			source:'有心天下',
            people: {id:'123',name:'阿斯大肆',img:'asdasd.png'},
            tweet: "#wolfymed 自慰伤身。单纯分析精液的成分比较片面。一次性高潮所引发的全身心运动耗费的神经递质补充起来比较慢。梅开二度的快感明显不如第一次强烈，递质消耗了人就容易疲劳。",
            reps: [
                   {id:'123',people: {name:'阿斯大肆',img:'asdasd.png'},time:'昨天 2011-02-08 23:12',tweet:'以前控制论坛中高级ID玩水军那招，马上要被用在微博上了'},
                   {id:'123',people: {name:'阿斯大肆',img:'asdasd.png'},time:'昨天 2011-02-08 23:12',tweet:'以前控制论坛中高级ID玩水军那招，马上要被用在微博上了'},
                   {id:'123',people: {name:'阿斯大肆',img:'asdasd.png'},time:'昨天 2011-02-08 23:12',tweet:'以前控制论坛中高级ID玩水军那招，马上要被用在微博上了'}
            ]
        };

    
	var ajlock = true;
		$(function () {
			
			
	        $('div.tweet').click(function (e) {
	        	if($(e.target).get(0).tagName != 'A'){
	        		var twid = $(this).attr('weiboid');
	        		
	        		//...
	        	
	            	var o = $('div.tweetexpand');
	        		o.empty().append(ich.tweetexp(tweetexp));
	            	o.css('left', '0');
	            	o.animate({ left: 540 }, 500, 'swing');
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
		      $("a.btn_rep").click(function () {
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
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply');
		    	  dia.dialog("open");
		      });
		      $("a.btn_hrep").live('click',function () {
		    	  var dia = $('#dia_ret');
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
				<span class="tweet-group">发自 <a href="${urlPrefix}${weibo.fromUrl}">${weibo.fromName}</a>
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
		<input id='ret_url' type='hidden'></input>
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