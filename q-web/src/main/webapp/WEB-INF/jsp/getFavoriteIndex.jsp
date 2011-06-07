<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="收藏" />
</jsp:include>
<style>
li.streambox{padding:15px 20px;}
</style>

<jsp:include page="icanhaz/pPicture.jsp" />
<jsp:include page="icanhaz/iStream.jsp" />
<jsp:include page="icanhaz/iQuote.jsp" />
<jsp:include page="icanhaz/iStream_ext.jsp" />
<script type='text/javascript'>
var tmp_weiboRepItem = "\
<div class='hd'>\
	{{#people}}\
    <a href='${urlPrefix}/people/{{id}}' title='{{ screenName }}'>\
    	<img class='img48' src='{{avatarPath}}-48' alt='{{ screenName }}'>\
    </a>\
    {{/people}}\
</div>\
<div class='bd'>\
	<div class='text'>\
		{{#people}}\
		<a class='lk' href='${urlPrefix}/people/{{id}}' title='{{ screenName }}'>{{ screenName }}</a>\
		{{/people}}\
		{{{ text }}}\
	</div>\
</div>\
<div class='fd'>\
	<span class='stat'>\
		<a href='${urlPrefix}/weibo/{{id}}'>{{screenTime}}</a>\
		{{#source}}<a class='ml5 lk'>{{source}}</a>{{/source}}\
	</span>\
	<a href='javascript:void(0);' class='hod lk lkrb r_resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>\
	<a href='javascript:void(0);' class='hod lk lkrb r_unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>\
    <a href='javascript:void(0);' class='hod lk r_fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>\
	{{#isown}}<a href='javascript:void(0);' class='lk r_del'>删除</a>{{/isown}}\
</div>";
</script>
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo','app/weibo_repitem'], function (q, w, r) {
var $ = q.jq;
	$(function(){
		w.Loader(q);
		r.Loader(q);
		var stream = $("#streams");
		var pv = $('a.prev');
		var nt = $('a.next');
		var ajaxweibo = function(startid,typed){
			$.ajax({ url: "${urlPrefix}/favorite",
				data: {size:10, startId:startid, type:typed},
				success: function(j){
	            	if (j.hasPrev) pv.show(); else pv.hide();
	            	if (j.hasNext) nt.show(); else nt.hide();
	            	stream.empty();
					$(j.favorites).each(function(){
						if(!this.item) return;
						this.item.order_id = this.id;
						var el = {};
						if(this.itemType ==1) {
							this.item.text = "回复：" + this.item.text;
							var t = new r.WeiboRepItemModel(this.item);
			                var view = new r.WeiboRepItemView({ 
			                	model: t,
			    	        	tmp:tmp_weiboRepItem, 
			    	        	quotetmp:tmp_quote,
			    	        	partials:partials
			                });
							el = view.render().el;
						} else {
							var t = new w.WeiboModel(this.item);
			                var view = new w.WeiboView({ 
			                	model: t,
			    	        	tmp:tmp_stream, 
			    	        	quotetmp:tmp_quote, 
			    	        	replytmp:tmp_stream_ext, 
			    	        	partials:partials
			                });
			                el = view.render().el;
						}
						stream.append(el);
						q.fixui($(el), true);
					});
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

<jsp:include page="models/foot.jsp" />