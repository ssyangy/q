<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#emots{position:absolute;top:22px;left:0;background:#f6f6f6;border:1px solid #dcdcdc;}
#emots img{margin:2px;cursor:pointer;}
#emots img:hover{border:1px solid #dcdcdc;margin:1px;}
</style>
<script type="text/javascript">
var isImg = false;
var upimgfix = 0;
var realHeight;
var realWidth;
var imgPath;
var dia_img;
seajs.use(['qcomcn','jqplus/jq_rotate', 'jqplus/jq_countable','plus/emote'], function(q,rota, cb, emote) {
	var $ = q.jq;
	rota($);
	cb($);
	$(function(){

		$("#emots>img").click(function(){
			var txt = emote.text[$(this).attr("mid")-1];
			var maintxt = $("#inputmain").val();
			if(maintxt == "说点什么 . . .") maintxt = "";
			$("#inputmain").val(maintxt+"["+txt+"]").focusaft();
		});

/* 		$('#inputmain').focus(function() {
			$(this).css('height', '40');
			$('#inputbtm').removeClass('height0');
		}); */
		
		$("body").bind("keyup", function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var tarname = $(e.target).get(0).tagName;
			if (tarname != 'INPUT' && tarname != 'TEXTAREA' && code == 65) {
				$('#inputmain').focus();
			}
		});

		$('#btnSubTweet').click(function() {
			$("#trDialog_img").bind('click', bindImgDia);
			if (!isImg) {
				imgPath = null;
				$("#picPath").attr("value", imgPath);
				$("#upimgfix").attr("value", upimgfix);
				upimgfix = 0;
			}
			isImg = false;
		});

		dia_img = $("#dia_img");
		bindImgDia = function() {
			dia_img.dialog("open");
			upimgfix = 0;
		}

		dia_img.data('hasPicture', false);
		$('input.donet', dia_img).click(function() {
			if (dia_img.data('hasPicture')) {
				$("#picPath").attr("value", imgPath);
				$("#upimgfix").attr("value", upimgfix);

				if (realHeight > realWidth) {
					$("#img").attr("width", 160 * realWidth / realHeight);
					$("#img").attr("height", 160);
				} else {
					$("#img").attr("height", 160 * realHeight / realWidth);
					$("#img").attr("width", 160);
				}
				$("#img").attr("src", imgPath + "-160");

				$('#trDialog_img').unbind();
				$('#upimgpbox').removeClass('hide');
				$('#upimgpbox img').rotate(upimgfix);
				$('#file').attr("value", "");
				$('ol.files').addClass('hide');
				dia_img.data('hasPicture', false);
			}
			$("#file").attr("value", '');
			dia_img.dialog("close");
		});
		$('input.undonet', dia_img).click(function() {
			$('#upimgpbox').addClass('hide');
			$('#upimg', dia_img).attr('src', '');
			$('#upimgpbox>img').attr('src', '');
			$("#trDialog_img").bind('click', bindImgDia);
			isImg = false;
			upimgfix = 0;
			dia_img.data('hasPicture', false);
			imgPath = null;
			$("#file").attr("value", '');
			$("#picPath").attr("value", imgPath);
			$("#upimgfix").attr("value", upimgfix);
		});

		$("#trDialog_img").bind('click', bindImgDia);
		$('#upimgdel').click(function() {
			$("#file").attr("value", '');
			$('#upimgpbox').addClass('hide');
			$('#upimg', dia_img).attr('src', '');
			$('#upimgpbox>img').attr('src', '');
			$("#trDialog_img").bind('click', bindImgDia);
			isImg = false;
			upimgfix = 0;
			dia_img.data('hasPicture', false);
			imgPath = null;
			$("#picPath").attr("value", imgPath);
			$("#upimgfix").attr("value", upimgfix);
		});
		$('#imgRotateR', dia_img).click(function() {
			$('#upimg', dia_img).rotate(upimgfix + 90);
			upimgfix += 90;
		});
		$('#imgRotateL', dia_img).click(function() {
			$('#upimg', dia_img).rotate(upimgfix - 90);
			upimgfix += 270;
		});
		$("#radio").buttonset();
		var check = function() {
			var filepath = document.getElementById("file").value;
			filepath = filepath.substring(filepath.lastIndexOf('.') + 1,
					filepath.length);
			if (filepath != 'jpg' && filepath != 'gif' && filepath != 'png'
					&& filepath != 'jpeg' && filepath != 'JPEG'
					&& filepath != 'JPG' && filepath != 'GIF') {

				$("#imgwrong").css("display", "block");
				$("#imgwrong").html("这不是一个图片文件!");
				document.getElementById("file").value = "";
				isImg = false;
				return;
			}
			$("#imgwrong").css("display", "none");
			isImg = true;
		}
		$('#file').bind('change', check);
		$('#upimgsub').bind('click', check);
		$('#formImg').bind('submit', function() {
			return isImg;
		});

		up = function() {
			return isImg;
		}
		notAImg=function() {
			isImg = false;
			$("#imgwrong").css("display", "block");
			$("#imgwrong").html("这不是一个图片文件!");
		}
		showWrong=function(value) {
			alert(value);
		}
		reloadImg = function(x, y, z) {
			realHeight = x;
			realWidth = y;
			imgPath = z;
			$('ol.files').removeClass('hide');
			$("#picPath").attr("value", imgPath);
			if (realHeight > realWidth) {
				max = "height";
				imageWidth = realWidth * 320 / realHeight;
				imageHeight = 320;
			} else {
				max = "width";
				imageHeight = realHeight * 320 / realWidth;
				imageWidth = 320;
			}
			$("#upimg").attr("width", imageWidth);
			$("#upimg").attr("height", imageHeight);
			$("#upimg").attr("src", imgPath + "-320");
			$('#upimg').rotate(upimgfix);
			dia_img.data('hasPicture', true);
		}

		function setImg() {
			if (realHeight > realWidth) {
				$("#img").attr("width", 160 * realWidth / realHeight);
				$("#img").attr("height", 160);
			} else {
				$("#img").attr("height", 160 * realHeight / realWidth);
				$("#img").attr("width", 160);
			}
			$("#img").attr("src", imgPath + "-160");
		}
	});
});

function checkWeibo() {
	var content=document.weiboForm.content.value.trim();
	if(content=='说点什么 . . .' || content=='' || content=='\n' || content=='\t' || content.length > 140) {
		return false;
	} else {
		return true;
	}

}
</script>
<form action="${urlPrefix}/weibo" onsubmit="return checkWeibo();" method="post" name="weiboForm">
	<div class="inputbx">
		<textarea id="inputmain" style="height:60px;" name="content" maxlength="140" class="mttextar_val countable">说点什么 . . .</textarea>
		<div id="inputbtm" class="rel clear">
			插入：
			<a class="lk mr10 emot" tgtt='emots'>表情</a>
	        <div id="emots" class='tgtbox'>
				<c:forEach var="id" begin="1" end="81">
					<img src="${imageUrlPrefix}/biaoqing/${id}.gif" mid="${id}">
				</c:forEach>
	        </div>
			<a id='trDialog_img' class="lk mr10 pict">图片</a>
			<c:choose>
				<c:when test="${param['selectGroup'] != null}">
					<select name="groupId">
						<option value="0">发到圈子？</option>
						<c:forEach items="${selectGroups}" var="group">
							<option value="${group.id}">${group.name}</option>
						</c:forEach>
					</select>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="groupId" value="${group.id}" />
				</c:otherwise>
			</c:choose>
			<span class="cttarget abs" style="right:70px;bottom:5px;"></span>
			<input id="btnSubTweet" type="submit" name="name" value="发表"
				class="btnr btninp" /> <input type="hidden" name="from"
				value="${param['from']}" /> <input type="hidden" name="picPath"
				id="picPath" /> <input type="hidden" name="upimgfix" id="upimgfix" />
		</div>
		<div id='upimgpbox' class='hide'>
			<img id="img" src='#' class='img160' /><br /> <a id='upimgdel'
				class="lk">删除</a>
		</div>
	</div>
</form>

<div id="dia_img" class='ui_dialog hide' title="上传图片">
	<form action="${urlPrefix}/weibo/picture" id="formImg" name="formImg"
		encType="multipart/form-data" method="post" onsubmit="return up()"
		target="hidden_frame">
		<input type="file" name="file" id="file"
			accept="image/gif, image/jpeg" style="width: 450"></input>
		<div style='display: none;' id="imgWrong"></div>
		<input id='upimgsub' type="submit" value="上传图片"></input>
		<p>Uploaded files:</p>
		<ol class='files hide'>
			<div class='upimgbox middle imgrote'>
				<img id='upimg' src='' />
			</div>
			<a id='imgRotateL' class='link mr10'>左转</a>
			<a id='imgRotateR' class='link'>右转</a>
		</ol>
		<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
	</form>
	<input type='hidden' class='donet' /> <input type='hidden'
		class='undonet' />
</div>