<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<script type="text/javascript">
mods.push(function (q) {
    var $ = q.jq;

    	$("a.joing").live("click",function(){
			$.ajax({ url: '${urlPrefix}/group/${group.id}/join', type: 'POST',
    			    o:$(this),
    			   	success: function(m){
						if(m&&m.id == null) return;
    			    	this.o.replaceWith("<span class='xiaogou in_bk'>已加入 | <a href='#' class='lk unjoing'>退出</a></span>");
    			    }
    		});
    	});
    	$("a.unjoing").live("click",function(){
    		  $.ajax({ url: '${urlPrefix}/group/${group.id}/join', type: 'POST',
    			    data:{_method:'delete'},   
    			    o:$(this),
    			   	success: function(m){
						if(m&&m.id == null) return;
						var sp = this.o.parent("span.xiaogous");
						sp.replaceWith("<a class='lk joing' href='#' >加入</a>");
    			   	}
    		});
    	});

});
</script>
<div class="groupinfo">
	<p class="fgray3 tar">由${group.creator.realName}创建于${group.time}</p>
	<p class="fgray3 tar">

<c:choose>
<c:when test="${loginCookie.peopleId != group.creator.id}">	
<c:choose>
	<c:when test="${join == null}">
		<a class="lk joing" href="#" >加入</a>
	</c:when>
	<c:otherwise>
		<span class="xiaogou in_bk">已加入 | <a href="#" class='lk unjoing'>退出</a></span>
	</c:otherwise>
</c:choose>	
</c:when>
</c:choose>	

<c:choose>
<c:when test="${loginCookie.peopleId == group.creator.id}">			
	<a href="${urlPrefix}/group/${group.id}/edit" class='btnb'>管理</a>
</c:when>
</c:choose>	

	</p>
</div>



