<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="models/head-unsign.jsp">
	<jsp:param name="title" value="补充个人资料" />
</jsp:include>
	<script type="text/javascript">
	areas=${rootArea.childsJson};
	seajs.use('qcomcn.js', function (q) {
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

seajs.use('jq.area.js',function(area){
	$(document).ready(function(){
	     provinceExist='${people.area.myProvince.id}';
        cityExist='${people.area.myCity.id}';
        countyExist='${people.area.myCounty.id}';

        hometownProvinceExist='${people.hometown.myProvince.id}';
        hometownCityExist='${people.hometown.myCity.id}';
        hometownCountyExist='${people.hometown.myCounty.id}';
        
        area.initArea();
        area.initHometownArea();
		
		$("select[name='province']").bind('change',area.changeCity);
		$("select[name='city']").bind('change',area.changeCounty);
		$("select[name='hometownProvince']").bind('change',area.changeHometownCity);
		$("select[name='hometownCity']").bind('change',area.changeHometownCounty);
	});
});

	});
	</script>
  </head>
  <body>
  <div class="wapper">
    <h1 id="logo">Q.com.cn</h1>
    <div class="content">

        <ul class='pssmap'>
            <li class="done">
                <div class="label">
                    <span class="checked"></span>
                    <span class="text">创建帐号</span>
                </div>
            </li>
            <li class="selected">
                <div class="label">
                    <span class="checked"></span>
                    <span class="text">填写资料</span>
                </div>
            </li>
        </ul>
        
        <form method='post' action='${urlPrefix}/people/${people.id}/full'  onsubmit="return check()">
        <table class='qform'>
			<tr>
				<td align="right">所在地：</td>
				<td class='localArea'>
					<select class='select' name="province" id="selProvince">
					</select>
					<select class='select' name="city" id="selCity">
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
				<td align="right">家乡：</td>
				<td>
					<select class='select' name="hometownProvince" id="selHometownProvince">
					</select>
					<select class='select' name="hometownCity" id="selHometownCity">
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
				<td align="right">性别：</td>
				<td>
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
				<td align="right">生日：</td>
				<td class='col-field' colspan='2'>
		             <jsp:include page="models/dateSelect.jsp" />
				</td>
			</tr>
			<tr>
				<td align="right">手机：</td>
				<td><input name="mobile" id="mobile" type='text' class='mttext' size='20' onblur="checkMobile(this.value)"/></td>
				<td class="col-help">
                      <div class="label-box-good" style="display: none;"
                           	id="mobilecorrect"></div>
                      <div class="label-box-error"style="display:none;"
                           	id="mobilewrong"></div>
                </td>
			</tr>
			<tr>
				<td align="right>感兴趣的圈子：</td>
				<td align="right">
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
				<td></td>
				<td><button class='ui_btn' type='submit'>完成</button>
				<input type='hidden' name='form' value="/group">
				</td>
			</tr>
		</table>
		</form>
    </div>
</div>
</body>
</html>
