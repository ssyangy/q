<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="找圈子" />
</jsp:include>
<link href='${staticUrlPrefix}/content-q/slider.css' rel='stylesheet' type='text/css' />
<style>
#passroll a,#passroll span{font-size:18px;}
#root{color:#FF0065;cursor:pointer;}
#root:hover{text-decoration:underline;}
span.pass {display:inline-block;zoom:1;*display:inline;position:relative;width:14px;height:14px;
background:url("${staticUrlPrefix}/content/images/arrow/sh_ex2.png") no-repeat scroll 0 0 transparent;}
span.tit{display:none;}
</style>
<script type="text/javascript">
	seajs.use('qcomcn.js', function (q) {
		var $ = q.jq;
		$(function () {
             q.Init();
             
             var lis = $("#sldroot>li");
             var roll = $('#passroll');
             var slider = $("#slider");
             var root = $('#root');
             root.click(function () {
                 if (root.data("clicked")) {
                    slider.animate({ left: 0 }, { duration: 500, easing: "swing" });
                    $('span.tit', roll).text('');
                    root.data("clicked", false);
                 } 
             });
             
			seajs.use('ICanHaz.js', function (ich) {
				lis.click(function () {
					window.gpid = parseInt($(this).attr('gpcid'));
					$('span.tit',roll).text($('p.name',this).text()).show();
					$.ajax({
					    url: "${urlPrefix}/group",
					    data: {size:6, catId: window.gpid, startid:'999999999999999999'},
					   	success: function(json){
					   		$("#sld2").html(ich.group(json));
							slider.animate({left: -560}, { duration: 500, easing: "swing" });
							root.data("clicked", true);
					    }
					});
				});
				$('#pagger>a.prev').live('click',function(){
					$.ajax({
					    url: "${urlPrefix}/group",
					    data: {size:6, catId: window.gpid, startid: parseInt(lis.last().data('replyid')) - 1, type: 0},
					   	success: function(json){
					   		$("#sld2").html(ich.group(json));
					    }
					});
				});
				$('#pagger>a.next').live('click',function(){
					$.ajax({
					    url: "${urlPrefix}/group",
					    data: {size:6, catId: window.gpid, startid: parseInt(lis.fast().data('replyid')) + 1, type: 1},
					   	success: function(json){
					   		$("#sld2").html(ich.group(json));
					    }
					});
				});				
			});
			
			$('#sld2 a.act').live('click',function(){
				var li = $(this).parent('li');
				$.ajax({
					url: '${urlPrefix}/group/' + li.attr('gid') + '/join',
					type: 'POST',
					msg:this,
					success: function(json){
						if(json == null){
							$(this.msg).siblings('a.actun').show();
							$(this.msg).hide();
						}
					}
				});
			});
			$('#sld2 a.actun').live('click',function(){
				var li = $(this).parent('li');
				$.ajax({
					url: '${urlPrefix}/group/' + li.attr('gid') + '/join',
					type: 'POST',
					data:{_method:'delete'},
					msg:this,
					success: function(json){
						if(json == null){
							$(this.msg).siblings('a.act').show();
							$(this.msg).hide();
						}
					}
				});
			});
			
		});
	});
</script>

<div class="layout grid-s5m0e6">
    <div class="col-main"><div class="main-wrap pr10">
        <div class="rel mb10">
            <p id="passroll"><a id="root">圈子分类</a><span class='pass'></span><span class='tit'></span></p>
            <a href="${urlPrefix}/group/new" class='btna btnw24' style="position:absolute;top:5px;right:5px;"><span class='btnadd'></span>新建圈子</a>
        </div>
        <div id="slidbox">
            <div id="slider">
            <ul id="sldroot" class="sldlist">
				<c:forEach items="${cats}" var="cat" varStatus="status">
				<li gpcid='${cat.id}' class='hov'>
					<img src="${avatarPath}-48" alt="gpcate" class="sldimg" >
					<p class='name'>${cat.name}</p>
					<p>
						<c:forEach items="${cat.groups}" var="group" varStatus="status">
							<a class="lk" href="${urlPrefix}/group/${group.id}">${group.name}</a>
						</c:forEach>
					</p>
				</li>
				</c:forEach>
            </ul>
            <div id='sld2'></div>
            <script type="text/html" id="group">
					<ul id="sldtrunk" class="sldlist">
					{{#groups}}
                    <li gid={{id}}><a href='${urlPrefix}/group/{{id}}'>
                        <img src="{{avatarPath}}-48" alt="avtor" class="sldimg" />
                        <a class='btn act {{#joined}}hide_im{{/joined}}'>关注</a>
                        <a class='btn actun	 {{^joined}}hide_im{{/joined}}'>取消关注</a>
                        <p>{{name}}</p>
                        <p>成员：{{joinNum}}人&nbsp;&nbsp;创建于：{{screenTime}}</p>
                        <p>{{intro}}</p></a>
                    </li>
					{{/groups}}
					</ul>
					<div id="pagger">
					{{#hasPrev}}<a class="lk mr10 prev">上一页</a>{{/hasPrev}}
					{{#hasNext}}<a class="lk next">下一页</a>{{/hasNext}}
					</div>
            </script>
            </div>
        </div>
    </div></div>
    <div class="col-sub">
		<jsp:include page="models/groups-mine.jsp">
			<jsp:param name="id" value="0" />
		</jsp:include>
    </div>
    <div class="col-extra pt20">

        <div class="component">
        <h3>圈子推荐<span class='separator'> · · · · · ·</span><a class='arr'>更多</a></h3>
        <ul class="slist">
            <li>
                    <a href="#"><img class="img48" src="/usersimg/1.png" alt="img" /></a>
                <div class="gray">
                    GEEK POWER
                </div>
            </li>
            <li class="">
                    <a href="#"><img class="img48" src="/usersimg/sago.jpg" alt="img" /></a>
                <div class="gray">
                    公司秘书丝袜
                </div>
            </li>

            <li class="end">
                    <a href="#"><img class="img48" src="/usersimg/7.png" alt="img" /></a>
                <div class="gray">
                    有木有！！～
                </div>
            </li>
            <li class="">
                    <a href="#"><img class="img48" src="/usersimg/sago.jpg" alt="img" /></a>
                <div class="gray">
                    公司秘书丝袜
                </div>
            </li>
            <li>
                    <a href="#"><img class="img48" src="/usersimg/4.png" alt="img" /></a>
                <div class="gray">
                    波多野结衣
                </div>
            </li>
            <li class='end'>
                    <a href="#"><img class="img48" src="/usersimg/1.png" alt="img" /></a>
                <div class="gray">
                    波多野结衣
                </div>
            </li>
        </ul>
        </div>

    </div>
</div><jsp:include page="models/foot.jsp" />