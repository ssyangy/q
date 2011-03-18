<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
// <![CDATA[
/**
*	@name							Defaultvalue
*	@descripton						Gives value to empty inputs
*	@version						1.4
*	@requires						Jquery 1.3.2
*
*	@author							Jan Jarfalk
*	@author-email					jan.jarfalk@unwrongest.com
*	@author-twitter					janjarfalk
*	@author-website					http://www.unwrongest.com
*
*	@licens							MIT License - http://www.opensource.org/licenses/mit-license.php
*
*	@param {String} str				The default value
*	@param {Function} callback		Callback function
*/

(function(jQuery){
     jQuery.fn.extend({
         defaultValue: function(o, callback) {
			
			if('placeholder' in this[0]){
				return false;
			}

			
			var options = o || {};
			var settings = jQuery.extend({
     			defaultValue: options.defaultValue || null
  			}, options);
			
            return this.each(function(index, element) {
				
				if($(this).data('defaultValued')){
					return false;
				}
				
				var $input				=	$(this);
				var	defaultValue		=	settings.value || $input.attr('placeholder');
				var	callbackArguments 	=	{'input':$input};
				
				// Mark as defaultvalued
				$input.data('defaultValued', true);
					
				// Create clone and switch
				var $clone = createClone();
				
				// Add clone to callback arguments
				callbackArguments.clone = $clone;
				
				$clone.insertAfter($input);
				
				var setState = function() {
					if( $input.val().length <= 0 ){
						$clone.show();
						$input.hide();
					} else {
						$clone.hide();
						$input.show().trigger('click');
					}
				};
				
				// Events for password fields
				$input.bind('blur', setState);
				
				// Create a input element clone
				function createClone(){
					
					var $el;
					
					if($input.context.nodeName.toLowerCase() == 'input') {
						$el = jQuery("<input />").attr({
							'type'	: 'text'
						});
					} else if($input.context.nodeName.toLowerCase() == 'textarea') {
						$el = jQuery("<textarea />");	
					} else {
						throw 'DefaultValue only works with input and textareas'; 
					}
					
					$el.attr({
						'value'		: defaultValue,
						'class'		: $input.attr('class')+' empty',
						'size'		: $input.attr('size'),
						'style'		: $input.attr('style'),
						'tabindex' 	: $input.attr('tabindex'),
						'name'		: 'defaultvalue-clone-' + (((1+Math.random())*0x10000)|0).toString(16).substring(1)
					});
					
					$el.focus(function(){
					
						// Hide text clone and show real password field
						$el.hide();
						$input.show();
						
						// Webkit and Moz need some extra time
						// BTW $input.show(0,function(){$input.focus();}); doesn't work.
						setTimeout(function () {
							$input.focus();
						}, 1);
					
					});				
					
					return $el;
				}

				setState();
				
				if(callback){
					callback(callbackArguments);
				}	
				
            });
        }
    });
})(jQuery);

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
