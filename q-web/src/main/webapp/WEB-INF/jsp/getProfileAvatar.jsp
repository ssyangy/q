<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <jsp:include page="head.jsp" />
    <jsp:include page="js-areas.jsp" />

    <link rel="stylesheet" type="text/css" href="${staticUrlPrefix}/style/jcrop/jquery-jcrop-0.9.8.css"  />
    <script type="text/javascript" src="${staticUrlPrefix}/js/jquery-jcrop-0.9.8.min.js"></script>
	<script type="text/javascript">
    var realWidth;
    var realHeight;
	var isImg=false;
	var imgWidth;
	var imgHeight;
    var cutter;
	function up(){
	if(isImg==true){
		cutter = new jQuery.UtrialAvatarCutter(
				{
					//主图片所在容器ID
					content : "myImage",

					//缩略图配置,ID:所在容器ID;width,height:缩略图大小
					purviews : [{id:"picture_24",width:24,height:24},{id:"picture_48",width:48,height:48},{id:"picture_128",width:128,height:128}],

					//选择器默认大小
					selector : {width:100,height:100}
				}
			);
			 cutter.init();
	}

	return isImg;
	}
	function save(){
	var data = cutter.submit();
	var x1=data.x;
	var y1=data.y;
	var x2=x1+data.w;
	var y2=y1+data.h;
    var realx1=Number(x1/imgWidth*realWidth);
    var realy1=Number(y1/imgHeight*realHeight);
    var realx2=Number(x2/imgWidth*realWidth);
    var realy2=Number(y2/imgHeight*realHeight);
    alert(realx1+" "+realy1+" "+realx2+" "+realy2);
    	var emailtext=$("#email").val();
	$.ajax({
    url: '${urlPrefix}/avatar/edit',
    type: 'POST',
    dataType: 'json',
    data:{realx1: realx1,realy1:realy1,realx2:realx2,realy2:realy2},
    timeout: 5000,
    error: function(){
          $("#savecorrect").css("display","none");
          $("#savewrong").css("display","block");
          $("#savewrong").html("服务器忙，请稍后再尝试");
    },
    success: function(json){
      if(json == null){
          $("#savecorrect").css("display","block");
          $("#savewrong").css("display","none");
          $("#savecorrect").html("修改头像图片成功");

      } else {
          $("#savecorrect").css("display","none");
          $("#savewrong").css("display","block");
          $("#savewrong").html(errorContext(json.error));

      }
    }
});
	}
    function notAImg(){
         $("#imgwrong").css("display","block");
         $("#imgwrong").html("这不是一个图片文件!");
    }
    function reloadImg(x,y,z){
    realHeight=x;
	realWidth=y;
	var imgPath=z;
      var tempImg= $("#tempImg");
      if(realHeight>realWidth){
         tempImg.height=200;
         tempImg.width=realWidth*200/realHeight;
         imgHeight=200;
         imgWidth=tempImg.width;
      }
      else{
         tempImg.width=200;
         tempImg.height=realHeight*200/realWidth;
         imgHeight=tempImg.height;
         imgWidth=200;
      }

      cutter.reload(imgPath);

	}
    function check(){
     var filepath=document.getElementById("file").value;
      filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length);
       if(filepath != 'jpg' && filepath != 'gif'&& filepath != 'png'&& filepath != 'jpeg'){
           $("#imgwrong").css("display","block");
           $("#imgwrong").html("这不是一个图片文件!");
           document.getElementById("file").value="";
           isImg= false;
           return ;
       }
        $("#imgwrong").css("display","none");
          isImg=true;
    }
    $(document).ready(function(){

	//	$('#tabs').tabs();
	//	$tabs.tabs('select', 1);
	});

    jQuery.UtrialAvatarCutter = function(config){
	var h,w,x,y;

	var os,oh,ow;

	var api = null;

	var sel = this;

	var img_content_id = config.content;

	var img_id = "img_"+(Math.random()+"").substr(3,8);
	var purviews = new Array();

	var select_width = null;
	var select_height = null;

	if(config.purviews){
		for(i=0,c=config.purviews.length;i<c;++i){
			purviews[purviews.length] = config.purviews[i];
		}
	}

	check_thums_img = function(){
		if(config.purviews){
			for(i=0,c=config.purviews.length;i<c;++i){
				if($('#'+config.purviews[i].id+" img").length==0){
					$('#'+config.purviews[i].id).html("<img src='"+os+"'/>");
				}else{
					$('#'+config.purviews[i].id+" img").attr("src",os);
				}
			}
		}
	}

	/*
	 *	重新加载图片
	 */
	this.reload = function(img_url){
		if(img_url!=null && img_url != ""){
			os = img_url+"?"+Math.random();
			$("#"+img_content_id).html("<img id='"+img_id+"' src='"+os+"'/>");
			$("#"+img_id).bind("load",
				function(){
					check_thums_img();
					sel.init();
				}
			);
		}
	}
	$("#"+img_content_id+" img").attr("id",img_id);

	var preview = function(c) {
		if ( c.w == 0 || c.h == 0 ) {
			api.setSelect([ x, y, x+w, y+h ]);
			api.animateTo([ x, y, x+w, y+h ]);
			return;
		}
		x = c.x;
		y = c.y;
		w = c.w;
		h = c.h;
		for(i=0,c=purviews.length;i<c;++i){
			var purview = purviews[i];
			var rx = purview.width / w;
			var ry = purview.height / h;
			$('#'+purview.id+" img").css({
				width: Math.round(rx * ow) + 'px',
				height: Math.round(ry * oh) + 'px',
				marginLeft: '-' + Math.round(rx * x) + 'px',
				marginTop: '-' + Math.round(ry * y) + 'px'
			});
		}
	}

	this.init = function(){
		if(api!=null){
			api.destroy();
		}
		os = $("#"+img_content_id+" img").attr("src");
		if(os=="")
			return;
		check_thums_img();
		for(i=0,c=purviews.length;i<c;++i){
			var purview = purviews[i];
			var purview_content = $("#"+purview.id);
			purview_content.css({position: "relative", overflow:"hidden", height:purview.height+"px", width:purview.width+"px"});
		}

		oh = $('#'+img_id).height();
		ow = $('#'+img_id).width();

		select_width = config.selector.width;
		select_height = config.selector.height;

		select_width = Math.min(ow,select_width);
		select_height = Math.min(oh,select_height);

		x = ((ow - select_width) / 2);
		y = ((oh - select_height) / 2);
		//这是原Jcrop配置,修改此处可修改Jcrop的其它各种功能
		api = $.Jcrop('#'+img_id,{
			aspectRatio: 1,
			onChange: preview,
			onSelect: preview
		});
		//设置选择框默认位置
		api.animateTo([ x, y, x+select_width, y+select_height ]);

	}

	this.submit = function(){
		return {w:w,h:h,x:x,y:y,s:os};
	}
}
	</script>
  </head>
  <body >
	<div id="doc">
	  <jsp:include page="top.jsp"/>
	 	<div id="settings">
			<div id="settings-container">
				<div class="heading">
					<h2>个人资料设定</h2>
				</div>
				   <div id="tabs" class="ui-tabs ui-widget">
						<jsp:include page="profile-tag.jsp">
							<jsp:param value="avatar" name="tab"/>
						</jsp:include>
					<div id="tabs-1" class="tab-canvas">

					</div>
					<div id="tabs-2" class="tab-canvas">

					<form action="${urlPrefix}/Avatar"  id="form1" name="form1"  encType="multipart/form-data"
					method="post" target="hidden_frame" onsubmit="return up()">
				    <input type="file" name="file" id="file" accept="image/gif, image/jpeg" onchange="check()" style="width:450"></input>
				    <input type="submit" value="上传头像"></input>
                    <br/>
                    <font color="blue">支持JPG,JPEG,GIF文件的上传</font>
                    <div style='display:none;' id="imgWrong"></div>
                    <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>
                    </form>
			    	<div id="myImage">
                    <img   alt="xxxxx" id="tempImg"/>
                    </div>

                    <!---
			         缩略图
		                 -->
	                    	<div id="picture_24"></div>
		                    <div id="picture_48"></div>
		                    <div id="picture_128"></div>
		                    <div style='display:none;' id="savewrong"></div>
		                    <div style='display:none;' id="savecorrect"></div>
		                     <input type="button" value="保存" onclick="save()"></input>
                             <input type="button" value="取消"></input>
				    </div>
					<div id="tabs-3" class="tab-canvas">
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
