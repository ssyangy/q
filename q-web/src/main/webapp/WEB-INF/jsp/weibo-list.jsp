<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    seajs.use('jquery.js', function ($) {
        $(function () {
            seajs.use('app/app_home.js', function (homeapp) {
                var txt1 = new homeapp.text.TextModel({
                    id: 1231423,
                    username: 'hecaitou',
                    realname: '木卫二',
                    text: '在复旦校园拍Alicia时，引来不少路人围观侧目。有一个GG骑着自行车带着MM路过，居然在车上行注目礼，直至看不见Alicia为止。车后座的MM脸都变色了。。。',
                    pushtime: '2011/4/19 10:34:20',
                    time: '',
                    attach: {
                        src: '#',
                        img: 'pdouban.jpg',
                        title: '想告诉你我了解的山下智久（5.22 番外君2号）',
                        soures: '山下智久',
                        cate: '圈子',
                        desc: '在这里，我想把我知道的山下智久尽可能完整的告诉你们他是如何一步一步...'
                    },
                    repmod: "装是常态。不装是病态。"
                });
                var txts = new homeapp.text.TextList();
                txts.add(txt1);
                window.app = new homeapp.app({
                    el: $('body'),
                    txtSeed: "#streams",
                    txtModel: txts
                });
                txt2 = new homeapp.text.TextModel({
                    id: 1231423,
                    username: 'hecaitou',
                    realname: '木卫二',
                    text: '在复旦校园拍Alicia时，引来不少路人围观侧目。有一个GG骑着自行车带着MM路过，居然在车上行注目礼，直至看不见Alicia为止。车后座的MM脸都变色了。。。',
                    pushtime: '2011/4/19 10:34:20',
                    time: '',
                    source:"前段工程师",
                    attach: {
                        src: '#',
                        img: 'pdouban.jpg',
                        title: '想告诉你我了解的山下智久（5.22 番外君2号）',
                        soures: '山下智久',
                        cate: '圈子',
                        desc: '在这里，我想把我知道的山下智久尽可能完整的告诉你们他是如何一步一步...'
                    }
                });
                txts.add(txt2);
            });
        });
    });
</script>
<ul id='streams'></ul>
<script type="text/html" id="stream">
<div class='hd'>
	{{#people}}
    <a href='${urlPrefix}/people/{{id}}' title='{{ screenName }}'>
    <img class='img48' src='{{avatarPath}}-48' alt='{{ screenName }}'>
    </a>
</div>
<div class='bd'>
	<div class='text'><a class='lk' href='${urlPrefix}/people/{{id}}' title='{{ screenName }}'>{{ screenName }}</a>
	{{/people}}
	{{ text }}
	</div>
	{{#picturePath}}
	<img src="{{picturePath}}-160" class="img160 weiboImg"/>
	<div class='imgPre hide'>
		<div class='imgrote middle'>
			<img src="{{picturePath}}-320" class="img320 preImg"/>
	</div>
	<a class='weiboImgRotateL link mr10'>左转</a>
	    <a class='weiboImgRotateR link mr10'>右转</a>
	    <a href='{{picturePath}}' class='link' target='_blank'>查看原图</a>
	</div>
	{{/picturePath}}
	{{#quote}}
	<div class='attach'>
		<div class='text'>
		{{#people}}
		<a href="${urlPrefix}/people/{{id}}"  class='lk'>{{screenName}}</a>：
		{{/people}}
		{{text}}
		</div>
		{{#picturePath}}
		<img src="{{picturePath}}-160" class="img160 weiboImg"/>
		<div class='imgPre hide'>
			<div class='imgrote middle'>
			<img src="{{picturePath}}-320" class="img320 preImg"/>
			</div>
			<a class='weiboImgRotateL link mr10'>左转</a>
	    	<a class='weiboImgRotateR link mr10'>右转</a>
	    	<a href='{{picturePath}}' class='link' target='_blank'>查看原图</a>
		</div>
		{{/picturePath}}
		<span class="">
			<a href="${urlPrefix}/weibo/{{id}}" class='lk'>原文转发</a>
			<a href="${urlPrefix}/weibo/{{id}}" class='lk'>原文回复</a>
		</span>
	</div>
	{{/quote}}
	{{#repmod}}<div class='repmod'><span class='gray'>回应了我：</span>{{repmod}}</div>{{/repmod}}
</div>
<div class='fd'>
	<b class='twarrow png'></b>
	<span class='stat'>{{time}}
		{{#source}}<a class='ml5 lk'>{{source}}</a>{{/source}}
	</span>
	<a href="#" class='btn_rep png'>回复{{#replyNum}}（{{replyNum}}）{{/replyNum}}</a>
	<a href="#" class='btn_ret ml5 png'>转发{{#retweetNum}}（{{retweetNum}}）{{/retweetNum}}</a>
	{{#favorited}}<a href="#" class='favun ml5 png'>取消收藏</a>{{/favorited}}
	{{^favorited}}<a href="#" class='fav ml5 png'>收藏</a>{{/favorited}}
	{{#isown}}<b href="#" class='btn_del cloarrow png'>删除</b>{{/isown}}
</div>
</script>
	<script id="tweet" type="text/html">
    <div class="stream-item tweet waitSlideDown" weiboId="{{id}}">
        <div class="tweet-image">
			{{#people}}
			<a href="${urlPrefix}/people/{{id}}">
				<img height="48" width="48" src="{{avatarPath}}-48">
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
					{{#picturePath}}
					<img src="{{picturePath}}-160" class="img160 weiboImg"/>
					<div class='imgPre hide'>
						<div class='imgrote middle'>
							<img src="{{picturePath}}-320" class="img320 preImg"/>
				     	</div>
				     	<a class='weiboImgRotateL link mr10'>左转</a>
					    <a class='weiboImgRotateR link mr10'>右转</a>
					    <a href='{{picturePath}}' class='link' target='_blank'>查看原图</a>
				    </div>
					{{/picturePath}}
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
					{{#favorited}}<a href="#" class='favun'>取消收藏</a>{{/favorited}}
					{{^favorited}}<a href="#" class='fav'>收藏</a>{{/favorited}}
					{{#isown}}<a href="#" class='favun'>取消收藏</a>{{/isown}}
					<span class="link-sep">·</span><a href="#" class='btn_ret'>转发{{#retweetNum}}（{{retweetNum}}）{{/retweetNum}}</a>
					<span class="link-sep">·</span><a href="#" class='btn_rep'>回复{{#replyNum}}（{{replyNum}}）{{/replyNum}}</a>
					{{#isown}}<a href="#" class='btn_del'>删除</a>{{/isown}}
				</span>
            </div>
        </div>
    </div>
	</script>
	<script id="tweetexp" type="text/html">
			{{#weibo}}
					<a class='btnreturn'></a>
					<div id='twrep' class='mb10' weiboid="{{id}}">
					<div class="tw_head">
					{{#people}}
					<a href="${urlPrefix}/people/{{id}}">
                        <img class="wh48" src="{{avatarPath}}-48" alt="head" />
					</a>
                    <h4><a class='link' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a></h4>
					{{/people}}
                    </div>
                    <div class="twtxt mt10">
					<div class='rcontent'>{{text}}</div>
					{{#picturePath}}
					<img src="{{picturePath}}-160" class="img160 weiboImg"/>
					<div class='imgPre hide'>
						<div class='imgrote middle'>
							<img src="{{picturePath}}-320" class="img320 preImg"/>
				     	</div>
				     	<a class='weiboImgRotateL link mr10'>左转</a>
					    <a class='weiboImgRotateR link mr10'>右转</a>
					    <a href='{{picturePath}}' class='link' target='_blank'>查看原图</a>
				    </div>
					{{/picturePath}}
			{{#quote}}
			<div class="tweet-ori">
				<div class="tweet-ori-inner">
					{{#people}}<a href="${urlPrefix}/people/{{id}}" class="tweet-ori-author">{{screenName}}</a>{{/people}}：
					<span class='wqcontent'>{{text}}</span><br/>
					{{#picturePath}}
<img id="img" src="{{picturePath}}-160" class="img160 weiboImg"/>
					<div class='imgPre hide'>
						<div class='imgrote middle'>
							<img src="{{picturePath}}-320" class="img320 preImg"/>
				     	</div>
				     	<a class='weiboImgRotateL link mr10'>左转</a>
					    <a class='weiboImgRotateR link mr10'>右转</a>
					    <a href='{{picturePath}}' class='link' target='_blank'>查看原图</a>
				    </div>
					{{/picturePath}}
					<div class="tweet-row clearfix">
					<div class="tweet-actions">
						<a href="${urlPrefix}/weibo/{{id}}">原文转发</a>
						<span class="link-sep">·</span>
						<a href="${urlPrefix}/weibo/{{id}}">原文回复</a>
					</div>
					</div>
				</div>
			</div>
			{{/quote}}
						<div class="mt10">
						<span class='gray mr10'>{{screenTime}}</span>
						<span class="gray">发自<a href="${urlPrefix}{{fromUrl}}">{{fromName}}</a></span>
						<a class="link ml5 FR btn_hrep" href="#">回复{{#replyNum}}（{{replyNum}}）{{/replyNum}}</a>
						<span class="link-sep FR">·</span>
						<a class="link ml5 mr5 FR btn_hret" href="#">转发{{#retweetNum}}（{{retweetNum}}）{{/retweetNum}}</a>
						<span class="link-sep FR">·</span>
						{{#favorited}}<a href="#" class='link mr5 FR hfavun'>取消收藏</a>{{/favorited}}
						{{^favorited}}<a href="#" class='link mr5 FR hfav'>收藏</a>{{/favorited}}
						{{#isown}}<a href="#" class='link mr5 FR hdel'>删除</a>{{/isown}}
						</div>
                    </div>
					</div>
					{{/weibo}}
					{{#replies}}
                    <div class="tweet_rep" weiboid="{{quoteWeiboId}}" replyid="{{id}}">
						{{#people}}
						<a href="${urlPrefix}/people/{{id}}">
                        	<img class="wh48" src="{{avatarPath}}-48" alt="head" />
						</a>
                    	<a class='link peop' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>
						{{/people}}
                        <span class="gray ml10">{{screenTime}}</span>

						<a class="link ml5 FR btn_rrep">回复{{#replyNum}}（{{replyNum}}）{{/replyNum}}</a>
						<span class="link-sep FR">·</span>
						<a class="link ml5 mr5 FR btn_rret">转发{{#retweetNum}}（{{retweetNum}}）{{/retweetNum}}</a>
						<span class="link-sep FR">·</span>
						{{#favorited}}<a href="#" class='link FR rfavun mr5'>取消收藏</a>{{/favorited}}
						{{^favorited}}<a href="#" class='link FR rfav mr5'>收藏</a>{{/favorited}}
						{{#isown}}<a href="#" class='link mr5 FR rdel'>删除</a>{{/isown}}
                        <div class="mt10 twtxtr">{{text}}</div>
                    </div>
					{{/replies}}
					{{#hasPrev}}<button id='rrepprev' class='button mr10 hide'>上一页</button>{{/hasPrev}}
					{{#hasNext}}<button id='rrepnext' class='button'>下一页</button>{{/hasNext}}

	</script>
	<script type="text/javascript">
		var ajlock = true;
		$(function () {

		    $("img.weiboImg").live('click',function(){
		    	$(this).addClass('hide');
		    	$(this).next('div.imgPre').removeClass('hide');
		    });
		    $("img.preImg").live('click',function(){
		    	var pre = $(this).closest('div.imgPre');
		    	pre.addClass('hide');
		    	pre.prev('img.weiboImg').removeClass('hide');
		    });
		    
			$('a.weiboImgRotateR').live('click',function(){
				var imgrote = $(this).prevAll('div.imgrote');
				var rote = imgrote.data('rote');
				if(rote == undefined) rote = 0;
				imgrote.children('img.preImg').rotate(rote + 90);
				imgrote.data('rote',rote + 90);
			});
			$('a.weiboImgRotateL').live('click',function(){
				var imgrote = $(this).prev('div.imgrote');
				var rote = imgrote.data('rote');
				if(rote == undefined) rote = 0;
				imgrote.children('img.preImg').rotate(rote - 90);
				imgrote.data('rote',rote - 90);
			});

			$('#rrepnext').live('click',function(){
        		$.ajax({
				    url: '${urlPrefix}/weibo/' + $('#twrep').attr('weiboid'),
				    type: 'GET',
				    dataType: 'json',
				    data: {size:8, startId:parseInt($('div.tweet_rep:last').attr('replyid'))-1, type:0},
				   	success: function(json){
				   		tweetex.empty().append(ich.tweetexp(json));
				    }
                });
			});
			$('#rrepprev').live('click',function(){
        		$.ajax({
				    url: '${urlPrefix}/weibo/' + $('#twrep').attr('weiboid'),
				    type: 'GET',
				    dataType: 'json',
				    data: {size:8, startId:parseInt($('div.tweet_rep:first').attr('replyid'))+1, type:1},
				   	success: function(json){
				   		tweetex.empty().append(ich.tweetexp(json));
				    }
                });
			});
			
			var tweetex = $('div.tweetexpand');
			var set = $('#page-container').offset();
			tweetex.css('left',set.left+10);
	        $('div.tweet').live('click',function (e) {
	        	if($(e.target).get(0).tagName != 'A'&&$(e.target).get(0).tagName != 'IMG'){
	        		$('div.tweet').removeClass('tweet_act');
	        		$(this).addClass('tweet_act');
	        		var twid = $(this).attr('weiboid');

	        		$.ajax({
					    url: '${urlPrefix}/weibo/' + twid,
					    type: 'GET',
					    dataType: 'json',
					    data: {size:8, startId:"999999999999999999",type:0},
					    timeout: 5000,
					    msg:this,
					   	success: function(json){
					   		json.weibo.isown = (json.weibo.people.id == '${loginCookie.peopleId}');
					   		$(json.replies).each(function(){
					   			this.isown = (this.people.id == '${loginCookie.peopleId}');
					   		});
					   		tweetex.empty().append(ich.tweetexp(json));
				            tweetex.css('left',set.left+10).show();
				            tweetex.animate({ left: 540+set.left }, 500, 'swing',function(){$('div.dashboardbb').hide();});
					    }
	                });
	        	}
	        });
	        $('a.btnreturn').live('click',function(){
	        	$('div.dashboardbb').show();
	        	tweetex.animate({ left: set.left+10 }, 500, 'swing',function(){
	        		tweetex.hide()
	        	});
	        });
			$('a.btn_del').live('click',function(){
				if(confirm('确定要删除？')){
					var tweet = $(this).closest('div.tweet');
					$.ajax({
					    url: '${urlPrefix}/weibo/' + tweet.attr('weiboId'),
					    type: 'POST',
					    dataType: 'json',
					    data: {_method:'delete'},
					    msg:tweet,
					   	success: function(json){
				   			this.msg.slideUp("slow",function(){
						   		   $(this).remove();
						   	 	});				   			
				   			if($('#twrep').attr('weiboId') == this.msg.attr('weiboId')){
					        	$('div.dashboardbb').show();
					        	tweetex.animate({ left: set.left+10 }, 500, 'swing',function(){
					        		tweetex.hide();
					        	});
				   			}
					    }
					});
				}
			});
			$('a.hdel').live('click',function(){
				if(confirm('确定要删除？')){
					var tweet = $('#twrep');
					$.ajax({
					    url: '${urlPrefix}/weibo/' + tweet.attr('weiboId'),
					    type: 'POST',
					    dataType: 'json',
					    data: {_method:'delete'},
					    msg:tweet,
					   	success: function(json){
					   		$("div.tweet[weiboid='"+this.msg.attr('weiboId')+"']").slideUp("slow",function(){
					   		   $(this).remove();
					   	 	});
				        	$('div.dashboardbb').show();
				        	tweetex.animate({ left: set.left+10 }, 500, 'swing',function(){
				        		tweetex.hide();
				        	});
					    }
					});
				}
			});
	        window.onresize = window.onload = function () {
	            gWinHeight = $(window).height();
	            $("#body").height(gWinHeight);
	            tweetex.height(gWinHeight-146);
	            $('div.main-content').css('min-height',gWinHeight-100);
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
						    data: {size:8, startId:parseInt(item.last().attr('weiboId'))-1,search:"${param['search']}"},
						    timeout: 5000,
						    error: function(msg){
						    },
						   	success: function(json){
							   	$(json).each(function(){
							   		this.isown = (this.people.id == '${loginCookie.peopleId}');
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
							    url: $("#ret_url",dia).val(),
							    type: 'POST',
							    dataType: 'json',
							    data: {content:$("textarea[name='content']",dia).val()},
							   	success: function(m){
							   		dia.dialog("close");
							   		$('img.ajaxload', dia).hide();
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
		    	  $("textarea[name='content']",dia).val('').val('//@'+$('span.tweet-user-name',rows.eq(0)).text().trim());
		    	  $("#ret_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/retweet');
		    	  dia.dialog("open");
		      });
		      $("a.btn_rret").live('click',function () {
		    	  var dia = $('#dia_ret');
		    	  var tweet = $(this).closest('div.tweet_rep');
		    	  $('div.wcontent',dia).empty().html($('div.twtxtr',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('a.peop',tweet).html());
		    	  $("textarea[name='content']",dia).val('').val('//@'+$('a.peop',tweet).text().trim());
		    	  $("#ret_url",dia).val('${urlPrefix}/reply/'+tweet.attr('replyid')+'/retweet');
		    	  dia.dialog("open");
		      });
		      $("a.btn_hret").live('click',function () {
		    	  var dia = $('#dia_ret');
		    	  var tweet = $(this).closest('#twrep');
		    	  $('div.wcontent',dia).empty().html($('div.rcontent',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('h4',tweet).html());
		    	  $("textarea[name='content']",dia).val('').val('//@'+$('h4',tweet).text().trim());
		    	  $("#ret_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/retweet');
		    	  dia.dialog("open");
		      });
		      $("a.btn_sret").live('click',function () {
		    	  var dia = $('#dia_ret');
		    	  var tweet = $(this).closest('div.tweet-ori');
		    	  $('div.wcontent',dia).empty().html($('span.wqcontent',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('a.tweet-ori-author',tweet).html());
		    	  $("textarea[name='content']",dia).val('').val('//@'+$('a.tweet-ori-author',tweet).text().trim());
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
							    url: $("#rep_url",dia).val(),
							    type: 'POST',
							    dataType: 'json',
							    data: {content:$("textarea[name='content']",dia).val()},
							   	success: function(m){
							   		dia.dialog("close");
							   		$('img.ajaxload', dia).hide();
					        		var twid = $("#rep_wid",dia).val();
					        		$.ajax({
									    url: '${urlPrefix}/weibo/' + twid,
									    type: 'GET',
									    dataType: 'json',
									    data: {size:8, startId:"999999999999999999",type:0},
									   	success: function(json){
									   		tweetex.empty().append(ich.tweetexp(json));
							        		$('div.dashboardbb').hide();
								            tweetex.show().css('left',set.left+10);
								            tweetex.animate({ left: 540+set.left }, 500, 'swing');
									    }
					                });
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
		    	  $("textarea[name='content']",dia).val('');
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply');
		    	  $("#rep_wid",dia).val(tweet.attr('weiboid'));
		    	  dia.dialog("open");
		      });
		      $("a.btn_rrep").live('click',function () {
		    	  var dia = $('#dia_rep');
		    	  var tweet = $(this).closest('div.tweet_rep');
		    	  $('div.wcontent',dia).empty().html($('div.twtxtr',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('a.peop',tweet).html());
		    	  $("textarea[name='content']",dia).val('');
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply?replyId=' + tweet.attr('replyid'));
		    	  $("#rep_wid",dia).val(tweet.attr('weiboid'));
		    	  dia.dialog("open");
		      });
		      $("a.btn_hrep").live('click',function () {
		    	  var dia = $('#dia_rep');
		    	  var tweet = $(this).closest('#twrep');
		    	  $('div.wcontent',dia).empty().html($('div.rcontent',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('h4',tweet).html());
		    	  $("textarea[name='content']",dia).val('');
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply');
		    	  $("#rep_wid",dia).val(tweet.attr('weiboid'));
		    	  dia.dialog("open");
		      });
		      $("a.btn_srep").live('click',function () {
		    	  var dia = $('#dia_rep');
		    	  var tweet = $(this).closest('div.tweet-ori');
		    	  $('div.wcontent',dia).empty().html($('span.wqcontent',tweet).html());
		    	  $('div.wpeople',dia).empty().html($('a.tweet-ori-author',tweet).html());
		    	  $("textarea[name='content']",dia).val('');
		    	  $("#rep_url",dia).val('${urlPrefix}/weibo/'+tweet.attr('weiboid')+'/reply');
		    	  $("#rep_wid",dia).val(tweet.attr('weiboid'));
		    	  dia.dialog("open");
		      });

		      $('a.fav').live('click',function(){
		    	  var tweet = $(this).closest('div.tweet');
		    	  favFun(tweet.attr('weiboid'),this, 'weibo', 'fav');
		      });
		      $('a.hfav').live('click',function(){
		    	  var tweet = $(this).closest('#twrep');
		    	  favFun(tweet.attr('weiboid'),this, 'weibo', 'hfav');
		      });
		      $('a.rfav').live('click',function(){
		    	  var tweet = $(this).closest('div.tweet_rep');
		    	  favFun(tweet.attr('replyid'),this, 'reply','rfav');
		      });
		      var favFun = function(id,o,url,clas){
				  $.ajax({
					    url: '${urlPrefix}/' + url + '/' + id + '/favorite',
					    type: 'POST',
					    dataType: 'json',
					    timeout: 5000,
					    msg:o,
					   	success: function(json){
					        $(this.msg).text("取消收藏");
					        $(this.msg).removeClass(clas).addClass(clas+'un');
					    }
				  });
		      }
		      $('a.favun').live('click',function(){
		    	  var tweet = $(this).closest('div.tweet');
		    	  favFunUn(tweet.attr('weiboid'),this,'weibo','fav');
		      });
		      $('a.hfavun').live('click',function(){
		    	  var tweet = $(this).closest('#twrep');
		    	  favFunUn(tweet.attr('weiboid'),this,'weibo','hfav');
		      });
		      $('a.rfavun').live('click',function(){
		    	  var tweet = $(this).closest('div.tweet_rep');
		    	  favFunUn(tweet.attr('replyid'),this,'reply','rfav');
		      });
		      var favFunUn = function(id,o,url,clas){
				  $.ajax({
					    url: '${urlPrefix}/' + url + '/' + id + '/favorite',
					    type: 'POST',
					    dataType: 'json',
					    data:{_method:'delete'},
					    timeout: 5000,
					    msg:o,
					   	success: function(json){
					   		$(this.msg).text("收藏");
					   		$(this.msg).removeClass(clas+'un').addClass(clas);
					    }
				  });
		      }

	    });
	</script>

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
		<input id='rep_wid' type='hidden'></input>
		<textarea name="content" rows="5" cols="50"></textarea>
		<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
</div>
