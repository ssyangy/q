<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="私信" />
</jsp:include>
<link href="${staticUrlPrefix}/content/slider.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.sldlist{width:570px;}
#sld2{left:570px;}

.msgmem{background-color:#f6f6f6;position:relative;padding:12px 20px 15px;
border-left:1px solid #dcdcdc;border-right:1px solid #dcdcdc;}
.msgmem p{line-height:24px;height:24px;}
.memdel{position:absolute;top:15px;right:20px;_right:30px;}
.msgrepbox{padding:15px 20px;border-left:1px solid #dcdcdc;border-right:1px solid #dcdcdc;}
.msgrepbox .mttextar{width:99%;height:40px;}
.repactbox{text-align:right;margin-top:4px;}
</style>
<script type="text/javascript">
seajs.use(['qcomcn','mustache'],function(q, mc){
    var $ = q.jq;
    $(function(){

        var sldroot = $('#sldroot');
        var sldrootul = $('ul.sldlist', sldroot);
        var intmsglist = function (json) {
            sldrootul.empty();
            $(json.messages).each(function () {
                var li = $(mc.to_html(tmp_msglist, this));
                li.data('members', this.receivers);
                li.data('sender', this.sender.screenName)
                sldrootul.append(li);
            });
            var pv = $('a.prev', sldroot); pv.hide(); if (json.hasPrev) pv.show();
            var nt = $('a.next', sldroot); nt.hide(); if (json.hasNext) nt.show();
            q.fixui(sldroot);
        }

        $.ajax({ url: "${urlPrefix}/message",
            data: { size: 7, startId: "999999999999999999" },
            success: intmsglist
        });
        $('a.next', sldroot).live('click', function () {
            $.ajax({ url: "${urlPrefix}/message",
                data: { size: 7, startId: $('ul.sldlist>li', sldroot).last().attr('order_id') },
                success: intmsglist
            });
        });
        $('a.prev', sldroot).live('click', function () {
            $.ajax({ url: "${urlPrefix}/message",
                data: { size: 7, startId: $('ul.sldlist>li', sldroot).first().attr('order_id'), type: 1 },
                success: intmsglist
            });
        });

        var sld2 = $("#sld2");
        var sld2ul = $('#sld2ul', sld2);
        var partners = $('span.partner', sld2);
        var sender = $('span.sender', sld2);
        var mems = $('p.mems', sld2);
        var msgli_ajsucc = function (j) {
            sld2ul.empty();
            $(j.replies).each(function () {
                sld2ul.append(mc.to_html(tmp_msgitem, this));
            });
            var pv = $('a.mrprev', sld2); pv.hide(); if (j.hasPrev) pv.show();
            var nt = $('a.mrnext', sld2); nt.hide(); if (j.hasNext) nt.show();
        }
        window.msgid = 0;
        $('li.msgli', sldroot).live('click', function () {
            window.msgid = $(this).attr('stream_id');
            $.ajax({ url: "${urlPrefix}/message/" + window.msgid + "/reply", msg: $(this),
                data: { size: 10, startid: '999999999999999999' },
                success: function (j) {
                    $('#slider').animate({ left: -570 }, { duration: 500, easing: "swing" });
                    sender.html(this.msg.data('sender'));
                    partners.empty();
                    mems.empty();
                    $.each(this.msg.data('members'), function () {
                        partners.append("<a class='lk' href='${urlPrefix}/people/" + this.id + "'>" + this.screenName + "</a> ");
                        mems.append("<img src='" + this.avatarPath + "-24' alt='ato' class='mr5' />");
                    });
                    if (j.replies) msgli_ajsucc(j);
                    $('#root').data("click",true).children(".rollbtn3").show();
                }
            });
        });
        $('#root').click(function(){
        	if($(this).data("click")){
        		$('#slider').animate({ left: 0 }, { duration: 500, easing: "swing" });
        		$(this).data("click",false).children(".rollbtn3").hide();
        	}
        });
        $('a.mrprev', sld2).live('click', function () {
            $.ajax({ url: "${urlPrefix}/message/" + window.msgid + "/reply",
                data: { size: 10, startid: parseInt($('li', sld2ul).last().attr('reply_id')), type: 1 },
                success: msgli_ajsucc
            });
        });
        $('a.mrnext', sld2).live('click', function () {
            $.ajax({ url: "${urlPrefix}/message/" + window.msgid + "/reply",
                data: { size: 10, startid: parseInt($('li', sld2ul).first().attr('reply_id')) },
                success: msgli_ajsucc
            });
        });

        $('a.memdel', sld2).live('click', function () {
            $.ajax({ url: "${urlPrefix}/message/" + window.msgid, type: 'POST',
				data:{_method:'delete'},
				success: window.location.reload()
            });
        });

        $('#btnrep').click(function () {
			var repContent = $("#tboxrep").val().trim();
			if(repContent=='' || repContent=='\n' || repContent=='\t' ) {
				return false;
			}
			/*
			var flag = true;
			$("li" ,sld2).each(function(){
				var rpc="回复：" + $('a.scn', this).text().trim();
				if(repContent == rpc) {
					flag = false;
				}
			});
			if(flag == false) {
				return false;
			}
			*/
            $.ajax({ url: "${urlPrefix}/message/" + window.msgid + "/reply", type: "POST",
                data: { content: $("#tboxrep").val(), replymessageid: $('#replaysource').val() },
                success: function (m) {
                    if (m != null && !m.id) return;
                    $.ajax({ url: "${urlPrefix}/message/" + window.msgid + "/reply",
                        data: { size: 10, startid: '999999999999999999' },
                        success: msgli_ajsucc
                    });
                    $("#tboxrep").val("");
                }
            });
        });
        $('a.mrrep').live("click", function () {
            var stream = $(this).closest('li');
            $('#replaysource').val(stream.attr("reply_id"));
            $("#tboxrep").val("回复：" + $('a.scn', stream).text() + " ").focus();
        });
    });
});
</script>


<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">
    	<h2 id="root" class='mb10 cur'>私信<a class="rollbtn3 in_bk hide"></a></h2>
        <div id="slidbox">
            <div id="slider">
				<script type='text/javascript'>
				var tmp_msglist = "\
                    <li class='msgli hov' stream_id='{{id}}' order_id='{{#lastReply}}{{id}}{{/lastReply}}'>\
						{{#sender}}\
		                <img src='{{avatarPath}}-48' alt='sender' class='sldimg' />\
		                <p class='rel pr100'>{{screenName}}\
						{{/sender}}->{{#receivers}}\
						<a class='lk' href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>\
						{{/receivers}} ({{replyNum}})\
		                <span class='time'>{{#lastReply}}{{screenTime}}{{/lastReply}}</span></p>\
		                {{#lastReply}}<p>{{text}}</p>{{/lastReply}}\
		            </li>";
				</script>
				<div id="sldroot" class="sldlist">
					<ul class="sldlist"></ul>
					<div class="pagger">
					<a class="lk mr10 prev hide">上一页</a>
					<a class="lk next hide">下一页</a>
					</div>
				</div>
				<script type='text/javascript'>
				var tmp_msgitem = "\
                    <li reply_id='{{id}}'>\
						{{#sender}}\
		                <img src='{{avatarPath}}-48' alt='avatar' class='sldimg' />\
		                <p class='rel'><a class='lk scn'>{{screenName}}</a>\
						{{/sender}}<span class='time ml10'>{{screenTime}}</span></p>\
		                <p class='rel'>{{text}}</p>\
						<span class='act'><a class='mrrep lk'>回复</a></span>\
		            </li>";
				</script>				
                <div id='sld2' class="sldlist">
                	<div class="msgmem">
						<p class="pr100 mb10"><span class='sender'></span> --> <span class='partner'></span></p>
						<p class="mems"></p>
						<a class="memdel btnb">删除</a>
					</div>
					<div class="msgrepbox">
						<textarea id="tboxrep" class='mttextar' style="height:64px;"></textarea>
						<input type="hidden" id="replaysource"/>
						<div class='repactbox'><a id="btnrep" class="btnr">回复</a></div>
					</div>
					<ul id="sld2ul"></ul>
					<div class="pagger">
					<a class="lk mr10 mrprev hide">上一页</a>
					<a class="lk mrnext hide">下一页</a>
					</div>
                </div>
            </div>
        </div>
    </div></div>
    <div class="col-sub" style="padding-top:36px;">
        <a href='${urlPrefix}/message/new' class="btnNM"></a>
    </div>
</div>
<jsp:include page="models/foot.jsp" />