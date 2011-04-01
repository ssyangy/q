<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
     <jsp:include page="head.jsp" />
	 <script type="text/javascript">
function checkNewPassword(a){
       if(a.length<6||a.length>16){
       $("#newpasswordcorrect").css("display","none");
       $("#newpasswordwrong").css("display","block");
       $("#newpasswordwrong").html("密码少于6位,或大于16位。");
        return false;
       }
      var password=/^\w+$/;
      if(password.test(a)){
         $("#newpasswordwrong").css("display","none");
         $("#newpasswordcorrect").css("display","block");
         return true;
      }
     $("#newpasswordcorrect").css("display","none");
     $("#newpasswordwrong").css("display","block");
     $("#newpasswordwrong").html("包含有数字,字母,下划线以外的字符。");
      return false;
}
function recheckPassword(a){
      if($("#newPassword").val()==$("#confirmPassword").val()){
    	  if($("#password").val()!=""){
          $("#repasswordcorrect").css("display","block");
          $("#repasswordwrong").css("display","none");
          return true;
      }
      }
      $("#repasswordcorrect").css("display","none");
      $("#repasswordwrong").css("display","block");
      $("#repasswordwrong").html("两次输入的密码不同。");
      return false;
}
function check() {
  var np=checkNewPassword($("#newPassword").val());
  var rp=recheckPassword($("#confirmPassword").val());

  if(!np || !rp )
  	return ;
  else
  allDataCheck();
 }
 function allDataCheck(){
  var oldPassword=$("#oldpassword").val();
  var newPassword=$("#newPassword").val();
  var rePassword=$("#confirmPassword").val();
  $.ajax({
    url: '${urlPrefix}/setting',
    type: 'POST',
    dataType: 'json',
    data:{oldPassword: oldPassword,newPassword:newPassword,rePassword:rePassword},
    timeout: 5000,
    error: function(){
    },
   success: function(json){
       if(json== null){
            $("#savecorrect").css("display","block");
            $("#savewrong").css("display","none");
            $("#savecorrect").html("修改密码成功");
            $("#passwordwrong").css("display","none");
            $("#newpasswordwrong").css("display","none");
            $("#repasswordwrong").css("display","none");
            //document.location.href="${urlPrefix}/profile/avator"; //跳转
         }
       else {
          var errorkind=errorType(json.error);
          if(errorkind=="oldPassword"){
            $("#passwordcorrect").css("display","none");
            $("#passwordwrong").css("display","block");
            $("#passwordwrong").html(errorContext(json.error));
          }
          else if(errorkind=="newPassword"){
            $("#newpasswordcorrect").css("display","none");
            $("#newpasswordwrong").css("display","block");
            $("#newpasswordwrong").html(errorContext(json.error));
          }
          else if(errorkind=="confirmPassword"){
            $("#repasswordcorrect").css("display","none");
            $("#repasswordwrong").css("display","block");
            $("#repasswordwrong").html(errorContext(json.error));
          }
      }
    }
});
}
		</script>
	</head>
  <body>
	<div id="doc">
            <jsp:include page="top.jsp"/>
		<div id="settings">
			<div id="settings-container">
				<div class="heading">
					<h2>帐户设置</h2>

				</div>
				<div id="tabs" class="ui-tabs ui-widget">
					<jsp:include page="setting-tag.jsp">
							<jsp:param value="basic" name="tab"/>
						</jsp:include>
					<div id="tabs-1" class="tab-canvas">
						<form id="password-setting" action="">
							<table id="setting-form" class='input-form'>
								<tbody>
									<tr>
										<th><label for=''>当前密码：</label></th>
										<td class='col-field'><input type='password' id="oldpassword" name="oldpassword" class='text_field' size='20'/></td>

										<td class='col-help'>
											<div class='label-box-good' id="passwordcorrect" style='display:none;'></div>
											<div class='label-box-error' id="passwordwrong" style='display:none;'></div>
										</td>
									</tr>
									<tr>
										<th></th>
										<td colspan='2' class='bottom'><span class='field-desc'><a href="">密码忘记了？</a></span></td>

									</tr>
									<tr>
										<th><label for=''>新密码：</label></th>
										<td class='col-field'><input type='password' class='text_field' size='20'
										id="newPassword" name="newPassword" onblur="checkNewPassword(this.value)"/></td>
										<td class='col-help'>
											<div class='label-box-good' id="newpasswordcorrect" style='display:none;'></div>
											<div class='label-box-error'id="newpasswordwrong" style='display:none;'></div>
										</td>
									</tr>

									<tr>
										<th></th>
										<td colspan='2' class='bottom'><span class='field-desc'></span></td>
									</tr>
									<tr>
										<th><label for=''>密码确认：</label></th>
										<td class='col-field'><input type='password' class='text_field' size='20'
										id="confirmPassword" name="confirmPassword" onblur="recheckPassword(this.value)"/></td>
										<td class='col-help'>
                                            <div class='label-box-good'  style='display:none;' id="repasswordcorrect"></div>
											<div class='label-box-error' style='display:none;' id="repasswordwrong"></div>
										</td>
									</tr>
									<tr>
										<th></th>
										<td colspan='2' class='bottom'></td>
									</tr>
									<tr>

										<th></th>
										<td colspan='2'><button class='button btn-x' type='button' onclick="check()">保存</button></td>
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
						</form>
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
	</div>

  </body>
</html>
