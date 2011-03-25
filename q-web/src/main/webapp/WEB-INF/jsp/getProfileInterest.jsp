<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
    <jsp:include page="head.jsp" flush="true"/>
    <script type="text/javascript">
    $(document).ready(function(){
    	$('#tabs').tabs();
		$tabs.tabs('select', 2);
		});
    function checkBook(a){
       if(a.length>1000){
       $("#bookcorrect").css("display","none");
       $("#bookwrong").css("display","block");
       $("#bookwrong").html("长度不能超过1000位。");
        return false;
       }
         $("#bookwrong").css("display","none");
         $("#bookcorrect").css("display","block");
         return true;
     }
</script>
</head>
<body >
	<div id="doc">
		<div id="top-stuff">
			<div id="quick-link">
				<div id='quick-link-inside'>
					<ul>
						<li><a href="profile.html">彪马拖鞋</a></li>
						<li><a href="messages.html">私信</a></li>
						<li><a href="notifications.html">通知</a></li>
						<li><a href="account_setting.html">设置</a></li>
						<li><span class='sep'>|</span></li>
						<li><a href="index.html">退出</a></li>
					</ul>
				</div>
			</div>
			<div id="top-bar">
				<div id="top-bar-inside">
					<div id="logo">
						<span class="logo-zh">圈子</span><span class="logo-en">Q.com.cn</span>
					</div>
					<div id="nav">
						<ul>
							<li><a href="groups-feed.html">圈子新鲜事</a></li>
							<li><a href="friends-feed.html">好友新鲜事</a></li>
							<li><a href="profile.html">我的主页</a></li>
							<li><a href="#friends">好友</a></li>
						</ul>
					</div>
					<div id="search">
						<input type="text" class="search_field" size="35" value="搜微博、圈子、好友"><a href="" class="button">搜索</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div id="settings">
			<div id="settings-container">
				<div class="heading">
					<h2>个人资料设定</h2>
				</div>
				<div id="tabs">
					<ul>
						<li><a href="#tabs-1">基本信息</a></li>
						<li><a href="#tabs-2">头像</a></li>
						<li><a href="#tabs-3">喜好</a></li>
					</ul>
					<div id="tabs-1" class="tab-canvas">
					</div>
					<div id="tabs-2" class="tab-canvas">
					</div>
					<div id="tabs-3" class="tab-canvas">
					<form>
							<table id="setting-form" class='input-form'>
							<tbody>
								<tr>
									<th><label for=''>喜欢的书：</label></th>
									<td class='col-field'><textarea cols="50" rows="4"></textarea></td>
									<td class='col-help'>

										<div class='label-box-good' id="bookcorrect" style='display:none;'></div>
										<div class='label-box-error'id="bookwrong" style='display:none;'></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>喜欢的电影：</label></th>
									<td class='col-field'><textarea cols="50" rows="4"></textarea></td>
									<td class='col-help'>

										<div class='label-box-good' style='display:none;'></div>
										<div class='label-box-error' style=''></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>喜欢的音乐：</label></th>
									<td class='col-field'><textarea cols="50" rows="4"></textarea></td>
									<td class='col-help'>

										<div class='label-box-good' style='display:none;'></div>
										<div class='label-box-error' style=''></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>影响我的人：</label></th>
									<td class='col-field'><textarea cols="50" rows="4"></textarea></td>
									<td class='col-help'>

										<div class='label-box-good' style='display:none;'></div>
										<div class='label-box-error' style=''></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>最近的愿望：</label></th>
									<td class='col-field'><textarea cols="50" rows="4"></textarea></td>
									<td class='col-help'>

										<div class='label-box-good' style='display:none;'></div>
										<div class='label-box-error' style=''></div>
									</td>
								</tr>
								<tr>
									<th></th>
									<td colspan='2' class='bottom'></td>
								</tr>
								<tr>

									<th></th>
									<td colspan='2'><button class='button tweet-button' type='submit'>保存</button></td>
								</tr>
							</tbody>
						</table>
						</form>

					</div>
				</div>
			</div>
		</div>


		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
					<div class="home-header">
						<div class="main-header-box">
							<div class="header-box">
								<div class="group-box-title">
									<h2>小芳CHINA</h2>
								</div>
								<div class="position" style="display:none;">
									<span class="desc">所在地：</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="dashboard" style="display:block;">
				</div>
			</div>
		</div>
	</div>
  </body>
</html>