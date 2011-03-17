<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<jsp:include page="head.jsp" flush="true"/>
	<title>补充个人资料</title>
	<jsp:include page="js-areas.jsp" flush="true"/>
	<script type="text/javascript">
	var sex=false;
	function checkLocation(){
	    $("#locationcorrect").css("display","block");
	    $("#locationwrong").css("display","none");
	}
	function checkGender(){
	    sex=true;
	    $("#sexcorrect").css("display","block");
	    $("#sexwrong").css("display","none");
	}
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
								<form method='post' action='${urlPrefix}/people/${people.id}/full'>
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
													<th></th>

													<td colspan='2' class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''><span class="required-field">*</span>性别：</label></th>
													<td class='col-field'>
														<input type='radio' name="gender" value="1" onclick="checkGender()" />
														<span class='value-label' >男</span>&nbsp;
														<input type='radio' name="gender" value="2" onclick="checkGender()" />
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
													<th><label for=''>生日：</label></th>
													<td class='col-field' colspan='2'>
														<select name="year" class='select'>
															<option value='1979'>1979</option>
															<option value='1980'>1980</option>
															<option value='1981'>1981</option>
														</select>
														<span class='value-label'>年</span>
														<select name="month" class='select'>
															<option value='1'>1</option>
															<option value='2'>2</option>
															<option value='3'>3</option>
														</select>
														<span class='value-label'>月</span>
														<select name="day" class='select'>
															<option value='1'>1</option>
															<option value='2'>2</option>
															<option value='3'>3</option>
														</select>
														<span class='value-label'>日</span>
													</td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th><label for=''>学历：</label></th>

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

													<td class='col-field'><input name="mobile" type='text' class='text_field' size='20'/></td>
													<td class='col-help'></td>
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
												</tr>
												<tr>
													<th></th>
													<td colspan='2'  class='bottom'><span class='field-desc'></span></td>
												</tr>
												<tr>
													<th></th>
													<td colspan='2'><button class='button btn-x' type='submit'>保存! 开始玩</button></td>

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
