<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="私信" />
</jsp:include>
<link href="${staticUrlPrefix}/content-q/slider.css" rel="stylesheet" type="text/css" />   
<style type="text/css">
#slidbox{width:560px;}
.main-wrap{padding-right:20px;}
.msgbox{border:1px solid #ddd;}
.msgmem{background-color:#FEF2E7;position:relative;padding:10px;}
.msgmem p{line-height:24px;}
.memdel{position:absolute;top:10px;right:10px;_right:30px;}
.msgrepbox{padding:10px;}
.msgrepbox .mttextar{width:99%;height:40px;}
.repactbox{text-align:right;}
</style>
<script type="text/javascript">
    seajs.use('qcomcn', function (q) {
        var $ = q.jq;
        $(function () {
        	seajs.use('ICanHaz', function (ich) {
	            var sldroot = $('#sldroot');
	            var sldrootul = $('ul.sldlist',sldroot);
	            var intmsglist = function(json){
		            sldrootul.empty();
	                $(json.messages).each(function(){
	                	var li = ich.msglist(this);
	                	li.data('members',this.receivers)
	                	sldrootul.append(li);
	                });
	            	var pv = $('a.prev',sldroot); pv.hide(); if (json.hasPrev) pv.show();
	            	var nt = $('a.next',sldroot); nt.hide(); if (json.hasNext) nt.show();	                
	            	q.fixui(sldroot);
	            }
	            
	            $.ajax({ url: "${urlPrefix}/message",
	                data: {size:7, startId:"999999999999999999"},
	                success: intmsglist
	            });
	            $('a.next',sldroot).live('click',function(){
	                $.ajax({ url: "${urlPrefix}/message",
	                    data: {size:7, startId:$('ul.sldlist>li',sldroot).last().attr('stream_id')},
	                    success: intmsglist
	                });
	            });
	            $('a.prev',sldroot).live('click',function(){
	                $.ajax({ url: "${urlPrefix}/message",
	                    data: {size:7, startId:$('ul.sldlist>li',sldroot).first().attr('stream_id'), type:1},
	                    success: intmsglist
	                });
	            });
	            
	            var sld2 = $("#sld2");
	            var sld2ul = $('ul.sldlist',sld2);
	            var partners = $('span.partner',sld2);
	            var mems = $('p.mems',sld2);
	            var msgli_ajsucc = function(j){
	            	sld2ul.empty();
	                $(j.replies).each(function(){
	                	sld2ul.append(ich.msgitem(this));
	                });
	            	var pv = $('a.mrprev',sld2); pv.hide(); if (j.hasPrev) pv.show();
	            	var nt = $('a.mrnext',sld2); nt.hide(); if (j.hasNext) nt.show();	                
	            }
	            $('li.msgli',sldroot).live('click',function(){
					window.msgid = $(this).attr('stream_id');
					window.msgmem = $(this).data('members');					
					$.ajax({ url: "${urlPrefix}/message/"+window.msgid+"/reply", msg:$(this),
					    data: {size:10, startid:'999999999999999999'},
					   	success: function(j){
					   		$('#slider').animate({left: -560}, { duration: 500, easing: "swing" });
					   		partners.empty();	
					   		mems.empty();
					   		$(window.msgmem).each(function(){
					   			partners.append("<a class='lk' href='${urlPrefix}/people/"+this.id+"'>"+this.screenName+"</a> ");
					   			mems.append("<img src='"+this.avatarPath+"-24' alt='ato' />");
					   		});
					   		if(j.replies) msgli_ajsucc(j);
					    }
					});
				});
				$('a.mrprev',sld2).live('click',function(){
					$.ajax({ url: "${urlPrefix}/message/"+window.msgid+"/reply",
					    data: {size:10, startid: parseInt($('li',sld2ul).last().attr('reply_id')), type: 1},
					   	success: msgli_ajsucc
					});
				});
				$('a.mrnext',sld2).live('click',function(){
					$.ajax({ url: "${urlPrefix}/message/"+window.msgid+"/reply",
					    data: {size:10, startid: parseInt($('li',sld2ul).first().attr('reply_id'))},
					   	success: msgli_ajsucc
					});
				});
				
				$('#btnrep').click(function(){
					$.ajax({ url: "${urlPrefix}/message/"+window.msgid+"/reply", type:"POST",
					    data: {content: $("#tboxrep").val(),replymessageid:$('#replaysource').val() },
					   	success: function(m){
					   		if (m != null && !m.id) return;
							$.ajax({ url: "${urlPrefix}/message/"+window.msgid+"/reply",
							    data: {size:10, startid:'999999999999999999'},
							   	success: msgli_ajsucc
							});
							$("#tboxrep").val("");
					   	}
					});
				});
				$('a.mrrep').live("click",function(){
					var stream = $(this).closest('li');
					$('#replaysource').val(stream.attr("reply_id"));
					$("#tboxrep").val("回复："+$('a.scn',stream).text()+" ").focus();
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
	                    <li class='msgli hov' stream_id='{{id}}'>
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
				</script>
				<div id="sldroot" class="sldlist">
					<ul class="sldlist"></ul>
					<div class="pagger">
					<a class="lk mr10 prev hide">上一页</a>
					<a class="lk next hide">下一页</a>
					</div>	
				</div>
                <script type="text/html" id="msgitem">
                    <li reply_id='{{id}}'>
						{{#sender}}
                        <img src="{{avatarPath}}-48" alt="avatar" class="sldimg" />
                        <p class='rel'><a class="lk scn">{{screenName}}</a>
						{{/sender}}
							<span class="time">{{screenTime}}</span>
						</p>
                        <p class="rel">{{text}}<span class="act"><a class="mrrep lk">回复</a></span></p>
                    </li>
                </script>
                <div id='sld2'>
                	<div class="msgmem">
						<p>参与者：<span class='partner'></span></p>
						<p class="mems"></p>
						<a class="memdel btn">删除</a>
					</div>
					<div class="msgrepbox">
						<textarea id="tboxrep" class='mttextar' style=""></textarea>
						<input type="hidden" id="replaysource"/>
						<div class='repactbox'><a id="btnrep" class="btn">回复</a></div>
					</div>
					<ul class="sldlist"></ul>
					<div class="pagger">
					<a class="lk mr10 mrprev hide">上一页</a>
					<a class="lk mrnext hide">下一页</a>
					</div>	
                </div>
            </div>
        </div>
    </div></div>
    <div class="col-sub">
        <a href='${urlPrefix}/message/new'><button class="ui_btn_plusthick">新私信</button></a>
    </div>
</div>
<jsp:include page="models/foot.jsp" />