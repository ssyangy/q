<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="修改密码" />
</jsp:include>
<script type="text/javascript">
mods.push(function (q) {
	$ = q.jq;
});

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
       if(json == null || json.id != null){
            $("#savecorrect").css("display","block");
            $("#savewrong").css("display","none");
            $("#savecorrect").html("修改密码成功");
            $("#passwordwrong").css("display","none");
            $("#newpasswordwrong").css("display","none");
            $("#repasswordwrong").css("display","none");
       }else {
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
<h2 class="mb20">修改密码</h2>
<div class="ui-tabs mt10">
    <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
        <li class="ui-state-default crt2"><a href="${urlPrefix}/profile/avatar">头像</a></li>
        <li class="ui-state-default crt2"><a href="${urlPrefix}/profile/basic">基本信息</a></li>
        <li class="ui-state-default crt2 ui-state-active"><a href="${urlPrefix}/setting/basic">修改密码</a></li>
    </ul>
</div>
<table class='qform'>
	<tr>
			<th align="right">当前密码：</th>
			<td class='col-field'><input type='password' id="oldpassword" name="oldpassword" class='mttext' size='20'/></td>
			<td class='col-help'>
				<div class='label-box-good' id="passwordcorrect" style='display:none;'></div>
				<div class='label-box-error' id="passwordwrong" style='display:none;'></div>
			</td>
	</tr>
	<tr>
			<th></th>
			<td colspan='2' class='bottom'><span class='field-desc'><a class="lk" href="/password/fprget">密码忘记了？</a></span></td>
	</tr>
	<tr>
			<th align="right">新密码：</th>
			<td class='col-field'><input type='password' class='mttext' size='20'
			id="newPassword" name="newPassword" onblur="checkNewPassword(this.value)"/></td>
			<td class='col-help'>
				<div class='label-box-good' id="newpasswordcorrect" style='display:none;'></div>
				<div class='label-box-error'id="newpasswordwrong" style='display:none;'></div>
			</td>
	</tr>
	<tr>
			<th align="right">密码确认：</th>
			<td class='col-field'><input type='password' class='mttext' size='20'
			id="confirmPassword" name="confirmPassword" onblur="recheckPassword(this.value)"/></td>
			<td class='col-help'>
                   <div class='label-box-good'  style='display:none;' id="repasswordcorrect"></div>
				<div class='label-box-error' style='display:none;' id="repasswordwrong"></div>
			</td>
	</tr>
	<tr>
		<th></th>
		<td colspan='2'><button class='btn' type='button' onclick="check()">保存修改</button></td>
	</tr>
	<tr>
		<th></th>
		<td colspan='2'>
			<div style='display:none;' id="savewrong"></div>
			<div style='display:none;' id="savecorrect"></div>
		</td>
	</tr>
</table>
<jsp:include page="models/foot.jsp" />


