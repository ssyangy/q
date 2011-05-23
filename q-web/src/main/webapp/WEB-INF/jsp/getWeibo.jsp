<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="微博" />
</jsp:include>
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo_rep'], function (q,rep) {
		var $ = q.jq;
        $(function () {
        	var ul = $('ul.msglist');
            var suc_repajax = function(j){
            	window.body.animate({scrollTop:0},700,"swing");
            	var pv = $('a.rrprev'); pv.hide(); if (j.hasPrev) pv.show();
            	var nt = $('a.rrnext'); nt.hide(); if (j.hasNext) nt.show();
                ul.empty();
                $(j.replies).each(function () {
                	this.parent = window.body;
                    var rr = new rep.WeiboRepModel(this);
                    var view = new rep.WeiboRepView({ model: rr });
                    ul.append(view.render().el);
                });
            }
            var initreplist = function(){
                $.ajax({
                    url: "${urlPrefix}/weibo/${weibo.id}/reply",
                    data: { size: 10, startid: '999999999999999999' },
                    success: suc_repajax
                });
            }
            initreplist();
            $("a.reply_btn").click(function () {
                $.ajax({ url: "${urlPrefix}/weibo/${weibo.id}/reply", type: 'POST',
                    data: { content: $('input.reply_val').val() },
                    success: function(m){
                    	if (m && !m.id) return;
                    	initreplist();
                    	$('input.reply_val', this.el).val('');
                    }
                });
            });
            $('a.rrprev').live('click',function(){
                var urlp = { startid: $('li.repbox', ul).first().data('replyid'), type:1 ,size:10};
                $.ajax({ url: "${urlPrefix}/weibo/${weibo.id}/reply?" + $.param(urlp),
                    success: suc_repajax
                });
            });
            $('a.rrnext').live('click',function(){
                var urlp = { startid: $('li.repbox', ul).last().data('replyid'), size:10 };
                $.ajax({ url: "${urlPrefix}/weibo/${weibo.id}/reply?" + $.param(urlp),
                    success: suc_repajax
                });
            });

        });
});
</script>
<div class="layout grid-m0s7">
<div class="col-main"><div class="main-wrap pr10">
    <div class='tweet'>
	    <a href="#"><img src="/userimg/avatar5.jpeg" alt='avator' /></a>
	    <p><a class="lk">${weibo.senderRealName}</a><span class='time ml10'></span></p>
	    <p>
	    
	    </p>
    </div>
    <div class="tw-sub">
	    <form action="${urlPrefix}/weibo/${weibo.id}/reply">
		<input class='mttext_val reply_val' type='text' value='发表点评论。。。' />
    	<a class='btn reply_btn'>提交</a>
	    </form>
    </div>
    <div class="tw-reps">
<script type="text/html" id="stream_ext">
	{{#people}}
	<a href="${urlPrefix}/people/{{id}}"><img class="wh24 sldimg" src="{{avatarPath}}-24" alt="head" /></a>
	<p>
	<a class='lk' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>
	{{/people}}
	<span class='reptext' repid="{{id}}">{{text}}</span>
	</p>
    <p class='rel'>
		<span class="stat gray">{{screenTime}}</span>
		<span class='actions'>
        <a href="javascript:void(0);" class='replay'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
        <a href="javascript:void(0);" class='resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
        <a href="javascript:void(0);" class='unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
        <a href="javascript:void(0);" class='fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
		{{#isown}}<a href="javascript:void(0);" class='lk del'>删除</a>{{/isown}}
		</span>
	</p>
</script>    
    <ul class="msglist mb5"></ul>
    <a class='lk mr10 rrprev hide'>上一页</a>
    <a class='lk rrnext hide'>下一页</a>
    </div>
</div></div>
<div class="col-sub">
    
</div></div>

<div id="dia_ret" class="ui_dialog" title="转发">
	<div class="wpeople mb10"></div>
	<div class="wsor mb10"></div>
	<div class="wcontent mb10"></div>
	<input class='ret_url' type='hidden' ></input>
	<textarea name="content" class="mttextar_val" style="width:100%"></textarea>
    <img src="${staticUrlPrefix}/content-q/images/ajax/ajaxload.gif" class="ajaxload hide" alt="ajaxload" />
    <input type='hidden' class='donet' />
</div>	
</div><jsp:include page="models/foot.jsp" />