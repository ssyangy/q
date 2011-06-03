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
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo_rep','ICanHaz','plus/rotate','app/dialog'],function(q, rep, ichp, rotate, dialog){
	var $ = q.jq;
	$(function(){
		dialog.Loader(q);
        $("a.imgRotateL").click(function(){
        	rotate.run($('img.preImg')[0], 'left');
        });
	    $("a.imgRotateR").click(function(){
	    	rotate.run($('img.preImg')[0], 'right');
	    });	
	    $("img.weiboImg,img.preImg").click(function () {
            $('div.imgPre').toggle();
            $('img.weiboImg').toggle();
        });
	    
        $(".resub").click(function () {
            dialog.At("${weibo.content}", "${weibo.people.username}", '/weibo/${weibo.id}/retweet');
        });
        
        $(".fav").click(function () {
            $.ajax({ url: window.urlprefix + '/weibo/${weibo.id}/favorite', type: 'POST',
                success: function () {
                    $('a.fav').addClass('hide');
                    $('a.unfav').removeClass('hide');
                }
            });
        });
        $(".unfav").click(function () {
            $.ajax({ url: window.urlprefix + '/weibo/${weibo.id}/favorite', type: 'POST',
                data: { _method: 'delete' },
                success: function () {
                    $('.unfav').addClass('hide');
                    $('.fav').removeClass('hide');
                }
            });
        });
        $(".del").click(function () {
            if (!confirm('确定要删除？')) return;
            $.ajax({ url: window.urlprefix + '/weiboReply/${weibo.id}', type: 'POST',
                data: { _method: 'delete' },
                success: function (m) {
                	if (q.ajaxr(m)) return;
                	window.location.href="${urlPrefix}/";
                }
            });
        });
		
		
		rep.Loader(q, ichp);
    	var ul = $('ul.msglist');
        var suc_repajax = function(j){
        	//$('body').animate({scrollTop:0},700,"swing");
        	var pv = $('a.rrprev'); pv.hide(); if (j.hasPrev) pv.show();
        	var nt = $('a.rrnext'); nt.hide(); if (j.hasNext) nt.show();
            ul.empty();
            $(j.replies).each(function () {
            	this.parent = $('body');
                var rr = new rep.WeiboRepModel(this);
                var view = new rep.WeiboRepView({ model: rr });
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
    <div class='tweet mt20'>
	    <p>${weibo.content}</p>
	    <p>
		    <c:if test="${weibo.picturePath != null }">
			    <img src="${weibo.picturePath}-160" class="img160 weiboImg"/>
				<div class='imgPre hide'>
					<p class='mt10 mb10'><img src="${weibo.picturePath}-320" class="img320 preImg"/></p>
					<a class='imgRotateL lk mr10'>左转</a>
			    	<a class='imgRotateR lk mr10'>右转</a>
			    	<a href='${weibo.picturePath}' class='lk' target='_blank'>查看原图</a>
				</div>
		    </c:if>
	    </p>
	    <p style="margin-top:38px;">
		    <span class="fgray2">${weibo.time}</span>
		    <span class="FR">
			<a href="javascript:void(0);" class='lk lkrb resub ml5'>转发<c:if test="${weibo.retweetNum != 0 }">(${weibo.retweetNum})</c:if></a>
			<a href="javascript:void(0);" class='lk lkrb unfav ml5 <c:if test="${weibo.fav == false }">hide</c:if>'>取消收藏</a>
			<a href="javascript:void(0);" class='lk lkrb fav ml5 <c:if test="${weibo.fav == true }">hide</c:if>'>收藏</a>
			<c:if test="${weibo.senderId == loginCookie.peopleId }"><a href="javascript:void(0);" class='lk del'>删除</a></c:if>
		    </span>
	    </p>
    </div>
    <h3 style="margin-bottom:20px;">回复</h3>
    <div class="tw-sub clear">
	    <form action="${urlPrefix}/weibo/${weibo.id}/reply">
	    <textarea id="reply_val" name="content" maxlength="140" class="mttextar_val countable">发表点评论 . . .</textarea>
		<div class="cttarget"></div>
    	<a id="reply_btn" class='btnr FR'>提交</a>
	    </form>
    </div>
    <div class="tw-reps mt10">
	<script type="text/html" id="stream_ext">
	{{#people}}
	<a href="${urlPrefix}/people/{{id}}"><img class="sldimg" src="{{avatarPath}}-48" alt="head" /></a>
	<p class="content">
	<a class='lk' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>
	{{/people}}
	{{text}}
	</p>
    <p class='rel'>
		<span class="stat gray">{{screenTime}}</span>
		<span class='FR'>
        <a href="javascript:void(0);" class='lk lkrb r_replay'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
        <a href="javascript:void(0);" class='lk lkrb r_resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
        <a href="javascript:void(0);" class='lk lkrb r_unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
        <a href="javascript:void(0);" class='lk lkrb r_fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
		{{#isown}}<a href="javascript:void(0);" class='lk r_del'>删除</a>{{/isown}}
		</span>
	</p>
	</script>
	    <ul class="msglist mb5"></ul>
	    <a class='lk mr10 rrprev hide'>上一页</a>
	    <a class='lk rrnext hide'>下一页</a>
    </div>
	</div></div>
    <div class="col-sub">
		<jsp:include page="icanhaz/iProfile.jsp" >
			<jsp:param name="peopleId" value="${weibo.people.id}" />
		</jsp:include>
    </div>
</div>

<jsp:include page="models/foot.jsp" />