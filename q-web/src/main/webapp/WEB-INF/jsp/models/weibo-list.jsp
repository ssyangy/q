<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>

<jsp:include page="../icanhaz/pPicture.jsp" />
<jsp:include page="../icanhaz/iQuote.jsp" />
<jsp:include page="../icanhaz/iStream.jsp" />
<jsp:include page="../icanhaz/iStream_ext.jsp" />

<script type="text/javascript">
seajs.use(['qcomcn','app/weibo','underscore'], function (q, w, _) {
	var $ = q.jq;
	$(function(){
		var seed = $('#streams');
		
		w.Loader(q);
	    w.weibos = new w.WeiboList();
	    var weiboAdd = function (o) {
	        var view = new w.WeiboView({ 
	        	model:o, 
	        	tmp:tmp_stream, 
	        	quotetmp:tmp_quote, 
	        	replytmp:tmp_stream_ext, 
	        	partials:partials 
	        });
	        var el = view.render().el;
	        if (o.get('old')) {
	            seed.append(el);
	        } else {
	            seed.prepend(el);
	        }
	        q.fixui($(el), true);
	    }
	    w.weibos.bind('add', weiboAdd);
	    
		var ajlock = true;
		var ajaxweibo = function(size,startid){
			$.ajax({ url: "${param['feedUrl']}?tab=${param['tab']}",
				data: {size:size, startId:startid, search:"${param['search']}"},
				success: function(j){
					$(j.weibos).each(function(){
						this.old = true;
						w.weibos.add(this);
					});
				},
				send: function(){ ajlock = false; },
				complete: function(){ ajlock = true; }
			});
		}
		ajaxweibo(8,"");

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
</script>

<ul id='streams'></ul>

