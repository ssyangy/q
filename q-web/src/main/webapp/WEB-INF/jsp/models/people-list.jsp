<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script type="text/javascript">
	seajs.use(['qcomcn.js','ICanHaz.js'], function (q, ich) {
		var $ = q.jq;
		$(function(){
			var mems = $('#mems');
			$.ajax({
				url:"${param['feedUrl']}",
				data:{startId:"999999999999999999",size:10},
				success:mem_ajsucc
			});
			$('a.mbprev').live('click',function(){
				$.ajax({
					url:"${param['feedUrl']}",
					data:{startId:$("li",mems).first().attr("stream_id"),size:10, type:1},
					success:mem_ajsucc
				});
			});
			$('a.mbnext').live('click',function(){
				$.ajax({
					url:"${param['feedUrl']}",
					data:{startId:$("li",mems).last().attr("stream_id") ,size:10},
					success:mem_ajsucc
				});
			});
			var mem_ajsucc = function(json){
            	var pv = $('a.mbprev'); pv.hide(); if (json.hasPrev) pv.show();
            	var nt = $('a.mbnext'); nt.hide(); if (json.hasNext) nt.show();	            
	            mems.empty();
                $(json.peoples).each(function(){
                	ich.members();
                	mems.append(ich.members(this));
                });
			}
			var dia_fow = $("#dia_fow");
	        $('input.donet', dia_fow).click(function () {
            	  $('img.ajaxload', this).show();
              	  var dia = $('#dia_fow');
              	  $.ajax({
					    url: '${urlPrefix}/weibo',
					    type: 'POST',
					    data: {content:$("textarea[name='content']",dia).val()},
					   	success: function(m){
					   		dia_fow.dialog("close");
					   		$('img.ajaxload', dia_fow).hide();
					    }
              	  });
	        });
			var dia_letter = $("#dia_letter");
	        $('input.donet', dia_letter).click(function () {
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
	        });	        
			$("a.btn_fow").live('click', function () {
				var stream = $(this).closest('div.stream-item');
				$("textarea[name='content']",dia_fow).val('').val('//@'+$('span.display-name',stream).text().trim());
				dia_fow.dialog("open");
			});
			$("a.btn_letter").live('click', function () {
				var stream = $(this).closest('div.stream-item');
				$('div.wpeople',dia_letter).empty().html('发私信给：'+ $('span.display-name',stream).text().trim());
				$("textarea[name='content']",dia_letter).val('');
				var peopleid = $('input.peopleid',stream).val();
				$("#letter_url",dia_letter).val('${urlPrefix}/message?receiverId='+peopleid);
				dia_letter.dialog("open");
			});
		});
	});
	</script>
<script type="text/html" id="members">
    <li stream_id='{{id}}'>
        <a href="${urlPrefix}/people/{{id}}"><img src="{{avatarPath}}" alt="{{realName}}" class="sldimg" /></a>
        <p><a class="lk" href="${urlPrefix}/people/{{id}}">{{realName}}</a></p>
        <p>{{area.myProvince.name}&nbsp;{{area.myCity.name}}&nbsp;{{area.myCounty.name}}</p>
        <p>{{ntro}}</p>
        <span class="act"><a class="btna msg">私信</a><a class="btn at">&#64</a><a class="btn unwat">解除关注</a></span>
    </li>
</script>
<ul id='mems' class="msglist"></ul>
<a class='lk mr10 mbprev hide'>上一页</a>
<a class='lk mbnext hide'>下一页</a>

<div id="dia_fow" class="ui_dialog" title="@">
	<textarea name="content" rows="5" cols="50"></textarea>
	<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>
<div id="dia_letter" class="ui_dialog" title="私信">
	<div class="wpeople mb10"></div>
	<input id='letter_url' type='hidden'></input>
	<textarea name="content" rows="5" cols="50"></textarea>
	<img src="${staticUrlPrefix}/style/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />		
</div>
