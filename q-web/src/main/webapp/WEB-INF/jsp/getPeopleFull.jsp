<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="q" uri="http://www.q.com.cn/jsp/tag"%>
<!DOCTYPE html>
<html>
<head>
    <title>Q.com.cn</title>
    <link href="${staticUrlPrefix}/content/main/jquery.ui.css" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/qcomcn.css" rel="stylesheet" type="text/css" />
    <link href="${staticUrlPrefix}/content/signup.css" rel="stylesheet" type="text/css" />
    <script src="${staticUrlPrefix}/scripts/sea.js" type="text/javascript"></script>
	<script type="text/javascript">
areas=${rootArea.childsJson};
seajs.use(['qcomcn','jq_area'],function(q, area){
		var $ = q.jq;
		checkLocation = function(){
		    $("#locationcorrect").css("display","block");
		    $("#locationwrong").css("display","none");
		}
		 checkGender = function(){
		    var radios=$(":radio");
		    if(radios[0].checked == true||radios[1].checked == true){
		    $("#sexcorrect").css("display","block");
		    $("#sexwrong").css("display","none");
		    return true;
		    }
		    $("#sexcorrect").css("display","none");
		    $("#sexwrong").css("display","block");
		    $("#sexwrong").html("性别还没有选择。");
		    return false;
		}
		checkMobile = function(a){
		   if(a==""){
		     $("#mobilewrong").css("display","none");
	         $("#mobilecorrect").css("display","none");
	         return true;
		   }
		   var patrn=/^[0-9]{11}$/;
		   if(patrn.test(a)){
	         $("#mobilewrong").css("display","none");
	         $("#mobilecorrect").css("display","block");
	         return true;
	      }

	         $("#mobilewrong").css("display","block");
	         $("#mobilecorrect").css("display","none");
	         $("#mobilewrong").html("手机号码的格式有误。");

	      return false;
		}
		checkGroup = function(){
		  return true; //xxx sean
	      var boxes=$(":checkbox");
	         for (var i = 0; i < boxes.length; i++) {
	             if (boxes[i].checked == true) {
	                 $("#groupwrong").css("display","none");
	                 $("#groupcorrect").css("display","block");
	                return true;
	               }
	          }
	         $("#groupwrong").css("display","block");
	         $("#groupcorrect").css("display","none");
	         $("#groupwrong").html("请至少选择一个您感兴趣的圈子。");
	       return false;
		}
		check = function(){
	         var cm=checkMobile($("#mobile").val());
	         var cg=checkGroup();
	         var csex=checkGender();
	          if(!cm||!csex||!cg)
	  	       return false;
		     return true;
		}
	$(document).ready(function(){
	     yeartemp='${people.year + 1900}';
	     monthtemp='${people.month}';
	     daytemp='${people.day}';

	     var yearx=document.getElementById("selYear");
	     var monthx=document.getElementById("selMonth");
	     var dayx=document.getElementById("selDay");

		$.each(yearx.options, function(index, option) {
		 	 if(option.value==yeartemp){
			    option.selected='selected';
			 }
		});
        if(monthtemp != '0') monthx.options[monthtemp-1].selected='selected';
        if(daytemp != '0') dayx.options[daytemp-1].selected='selected';

	    provinceExist='${people.area.myProvince.id}';
        cityExist='${people.area.myCity.id}';
        countyExist='${people.area.myCounty.id}';

        hometownProvinceExist='${people.hometown.myProvince.id}';
        hometownCityExist='${people.hometown.myCity.id}';
        hometownCountyExist='${people.hometown.myCounty.id}';

        area.changeProvince();
        area.changeHometownProvince();

		$("select[name='province']").bind('change',area.changeCity);
		$("select[name='city']").bind('change',area.changeCounty);
		$("select[name='hometownProvince']").bind('change',area.changeHometownCity);
		$("select[name='hometownCity']").bind('change',area.changeHometownCounty);
	});
});

	</script>
  </head>
<body>
	<div class="signup">
		<h1 class="logo2"></h1>
		<span class='info'>已有Q.com.cn帐号？<a class='lk ml5'>马上登录</a></span>
		<ul class='pssyao clear'>
            <li class="done2">创建帐号</li>
            <li class="done end">填写资料</li>
        </ul>

			<form method='post' action='${urlPrefix}/people/${people.id}/full' onsubmit="return check()">
				<table class='qform'>
					<tr>
						<td align="right" class="f14">所在地：</td>
						<td class='localArea'><select class='select' name="province"
							id="selProvince">
						</select> <select class='select' name="city" id="selCity">
						</select> <select class='select' name="county" id="selCounty">
						</select></td>
						<td class='col-help'>
							<div class='label-box-good' style='display: none;'
								id="locationcorrect"></div>
							<div class='label-box-error' style='display: none;'
								id="locationwrong"></div></td>
					</tr>
					<tr>
						<td align="right" class="f14">家乡：</td>
						<td><select class='select' name="hometownProvince"
							id="selHometownProvince">
						</select> <select class='select' name="hometownCity" id="selHometownCity">
						</select> <select class='select' name="hometownCounty"
							id="selHometownCounty">
						</select></td>
						<td class='col-help'>
							<div class='label-box-good' style='display: none;'
								id="hometowncorrect"></div>
							<div class='label-box-error' style='display: none;'
								id="hometownwrong"></div></td>
					</tr>
					<tr>
						<td align="right" class="f14">性别：</td>
						<td><input type='radio' name="gender" value="1"
							<c:choose><c:when test="${people.gender.value == 1}">checked="checked"</c:when></c:choose>
							onclick="checkGender()" /> <span class='value-label'>男</span>&nbsp;
							<input type='radio' name="gender" value="2"
							<c:choose><c:when test="${people.gender.value == 2}">checked="checked"</c:when></c:choose>
							onclick="checkGender()" /> <span class='value-label'>女</span></td>
						<td class='col-help'>
							<div class='label-box-good' style='display: none;'
								id="sexcorrect"></div>
							<div class='label-box-error' style='display: none;' id="sexwrong"></div>
						</td>
					</tr>
					<tr>
						<td align="right" class="f14">生日：</td>
						<td class='col-field' colspan='2'><jsp:include
								page="models/dateSelect.jsp" /></td>
						<td class="col-help">
							<div class="label-box-good" style="display: none;"
								id="birthdaycorrect"></div>
							<div class="label-box-error" style="display: none;"
								id="birthdaywrong"></div></td>
					</tr>
					<tr>
						<td align="right" class="f14">手机：</td>
						<td><input name="mobile" id="mobile" type='text'
							class='mttext' size='20' onblur="checkMobile(this.value)"
							value="<c:if test="${people.mobile>0}">${people.mobile}</c:if>" />
						</td>
						<td class="col-help">
							<div class="label-box-good" style="display: none;"
								id="mobilecorrect"></div>
							<div class="label-box-error" style="display: none;"
								id="mobilewrong"></div></td>
					</tr>
					<tr>
						<td align="right" class="f14" style="line-height:22px;padding-top:4px;">可能感兴趣<br/>的圈子：</td>
						<td style="width:300px;padding-top:4px;">
							<c:forEach items="${groups}" var="group">
								<span class='mr10 favgroup'>
								<input name="group" type='checkbox' value="${group.id}" class="mr5"/>${group.name}
								</span>
							</c:forEach>
						</td>
						<td>
							<div class="label-box-good" style="display: none;"
								id="groupcorrect"></div>
							<div class="label-box-error" style="display: none;"
								id="groupwrong"></div></td>
					</tr>

					<tr>
						<td></td>
						<td>
							<input class='btnr' type='submit' value="保存" />
							<input type='hidden' name='from' value="${urlPrefix}" />
						</td>
					</tr>
				</table>
			</form>

	</div>
</body>
</html>
