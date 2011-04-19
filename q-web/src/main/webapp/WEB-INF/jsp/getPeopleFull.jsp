<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<jsp:include page="head.jsp" />
	<title>补充个人资料</title>
	<jsp:include page="js-areas.jsp" />
	<script type="text/javascript">
	function checkLocation(){
	    $("#locationcorrect").css("display","block");
	    $("#locationwrong").css("display","none");
	}
	function checkGender(){
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
	function checkMobile(a){
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
	function checkGroup(){
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
	function check(){
         var cm=checkMobile($("#mobile").val());
         var cg=checkGroup();
         var csex=checkGender();
          if(!cm||!csex||!cg)
  	       return false;
	     return true;
	}
	$(document).ready(function(){
	     provinceExist='${people.area.myProvince.id}';
         cityExist='${people.area.myCity.id}';
         countyExist='${people.area.myCounty.id}';

         hometownProvinceExist='${people.hometown.myProvince.id}';
         hometownCityExist='${people.hometown.myCity.id}';
         hometownCountyExist='${people.hometown.myCounty.id}';
         
          initArea();
          initHometownArea();
		});
	</script>
  </head>
  <body>
	<div id='container'>
		<div id='header'>
			<span class='logo-zh'>圈子</span><span class='logo-en'>Q.com.cn</span>
		</div>
		<table cellspacing='0' class='columns'>
			<tbody>
				<tr>
					<td id='content' class='round-left column wide'>
						<div class='wrapper'>
							<div class='content-heading'>
								<div class='heading'>
									<h2>帐号创建成功！再花几秒钟丰富以下资料就可以开始玩了！</h2>
								</div>
							</div>
							<div id='signup-form'>
								<form method='post' action='${urlPrefix}/people/${people.id}/full'  onsubmit="return check()">
									<fieldset>
										<table class='input-form'>
											<tbody>
												<tr>
													<th><label for=''><span class="required-field">*</span>所在地：</label></th>
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
													<th><label for=''><span class="required-field">*</span>家乡：</label></th>
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
													<th><label for=''><span class="required-field">*</span>性别：</label></th>
													<td class='col-field'>
														<input type='radio'  name="gender" value="1" onclick="checkGender()" />
														<span class='value-label' >男</span>&nbsp;
														<input type='radio'  name="gender" value="2" onclick="checkGender()" />
														<span class='value-label'>女</span>
													</td>
													<td class='col-help'>
														<div class='label-box-good' style='display:none;' id="sexcorrect"></div>
														<div class='label-box-error' style='display:none;' id="sexwrong"></div>
													</td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2' class='bottom'><span class='field-desc'></span></td>

												</tr>
												<tr>
													<th><label for=''><span class="required-field">*</span>生日：</label></th>
													<td class='col-field' colspan='2'>
											             <jsp:include page="dateSelect.jsp" />
													</td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''><span class="required-field">*</span>学历：</label></th>

													<td class='col-field'>
														<select name="degree" class='select'>
															<c:forEach items="${degrees}" var="degree">
																<option value="${degree.value}">${degree.name}</option>
															</c:forEach>
														</select>
													</td>

													<td class='col-help'></td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''>手机：</label></th>

													<td class='col-field'><input name="mobile" id="mobile" type='text' class='text_field' size='20'
													onblur="checkMobile(this.value)"/></td>
													<td class="col-help">
						                                  <div class="label-box-good" style="display: none;"
						                                       	id="mobilecorrect"></div>
						                                  <div class="label-box-error"style="display:none;"
						                                       	id="mobilewrong"></div>
						                            </td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''><span class="required-field">*</span>感兴趣的圈子：</label></th>

													<td class='col-field' colspan='2'>
														<c:forEach items="${groups}" var="group">
															<input name="group" type='checkbox' value="${group.id}"/><span class='group-name'>${group.name}</span>
														</c:forEach>
													</td>
													<td class="col-help">
						                                  <div class="label-box-good" style="display: none;"
						                                       	id="groupcorrect"></div>
						                                  <div class="label-box-error"style="display:none;"
						                                       	id="groupwrong"></div>
						                            </td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'><button class='button btn-x' type='submit' >保存! 开始玩</button></td>

												</tr>
											</tbody>
										</table>
									</fieldset>
								</form>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
  </body>
</html>
