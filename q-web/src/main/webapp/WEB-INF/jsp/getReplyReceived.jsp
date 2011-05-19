<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="我的回复" />
</jsp:include>
<style type="text/css">
.replist{border-bottom:1px solid #ddd;}
.replist li{border:1px solid #ddd;border-bottom:none 0;padding:10px 10px;*zoom:1;}
.replist .time{position:absolute;top:0;right:100px;color:#B2B7B5}
.replist .del{position:absolute;top:0;right:50px;}
.replist .repsou{border:1px solid #efefef;padding:10px 10px 10px 68px;position:relative;background-color:#fefefe;margin-top:10px;color:#666;*zoom:1;
                 min-height:48px;height:auto !important; height:48px; overflow:visible;}
.replist .sldimg{position:absolute;top:10px;left:10px;}
</style>
	<script type="text/javascript">
	seajs.use(['qcomcn.js','ICanHaz.js'], function (q, ich) {
		var $ = q.jq;
		$(function(){
			var wbs = $('ul.replist');
			var wbs_ajsucc = function(j){
            	var pv = $('a.prev'); pv.hide(); if (j.hasPrev) pv.show();
            	var nt = $('a.next'); nt.hide(); if (j.hasNext) nt.show();	            
            	wbs.empty();
                $(j.replies).each(function(){
                	wbs.append(ich.replys(this));
                });
			}
			$.ajax({ url:"${urlPrefix}/reply/received",
				data:{startId:"999999999999999999",size:10},
				success:wbs_ajsucc });
			$('a.mbprev').click(function(){
				$.ajax({ url:"${urlPrefix}/reply/received",
					data:{startId:$("li",wbs).first().attr('stream_id'),size:10, type:1},
					success:wbs_ajsucc });
			});
			$('a.mbnext').click(function(){
				$.ajax({ url:"${urlPrefix}/reply/received",
					data:{startId:$("li",wbs).last().attr('stream_id') ,size:10},
					success:wbs_ajsucc });
			});
			$("a.del",wbs).live("click", function(){
				var li = $(this).closest("li");
	            $.ajax({ url: '${urlPrefix}/reply/' + li.attr("stream_id"), type: 'POST',
	                data: { _method: 'delete' },
	                success: function (m) {
	                	if (m != null && !m.id) return;
	                	li.css({ "overflow": "hidden" }).slideUp("slow", function () { $(this).remove(); });
	                } });
			});
		});
	});
</script>
<div class="layout grid-m0s7 mt10">
    <div class="col-main"><div class="main-wrap pr10">
        <p class='simptab'><span>收到的回应（23）</span><span class='split'>|</span><a class="lk" href="${urlPrefix}/reply/sended">发出的回应</a></p>
		<script type="text/html" id="replys">
            <li stream_id="{{id}}">
                <p class="rel">{{text}} <span class='time'>{{screenTime}}</span><a class='lk del'>删除</a></p>
                <div class='repsou'>
                    <img src="/usersimg/avatar5.jpeg" alt="avtor" class="sldimg" />
                    {{quoteWeiboId}}
                </div>
            </li>
        </script>
        <ul class='replist mt20'></ul>
		<div class="pagger">
			<a class="lk mr10 prev hide">上一页</a>
			<a class="lk next hide">下一页</a>
		</div>
    </div></div>
    <div class="col-sub">
        <jsp:include page="models/profile.jsp"/>
    </div>
</div>
<jsp:include page="models/foot.jsp" />
