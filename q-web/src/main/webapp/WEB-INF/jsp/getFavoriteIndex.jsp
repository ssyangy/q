<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="收藏" />
</jsp:include>
<script type="text/javascript">
mods.push(function(q){
seajs.use(['app/weibo','app/weibo_repitem','underscore'], function (w,r, _) {
	var $ = q.jq;
	w.init(q);
	r.init(q,w.ich);
	
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
				q.fixui(stream);
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
	<div class='quote'>
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
			<a class='weiboImgRotateL lk mr10'>左转</a>
	    	<a class='weiboImgRotateR lk mr10'>右转</a>
	    	<a href='{{picturePath}}' class='lk' target='_blank'>查看原图</a>
		</div>
		{{/picturePath}}
		<span class="">
			<a href="${urlPrefix}/weibo/{{id}}" class='lk'>原文转发{{retweetNum}}</a>
			<a href="${urlPrefix}/weibo/{{id}}" class='lk'>原文回复{{replyNum}}</a>
		</span>
	</div>
	{{/quote}}
	{{#repmod}}<div class='repmod'><span class='gray'>回应了我：</span>{{repmod}}</div>{{/repmod}}
</div>
<div class='fd'>
	<span class='stat'><a href="${urlPrefix}/weibo/{{id}}">{{screenTime}}</a>
		{{#source}}<a class='ml5 lk'>{{source}}</a>{{/source}}
	</span>
	<a href="javascript:void(0);" class='hod lk lkrb togreply'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
	<a href="javascript:void(0);" class='hod lk lkrb resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
	<a href="javascript:void(0);" class='hod lk lkrb unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
    <a href="javascript:void(0);" class='hod lk fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
	{{#isown}}<b class='cloarrow'></b>{{/isown}}
</div>
<div class='extend'>
    <input class='mttext_val reply_val' type='text' value='发表点评论。。。' />
    <a class='btnb reply_btn'>提交</a>
    <ul class='msglist mb5'></ul>
    <a class='lk mr10 rrprev hide'>上一页</a>
    <a class='lk rrnext hide'>下一页</a>
 </div>
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
	{{#quote}}
	<div class='quote'>
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
			<a class='weiboImgRotateL lk mr10'>左转</a>
	    	<a class='weiboImgRotateR lk mr10'>右转</a>
	    	<a href='{{picturePath}}' class='lk' target='_blank'>查看原图</a>
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
	<span class='stat'><a href="${urlPrefix}/weibo/{{id}}">{{screenTime}}</a>
		{{#source}}<a class='ml5 lk'>{{source}}</a>{{/source}}
	</span>
	<a href="javascript:void(0);" class='hod lk lkrb r_resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
	<a href="javascript:void(0);" class='hod lk lkrb r_unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
    <a href="javascript:void(0);" class='hod lk r_fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
	{{#isown}}<a href="javascript:void(0);" class='lk r_del'>删除</a>{{/isown}}
</div>
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

<div class="layout grid-m0s220 mingrid">
<div class="col-main"><div class="main-wrap">
	<h2 class="mb20">我的收藏</h2>
	<ul id='streams'></ul>
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