<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <jsp:include page="head.jsp" />
    <jsp:include page="js-areas.jsp" />
	<script type="text/javascript">
var areas=${rootArea.childsJson};
var selProvince;
var selCity;
var selCounty;
var cities;
var undifined;

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
     	  return true;//skip, TODO sean
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
     var crp=true;
     if(!cr)
  	   return ;
  	 var cu=checkUrl($("#url").val());
  	 if(!cu)
  	   return;
     else
       allDataCheck();
 }
function allDataCheck(){//fix me (intro)
  var realName=$("#realName").val();
  var year=$("#selYear").val();
  var month=$("#selMonth").val();
  var day=$("#selDay").val();
  var province=$("#selProvince").val();
  var city=$("#selCity").val();
  var county=$("#selCounty").val();
  var hometownProvince=$("#selHometownProvince").val();
  var hometownCity=$("#selHometownCity").val();
  var hometownCounty=$("#selHometownCounty").val();
  var url=$("#url").val();
  if($("#male").attr("checked")==true){
   var gender=$("#male").val();
  }else{
   var gender=$("#female").val();
  }
  var intro=$("#intro").val();
  $.ajax({
    url: '${urlPrefix}/profile/basic',
    type: 'POST',
    dataType: 'json',
    data:{realName:realName,year:year,month:month,day:day,province:province,city:city,county:county,url:url,gender:gender,intro:intro,hometownProvince:hometownProvince,hometownCity:hometownCity,hometownCounty:hometownCounty},
    timeout: 5000,
    error: function(){
    },
   success: function(json){
        if(json== null){
            $("#savecorrect").css("display","block");
            $("#savewrong").css("display","none");
            $("#savecorrect").html("修改数据成功");
            //document.location.href="${urlPrefix}/profile/avator"; //跳转
         }
       else {
          var errorkind=errorType(json.error);
          if(errorkind=="url"){
            $("#urlcorrect").css("display","none");
            $("#urlwrong").css("display","block");
            $("#urlwrong").html(errorContext(json.error));
          }
          else if(errorkind=="realName"){
            $("#realNamecorrect").css("display","none");
            $("#realNamewrong").css("display","block");
            $("#realNamewrong").html(errorContext(json.error));
          }
      }
    }
});
}
$(document).ready(function(){
         yeartemp='${people.year}';
	     monthtemp='${people.month}';
	     daytemp='${people.day}';

	    var yearx=document.getElementById("selYear");
	    var monthx=document.getElementById("selMonth");
	    var dayx=document.getElementById("selDay");

        yearx.options[yearx.options.length-yeartemp-1].selected='selected';

        monthx.options[monthtemp-1].selected='selected';
        dayx.options[daytemp-1].selected='selected';

         provinceExist='${people.area.myProvince.id}';
         cityExist='${people.area.myCity.id}';
         countyExist='${people.area.myCounty.id}';

         hometownProvinceExist='${people.hometown.myProvince.id}';
         hometownCityExist='${people.hometown.myCity.id}';
         hometownCountyExist='${people.hometown.myCounty.id}';

        initArea();
        initHometownArea();
		//$('#tabs').tabs();
		//$tabs.tabs('select', 0);
		});
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
							<jsp:param value="basic" name="tab"/>
						</jsp:include>
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
													<th><label for=''>家乡：</label></th>
													<td class='col-field'>
														<select class='select' name="hometownProvince" id="selHometownProvince"  onchange="changeHometownCity()">
														</select>
														<select class='select' name="hometownCity" id="selHometownCity" onchange="changeHometownCounty()">
														</select>
														<select class='select' name="hometownCounty" id="selHometownCounty">
														</select>
													</td>
													<td class='col-help'>
														<div class='label-box-good' style='display:none;' id="hometowncorrect"></div>
														<div class='label-box-error' style='display:none;' id="hometownwrong"></div>
													</td>
												</tr>
								<tr>
									<th></th>
									<td colspan='2' class='bottom'><span class='field-desc'></span></td>
								</tr>
								<tr>
									<th><label for=''>性别：</label></th>
									<td class='col-field'>
										<input type='radio' value='1' name="gender" id="male" <c:choose><c:when test="${people.gender.value == 1}">checked="checked"</c:when></c:choose> />
										<span class='value-label'>男</span>&nbsp;&nbsp;
										<input type='radio' value='2' name="gender" id="female" <c:choose><c:when test="${people.gender.value == 2}">checked="checked"</c:when></c:choose>/>
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
										 <jsp:include page="dateSelect.jsp" />
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
			                    <tr>
			                            <th></th>
			                            <td colspan='2'>
										 <div style='display:none;' id="savewrong"></div>
		                                 <div style='display:none;' id="savecorrect"></div>
		                                </td>

			                    </tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
  </body>
</html>
