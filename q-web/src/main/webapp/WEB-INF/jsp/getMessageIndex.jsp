<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="私信" />
</jsp:include>
<link href="${staticUrlPrefix}/content-q/slider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    seajs.use('qcomcn.js', function (q) {
        var $ = q.jq;
        $(function () {
        	seajs.use('ICanHaz.js', function (ich) {
	            var sldroot = $('#sldroot');
	            var intmsglist = function(json){
	            	sldroot.html(ich.msglist(json));
	            	q.fixui(sldroot);
	            }
	            $.ajax({ url: "${urlPrefix}/message",
	                data: {size:5, startId:"999999999999999999"},
	                success: intmsglist
	            });
	            $('a.next',sldroot).live('click',function(){
	                $.ajax({ url: "${urlPrefix}/message",
	                    data: {size:10, startId:$('ul.sldlist>li',sldroot).last().attr('stream-id')-1},
	                    success: intmsglist
	                });
	            });
	            $('a.prev',sldroot).live('click',function(){
	                $.ajax({ url: "${urlPrefix}/message",
	                    data: {size:10, startId:$('ul.sldlist>li',sldroot).first().attr('stream-id')+1},
	                    success: intmsglist
	                });
	            });
	            
	            $('li.msgli',sldroot).live('click',function(){
					window.msgid = parseInt($(this).attr('stream-id'));
					$.ajax({
					    url: "${urlPrefix}/message/"+window.msgid+"/reply",
					    data: {size:10, startid:'999999999999999999'},
					   	success: function(json){
					   		$("#sld2").html(ich.msgitem(json));
							$('#slider').animate({left: -560}, { duration: 500, easing: "swing" });
					    }
					});
				});
				$('#pagger>a.prev').live('click',function(){
					$.ajax({
					    url: "${urlPrefix}/message/"+window.msgid+"/reply",
					    data: {size:10, startid: parseInt($('li.msgrepli').last().data('replyId')) - 1, type: 0},
					   	success: function(json){
					   		$("#sld2").html(ich.msgitem(json));
					    }
					});
				});
				$('#pagger>a.next').live('click',function(){
					$.ajax({
					    url: "${urlPrefix}/message/"+window.msgid+"/reply",
					    data: {size:10, startid: parseInt($('li.msgrepli').fast().data('replyId')) + 1, type: 1},
					   	success: function(json){
					   		$("#sld2").html(ich.msgitem(json));
					    }
					});
				});	
        	});
        });
    });
</script>
<h2 class='mb10'>私信</h2>
<div class="layout grid-m0s7">
    <div class="col-main"><div class="main-wrap">
        <div id="slidbox">
            <div id="slider">
            	<script type="text/html" id="msglist">
						<ul class="sldlist">
						{{#messages}}
	                    <li class='msgli hov' stream-id='{{id}}'>
							{{#sender}}
	                        <img src="{{avatarPath}}-48" alt="sender" class="sldimg" />
	                        <p class='rel'>{{screenName}}
							{{/sender}}
							 -> 
							{{#receivers}}
							<a class="lk" href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>
							{{/receivers}}
	                        <span class="time">{{screenTime}}</span></p>
	                        {{#lastReply}}<p>{{text}}</p>{{/lastReply}}
	                    </li>
						{{/messages}}
						</ul>
					<div id="pagger">
					{{#hasPrev}}<a class="lk mr10 prev">上一页</a>{{/hasPrev}}
					{{#hasNext}}<a class="lk next">下一页</a>{{/hasNext}}
					</div>	
				</script>
				<div id="sldroot"></div>
                <script type="text/html" id="msgitem">
					<div class="msgmem">
						<p>参与者：我，
						{{#peoples}}
						<a class="lk">{{name}}</a>, 
						{{/peoples}}
						<p class="mems">
						{{#peoples}}
						<img src="{{avator}}" alt="avator" class='img24' />
						{{/peoples}}
						</p>
						<a class="memdel btn">删除</a>
					</div>
					<div class="msgrepbox">
						<textarea class='mttextar' style=""></textarea>
						<div class='repactbox'><a class="memrep btn">回复</a></div>
					</div>
					<ul class="sldlist" id="sldmsg">
					{{#replies}}
                    <li class='msgrepli' replyId='{{id}}'>
						{{sender}}
                        <img src="{{avatarPath-48}}" alt="avatar" class="sldimg" />
                        <p class='rel'>{{screenName}}{{/sender}}<span class="time">{{screenTime}}</span></p>
                        <p>{{text}}</p>
                    </li>
					{{/replies}}
					</ul>
					<div id="pagger">
					{{#hasPrev}}<a class="lk mr10 prev">上一页</a>{{/hasPrev}}
					{{#hasNext}}<a class="lk next">下一页</a>{{/hasNext}}
					</div>
                </script>
                <div id='sld2'></div>
            </div>
        </div>
    </div></div>
    <div class="col-sub">
        <a href='${urlPrefix}/message/new'><button class="ui_btn_plusthick">新私信</button></a>
    </div>
</div>
<jsp:include page="models/foot.jsp" />