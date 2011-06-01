<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="修改头像" />
</jsp:include>
<link rel="stylesheet" type="text/css" href="${staticUrlPrefix}/content/jcrop/jquery-jcrop-0.9.8.css" />
<style type="text/css">
.imgbox{float:left;width:200px;height:200px;margin-right:25px;background:url(http://i1.q.com.cn/default/male-def-128) no-repeat scroll 0 0;}
.imgmain{float:left;width:500px;line-height:25px;}
</style>
<script type="text/javascript" src="${staticUrlPrefix}/scripts/src/jquery-1.6.1.js"></script>
<script type="text/javascript" src="${staticUrlPrefix}/scripts/src/jq.jcrop.js"></script>
<script type="text/javascript">
var realWidth;
var realHeight;
var imageWidth;
var imageHeight;
var isImg=true;
var cutter;
var divide=1;
var groupId;
var groupPath;
	$(function () {
         		cutter = new jQuery.UtrialAvatarCutter(
			{
				//主图片所在容器ID
				content : "myImage",

				//缩略图配置,ID:所在容器ID;width,height:缩略图大小
				purviews : [{id:"picture_48",width:48,height:48},{id:"picture_64",width:64,height:64}],

				//选择器默认大小
				selector : {width:100,height:100}
			}

		);

        groupId="${group.id}";
        groupPath="${avatarPath}";
		if("${avatarExists}"=="true"){
		var img=new Image();
		img.src="${avatarPath}";

		var dick=setInterval(function(){
			if(img.complete){
				clearInterval(dick);
				reloadImg(img.height,img.width,"${avatarPath}");
				cutter.init();
			}
		},100);
		 $("#saveButton").css("display","block");
		  $("#cancelButton").css("display","block");
		}
	});


function upload(){
check();
$("#hidden_frame").css("display","none");
	return isImg;
	}
	function save(){

	var data = cutter.submit();
	var x1=data.x;
	var y1=data.y;
	var x2=x1+data.w;
	var y2=y1+data.h;
	var realx1=x1*realWidth/imageWidth;
	var realx2=x2*realWidth/imageWidth;
	var realy1=y1*realHeight/imageHeight;
	var realy2=y2*realHeight/imageHeight;

	$.ajax({
    url: '${urlPrefix}/group/edit',
    type: 'POST',
    dataType: 'json',
    data:{realx1: realx1,realy1:realy1,realx2:realx2,realy2:realy2,groupId:groupId},
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
	function showWrong(value){
	isImg=false;
	alert(value);

	}
    function notAImg(){
    	isImg=false;
         $("#imgwrong").css("display","block");
         $("#imgwrong").html("这不是一个图片文件!");
    }
    function reloadImg(x,y,z,id){
        if(id!=null)
    	groupId=id;

     $("#saveButton").css("display","block");
    $("#cancelButton").css("display","block");
    realHeight=x;
	realWidth=y;
	var imgPath=z;

      if(realHeight>realWidth){
    	 imageWidth=realWidth*200/realHeight;
         imageHeight=200;
      }
      else{
    	 imageHeight=realHeight*200/realWidth;
         imageWidth=200;
      }
      cutter.reload(imgPath,imageWidth,imageHeight);
	}

    function check(){
     var filepath=document.getElementById("file").value;
      filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length);
       if(filepath != 'jpg' && filepath != 'gif'&& filepath != 'png'&& filepath != 'jpeg'&& filepath != 'JPEG'&& filepath != 'JPG'&& filepath != 'GIF'){
           $("#imgwrong").css("display","block");
           $("#imgwrong").html("这不是一个图片文件!");
           document.getElementById("file").value="";
           isImg= false;
           return ;
       }
        $("#imgwrong").css("display","none");
          isImg=true;
    }


    jQuery.UtrialAvatarCutter = function(config){
	var h,w,x,y;

	var os,oh,ow;

	var api = null;

	var sel = this;

	var img_content_id = config.content;

	var img_id = "myPhoto";
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
	this.reload = function(img_url,width,height){
		if(img_url!=null && img_url != ""){
			os = img_url+"?"+Math.random();
			$("#"+img_content_id).html("<img id='"+img_id+"' src='"+os+"' width='"+width+"' height='"+height+"'/>");
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
		api = jQuery.Jcrop('#'+img_id,{
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
<div class="layout grid-s4m0e6">
	<div class="col-main">
		<div class="main-wrap pr10">
			<h2>圈子设置</h2>
			<div class="ui-tabs mt10">
				<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
					<li class="ui-state-default crt2 "><a
						href="${urlPrefix}/group/${group.id}/edit">基本信息</a></li>
					<li class="ui-state-default crt2 ui-state-active"><a
						href="${urlPrefix}/group/${group.id}/avatar">圈子头像</a></li>

				</ul>
			</div>
			<div class='tabscont clear' style="padding: 10px;">

				<div class="imgbox">
					<div id="myImage"></div>
				</div>
				<div class="imgmain">
					从电脑中选择你喜欢的照片：<br /> <span class="gray">您可以上传JPG、JPEG、GIF或PNG文件。</span>
					<br />
					<form action="${urlPrefix}/group/${group.id}/picture" id="form1" name="form1" method="post"
						encType="multipart/form-data"  target="hidden_frame" onsubmit="return upload()">
						<ul>
							<li>
							1. <input type="file" name="file" id="file" accept="image/gif, image/jpeg" onchange="check()"></input>
							</li>
							<li>2. <input type="submit" value="上传头像"></input></li>
							<li>3. 随意拖拽或缩放大图中的虚线方格，下方预览的小图即为保存后的头像图标。</li>
						</ul>
						<div style='display: none;' id="imgwrong"></div>
						<iframe name='hidden_frame' id="hidden_frame" style='display: none'> </iframe>
					</form>

					<div class='clear'>
						<div id="picture_48" class="wh48" style="overflow: hidden; float: left;"></div>
						<div id="picture_64" class="wh64" style="overflow: hidden; float: left;"></div>
					</div>
					<div>
						<div class='hide' id="savewrong"></div>
						<div class='hide' id="savecorrect"></div>
						<input type="button" id="saveButton" class='btnr hide' value='保存' onclick="save()"></input> 
						<input type="button" id="cancelButton" class='btnr hide' value='取消'></input>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sub">
		<jsp:include page="models/groups-mine.jsp">
			<jsp:param name="id" value="${group.id}" />
		</jsp:include>
	</div>
	<div class="col-extra"></div>
</div>


<jsp:include page="models/foot.jsp" />
