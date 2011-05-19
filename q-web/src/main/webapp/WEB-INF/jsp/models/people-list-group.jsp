<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script type="text/javascript">
	seajs.use(['qcomcn.js','ICanHaz.js'], function (q, ich) {
		var $ = q.jq;
		$(function(){
			var mems = $('#mems');
			var mem_ajsucc = function(json){
            	var pv = $('a.mbprev'); pv.hide(); if (json.hasPrev) pv.show();
            	var nt = $('a.mbnext'); nt.hide(); if (json.hasNext) nt.show();	            
	            mems.empty();
                $(json.peoples).each(function(){
                	var mm = ich.members(this);
                	$(mm).data("groupjoinid",this.joinGroup.id);
                	mems.append(mm);
                });
			}
			$.ajax({ url:"${param['feedUrl']}",
				data:{startId:"999999999999999999",size:10},
				success:mem_ajsucc });
			$('a.mbprev').click(function(){
				$.ajax({ url:"${param['feedUrl']}",
					data:{startId:$("li",mems).first().data("groupjoinid"),size:10, type:1},
					success:mem_ajsucc });
			});
			$('a.mbnext').click(function(){
				$.ajax({ url:"${param['feedUrl']}",
					data:{startId:$("li",mems).last().data("groupjoinid") ,size:10},
					success:mem_ajsucc });
			});

			$("a.unwat").live('click', function () {
	          	  var stream = $(this).closest('li');
	          	  $.ajax({ url: '${urlPrefix}/people/'+stream.attr('stream_id') +"/following", msg:$(this), type: 'POST',
	          			data:{_method:'delete'},
					   	success: function(m){
					   		if (m != null && !m.id) return;
					   		this.msg.addClass('hide_im');
							this.msg.siblings('a.wat').removeClass('hide_im');
							this.msg.siblings('a.btnletter').hide();
					    } });
			});
			$("a.wat").live('click', function () {
	          	  var stream = $(this).closest('li');
	          	  $.ajax({ url: '${urlPrefix}/people/'+stream.attr('stream_id') +"/following", msg:$(this), type: 'POST',
					   	success: function(m){
					   		if (m != null && !m.id) return;
					   		this.msg.addClass('hide_im');
							this.msg.siblings('a.unwat').removeClass('hide_im');
							this.msg.siblings('a.btnletter').show();
					    } });
			});
			
			var dia_ret = $("#dia_ret");
			$("a.btnat").live('click', function () {
				var stream = $(this).closest('li');
				$("textarea[name='content']",dia_ret).val('').val('//@'+$('a.dispname',stream).text().trim());
				dia_ret.dialog("open");
			});
	        $('input.donet', dia_ret).live("click",function () {
				$('img.ajaxload', this).show();
				var dia = $('#dia_fow');
				$.ajax({ url: '${urlPrefix}/weibo', type: 'POST',
					data: {content:$("textarea[name='content']",dia).val()},
					success: function(m){
						dia_ret.dialog("close");
						$('img.ajaxload', dia_fow).hide();
					} });
	        });

			var dia_letter = $("#dia_letter");
			$("a.btnletter").live('click', function () {
				var stream = $(this).closest('li');
				$('div.wpeople',dia_letter).empty().html('发私信给：'+ $('a.dispname',stream).text().trim());
				$("textarea[name='content']",dia_letter).val('');
				$("#letter_url",dia_letter).val('${urlPrefix}/message?receiverId='+stream.attr('stream_id'));
				dia_letter.dialog("open");
			});
	        $('input.donet', dia_letter).live("click",function () {
				$('img.ajaxload', this).show();
				var dia = $('#dia_letter');
				$.ajax({ url: $("#letter_url",dia).val(), type: 'POST',
					data: {content:$("textarea[name='content']",dia).val()},
					success: function(m){
						dia.dialog("close");
						$('img.ajaxload', dia).hide();
					} });
	        });	        

		});
	});
	</script>
<script type="text/html" id="members">
    <li stream_id='{{id}}'>
        <a href="${urlPrefix}/people/{{id}}"><img src="{{avatarPath}}-48" alt="{{screenName}}" class="sldimg" /></a>
        <p><a class="lk dispname" href="${urlPrefix}/people/{{id}}">{{screenName}}</a></p>
        <p>{{area.province}&nbsp;{{area.city}}</p>
        <p>{{ntro}}&nbsp;</p>
        <span class="act">
			<a class="btna btnletter" href='javascript:void(0);'>私信</a>
			<a class="btn btnat">&#64</a>
			<a class="btn unwat {{^following}}hide_im{{/following}}">解除关注</a>
			<a class="btn wat {{#following}}hide_im{{/following}}">关注</a>
		</span>
    </li>
</script>
<ul id='mems' class="msglist"></ul>
<a class='lk mr10 mbprev hide'>上一页</a>
<a class='lk mbnext hide'>下一页</a>

<div id="dia_ret" class="ui_dialog" title="@">
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<img src="${staticUrlPrefix}/content-q/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>
<div id="dia_letter" class="ui_dialog" title="私信">
	<div class="wpeople mb10"></div>
	<input id='letter_url' type='hidden'></input>
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<img src="${staticUrlPrefix}/content-q/images/ajaxload.gif" class="ajaxload" alt="ajaxload" />
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />		
</div>
