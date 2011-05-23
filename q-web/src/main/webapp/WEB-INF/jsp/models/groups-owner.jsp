<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<script type="text/javascript">
mods.push(function (q) {
    var $ = q.jq;
    $(function () {
    	$("a.joing").live("click",function(){
			$.ajax({
    			    url: '${urlPrefix}/group/${group.id}/join',
    			    type: 'POST',
    			    o:$(this),
    			   	success: function(json){
    			       if(json == null){
    			    	   this.o.text("已加入,退出");
    			    	   this.o.removeClass("joing").addClass("unjoing");
    			       }
    			    }
    		});
    	});
    	$("a.unjoing").live("click",function(){
    		  $.ajax({
    			    url: '${urlPrefix}/group/${group.id}/join',
    			    type: 'POST',
    			    data:{_method:'delete'},   
    			    o:$(this),
    			   	success: function(json){
     			       if(json == null){
    			    	   this.o.text("加入");
    			    	   this.o.removeClass("unjoing").addClass("joing");
    			       }
    			   	}
    		});
    	});
    });
});
</script>
<p>由${group.creator.realName}创建于${group.time}</p><br />
<c:choose>
	<c:when test="${join == null}">
			<a class="btn joing" href="#" >加入</a>
	</c:when>
	<c:otherwise>
			<a class="btn btnw24 unjoing" href="#">已加入,退出</a>
	</c:otherwise>
</c:choose>
<c:if test="${loginCookie.peopleId == group.creator.id}">			
	<a href="${urlPrefix}/group/${group.id}/edit" class='btna'>管理</a>
</c:if>
