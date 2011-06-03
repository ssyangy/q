<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
seajs.use(["qcomcn","app/people"],function(q,p){
	var $ = q.jq;
	seajs.use('ICanHaz',function(ich){
		p.Loader(q,ich);
		
		var mems = $('#mems');
		var mem_ajsucc = function(j){
           	var pv = $('a.mbprev'); pv.hide(); if (j.hasPrev) pv.show();
           	var nt = $('a.mbnext'); nt.hide(); if (j.hasNext) nt.show();
            mems.empty();
            $(j.peoples).each(function(){
				var pp = new p.PeopModel(this);
                var view = new p.PeopView({ model: pp });
                mems.append(view.render().el);
            });
            q.fixui(mems);
		}
		
		$.ajax({ url:"${param['feedUrl']}",
			data:{size:10, search:"${param['search']}"},
			success:mem_ajsucc });
		
		$('a.mbprev').click(function(){
			var o = $("li",mems).first();
			var idd = o.attr("order_id");
			if(!idd) idd = o.attr("people_id");
			$.ajax({ url:"${param['feedUrl']}",
				data:{startId:idd,size:10, search:"${param['search']}", type:1},
				success:mem_ajsucc });
		});
		$('a.mbnext').click(function(){
			var o = $("li",mems).last();
			var idd = o.attr("order_id");
			if(!idd) idd = o.attr("people_id");
			$.ajax({ url:"${param['feedUrl']}",
				data:{startId:idd,size:10, search:"${param['search']}"},
				success:mem_ajsucc });
		});
		
	});
});
</script>

<jsp:include page="../icanhaz/iPeople.jsp" />
<ul id='mems' class="msglist"></ul>
<a class='lk mr10 mbprev hide'>上一页</a>
<a class='lk mbnext hide'>下一页</a>
