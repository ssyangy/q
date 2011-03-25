<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <jsp:include page="head.jsp" flush="true"/>
    <jsp:include page="js-areas.jsp" flush="true"/>
    <link rel="stylesheet" type="text/css" href="${staticUrlPrefix}/style/jcrop/jquery-jcrop-0.9.8.css"  />
    <script type="text/javascript" src="${staticUrlPrefix}/js/jquery-jcrop-0.9.8.min.js"></script>
	<script type="text/javascript">
    function check(){
     var filepath=document.getElementById("pic").value;
      filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length);
       if(filepath != 'jpg' && filepath != 'gif'&& filepath != 'png'){
           $("#imgwrong").css("display","block");
           $("#imgwrong").html("这不是一个图片文件!");
           document.getElementById("pic").value="";
           return;
       }
        $("#imgwrong").css("display","none");
        var api=$.Jcrop('#myImage',{

        aspectRatio:1
        });

    }
    $(document).ready(function(){
		$('#tabs').tabs();
		$tabs.tabs('select', 1);


		});


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
						<li><a href="#tabs-4">教育与工作</a></li>
					</ul>
					<div id="tabs-1" class="tab-canvas">
					aaa
					</div>
					<div id="tabs-2" class="tab-canvas">
				    <input type="file" name="pic" id="pic" accept="image/gif, image/jpeg" onchange="check()" ></input>
					<div class="label-box-error" style="display: none;" id="imgwrong"></div>
					<div>
                       <img id="myImage" src="http://www.q.net/a/1.png" alt="xxxxx" />
                    </div>
					</div>
					<div id="tabs-3" class="tab-canvas">
					</div>
					<div id="tabs-4" class="tab-canvas">
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
