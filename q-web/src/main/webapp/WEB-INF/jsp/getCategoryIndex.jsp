<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="找圈子" />
</jsp:include>
<link href='${staticUrlPrefix}/content/slider.css' rel='stylesheet' type='text/css' />
<style>
#passroll a,#passroll span{font-size:18px;}
span.pass {display:inline-block;zoom:1;*display:inline;position:relative;width:14px;height:14px;
background:url("${staticUrlPrefix}/content/images/arrow/sh_ex2.png") no-repeat scroll 0 0 transparent;}
span.tit{display:none;}
</style>
<script type="text/javascript">
mods.push(function (q) {
	var $ = q.jq;
         
         var lis = $("#sldroot>li");
         var roll = $('#passroll');
         var slider = $("#slider");
         var root = $('#root');
         root.click(function () {
             if (root.data("clicked")) {
                slider.animate({ left: 0 }, { duration: 500, easing: "swing" });
                $('span.tit', roll).text('');
                root.data("clicked", false).removeClass("lk3 rollbtn2");;
             } 
         });
         
		seajs.use('ICanHaz', function (ich) {
			lis.click(function () {
				window.gpid = $(this).attr('gpcid');
				$('span.tit',roll).text($('.name',this).text()).show();
				$.ajax({ url: "${urlPrefix}/category/"+ window.gpid +"/group",
				    data: {size:5},
				   	success: function(j){
						$("#sld2").html(ich.group(j));
						slider.animate({left: -580}, { duration: 500, easing: "swing" });
						root.data("clicked", true).addClass("lk3 rollbtn2");
				    }
				});
			});
			$('#pagger>a.prev').live('click',function(){
				$.ajax({ url: "${urlPrefix}/category/"+ window.gpid +"/group",
				    data: {size:5, startId: $("#sldtrunk>li").first().attr('gid'), type: 1},
				   	success: function(json){
				   		$("#sld2").html(ich.group(json));
				    }
				});
			});
			$('#pagger>a.next').live('click',function(){
				$.ajax({ url: "${urlPrefix}/category/"+ window.gpid +"/group",
				    data: {size:5, startId:$("#sldtrunk>li").last().attr('gid')},
				   	success: function(json){
				   		$("#sld2").html(ich.group(json));
				    }
				});
			});				
		});
		
		$('#sld2 a.act').live('click',function(){
			var li = $(this).parent('li');
			$.ajax({ url: '${urlPrefix}/group/' + li.attr('gid') + '/join', type: 'POST', msg:this,
				success: function(m){
					if(m != null) return;
					$(this.msg).siblings('a.actun').removeClass('hide_im');
					$(this.msg).addClass('hide_im');
				}
			});
		});
		$('#sld2 a.actun').live('click',function(){
			var li = $(this).parent('li');
			$.ajax({ url: '${urlPrefix}/group/' + li.attr('gid') + '/join', type: 'POST', msg:this,
				data:{_method:'delete'},
				success: function(json){
					if(m != null) return;
					$(this.msg).siblings('a.act').removeClass('hide_im');
					$(this.msg).addClass('hide_im');
				}
			});
		});
});
</script>

<div class="layout grid-s4m0e6">
    <div class="col-main"><div class="main-wrap">
        <div class="rel mb10">
            <p id="passroll">
            <a id="root" class='fw'>圈子分类</a>
            <span class='pass'></span>
            <span class='tit fw'></span>
            </p>
        </div>
        <div id="slidbox">
            <div id="slider">
            <ul id="sldroot" class="sldlist">
				<c:forEach items="${cats}" var="cat" varStatus="status">
				<li gpcid='${cat.id}' class='hov'>
					<img src="${staticUrlPrefix}/content/images/icons/icons-0${status.index + 2}.png" alt="gpcate" class="sldimg" >
					<ps><span class='name f14 fblue'>${cat.name}</span> - ${cat.intro}</p>
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
                    <li gid={{id}}>
                        <img src="{{avatarPath}}" alt="avtor" class="sldimg" />
                        <a class='btnb act {{#joined}}hide_im{{/joined}}'>关注</a>
                        <a class='btnb actun {{^joined}}hide_im{{/joined}}'>取消关注</a>
                       <p><a href='${urlPrefix}/group/{{id}}' class='lk'>{{name}}</a></p>
                        <p>成员：{{joinNum}}人&nbsp;&nbsp;创建于：{{screenTime}}</p>
                        <p>{{intro}}</p>
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
    <div class="col-extra" style="padding-top:13px;">

        <div class="component">
        <h3>圈子推荐</h3>
        <ul class="slist">
            <c:forEach items="${recommendGroups}" var="group" varStatus="status">
            <li class="<c:if test="${status.count%3==0}">end</c:if>">
                <a href="${urlPrefix}/group/${group.id}">
                	<img class="img48" src="${group.avatarPath}" alt="img" />
                </a>
                <div class="gray">
                    <a href="${urlPrefix}/group/${group.id}" class='lk'>${group.name}</a>
                </div>
            </li>
			</c:forEach>
        </ul>
        </div>

    </div>
</div><jsp:include page="models/foot.jsp" />