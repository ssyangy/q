<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="收藏" />
</jsp:include>
<style>
li.streambox{padding:15px 20px;}
</style>
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo','app/weibo_repitem','underscore'], function (q, w, r, _) {
var $ = q.jq;
$(function(){
	seajs.use('ICanHaz',function(ich){
		w.Loader(q,ich);
		r.Loader(q,ich);
		var stream = $("#streams");
		var pv = $('a.prev');
		var nt = $('a.next');
		var ajaxweibo = function(startid,typed){
			$.ajax({ url: "${urlPrefix}/favorite",
				data: {size:10, startId:startid, type:typed},
				success: function(j){
	            	if (j.hasPrev) pv.show();
	            	else pv.hide();
	            	if (j.hasNext) nt.show();
	            	else nt.hide();

	            	stream.empty();
					$(j.favorites).each(function(){
						this.item.order_id = this.id;
						if(this.itemType ==1) {
							this.item.text = "回复：" + this.item.text;
							var t = new r.WeiboRepItemModel(this.item);
			                var view = new r.WeiboRepItemView({ model: t });
							stream.append(view.render().el);
						} else {
							var t = new w.WeiboModel(this.item);
			                var view = new w.WeiboView({ model: t });
							stream.append(view.render().el);
						}
					});
					q.fixui(stream, true);
				}
			});
		}
		ajaxweibo("",0);

		pv.click(function(){
			ajaxweibo($("li.streambox", stream).first().attr("order_id"),1);
		});
		nt.click(function(){
			ajaxweibo($("li.streambox", stream).last().attr("order_id"),0);
		});
	});
});
});
</script>
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

<script type="text/html" id="weibo_repitem">
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
</div>
<div class='fd'>
	<span class='stat'><a href="${urlPrefix}/weibo/{{id}}">{{screenTime}}</a>
		{{#source}}<a class='ml5 lk'>{{source}}</a>{{/source}}
	</span>
	<a href="javascript:void(0);" class='hod lk lkrb r_resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
	<a href="javascript:void(0);" class='hod lk lkrb r_unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
    <a href="javascript:void(0);" class='hod lk r_fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
	{{#isown}}<a href="javascript:void(0);" class='lk r_del'>删除</a>{{/isown}}
</div>
</script>

<div class="layout grid-m0s220 mingrid">
<div class="col-main"><div class="main-wrap">
	<h2 style="margin-bottom:13px;">我的收藏</h2>
	<div style="border:1px solid #dcdcdc;">
	<ul id='streams'></ul>
	</div>
	<div class="pager">
	<a class="lk prev hide">上一页</a>
	<a class="lk next hide">下一页</a>
	</div>
</div></div>
<div class="col-sub">
    <jsp:include page="models/profile.jsp"/>
</div>
</div>

<div id="dia_ret" class="ui_dialog hide" title="转发">
	<div class="wpeople mb10"></div>
	<div class="wsor mb10"></div>
	<div class="wcontent mb10"></div>
	<input class='ret_url' type='hidden' ></input>
	<textarea name="content" class="mttextar" style="width:100%"></textarea>
    <img src="${staticUrlPrefix}/content/images/ajax/ajaxload.gif" class="ajaxload hide" alt="ajaxload" />
    <input type='hidden' class='donet' />
</div>

<jsp:include page="models/foot.jsp" />