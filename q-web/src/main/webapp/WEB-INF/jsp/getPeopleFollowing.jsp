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
		$("a.aunwat").click(function () {
        	  var stream = $(this).closest('li');
        	  $.ajax({ url: '${urlPrefix}/people/${people.id}/following', o:$(this), type: 'POST',
        			data:{_method:'delete'},
				   	success: function(m){
				   		if (m && !m.id) return;
				   		this.o.siblings('a.abtnletter').hide();
				   		this.o.after("<a class='btnb awat'>关注</a>");
				   		this.o.remove();
				    } });
		});
		$("a.awat").click(function () {
        	  var stream = $(this).closest('li');
        	  $.ajax({ url: '${urlPrefix}/people/${people.id}/following', o:$(this), type: 'POST',
				   	success: function(m){
				   		if (m && !m.id) return;
				   		this.o.siblings('a.abtnletter').show();
				   		this.o.after("<a class='btnb aunwat'>解除关注</a>");
				   		this.o.remove();
				    } });
		});

		var adia_ret = $("#adia_ret");
		$("a.abtnat").click(function () {
			$("textarea[name='content']",adia_ret).val('').val('//@${people.username}');
			adia_ret.dialog("open");
		});
      $('input.donet', adia_ret).live("click",function () {
			$.ajax({ url: '${urlPrefix}/weibo', type: 'POST',
				data: {content:$("textarea[name='content']",adia_ret).val()},
				success: function(m){
					if (m && !m.id) return;
					adia_ret.dialog("close");
				} });
      });

		var adia_letter = $("#adia_letter");
		$("a.abtnletter").click(function () {
			$('div.wpeople',adia_letter).empty().html('发私信给：${people.realName}');
			$("textarea[name='content']",adia_letter).val('');
			$("#letter_url",adia_letter).val('${urlPrefix}/message?receiverId=${people.id}');
			adia_letter.dialog("open");
		});
      $('input.donet', adia_letter).live("click",function () {
			$.ajax({ url: $("#letter_url",adia_letter).val(), type: 'POST',
				data: {content:$("textarea[name='content']",adia_letter).val()},
				success: function(m){
					if (m && !m.id) return;
					adia_letter.dialog("close");
				} });
      });
	});
});
</script>
<c:choose>
	<c:when test="${people.self}">
		<c:set var="call" value="我"/>
	</c:when>
	<c:when test="${people.gender.value==2}">
		<c:set var="call" value="她"/>
	</c:when>
	<c:otherwise>
		<c:set var="call" value="他"/>
	</c:otherwise>
</c:choose>
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
        
     	<p class="tar" style="min-height:22px;">
        <c:if test="${!people.self}">
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
		</c:if>
        </p>
    </div>
</div>    
<div class='mgroups clear'>
<p class="fgray2 mb5">${call}的圈子：</p>
<c:forEach items="${groups}" var="group" varStatus="status">
	<a href="${urlPrefix}/group/${group.id}" class='lk mr10'>${group.name}</a>
</c:forEach>
</div>

<div class="ui-tabs mt10">
    <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
        <li class="ui-state-default crt2 ui-state-un"><a href="${urlPrefix}/people/${people.id}">${call}的发言</a></li>
        <li class="ui-state-default crt2 ui-state-active"><a href="${urlPrefix}/people/${people.id}/following">${call}关注的</a></li>
        <li class="ui-state-default crt2 ui-state-un"><a href="${urlPrefix}/people/${people.id}/follower">关注${call}的</a></li>
    </ul>
</div>
<div class='tabscont' style="border-top:none 0;">
        <jsp:include page="models/people-list.jsp">
			<jsp:param name="feedUrl" value="${urlPrefix}/people/${people.id}/following" />
			<jsp:param name="order" value="relation" />
		</jsp:include>
</div>
</div></div>
</div>
<jsp:include page="models/foot.jsp" />

<div id="adia_ret" class="ui_dialog hide" title="@">
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>
<div id="adia_letter" class="ui_dialog hide" title="私信">
	<div class="wpeople mb10"></div>
	<input id='letter_url' type='hidden'></input>
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>