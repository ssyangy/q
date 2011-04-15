<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<div class="profile-header">
	<div class="profile-info clearfix">
		<div class="profile-image-container">
			<img width="128" height="128" src="${people.avatarPath}-128"/>
		</div>
		<div class="profile-details">
			<c:if test="${loginCookie.peopleId == people.id }">
			<div class="profile-edit"><a href="${urlPrefix}/profile/basic" class="button">修改我的资料</a></div>
			</c:if>
			<div class="full-name"><h2>${people.realName}</h2></div>
			<div class="screen-name-and-location">${people.area.myProvince.name}&nbsp;${people.area.myCity.name}&nbsp;${people.area.myCounty.name}</div>
			<div class="bio">${people.intro}</div>
			<div class="url">
				<a href="${people.url}" >${people.url}</a>
			</div>
		</div>
	</div>
	<div class="groups">
		${people.gender.cncall}的圈子：
		<c:forEach items="${groups}" var="group" varStatus="status">
		<a href="${urlPrefix}/group/${group.id}">${group.name}</a>
		</c:forEach>
	</div>
	<c:if test="${loginCookie.peopleId != people.id }">
	<div class="profile-actions-container">
		<div class="component">
			<div class="profile-actions clearfix">
				<div class="buttons">
					<c:choose>
					<c:when test="${isFollowing == false}">
						<button class="button" onclick="follow(this,'${people.id}')">关注</button>
					</c:when>
					<c:otherwise>
						<button class="button" onclick="unFollow(this,'${people.id}')">取消关注</button>
					</c:otherwise>
					</c:choose>
					<div class="button">
						<a id='btn_weibo'>@${people.gender.cncall}</a>
					</div>
					<div class="button">
						<a id='btn_msg'>发私信</a>
					</div>
				</div>
				<div class="kimoji">向${people.gender.cncall}表达：</div>
			</div>
		</div>
	</div>
	  <style type="text/css">
      .ui-widget-overlay {background: #333 url(css/images/ui-bg_flat_0_aaaaaa_40x100.png) 50% 50% repeat-x;}
      </style>
      <script type="text/javascript">
      $(function(){
          $('#dia_weibo').dialog({
              resizable: false,
              modal: true,
              autoOpen: false,
              hide: "drop",
              width:350,
              buttons: {
                  "发言": function () {
                      $('button.donet', this).click();
                      $(this).dialog("close");
                  },
                  "取消": function () {
                      $(this).dialog("close");
                  }
              }
          });
	      $("#btn_weibo").click(function () {
	    	  $('#dia_weibo').dialog("open");
	      });
          $('#dia_msg').dialog({
              resizable: false,
              modal: true,
              autoOpen: false,
              hide: "drop",
              width:350,
              buttons: {
                  "发言": function () {
                      $('button.donet', this).click();
                      $(this).dialog("close");
                  },
                  "取消": function () {
                      $(this).dialog("close");
                  }
              }
          });
	      $("#btn_msg").click(function () {
	    	  $('#dia_msg').dialog("open");
	      });
      });

      </script>
	<div id="dia_weibo" class="ui_dialog" title="@${people.realName}">
    	<form action="${urlPrefix}/weibo" method="post">
			<input type="hidden" name="from" value="${urlPrefix}/people/${people.id}"/>
			<textarea name="content" rows="5" cols="50">@${people.realName}</textarea><br/>
			<button class='hide donet' type='submit'></button>
		</form>
    </div>
	<div id="dia_msg" class="ui_dialog" title="发私信给:${people.realName}">
		<form action="${urlPrefix}/message" method="post">
			<input type="hidden" name="receiverId" value="${people.id}"/>
			<input type="hidden" name="from" value="${urlPrefix}/people/${people.id}"/>
			<textarea name="content" rows="5" cols="50"></textarea><br/>
			<button class='hide donet' type='submit'></button>
		</form>
    </div>
	</c:if>
</div>