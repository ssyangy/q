<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <jsp:include page="head.jsp" flush="true"/>
	<script type="text/javascript">
var areas=${rootArea.childsJson};
var selProvince;
var selCity;
var selCounty;
var cities;
var undifined;
function changeCounty(){
	 selCounty.options.length=0;
	 selCounty.style.display = "none";

	 if(cities != undifined) {
	   var countyId='${people.area.myCounty.id}';
		 var cityId = parseInt(selCity.value, 10);
		 $.each(cities, function(index, city) {
		 	if (city.id == cityId) {
		 		if(city.childs != undefined) {
		 			selCounty.style.display = "block";
			 		$.each(city.childs, function(index, county) {
			 			selCounty.options.add(new Option(county.name, county.id));
			 			if(area.id==countyId){
	    	            selCounty.options[selCounty.options.length-1].selected='selected';
	 	            }
			 		});
		 		}
		 	}
		 });
	 }
}
function changeCity(){
	selCity.options.length=0;
	selCity.style.display = "none";
	 var cityId='${people.area.myCity.id}';
	 var areaId = parseInt(selProvince.value, 10);
	 $.each(areas, function(index, area) {
	 	if (area.id == areaId) {
	 		if(area.childs != undifined) {
	 			selCity.style.display = "block";
	 			cities = area.childs;
		 		$.each(area.childs, function(index, area) {
		 			selCity.options.add(new Option(area.name, area.id));
		 			if(area.id==cityId){
	    	        selCity.options[selCity.options.length-1].selected='selected';
	 	}
		 		});
	 		}
	 	}
	 });
	 changeCounty();
}

	   function checkrealName(a){
       if(a.length<1||a.length>12){
       $("#realNamecorrect").css("display","none");
       $("#realNamewrong").css("display","block");
       $("#realNamewrong").html("昵称长度要在1-12位之间。");
        return false;
        }
       $("#realNamecorrect").css("display","block");
       $("#realNamewrong").css("display","none");
      return true;
     }
     function checkUrl(a){
          var url=/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]':+!]*([^<>\"\"])*$/;
          if (!url.test(a)){
              $("#urlcorrect").css("display","none");
              $("#urlwrong").css("display","block");
              $("#urlwrong").html("输入的URL无效。");
           return false;
           }
           else{
              $("#urlcorrect").css("display","block");
              $("#urlwrong").css("display","none");
           return true;
          }

     }
  function check() {
     var cr=checkrealName($("#realName").val());
     var cu=checkUrl($("#url").val());
     var crp=true;
     if(!cr || !cu )
  	   return ;
     else
       allDataCheck();
 }
function allDataCheck(){//fix me (intro)
  var realName=$("#realName").val();
  var year=$("#year").val();
  var month=$("#month").val();
  var day=$("#day").val();
  var provinceId=$("#selProvince").val();
  var cityId=$("#selCity").val();
  var countyId=$("#selCounty").val();
  var url=$("#url").val();
  var gender=$("#gender").val();
  var intro=$("#intro").innerText;
  $.ajax({
    url: '${urlPrefix}/profile/basic',
    type: 'POST',
    dataType: 'json',
    data:{realName: realName,year:year,month:month,day:day,provinceId:provinceId,cityId:cityId,countyId:countyId，url：url,gender:gender,intro:intro},
    timeout: 5000,
    error: function(){
    },
   success: function(json){
        if(json.id!= null){
            document.location.href="${urlPrefix}/profile/avator"; //跳转
         }
       else {
          var errorkind=errorType(json.error);
          if(errorkind=="email"){
            $("#emailcorrect").css("display","none");
            $("#emailwrong").css("display","block");
            $("#emailwrong").html(errorContext(json.error));
          }
          else if(errorkind=="password"){
            $("#passwordcorrect").css("display","none");
            $("#passwordwrong").css("display","block");
            $("#passwordwrong").html(errorContext(json.error));
          }
          else if(errorkind=="confirmPassword"){
            $("#repasswordcorrect").css("display","none");
            $("#repasswordwrong").css("display","block");
            $("#repasswordwrong").html(errorContext(json.error));
          }
          else if(errorkind=="authcode"){
            $("#authcodecorrect").css("display","none");
            $("#authcodewrong").css("display","block");
            $("#authcodewrong").html(errorContext(json.error));
          }
          else if(errorkind=="username"){
            $("#usernamecorrect").css("display","none");
            $("#usernamewrong").css("display","block");
            $("#usernamewrong").html(errorContext(json.error));
          }
          else if(errorkind=="real_name"){
            $("#realNamecorrect").css("display","none");
            $("#realNamewrong").css("display","block");
            $("#realNamewrong").html(errorContext(json.error));
          }
      }
    }
});
}
	$(function(){
	    var yeartemp='${people.year}';
	    var monthtemp='${people.month}';
	    var daytemp='${people.day}';
        year.options[year.options.length-yeartemp-1].selected='selected';
        month.options[monthtemp-1].selected='selected';
        day.options[daytemp-1].selected='selected';
	    var provinceId='${people.area.myProvince.id}';
	    selProvince = $("#selProvince")[0];
	    selCity = $("#selCity")[0];
	    selCounty = $("#selCounty")[0];
	    $.each(areas, function(index, area) {
	 	selProvince.options.add(new Option(area.name, area.id));
	 	 if(area.id==provinceId){
	    	selProvince.options[selProvince.options.length-1].selected='selected';
	 	}
	   });
	     changeCity();
			$('#tabs').tabs();
			$tabs.tabs('select', 0);
		});
	</script>
  </head>
  <body onload="load_saved()">
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
						<table id="setting-form" class='input-form'>
							<tbody>
								<tr>
									<th><label for=''>昵称：</label></th>
									<td class='col-field'>
									<input type='text' value="${people.realName}" class='text_field' size='20' id="realName" onblur="checkrealName(this.value)">

									</input>
									</td>
									<td class="col-help">
					     	          <div class="label-box-good" style="display: none;"
						              id="realNamecorrect"></div>
						              <div class="label-box-error" style="display: none;"
							          id="realNamewrong"></div>
					            	</td>
								</tr>
								<tr>
									<th></th>
									<td colspan='2' class='bottom'><span class='field-desc'></span></td>
								</tr>
								<tr>
													<th><label for=''>所在地：</label></th>
													<td class='col-field'>
														<select class='select' name="province" id="selProvince"  onchange="changeCity()">
														</select>
														<select class='select' name="city" id="selCity" onchange="changeCounty()">
														</select>
														<select class='select' name="county" id="selCounty">
														</select>
													</td>
													<td class='col-help'>
														<div class='label-box-good' style='display:none;' id="locationcorrect"></div>
														<div class='label-box-error' style='display:none;' id="locationwrong"></div>
													</td>
												</tr>
								<tr>
									<th></th>
									<td colspan='2' class='bottom'><span class='field-desc'></span></td>
								</tr>
								<tr>
									<th><label for=''>性别：</label></th>
									<td class='col-field'>
										<input type='radio' value='1' name="gender" id="gender"<c:choose><c:when test="${people.gender.value == 1}">checked="checked"</c:when></c:choose> />
										<span class='value-label'>男</span>&nbsp;&nbsp;
										<input type='radio' value='2' name="gender" id="gender"<c:choose><c:when test="${people.gender.value == 2}">checked="checked"</c:when></c:choose>/>
										<span class='value-label'>女</span>
									</td>
									<td class='col-help'>
										<div class='label-box-good'></div>
									</td>
								</tr>
								<tr>
									<th></th>
									<td colspan='2' class='bottom'><span class='field-desc'></span></td>
								</tr>
								<tr>
									<th><label for=''>生日：</label></th>
									<td class='col-field' colspan='2'>
										 <jsp:include page="dateSelect.jsp" flush="true"/>
									</td>
								</tr>
								<tr>
									<th></th>
									<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
								</tr>
								<tr>
									<th><label for=''>博客或个人网址：</label></th>
									<td class='col-field'><input type='text' input name="url" value="${people.url}"
									class='text_field' size='40' id="url" onblur="checkUrl(this.value)"></td>
									<td class="col-help">
					     	          <div class="label-box-good" style="display: none;"
						              id="urlcorrect"></div>
						              <div class="label-box-error" style="display: none;"
							          id="urlwrong"></div>
					            	</td>
								</tr>
								<tr>
									<th></th>
									<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
								</tr>
								<tr>
									<th><label for=''>关于我：</label></th>
									<td class='col-field'><textarea cols="50" rows="6" id="intro">${people.intro}</textarea></td>
									<td class='col-help'></td>
								</tr>
								<tr>
									<th></th>
									<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
								</tr>
								<tr>
										<th></th>
										<td colspan='2'><button class='button btn-x' type="button" onclick="check()" >保存修改</button></td>

			                    </tr>
							</tbody>
						</table>
					</div>
					<div id="tabs-2" class="tab-canvas">
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
