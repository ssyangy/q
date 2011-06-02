<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="微博" />
</jsp:include>
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo_rep','ICanHaz'],function(q, rep, ichp){
	var $ = q.jq;
	$(function(){
		rep.Loader(q, ichp);
    	var ul = $('ul.msglist');
        var suc_repajax = function(j){
        	$('body').animate({scrollTop:0},700,"swing");
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
	    <a href="${urlPrefix}/people/${weibo.people.id}"><img src="${weibo.people.avatarPath}-24" alt='avator' /></a>
	    <p><a class="lk">${weibo.people.realName}</a><span class='time ml10'>${weibo.time}</span></p>
	    <p>
	    ${weibo.content}
	    </p>
    </div>
    <div class="tw-sub">
	    <form action="${urlPrefix}/weibo/${weibo.id}/reply">
		<input class='mttext_val reply_val' type='text' value='发表点评论。。。' />
    	<a class='btnb reply_btn'>提交</a>
	    </form>
    </div>
    <div class="tw-reps">
	<jsp:include page="icanhaz/iStream_ext.jsp" />
    <ul class="msglist mb5"></ul>
    <a class='lk mr10 rrprev hide'>上一页</a>
    <a class='lk rrnext hide'>下一页</a>
    </div>
</div></div>
<div class="col-sub">
    
</div></div>

</div><jsp:include page="models/foot.jsp" />