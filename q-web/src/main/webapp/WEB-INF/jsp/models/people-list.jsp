<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
seajs.use("qcomcn",function(q){
	var $ = q.jq;
	seajs.use('ICanHaz',function(ich){
		var mems = $('#mems');
		var mem_ajsucc = function(json){
           	var pv = $('a.mbprev'); pv.hide(); if (json.hasPrev) pv.show();
           	var nt = $('a.mbnext'); nt.hide(); if (json.hasNext) nt.show();
            mems.empty();
            $(json.peoples).each(function(){
            	mems.append(ich.members(this));
            });
            q.fixui(mems);
		}
		$.ajax({ url:"${param['feedUrl']}",
			data:{startId:"999999999999999999",size:10, search:"${param['search']}"},
			success:mem_ajsucc });
		$('a.mbprev').click(function(){
			var o = $("li",mems).first();
			var idd = o.attr("stream_id");
			if(!idd) idd = o.attr("people_id");
			$.ajax({ url:"${param['feedUrl']}",
				data:{startId:idd,size:10, search:"${param['search']}", type:1},
				success:mem_ajsucc });
		});
		$('a.mbnext').click(function(){
			var o = $("li",mems).last();
			var idd = o.attr("stream_id");
			if(!idd) idd = o.attr("people_id");
			$.ajax({ url:"${param['feedUrl']}",
				data:{startId:idd,size:10, search:"${param['search']}"},
				success:mem_ajsucc });
		});

		$("a.unwat").live('click', function () {
          	  var stream = $(this).closest('li');
          	  $.ajax({ url: '${urlPrefix}/people/'+stream.attr('people_id') +"/following", msg:$(this), type: 'POST',
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
          	  $.ajax({ url: '${urlPrefix}/people/'+stream.attr('people_id') +"/following", msg:$(this), type: 'POST',
				   	success: function(m){
				   		if (m != null && !m.id) return;
				   		this.msg.addClass('hide_im');
						this.msg.siblings('a.unwat').removeClass('hide_im');
						this.msg.siblings('a.btnletter').show();
				    } });
		});

		var dia_ret = $("#dia_ret");
		$("a.btnat").live('click', function () {
			dia_ret.dialog("open");
			var stream = $(this).closest('li');
			$("textarea[name='content']",dia_ret).val('').val('//@'+$('span.username',stream).text()).focusaft();
		});
        $('input.donet', dia_ret).live("click",function () {
			var dia = $('#dia_ret');
			$.ajax({ url: '${urlPrefix}/weibo', type: 'POST',
				data: {content:$("textarea[name='content']",dia).val()},
				success: function(m){
					if (m && !m.id) return;
					dia_ret.dialog("close");
				} });
        });

		var dia_letter = $("#dia_letter");
		$("a.btnletter").live('click', function () {
			dia_letter.dialog("open");
			var stream = $(this).closest('li');
			$('div.wpeople',dia_letter).empty().html('发私信给：'+ $('span.username',stream).text());
			$("textarea[name='content']",dia_letter).val('').focusaft();
			$("#letter_url",dia_letter).val('${urlPrefix}/message?receiverId='+stream.attr('people_id'));
		});
        $('input.donet', dia_letter).live("click",function () {
			var dia = $('#dia_letter');
			$.ajax({ url: $("#letter_url",dia).val(), type: 'POST',
				data: {content:$("textarea[name='content']",dia).val()},
				success: function(m){
					if (m && !m.id) return;
					dia.dialog("close");
				} });
        });
	});
});
</script>
<script type="text/html" id="members">
    <li stream_id='{{# ${param['orderId']} }}{{ id }}{{/ ${param['orderId']} }}' people_id="{{id}}" class='hov'>
        <a href="${urlPrefix}/people/{{id}}"><img src="{{avatarPath}}-48" alt="{{screenName}}" class="sldimg" /></a>
        <p><a class="lk" href="${urlPrefix}/people/{{id}}">{{screenName}}</a></p>
        <p>{{area.province}&nbsp;{{area.city}}</p>
        <p>{{ntro}}&nbsp;</p>
        <span class="act">
			<span class='username hide'>{{username}}</span>
			<a class="btnb btnletter" href='javascript:void(0);'>私信</a>
			<a class="btnb btnat">&#64</a>
			<a class="btnb unwat {{^following}}hide_im{{/following}}">解除关注</a>
			<a class="btnb wat {{#following}}hide_im{{/following}}">关注</a>
		</span>
    </li>
</script>
<ul id='mems' class="msglist"></ul>
<a class='lk mr10 mbprev hide'>上一页</a>
<a class='lk mbnext hide'>下一页</a>

<div id="dia_ret" class="ui_dialog hide" title="@">
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>
<div id="dia_letter" class="ui_dialog hide" title="私信">
	<div class="wpeople mb10"></div>
	<input id='letter_url' type='hidden'></input>
	<textarea name="content" style="width:100%;height:100px;"></textarea>
	<input type='hidden' class='donet' />
	<input type='hidden' class='undonet' />
</div>
