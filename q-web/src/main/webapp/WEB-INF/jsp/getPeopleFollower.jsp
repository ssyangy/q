<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="${people.username}" />
</jsp:include>
<style type="text/css">
.profileintro{min-height:64px; _height:64px; overflow:visible;}
.mgroups{border:1px solid #ddd;background-color:#f6f6f6;padding:10px;margin:10px 0;line-height:20px;}
</style>
<script type="text/javascript">
seajs.use("qcomcn",function(q){
	var $ = q.jq;
	$(function(){
		$("a.unwat").live('click', function () {
          	  var stream = $(this).closest('li');
          	  $.ajax({ url: '${urlPrefix}/people/${people.id}/following', o:$(this), type: 'POST',
          			data:{_method:'delete'},
				   	success: function(m){
				   		if (m && !m.id) return;
				   		this.o.siblings('a.btnletter').hide();
				   		this.o.after("<a class='btnb wat'>关注</a>");
				   		this.o.remove();
				    } });
		});
		$("a.wat").live('click', function () {
          	  var stream = $(this).closest('li');
          	  $.ajax({ url: '${urlPrefix}/people/${people.id}/following', o:$(this), type: 'POST',
				   	success: function(m){
				   		if (m && !m.id) return;
				   		this.o.siblings('a.btnletter').show();
				   		this.o.after("<a class='btnb unwat'>解除关注</a>");
				   		this.o.remove();
				    } });
		});

		var dia_ret = $("#dia_ret");
		$("a.btnat").live('click', function () {
			var stream = $(this).closest('li');
			$("textarea[name='content']",dia_ret).val('').val('//@${people.username}');
			dia_ret.dialog("open");
		});
        $('input.donet', dia_ret).live("click",function () {
			var dia = $('#dia_ret');
			$.ajax({ url: '${urlPrefix}/weibo', type: 'POST',
				data: {content:$("textarea[name='content']",dia).val()},
				success: function(m){
					if (m && !m.id) return;
					dia_ret.dialog("close");
				} });
        });

		var dia_letter = $("#dia_letter");
		$("a.btnletter").live('click', function () {
			var stream = $(this).closest('li');
			$('div.wpeople',dia_letter).empty().html('发私信给：${people.realName}');
			$("textarea[name='content']",dia_letter).val('');
			$("#letter_url",dia_letter).val('${urlPrefix}/message?receiverId=${people.id}');
			dia_letter.dialog("open");
		});
        $('input.donet', dia_letter).live("click",function () {
			var dia = $('#dia_letter');
			$.ajax({ url: $("#letter_url",dia).val(), type: 'POST',
				data: {content:$("textarea[name='content']",dia).val()},
				success: function(m){
					if (m && !m.id) return;
					dia.dialog("close");
				} });
        });
	});
});
</script>
<div class="layout grid-m0s220 mingrid">
    <div class="col-main"><div class="main-wrap">

<div class="profile mb10">
	<a href="${urlPrefix}/people/${people.id}">
    <img src="${people.avatarPath}-128" alt="portrait" class="FL mr10" /></a>
    <div class='proline'>
        <p>
        <span class="f16">${people.realName}</span>
        <a href="${urlPrefix}/people/${people.id}" class="lk ml10">@${people.username}</a>
        </p>
        <p>
        <span class="mr10">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</span>
        </p>
        <p class="profileintro">${people.intro}</p>
        <p class="tar">
        	<a class="btnb btnletter" href='javascript:void(0);'>私信</a>
			<a class="btnb btnat">&#64</a>
			<c:choose>
			<c:when test="${people.following == true}">
				<a class="btnb unwat">解除关注</a>
			</c:when>
			<c:otherwise>
				<a class="btnb wat">关注</a>
			</c:otherwise>
			</c:choose>
        </p>
    </div>
</div>    
<div class='mgroups clear'>
<p class="fgray2 mb5">他的圈子：</p>
<c:forEach items="${groups}" var="group" varStatus="status">
	<a href="${urlPrefix}/group/${group.id}" class='lk mr10'>${group.name}</a>
</c:forEach>
</div>

<div class="ui-tabs mt10">
    <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
        <li class="ui-state-default crt2 ui-state-un"><a href="${urlPrefix}/people/${people.id}">他点发言</a></li>
        <li class="ui-state-default crt2 ui-state-un"><a href="${urlPrefix}/people/${people.id}/following">他关注的</a></li>
        <li class="ui-state-default crt2 ui-state-active"><a href="${urlPrefix}/people/${people.id}/follower">关注他的</a></li>
    </ul>
</div>
<div class='tabscont' style="border-top:none 0;">
        <jsp:include page="models/people-list.jsp">
			<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/follower" />
			<jsp:param name="orderId" value="relation" />
		</jsp:include>
</div>
</div></div>
    <div class="col-sub">
    
    <h3>他关注的<a class="lk" href="${urlPrefix}/people/${people.id}/following">更多</a></h3>
	<ul class="slist">
	<c:forEach items="${}" var="people" varStatus='stat'>
	<c:choose>
		<c:when test="${!stat.last}"><li></c:when>
		<c:otherwise><li class='end'></c:otherwise>
	</c:choose>
		<a href="${urlPrefix}/people/${people.id}">
		<img src="${people.avatarPath}-48" class="wh48" alt="img"></a>
		<div class="gray">
			<a href="${urlPrefix}/people/${people.id}" class="lk" >${people.realName}</a>
		</div>
		</li>
	</c:forEach>
	</ul>
	
    <h3>关注他的<a class="lk" href="${urlPrefix}/people/${people.id}/follower">更多</a></h3>
	<ul class="slist">
	<c:forEach items="${}" var="people" varStatus='stat'>
	<c:choose>
		<c:when test="${!stat.last}"><li></c:when>
		<c:otherwise><li class='end'></c:otherwise>
	</c:choose>
		<a href="${urlPrefix}/people/${people.id}">
		<img src="${people.avatarPath}-48" class="wh48" alt="img"></a>
		<div class="gray">
			<a href="${urlPrefix}/people/${people.id}" class="lk" >${people.realName}</a>
		</div>
		</li>
	</c:forEach>
	</ul>
	
    </div>
</div>
<jsp:include page="models/foot.jsp" />

<div id="dia_ret" class="ui_dialog hide" title="@">
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>
<div id="dia_letter" class="ui_dialog hide" title="私信">
	<div class="wpeople mb10"></div>
	<input id='letter_url' type='hidden'></input>
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>