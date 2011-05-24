<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href='${staticUrlPrefix}/content/slider.css' rel='stylesheet' type='text/css' />
	<script type="text/javascript">
mods.push(function (q) {
	var $ = q.jq;
	seajs.use('ICanHaz',function(ich){
			var gbs = $('#groups');
			var gbul = $("ul.msglist",gbs);
			var gbs_ajsucc = function(j){
            	var pv = $('a.gbprev'); pv.hide(); if (j.hasPrev) pv.show();
            	var nt = $('a.gbnext'); nt.hide(); if (j.hasNext) nt.show();	            
            	gbul.empty();
                $(j.groups).each(function(){
                	gbul.append(ich.groups(this));
                });
			}
			$.ajax({ url:"${param['feedUrl']}",
				data:{size:10, search:"${param['search']}"},
				success: gbs_ajsucc });
			$('a.gbprev', gbs).click(function(){
				$.ajax({ url:"${param['feedUrl']}",
					data:{startId:$("li",gbul).first().attr("stream_id"),size:10, search:"${param['search']}", type:1},
					success: gbs_ajsucc });
			});
			$('a.gbnext', gbs).click(function(){
				$.ajax({ url:"${param['feedUrl']}",
					data:{startId:$("li",gbul).last().attr("stream_id"),size:10, search:"${param['search']}"},
					success: gbs_ajsucc });
			});

			$("a.actun",gbul).live('click', function () {
				var li = $(this).parent('li');
				$.ajax({ url: '${urlPrefix}/group/' + li.attr('stream_id') + '/join', type: 'POST', msg:$(this),
					data:{_method:'delete'},
					success: function(m){
						if (m != null && !m.id) return;
						this.msg.siblings('a.act').removeClass('hide_im');
						this.msg.addClass('hide_im');
					} });
			});
			$("a.act",gbul).live('click', function () {
				var li = $(this).parent('li');
				$.ajax({
					url: '${urlPrefix}/group/' + li.attr('stream_id') + '/join', type: 'POST', msg:this,
					success: function(m){
						if (m != null && !m.id) return;
						this.msg.siblings('a.actun').removeClass('hide_im');
						this.msg.addClass('hide_im');
					} });
			});
	});
}); 
</script>
<script type="text/html" id="groups">
                    <li stream_id={{id}}>
                        <img src="{{avatarPath}}" alt="avtor" class="sldimg" />
                        <a class='btn act {{#joined}}hide_im{{/joined}}'>关注</a>
                        <a class='btn actun	 {{^joined}}hide_im{{/joined}}'>取消关注</a>
                       	<p><a href='${urlPrefix}/group/{{id}}' class='lk'>{{name}}</a></p>
                        <p>成员：{{joinNum}}人&nbsp;&nbsp;创建于：{{screenTime}}</p>
                        <p>{{intro}}</p>
                    </li>
</script>
<div id="groups">
<ul class="msglist"></ul>
<a class='lk mr10 gbprev hide'>上一页</a>
<a class='lk gbnext hide'>下一页</a>
</div>
