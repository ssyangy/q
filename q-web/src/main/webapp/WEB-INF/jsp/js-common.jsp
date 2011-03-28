<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
// <![CDATA[

function favWeibo(button,weiboId){
  $.ajax({
    url: '${urlPrefix}/weibo/' + weiboId + '/favorite',
    type: 'POST',
    dataType: 'json',
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "取消收藏";
           	button.onclick = function(event){unFavWeibo(button,weiboId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
function unFavWeibo(button,weiboId){
  $.ajax({
    url: '${urlPrefix}/weibo/' + weiboId + '/favorite',
    type: 'POST',
    dataType: 'json',
    data:{_method:'delete'},
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "收藏";
           	button.onclick =  function(event){favWeibo(button,weiboId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
function favWeiboReply(button,weiboId){
  $.ajax({
    url: '${urlPrefix}/reply/' + weiboId + '/favorite',
    type: 'POST',
    dataType: 'json',
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "取消收藏";
           	button.onclick = function(event){unFavWeiboReply(button,weiboId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
function unFavWeiboReply(button,weiboId){
  $.ajax({
    url: '${urlPrefix}/reply/' + weiboId + '/favorite',
    type: 'POST',
    dataType: 'json',
    data:{_method:'delete'},
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "收藏";
           	button.onclick =  function(event){favWeiboReply(button,weiboId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
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
function joinGroup(button,groupId){
  $.ajax({
    url: '${urlPrefix}/group/' + groupId + '/join',
    type: 'POST',
    dataType: 'json',
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "退出";
           	button.onclick = function(event){unJoinGroup(button,groupId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
function unJoinGroup(button,groupId){
  $.ajax({
    url: '${urlPrefix}/group/' + groupId + '/join',
    type: 'POST',
    dataType: 'json',
    data:{_method:'delete'},    
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "加入";
           	button.onclick = function(event){joinGroup(button,groupId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
function joinEvent(button,eventId){
  $.ajax({
    url: '${urlPrefix}/event/' + eventId + '/join',
    type: 'POST',
    dataType: 'json',
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "不参加了";
           	button.onclick = function(event){unJoinEvent(button,eventId);};
        } else {
        	console.log(json);
     	}
    }
  });
}
function unJoinEvent(button,eventId){
  $.ajax({
    url: '${urlPrefix}/event/' + eventId + '/join',
    type: 'POST',
    dataType: 'json',
    data:{_method:'delete'},    
    timeout: 5000,
    error: function(){
    	console.log(json);
    },
   	success: function(json){
       if(json == null){
           	button.innerHTML = "我要参加";
           	button.onclick = function(event){joinEvent(button,eventId);};
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
