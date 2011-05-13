<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="私信" />
</jsp:include>
<link href="${staticUrlPrefix}/content-q/slider.css" rel="stylesheet" type="text/css" />
<style>

</style>
<script type="text/javascript">
    seajs.use('qcomcn.js', function (q) {
        var $ = q.jq;
        
        $(function () {
        	seajs.use('ICanHaz.js', function (ich) {
            q.Init();
            
            var sldroot = $('#sldroot');
            var intmsglist = function(json){
            	sldroot.html(ich.msglist(json));
            	q.fixui();
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
	                    <li class='hov' stream-id='{{id}}'>
							{{#sender}}
	                        <img src="{{avatarPath}}-48" alt="sender" class="sldimg" />
	                        <p class='rel'>{{screenName}}
							{{/sender}}
							 -> 
							{{#receivers}}
							<a class="lk" href='${urlPrefix}/people/{{id}}'>{{screenName}}</a>
							{{/receivers}}
	                        <span class="time">{{screenTime}}</span></p>
	                        <p>{{content}}</p>
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
					{{#msg}}
                    <li>
                        <img src="/usersimg/{{img}}" alt="Alternate Text" class="sldimg" />
                        <p class='rel'>{{members}}<span class="time">{{time}}</span></p>
                        <p>{{text}}</p>
                    </li>
					{{^msg}}
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