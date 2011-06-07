<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="微博" />
</jsp:include>
<style>
.tweet{padding:10px 0;position:relative;font-size:14px;}
.tweet img.avator{position:absolute;top:0;left:0;}
.tweet p{line-height:24px;}
#reply_val{width:99%;height:60px;}
#reply_btn{position:relation;top:-10px;}
ul.msglist li{padding:15px 10px 15px 68px;}
ul.msglist img.sldimg {left: 10px;}
ul.msglist p.content{min-height:30px; _height:30px; overflow:visible;}
</style>
<jsp:include page="icanhaz/pPicture.jsp" />
<jsp:include page="icanhaz/iQuote.jsp" />
<script type='text/javascript'>
var tmp_tweet = "\
    <p>{{{ text }}}</p>\
    <p>{{>picture}}</p>\
    <p style='margin-top:38px;'>\
	    <span class='fgray2'>{{screenTime}}</span>\
	    <span class=FR'>\
		<a href='javascript:void(0);' class='hod lk lkrb resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>\
		<a href='javascript:void(0);' class='hod lk lkrb unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>\
		<a href='javascript:void(0);' class='hod lk lkrb fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>\
		{{#isown}}<a href='javascript:void(0);' class='hod lk del'>删除</a>{{/isown}}\
	    </span>\
    </p>";
</script>
<script type='text/javascript'>
var tmp_stream_ext = "\
	{{#people}}\
	<a href='${urlPrefix}/people/{{id}}'><img class='sldimg' src='{{avatarPath}}-48' alt='head' /></a>\
	<p class='content'>\
	<a class='lk' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>\
	{{/people}}\
	{{text}}\
	</p>\
    <p class='rel'>\
		<span class='stat gray'>{{screenTime}}</span>\
		<span class='FR'>\
        <a href='javascript:void(0);' class='lk lkrb r_replay'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>\
        <a href='javascript:void(0);' class='lk lkrb r_resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>\
        <a href='javascript:void(0);' class='lk lkrb r_unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>\
        <a href='javascript:void(0);' class='lk lkrb r_fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>\
		{{#isown}}<a href='javascript:void(0);' class='lk r_del'>删除</a>{{/isown}}\
		</span>\
	</p>";
</script>
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo','app/weibo_rep','plus/rotate','app/dialog'],function(q, wb, rep, rotate, dialog){
	var $ = q.jq;
	$(function(){
		wb.Loader(q);
		rep.Loader(q);
		dialog.Loader(q);
		
		$.ajax({ url:"${urlPrefix}/weibo/${weibo.id}",
			success:function(j){
				var w = new wb.WeiboModel(j);
		        var view = new wb.WeiboView({ 
		        	model:w, 
		        	tmp:tmp_tweet, 
		        	quotetmp:tmp_quote,
		        	partials:partials
		        });
		        $("#tweet").html(view.render().el);
			}
		});
		
    	var ul = $('ul.msglist');
        var suc_repajax = function(j){
        	//$('body').animate({scrollTop:0},700,"swing");
        	var pv = $('a.rrprev'); pv.hide(); if (j.hasPrev) pv.show();
        	var nt = $('a.rrnext'); nt.hide(); if (j.hasNext) nt.show();
            ul.empty();
            $(j.replies).each(function () {
            	this.parent = $('body');
                var rr = new rep.WeiboRepModel(this);
                var view = new rep.WeiboRepView({ model: rr,tmp: tmp_stream_ext });
                ul.append(view.render().el);
            });
        }
        var initreplist = function(){
            $.ajax({ url: "${urlPrefix}/weibo/${weibo.id}/reply",
                data: { size: 10 },
                success: suc_repajax
            });
        }
        initreplist();
        $("#reply_btn").click(function () {
            $.ajax({ url: "${urlPrefix}/weibo/${weibo.id}/reply", type: 'POST',
                data: { content: $('#reply_val').val() },
                success: function(m){
                	if (m && !m.id) return;
                	initreplist();
                	$('#reply_val').val('');
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
<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">
    <div id="tweet" class='tweet mt20'></div>
    <h3 style="margin-bottom:20px;">回复</h3>
    <div class="tw-sub clear">
	    <form action="${urlPrefix}/weibo/${weibo.id}/reply">
	    <textarea id="reply_val" name="content" maxlength="140" class="mttextar_val countable">发表点评论 . . .</textarea>
		<div class="cttarget"></div>
    	<a id="reply_btn" class='btnr FR'>提交</a>
	    </form>
    </div>
    <div class="tw-reps mt10">
	    <ul class="msglist mb5"></ul>
	    <a class='lk mr10 rrprev hide'>上一页</a>
	    <a class='lk rrnext hide'>下一页</a>
    </div>
	</div></div>
    <div class="col-sub">
		<jsp:include page="models/profile.jsp" >
			<jsp:param name="peopleId" value="${weibo.people.id}" />
			<jsp:param name="avatarSize" value="48" />
		</jsp:include>
    </div>
</div>

<jsp:include page="models/foot.jsp" />