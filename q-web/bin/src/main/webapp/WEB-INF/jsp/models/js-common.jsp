<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
// <![CDATA[
function follow(button,peopleId){
  $.ajax({
    url: '${urlPrefix}/people/' + peopleId + '/following',
    type: 'POST',
    dataType: 'json',
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "取消关注";
           	button.onclick = function(event){unFollow(button,peopleId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
function unFollow(button,peopleId){
  $.ajax({
    url: '${urlPrefix}/people/' + peopleId + '/following',
    type: 'POST',
    dataType: 'json',
    data:{_method:'delete'},
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "关注";
           	button.onclick = function(event){follow(button,peopleId);};
        } else {
        	console.log(json);
     	}
    }
  });
}

function errorType(error){
  var exist=error.indexOf(':');
  if(exist>-1){
    var errorkind=error.substring(0, exist);
    return errorkind;
  } else{
	return null;
  }
}
function errorContext(error){
 var exist=error.indexOf(':');
  if(exist>-1){
    var errorcontext=error.substring(exist+1, error.length);
    return errorcontext;
  } else{
	return null;
  }
}
// ]]>
</script>
