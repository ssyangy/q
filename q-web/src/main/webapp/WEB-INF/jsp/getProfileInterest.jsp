<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
    <jsp:include page="head.jsp" flush="true"/>
    <script type="text/javascript">
    $(document).ready(function(){
    //	$('#tabs').tabs();
	//	$tabs.tabs('select', 2);
		});
    function checkBook(a){
       if(a.length>1000){
       $("#bookcorrect").css("display","none");
       $("#bookwrong").css("display","block");
       $("#bookwrong").html("长度不能超过1000位。");
        return false;
       }
         $("#bookwrong").css("display","none");
         $("#bookcorrect").css("display","block");
         return true;
     }
     function checkFilm(a){
       if(a.length>1000){
       $("#filmcorrect").css("display","none");
       $("#filmwrong").css("display","block");
       $("#filmwrong").html("长度不能超过1000位。");
        return false;
       }
         $("#filmwrong").css("display","none");
         $("#filmcorrect").css("display","block");
         return true;
     }
     function checkMusic(a){
       if(a.length>1000){
       $("#musiccorrect").css("display","none");
       $("#musicwrong").css("display","block");
       $("#musicwrong").html("长度不能超过1000位。");
        return false;
       }
         $("#musicwrong").css("display","none");
         $("#musiccorrect").css("display","block");
         return true;
     }
      function checkIdol(a){
       if(a.length>1000){
       $("#idolcorrect").css("display","none");
       $("#idolwrong").css("display","block");
       $("#idolwrong").html("长度不能超过1000位。");
        return false;
       }
         $("#idolwrong").css("display","none");
         $("#idolcorrect").css("display","block");
         return true;
     }
      function checkHope(a){
       if(a.length>1000){
       $("#hopecorrect").css("display","none");
       $("#hopewrong").css("display","block");
       $("#hopewrong").html("长度不能超过1000位。");
        return false;
       }
         $("#hopewrong").css("display","none");
         $("#hopecorrect").css("display","block");
         return true;
     }
       function check() {

     var cb=checkBook($("#book").val());
     var cf=checkFilm($("#film").val());
     var cm=checkMusic($("#music").val());
     var ci=checkIdol($("#idol").val());
     var ch=checkHope($("#hope").val());
     if(!cb || !cf || !cm || !ci || !ch )
  	   return ;
     else
       allDataCheck();
     }
  function allDataCheck(){
  var book=$("#book").val();
  var film=$("#film").val();
  var music=$("#music").val();
  var idol=$("#idol").val();
  var hope=$("#hope").val();
  $.ajax({
    url: '${urlPrefix}/profile/interest',
    type: 'POST',
    dataType: 'json',
    data:{book:book,film:film,music:music,idol:idol,hope:hope},
    timeout: 5000,
    error: function(){
    },
   success: function(json){
        if(json.id!= null){
            document.location.href="${urlPrefix}/profile/avator"; //跳转
         }
       else {
          var errorkind=errorType(json.error);
          if(errorkind=="book"){
            $("#bookcorrect").css("display","none");
            $("#bookwrong").css("display","block");
            $("#bookwrong").html(errorContext(json.error));
          }
          else if(errorkind=="film"){
            $("#filmcorrect").css("display","none");
            $("#filmwrong").css("display","block");
            $("#filmwrong").html(errorContext(json.error));
          }
            else if(errorkind=="music"){
            $("#musiccorrect").css("display","none");
            $("#musicwrong").css("display","block");
            $("#musicwrong").html(errorContext(json.error));
          }
            else if(errorkind=="idol"){
            $("#idolcorrect").css("display","none");
            $("#idolwrong").css("display","block");
            $("#idolwrong").html(errorContext(json.error));
          }
            else if(errorkind=="hope"){
            $("#hopecorrect").css("display","none");
            $("#hopewrong").css("display","block");
            $("#hopewrong").html(errorContext(json.error));
          }
      }
    }
});
}
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
							<jsp:param value="interest" name="tab"/>
						</jsp:include>
					<div id="tabs-1" class="tab-canvas">
					</div>
					<div id="tabs-2" class="tab-canvas">
					</div>
					<div id="tabs-3" class="tab-canvas">

							<table id="setting-form" class='input-form'>
							<tbody>
								<tr>
									<th><label for=''>喜欢的书：</label></th>
									<td class='col-field'><textarea id="book" cols="50" rows="4" onblur="checkBook(this.value)">${people.book}</textarea></td>
									<td class='col-help'>

										<div class='label-box-good' id="bookcorrect" style='display:none;'></div>
										<div class='label-box-error'id="bookwrong" style='display:none;'></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>喜欢的电影：</label></th>
									<td class='col-field'><textarea cols="50" id="film" rows="4" onblur="checkFilm(this.value)">${people.film}</textarea></td>
									<td class='col-help'>

										<div class='label-box-good' id="filmcorrect" style='display:none;'></div>
										<div class='label-box-error' id="filmwrong" style='display:none;'></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>喜欢的音乐：</label></th>
									<td class='col-field'><textarea cols="50"  id="music" rows="4" onblur="checkMusic(this.value)">${people.music}</textarea></td>
									<td class='col-help'>

										<div class='label-box-good' id="musiccorrect" style='display:none;'></div>
										<div class='label-box-error' id="musicwrong" style='display:none;'></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>影响我的人：</label></th>
									<td class='col-field'><textarea cols="50" id="idol" rows="4" onblur="checkPeople(this.value)">${people.idol}</textarea></td>
									<td class='col-help'>

										<div class='label-box-good' id="idolcorrect" style='display:none;'></div>
										<div class='label-box-error' id="idolwrong" style='display:none;'></div>
									</td>
								</tr>
								<tr>
									<th><label for=''>最近的愿望：</label></th>
									<td class='col-field'><textarea cols="50" id="hope" rows="4" onblur="checkHope(this.value)">${people.hope}</textarea></td>
									<td class='col-help'>

										<div class='label-box-good' id="hopecorrect" style='display:none;'></div>
										<div class='label-box-error' id="hopewrong" style='display:none;'></div>
									</td>
								</tr>
								<tr>
									<th></th>
									<td colspan='2' class='bottom'></td>
								</tr>
								<tr>

									<th></th>
									<td colspan='2'><button class='button btn-x' type="button" onclick="check()" >保存修改</button></td>
								</tr>
							</tbody>
						</table>


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