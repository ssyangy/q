<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo','underscore'], function (q, w, _) {
	var $ = q.jq;
	$(function(){
		seajs.use('ICanHaz',function(ich){
			w.Loader(q, ich);
			var ajlock = true;
			var ajaxweibo = function(size,startid){
				$.ajax({ url: "${param['feedUrl']}?tab=${param['tab']}",
					data: {size:size, startId:startid, search:"${param['search']}"},
					success: function(json){
						$(json.weibos).each(function(){
							this.old = true;
							var t = new w.WeiboModel(this);
							w.weibos.add(t);
						});
					},
					send: function(){ ajlock = false; },
					complete: function(){ ajlock = true; }
				});
			}
			ajaxweibo(8,'');

			var o = $("html")[0];
			var updateweibo = function(){
				if (o.scrollTop + window.winHeight < o.scrollHeight) return;
				if (!ajlock) return;
				ajaxweibo(1,w.weibos.oldlast().get('id'));
			}
			var throttled = _.throttle(updateweibo, 300);
			window.win.scroll(throttled);
		});
	});
});
</script>

<ul id='streams'></ul>
<script id="picture" class="partial" type="text/html">
		{{#picturePath}}
		<img src="{{picturePath}}-160" class="img160 weiboImg"/>
		<div class='imgPre hide'>
			<p class='mt10 mb10'><img src="{{picturePath}}-320" class="img320 preImg"/></p>
			<a class='imgRotateL lk mr10'>左转</a>
	    	<a class='imgRotateR lk mr10'>右转</a>
	    	<a href='{{picturePath}}' class='lk' target='_blank'>查看原图</a>
		</div>
		{{/picturePath}}
</script>
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
	{{>picture}}
</div>
<div class='fd'>
	<span class='stat'>{{screenTime}}
		{{#source}}<a class='ml5 lk'>{{source}}</a>{{/source}}
	</span>
	<a href="javascript:void(0);" class='hod lk lkrb togreply'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
	<a href="javascript:void(0);" class='hod lk lkrb resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
	<a href="javascript:void(0);" class='hod lk lkrb unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
    <a href="javascript:void(0);" class='hod lk lkrb fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
	{{#isown}}<a href="javascript:void(0);" class='hod lk del'>删除</a>{{/isown}}
</div>
<div class='extend'>
    <input class='mttext_val reply_val' type='text' value='发表点评论。。。' />
    <a class='btnb reply_btn'>提交</a>
    <ul class='msglist mb5'></ul>
    <a class='lk mr10 rrprev hide'>上一页</a>
    <a class='lk rrnext hide'>下一页</a>
 </div>
</script>
<script type="text/html" id="quote">
{{#delete}}<p>{{delete}}</P>{{/delete}}
{{^delete}}
		<div class='text'>
		{{#people}}
		<a href="${urlPrefix}/people/{{id}}"  class='lk'>{{screenName}}</a>：
		{{/people}}
		{{text}}
		</div>
		{{>picture}}
		<span class="">
			<a href="javascript:void(0);" class='lk qresub'>原文转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
			<a href="${urlPrefix}/weibo/{{id}}" class='lk'>原文回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
		</span>
{{/delete}}
</script>
<script type="text/html" id="stream_ext">
	{{#people}}
	<a href="${urlPrefix}/people/{{id}}"><img class="wh24 sldimg" src="{{avatarPath}}-24" alt="head" /></a>
	<p>
	<a class='lk' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>
	{{/people}}
	{{text}}
	</p>
    <p class='rel'>
		<span class="stat gray">{{screenTime}}</span>
		<span class='actions'>
        <a href="javascript:void(0);" class='lk lkrb r_replay'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
        <a href="javascript:void(0);" class='lk lkrb r_resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
        <a href="javascript:void(0);" class='lk lkrb r_unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
        <a href="javascript:void(0);" class='lk lkrb r_fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
		{{#isown}}<a href="javascript:void(0);" class='lk r_del'>删除</a>{{/isown}}
		</span>
	</p>
</script>

<div id="dia_ret" class="ui_dialog hide" title="转发">
	<div class="wpeople mb10"></div>
	<div class="wsor mb10"></div>
	<div class="wcontent mb10"></div>
	<input class='ret_url' type='hidden' ></input>
	<textarea name="content" class="mttextar" style="width:100%"></textarea>
    <img src="${staticUrlPrefix}/content/images/ajax/ajaxload.gif" class="ajaxload hide" alt="ajaxload" />
    <input type='hidden' class='donet' />
</div>
