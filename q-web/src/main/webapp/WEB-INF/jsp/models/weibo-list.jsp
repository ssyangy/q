<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    seajs.use('qcomcn.js', function (q) {
    	var $ = q.jq;
    	var _ = {};
    	seajs.use('underscore.js',function(us){ _ = us});
    	var ajlock = true;
    	window.loginCookie = '${loginCookie.peopleId}';
    	
        $(function () {
            seajs.use('app/weibo.js', function (weibo) {
            	var weibos = new weibo.WeiboList();
            	var weiboAdd = function (txt) {
                    var view = new weibo.WeiboView({ model: txt });
                    $('#streams').prepend(view.render().el);
                    q.fixui();
                }
            	weibos.bind('add', weiboAdd);

            	$.ajax({
            		send:function(){ ajlock = false; },
				    url: "${param['feedUrl']}?tab=${param['tab']}",
				    data: {size:8, startId:'999999999999999999',search:"${param['search']}"},
				   	success: function(json){
					   	$(json).each(function(){
					   	 	var t = new weibo.WeiboModel(this);
					   		weibos.add(t);
					   	});
				    },
				    complete: function(){ ajlock = true; }
				});
            	
            	var body = $('#body');
    	        var o = body[0];
                var updateweibo = function(){
                	if (o.scrollTop + gWinHeight < o.scrollHeight) return;
                   	if (!ajlock) return;
       				var lis = $('li.streambox');
       	            $.ajax({
       	            	send:function(){ ajlock = false; },
   					    url: "${param['feedUrl']}?tab=${param['tab']}",
   					    data: {size:1, startId:parseInt(lis.last().attr('stream-id'))-1,search:"${param['search']}"},
   					   	success: function(json){
   						   	$(json).each(function(){
   						   	 	var t = new weibo.WeiboModel(this);
   						   		weibos.add(t);
   						   	});
   					    },
   					    complete: function(){ ajlock = true; }
       				});
				}
                var throttled = _.throttle(updateweibo, 100);
                body.scroll(throttled);
            	
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
		<div class='imgrote middle'><img src="{{picturePath}}-320" class="img320 preImg"/></div>
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
	<a href="#" class='replay png'>回复{{#replyNum}}（{{replyNum}}）{{/replyNum}}</a>
	<a href="#" class='resub ml5 png'>转发{{#retweetNum}}（{{retweetNum}}）{{/retweetNum}}</a>
	{{#favorited}}<a href="#" class='unfav ml5 png'>取消收藏</a>{{/favorited}}
	{{^favorited}}<a href="#" class='fav ml5 png'>收藏</a>{{/favorited}}
	{{#isown}}<b class='cloarrow png'></b>{{/isown}}
</div>
<div class='extend hide'></div>
</script>
<script type="text/html" id="stream-ext">
    <input class='mttext' type='text' value='发表点评论。。。' />
    <a class='btn'>提交</a>
	<ul class='msglist'>
	{{#replies}}
	<li>
	{{#people}}
	<a href="${urlPrefix}/people/{{id}}"><img class="wh48" src="{{avatarPath}}-48" alt="head" /></a>
	<p><a class='link peop' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a></p>
	{{/people}}
	<p>{{text}}</p>
	<p><span class="gray">{{screenTime}}</span>
		<a class="link ml5 FR btn_rrep">回复{{#replyNum}}（{{replyNum}}）{{/replyNum}}</a>
		<span class="link-sep FR">·</span>
		<a class="link ml5 mr5 FR btn_rret">转发{{#retweetNum}}（{{retweetNum}}）{{/retweetNum}}</a>
		<span class="link-sep FR">·</span>
		{{#favorited}}<a href="#" class='link FR rfavun mr5'>取消收藏</a>{{/favorited}}
		{{^favorited}}<a href="#" class='link FR rfav mr5'>收藏</a>{{/favorited}}
		{{#isown}}<a href="#" class='link mr5 FR rdel'>删除</a>{{/isown}}
	</p>
	<li>
	{{/replies}}
	</ul>
	{{#hasPrev}}<button id='rrepprev' class='lk mr10'>上一页</button>{{/hasPrev}}
	{{#hasNext}}<button id='rrepnext' class='lk'>下一页</button>{{/hasNext}}
</script>
<div id="dia_ret" class="ui_dialog" title="转发">
	<div class="wcontent mb10"></div>
	<div class="wpeople mb10"></div>
	<input id='ret_url' type='hidden' ></input>
	<textarea name="content" rows="5" cols="50"></textarea>
	<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
</div>
