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
background:url("/content/images/arrow/sh_ex2.png") no-repeat scroll 0 0 transparent;}
</style>
<script type="text/javascript">
	seajs.use('qcomcn.js', function (qcomcn) {
		//seajs.use('jq.easing.js');
		var $ = qcomcn.jq;
		$(function () {
             qcomcn.Init();
             
             var lis = $("#sldroot>li");
             lis.hover(function () {
                 $(this).addClass('hover');
             }, function () {
                 $(this).removeClass('hover');
             });
             var roll = $('#passroll');
             var slider = $("#slider");
             var root = $('#root');
             root.click(function () {
                 if (root.data("clicked")) {
                     slider.animate({
                         left: 0
                     }, { duration: 500, easing: "swing" });
                    $('span.tit,span.pass', roll).remove();
                 } 
             });
             
			var groups = $("#sldtrunk");
			seajs.use('ICanHaz.js', function (ich) {
				lis.click(function () {
					groups.empty();
					$.ajax({
					    url: "${param['feedUrl']}?tab=${param['tab']}",
					    data: {id:parseInt($(this).attr('gpcid'))},
					   	success: function(json){
							$(json).each(function(){
								groups.append(ich.group(this));
							}
							slider.animate({
								left: -560
							}, { duration: 500, easing: "swing" });
							var grouptit = "fuck分类";
							roll.append("<span class='pass'></span><span class='tit'>" + groupt
							root.data("clicked", true);
					    }
					});
				});
			});

		});
	});
</script>

<div class="layout grid-s5m0e6">
    <div class="col-main"><div class="main-wrap pr10">
        <div class="rel mb10">
            <p id="passroll"><a id="root">圈子分类</a></p>
            <a href="${urlPrefix}/group/new" class='btna btnw24' style="position:absolute;top:5px;right:5px;"><span class='btnadd'></span>新建圈子</a>
        </div>
        <div id="slidbox">
            <div id="slider">
            <ul class="sldlist" id="sldroot">
				<c:forEach items="${cats}" var="cat" varStatus="status">
				<li gpcid='${cat.id}'>
					<img src="${imageUrl}/default/cat-def.png" alt="gpcate" class="sldimg" >
					<p>${cat.name}</p>
					<p>
						<c:forEach items="${cat.groups}" var="group" varStatus="status">
							<a class="lk" href="${urlPrefix}/group/${group.id}">${group.name}</a>
						</c:forEach> 
					</p>
				</li>
				</c:forEach>
            </ul>
            <script type="text/html" id="group">
                    <li>
                        <img src="/usersimg/{{img}}" alt="Alternate Text" class="sldimg" />
                        <a class='btn act'>关注</a>
                        <p>{{title}}</p>
                        <p>成员：{{member}}人&nbsp;&nbsp;创建于：{{time}}</p>
                        <p>{{sign}}</p>
                    </li>
            </script>
            <ul id="sldtrunk" class="sldlist"></ul>
            <div id="pagger"><a class="lk mr10">上一页</a><a class="lk">下一页</a></div>
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