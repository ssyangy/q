<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<script type="text/javascript">
seajs.use(["qcomcn","app/people"],function(q,p){
	var $ = q.jq;
	seajs.use('ICanHaz',function(ich){
		p.Loader(q,ich);
		var prop = $('ul.profile');
		$.ajax({ url:"${urlPrefix}/people/${param['peopleId']}",
			success:function(j){
				var pp = new p.PeopModel(j);
                var view = new p.PeopView({ model: pp, ich:ich.profile, tag:"div" });
                prop.html(view.render().el);
			}
		});
	});
});
</script>
<ul class="profile"></ul>
<script type="text/html" id="profile">
<div class="autocol clear proline">
	<a class="att" href="${urlPrefix}/people/{{id}}">
		<img src="{{avatarPath}}-48" alt="{{screenName}}" />
	</a>
	<div class="cont">
		<p><a href="${urlPrefix}/people/{{id}}" class="lk">{{screenName}}</a></p>
		<p><a href="${urlPrefix}/people/{{id}}" class="lk">{{username}}</a></p>
		<p>{{area.province}&nbsp;{{area.city}}</p>
		<p class="gray">{{ntro}}</p>
		<p class="act {{#isown}}hide_im{{/isown}}">
			<a href='javascript:void(0);' class="btnb letter {{^letter}}hide_im{{/letter}}" >私信</a>
			<a href='javascript:void(0);' class="btnb nat" >&#64</a>
			<a href='javascript:void(0);' class="btnb focus" >{{focus}}</a>
		</p>
	</div>
</div>
</script>