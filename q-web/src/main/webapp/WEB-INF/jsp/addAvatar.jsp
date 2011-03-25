<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type='text/javascript'>
   var isImg='<%=request.getAttribute("isImg")%>' ;
   if(isImg){
	   var imgHeight='<%=request.getAttribute("imgHeight")%>';
	   var imgWidth='<%=request.getAttribute("imgWidth")%>';
	   var imgPath='<%=request.getAttribute("imgPath")%>';
	   parent.reloadImg(imgHeight,imgWidth,imgPath);
   }
   else{
	   parent.notAImg();
   }

</script>

