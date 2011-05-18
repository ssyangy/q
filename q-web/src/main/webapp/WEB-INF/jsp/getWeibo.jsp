<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="微博" />
</jsp:include>
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
        <a href="${urlprefix}/weibo/{{id}}" class='replay'>回复{{#replyNum}}({{replyNum}}){{/replyNum}}</a>
        <a href="javascript:void(0);" class='resub ml5'>转发{{#retweetNum}}({{retweetNum}}){{/retweetNum}}</a>
        <a href="javascript:void(0);" class='unfav ml5 {{^favorited}}hide{{/favorited}}'>取消收藏</a>
        <a href="javascript:void(0);" class='fav ml5 {{#favorited}}hide{{/favorited}}'>收藏</a>
		{{#isown}}<a href="javascript:void(0);" class='lk del'>删除</a>{{/isown}}
		</span>
	</p>
</script>
<script type="text/javascript">
    seajs.use('qcomcn.js', function (q) {
    	var $ = q.jq;
        seajs.use('app/weibo-rep.js', function (w) {
            $(function () {
                $.ajax({
                    url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply",
                    data: { size: 10, startid: '999999999999999999' },
                    success: this.suc_repajax
                });
                var defajaxurl: { size: 10, type: 0 },
                $('a.rrprev').live('click',function(){
                    var lis = $('li.repbox', this.el);
                    var urlp = { startid: parseInt(lis.last().data('replyid')) - 1 };
                    _.extend(urlp, this.defajaxurl);
                    $.ajax({
                        url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply?" + $.param(urlp),
                        success: this.suc_repajax
                    });
                });
                $('a.rrnext').live('click',function(){
                    var lis = $('li.repbox', this.el);
                    var urlp = { startid: parseInt(lis.last().data('replyid')) - 1,type:1 };
                    _.extend(urlp, this.defajaxurl);
                    $.ajax({
                        url: window.urlprefix + "/weibo/" + this.model.get('id') + "/reply?" + $.param(urlp),
                        success: this.suc_repajax
                    });
                });
                var ul = $('ul.msglist');
                var suc_reqajax = function(json){
                    if (json.hasPrev) { $('.rrprev', this.el).show() } else { $('.rrprev', this.el).hide() };
                    if (json.hasNext) { $('.rrnext', this.el).show() } else { $('.rrnext', this.el).hide() };
                    ul.empty();
                    $(json.replies).each(function () {
                        var rr = new rep.WeiboRepModel(this);
                        var view = new rep.WeiboRepView({ model: rr });
                        ul.append(view.render().el);
                    });
                    q.fixui(ul);
                }
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
	    <textarea id="repinp" class="mttextar_val">回复点什么 . . .</textarea>
	    <a class='btn'>回复</a>
	    </form>
    </div>
    <div class="tw-reps">
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