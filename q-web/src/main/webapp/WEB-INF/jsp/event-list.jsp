<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content-outer"> 
	<div class="content-inner"> 
		<table width="100%" class="tbl-events" cellspacing="0"> 
			<tbody> 
				<tr> 
					<th width="18%">开始时间</th> 
					<th>主题</th> 
					<th width="11%">报名人数</th> 
					<th width="16%">发起人</th> 
				</tr>
				<c:forEach items="${events}" var="event"> 
				<tr> 
					<td>${event.startedMdhm}</td> 
					<td>
						<p>
							${event.area.myProvince.name}&nbsp;${event.area.myCity.name}&nbsp;${event.area.myCounty.name} - 
							<a href="${urlPrefix}/event/${event.id}">${event.name}</a>
						</p>
					</td> 
					<td align="center">${event.joinNum}/${event.number}</td> 
					<td align="center"><a href="${urlPrefix}/people/${event.creatorId}">${event.creatorRealName}</a></td> 
				</tr> 
				</c:forEach>
			</tbody> 
		</table> 
	</div> 
</div> 
