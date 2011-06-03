<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
li.streambox .bd {margin-left: 10px;}
li.streambox .fd {left: 10px;}
li.streambox .extend {padding: 10px 0px 5px 10px;}
</style>
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

<script type="text/html" id="stream">
<div class='bd'>
	{{#people}}
	<div class='text'><a class='lk' href='${urlPrefix}/people/{{id}}' title='{{ screenName }}'>{{ screenName }}</a>
	{{/people}}
	{{{ text }}}
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
<jsp:include page="../icanhaz/iPicture.jsp" />
<jsp:include page="../icanhaz/iQuote.jsp" />
<jsp:include page="../icanhaz/iStream_ext.jsp" />
