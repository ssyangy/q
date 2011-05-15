<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Q.com.cn</title>
    <link href="${staicUrlPrfix}/content-q/jquery.ui.css")" rel="stylesheet" type="text/css" />
    <link href="${staicUrlPrfix}/content-q/qcomcn.css")" rel="stylesheet" type="text/css" />
    <style type="text/css">
	    .wapper{position:relative;height:600px;width:800px;}
	    #index{margin:20px auto;padding:20px 0px;width:800px;background-color:#fff;}
	    #index .header .logo{font-size:36px;font-weight:bold;}
	    #index .header .slogan{margin-top:15px;font-size:14px;line-height:1.5em;}
	    .logo-area{width:490px;float:left;}
	    .signin-area{float:left;padding-left:20px;border-left:1px solid #EBEBEB;}
	    .signin-area th{font-size: 14px;font-weight: normal;text-align: right;color: #333;}
	    .signin-area td{height:35px;}
	    #index .content-inner{padding:0;}
	    #index .main{float:left;width:490px}
	    #index .main-inner{padding-right:20px;}
	    #index .head-line .head-h3{float:left;margin-right:20px;}
	    #index .head-line .location{float:left;margin-top:16px;}
	    #index .photo-wall .one-photo{float:left;margin-right:5px;margin-bottom:5px;}
	    #index .side{float:left;padding-left:20px;width:240px;border-left:1px solid #EBEBEB;}
	    #index .content h3{margin:15px 0 10px 0;}
	    #index .member-online{margin-top:20px;}
	    #index .hot-events td{height:20px;}
	    #index .footer{margin-top:20px;text-align:center;}
	    #index .hot-tweets .avatar{float:left;margin-right:10px;}
	    #index .hot-tweets .brief{float:left;width:180px;}
	    #index .one-tweet{margin-bottom:10px;}
	    #index .footer ul{list-style: none outside none;}
	    #index .footer li{display:inline;margin-top:40px;margin-right:10px;}
    </style>
    <script src="${staticUrlPrefix}/scripts-q/sea.js")" type="text/javascript"></script>
    <script type="text/javascript">
        seajs.use('qcomcn.js', function (q) {
            var $ = q.jq;
            $(function () {
                q.Init();
            });
            check = function(){
        		  var username=$("#username").val();
        		  var password=$("#password").val();
        		  $.ajax({
        		    url: '${urlPrefix}/login',
        		    type: 'POST',
        		    dataType: 'json',
        		    data:{password:password,username:username},
        		    timeout: 5000,
        		    error: function(){
        		    	console.log("error");
        		    },
        		   	success: function(json){
        		        if(json.id != null){
        		            document.location.href="${urlPrefix}/group/feed"
        		         } else {
        		          var errorkind = errorType(json.error);
        		          var errormsg = errorContext(json.error);
        		          var loginWrong = $("#loginWrong");
        		          loginWrong.css("display","block");
        		          loginWrong.html(errormsg);
        		      }
        		    }
				});
			}            
        });
    </script>
</head>
<body>
<div id="index" class="wapper">
	<div class="header">
		<div class="logo-area">
			<div class="logo"><span class="logo-zh">圈子</span><span class="logo-en">Q.com.cn</span></div>
			<div class="slogan">找到志趣相投的朋友，吃喝玩乐应有尽有，圈子就是好玩！有圈子才尽兴！<br />喂！...你哪个圈儿的？！</div>
		</div>
		<div class="signin-area input-form">
			<table>
				<tbody>
					<tr>
						<th>邮箱：</th>
						<td><input type='text' class='mttext' size='23' accesskey='l'></td>
					</tr>
					<tr>
						<th>密码：</th>
						<td><input type='text' class='mttext' size='23' accesskey='l'></td>
					</tr>
					<tr>
						<th></th>
						<td><input type="checkbox"> 保持登录状态<a href="forgot_password.html" class="lk ml20">忘记密码</a></td>
					</tr>
					<tr>
						<th></th>
						<td><a href="#" class="btn access_l" onclick="check()">登 录</a><a href="signup.html" class="lk ml20">立即注册</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
	</div>
	<div class="content">
		<div class="content-inner">
			<div class="main">
				<div class="main-inner">
					<div class="groups-cat">
					<h3>圈子分类</h3>
					<table class="groups-cat" width="100%">
						<tbody>
							<tr>
								<th><img src="images/clock.png"></th>
								<td><div class="desc"><div class="action"><a href="">更多...</a></div><div>八小时以外是另一个我</div></div>
									<div class="group"><a href="group.html">吃好喝好</a><a href="">泡吧</a><a href="">麻将</a><a href="">扑克</a><a href="">桌游</a><a href="">三国杀</a><a href="">旅行</a></div>
								</td>
							</tr>
							<tr>
								<th><img src="images/clock.png"></th>
								<td><div class="desc"><div class="action"><a href="">更多...</a></div><div>八小时以外是另一个我</div></div>
									<div class="group"><a href="">吃好喝好</a><a href="">泡吧</a><a href="">麻将</a><a href="">扑克</a><a href="">桌游</a><a href="">三国杀</a><a href="">旅行</a></div>
								</td>
							</tr>
							<tr>
								<th><img src="images/clock.png"></th>
								<td><div class="desc"><div class="action"><a href="">更多...</a></div><div>八小时以外是另一个我</div></div>
									<div class="group"><a href="">吃好喝好</a><a href="">泡吧</a><a href="">麻将</a><a href="">扑克</a><a href="">桌游</a><a href="">三国杀</a><a href="">旅行</a></div>
								</td>
							</tr>
							<tr>
								<th><img src="images/clock.png"></th>
								<td><div class="desc"><div class="action"><a href="">更多...</a></div><div>八小时以外是另一个我</div></div>
									<div class="group"><a href="">吃好喝好</a><a href="">泡吧</a><a href="">麻将</a><a href="">扑克</a><a href="">桌游</a><a href="">三国杀</a><a href="">旅行</a></div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="member-online">
					<div class="head-line">
						<div class="head-h3"><h3>同城在线</h3></div>
						<div class="location">上海 - 长宁区</div>
						<div class="clear"></div>
					</div>
					<div class="photo-wall">
						<div class="one-photo"><img src="images/1.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/2.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/3.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/4.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/5.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/6.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/7.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/avatar3.jpg" width="72" height="72"></div>
						<div class="one-photo"><img src="images/avatar5.jpeg" width="72" height="72"></div>
						<div class="one-photo"><img src="images/1.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/2.png" width="72" height="72"></div>
						<div class="one-photo"><img src="images/3.png" width="72" height="72"></div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="side">
				<div class="hot-events">
					<h3>活动ING</h3>
					<table width="100%">
						<tr>
							<td width="17%">02-14</td>
							<td width="23%">长宁区</td>
							<td><a href="event.html">G+ 新天地单身派对</a></td>
						</tr>
						<tr>
							<td width="17%">01-01</td>
							<td width="23%">黄浦区</td>
							<td><a href="">MUSE倒计时，苍井空</a></td>
						</tr>
						<tr>
							<td width="17%">02-14</td>
							<td width="23%">长宁区</td>
							<td><a href="">G+ 新天地单身派对</a></td>
						</tr>
						<tr>
							<td width="17%">01-01</td>
							<td width="23%">黄浦区</td>
							<td><a href="">MUSE倒计时，苍井空</a></td>
						</tr>
						<tr>
							<td width="17%">02-14</td>
							<td width="23%">长宁区</td>
							<td><a href="">G+ 新天地单身派对</a></td>
						</tr>
						<tr>
							<td width="17%">01-01</td>
							<td width="23%">黄浦区</td>
							<td><a href="">MUSE倒计时，苍井空</a></td>
						</tr>
						<tr>
							<td width="17%">02-14</td>
							<td width="23%">长宁区</td>
							<td><a href="">G+ 新天地单身派对</a></td>
						</tr>
						<tr>
							<td width="17%">01-01</td>
							<td width="23%">黄浦区</td>
							<td><a href="">MUSE倒计时，苍井空</a></td>
						</tr>
					</table>
				</div>
				<div class="hot-tweets">
					<h3>热议ING</h3>
					<div class="one-tweet">
						<div class="avatar">
							<img src="images/avatar3.jpg">
						</div>
						<div class="brief">
							<a href="" class"author">Lodge</a>：话说每年也只有315的时候，国内的媒体才有功夫打假之类的，不然怎么可能所有的的事情都在315的时候爆出来，平时就没见报过啥呢
						</div>
						<div class="clear"></div>
					</div>
					<div class="one-tweet">
						<div class="avatar">
							<img src="images/avatar3.jpg">
						</div>
						<div class="brief">
							<a href="" class"author">Lodge</a>：发现家里有一瓶梅子酒…喝着梅酒看中央电视台
						</div>
						<div class="clear"></div>
					</div>
					<div class="one-tweet">
						<div class="avatar">
							<img src="images/avatar3.jpg">
						</div>
						<div class="brief">
							<a href="" class"author">Lodge</a>：话说每年也只有315的时候，国内的媒体才有功夫打假之类的，不然怎么可能所有的的事情都在315的时候爆出来，平时就没见报过啥呢
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
		<div class="footer">
			<ul>
				<li><a href="">关于我们</a></li>
				<li><a href="">版权所有2011-END</a></li>
				<li><a href="">沪TMD备7654321</a></li>
			</ul>
		</div>
</div>
</body>
</html>