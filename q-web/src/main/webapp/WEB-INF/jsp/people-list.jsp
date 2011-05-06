<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
	<script type="text/javascript">
	$(function(){
        window.onresize = window.onload = function () {
            gWinHeight = $(window).height();
            $("#body").height(gWinHeight);
            tweetex.height(gWinHeight-146);
            $('div.main-content').css('min-height',gWinHeight-100);
        };
        $('#dia_fow').dialog({
            resizable: false,
            modal: true,
            autoOpen: false,
            hide: "drop",
            width:350,
            buttons: {
                "发送": function () {
              	  $('img.ajaxload', this).show();
              	  var dia = $('#dia_fow');
              	  $.ajax({
						    url: '${urlPrefix}/weibo',
						    type: 'POST',
						    dataType: 'json',
						    data: {content:$("textarea[name='content']",dia).val()},
						   	success: function(m){
						   		dia.dialog("close");
						   		$('img.ajaxload', dia).hide();
						    }
              	  });
                },
                "取消": function () {
                    $(this).dialog("close");
                }
            }
        });
        $('#dia_letter').dialog({
            resizable: false,
            modal: true,
            autoOpen: false,
            hide: "drop",
            width:350,
            buttons: {
                "发送": function () {
              	  $('img.ajaxload', this).show();
              	  var dia = $('#dia_letter');
              	  $.ajax({
				    url: $("#letter_url",dia).val(),
				    type: 'POST',
				    dataType: 'json',
				    data: {content:$("textarea[name='content']",dia).val()},
				   	success: function(m){
				   		dia.dialog("close");
				   		$('img.ajaxload', dia).hide();
				    }
              	  });
                },
                "取消": function () {
                    $(this).dialog("close");
                }
            }
        });        
        
	      $("a.btn_fow").live('click', function () {
	    	  var dia = $('#dia_fow');
	    	  var stream = $(this).closest('div.stream-item');
	    	  $("textarea[name='content']",dia).val('').val('//@'+$('span.display-name',stream).text().trim());
	    	  dia.dialog("open");
	      });
	      
	      $("a.btn_letter").live('click', function () {
	    	  var dia = $('#dia_letter');
	    	  var stream = $(this).closest('div.stream-item');
	    	  $('div.wpeople',dia).empty().html('发私信给：'+ $('span.display-name',stream).text().trim());
	    	  $("textarea[name='content']",dia).val('');
	    	  var peopleid = $('input.peopleid',stream).val();
	    	  $("#letter_url",dia).val('${urlPrefix}/message?receiverId='+peopleid);
	    	  dia.dialog("open");
	      });
	});
	</script>
<div class="stream-items search members">
<c:forEach items="${peoples}" var="people">
	<div class="stream-item">
		<c:if test="${loginCookie.peopleId != people.id}">
		<div class="action">
			<input class='peopleid' type='hidden' value='${people.id}' />
			<a href="#" class="button btn_fow">@${people.gender.cncall}</a>
			<a href="#" class="button btn_letter">私信</a>
			<c:choose>
			<c:when test="${!people.following}">
				<button class="button" onclick="follow(this,${people.id})">关注</button>
			</c:when>
			<c:otherwise>
				<button class="button" onclick="unFollow(this,${people.id})">取消关注</button>
			</c:otherwise>
			</c:choose>
		</div>
		</c:if>
		<div class="people">
			<div class="avatar">
				<a href="${urlPrefix}/people/${people.id}">
					<img height="48" width="48" src="${people.avatarPath}-48"/>
				</a>
			</div>
			<div class="people-info-block">
				<div class="name people-info-line">
					<a href="${urlPrefix}/people/${people.id}">
						<span class="display-name">${people.realName}</span>
						<span class="username">@${people.username}</span>
					</a>
				</div>
				<div class="location people-info-line">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</div>
				<div class="url people-info-line">
					<a href="${people.url}">${people.url}</a>
				</div>
				<div class="bio">${people.intro}</div>
			</div>
			<div class="clearfix2"></div>
		</div>
	</div>
</c:forEach>
</div>
<div id="dia_fow" class="ui_dialog" title="@">
		<textarea name="content" rows="5" cols="50"></textarea>
		<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
</div>
<div id="dia_letter" class="ui_dialog" title="私信">
		<div class="wpeople mb10"></div>
		<input id='letter_url' type='hidden'></input>
		<textarea name="content" rows="5" cols="50"></textarea>
		<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
</div>
