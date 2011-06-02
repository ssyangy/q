<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
seajs.use(['qcomcn','app/weibo','underscore'], function (q, w, _) {
	var $ = q.jq;
	$(function(){
		seajs.use('ICanHaz',function(ich){
			w.Loader(q, ich);
			var ajlock = true;
			var ajaxweibo = function(size,startid){
				$.ajax({ url: "${param['feedUrl']}?tab=${param['tab']}",
					data: {size:size, startId:startid, search:"${param['search']}"},
					success: function(j){
						$(j.weibos).each(function(){
							this.old = true;
							var t = new w.WeiboModel(this);
							w.weibos.add(t);
						});
					},
					send: function(){ ajlock = false; },
					complete: function(){ ajlock = true; }
				});
			}
			ajaxweibo(8,'');

			var o = $("html")[0];
			var updateweibo = function(){
				if (o.scrollTop + window.winHeight < o.scrollHeight) return;
				if (!ajlock) return;
				ajaxweibo(1,w.weibos.oldlast().get('id'));
			}
			var throttled = _.throttle(updateweibo, 300);
			window.win.scroll(throttled);
		});
	});
});
</script>

<ul id='streams'></ul>
<jsp:include page="../icanhaz/iPicture.jsp" />
<jsp:include page="../icanhaz/iQuote.jsp" />
<jsp:include page="../icanhaz/iStream.jsp" />
<jsp:include page="../icanhaz/iStream_ext.jsp" />
