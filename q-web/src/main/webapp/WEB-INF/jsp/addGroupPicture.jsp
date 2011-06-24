<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type='text/javascript'>
   var isImg='<%=request.getAttribute("isImg")%>' ;
   var value='<%=request.getAttribute("value")%>';

   if(value!="上传成功"){
	   parent.showWrong(value);
   }
   else{
   if(isImg){
	   var imgHeight='<%=request.getAttribute("imgHeight")%>';
	   var imgWidth='<%=request.getAttribute("imgWidth")%>';
	   var imgPath='<%=request.getAttribute("imgPath")%>';
	   var id='<%=request.getAttribute("id")%>';
	   parent.reloadImg(imgHeight,imgWidth,imgPath,id);
   }
   else{

	   parent.notAImg();
   }
   }
</script>

