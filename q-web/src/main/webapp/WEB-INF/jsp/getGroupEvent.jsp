<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" flush="true"/>
<title>我的圈子-活动</title>
<script type="text/javascript">
	$(function(){
		// Tabs
		$('#tabs').tabs();
		$tabs.tabs('select', 0);
	});
</script>	
</head>
<body>
	<div id="doc">
	<jsp:include page="top.jsp" flush="true"/>
		<div id="page-outer" class="groups-feed">
			<div id="page-container"> 
				<div class="main-content" style="min-height:400px"> 
					<jsp:include page="group-head.jsp" flush="true" >
						<jsp:param name="tab" value="groupEvent" />
					</jsp:include>
					<div class="main-tweet-box group">
						<div class="tweet-box">
							<div class="submit">
								<a href="${urlPrefix}/event/new?groupId=${group.id}" class="button" target="_blank">发起活动</a>
							</div>
						</div>
					</div>					
					<div class="stream-manager"> 
						<div id="tabs"> 
							<ul class="stream-tabs"> 
								<li class="stream-tab"><a class="tab-text" href="#tabs-1">最新活动</a></li> 
								<li class="stream-tab"><a class="tab-text" href="#tabs-2">我参加的</a></li> 
								<li class="stream-tab"><a class="tab-text" href="#tabs-3">我发起的</a></li> 
							</ul> 
							<div id="tabs-1"> 
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
													<td align="center">${event.joinNumber}/${event.number}</td> 
													<td align="center"><a href="${urlPrefix}/people/${event.creatorId}">${event.creatorRealName}</a></td> 
												</tr> 
												</c:forEach>
											</tbody> 
										</table> 
									</div> 
								</div> 
							</div> 
							<div id="tabs-2"> 
							</div> 
							<div id="tabs-3"> 
							</div> 
						</div> 
					</div>
				</div> 
			</div>		
			<jsp:include page="group-dashboard.jsp" flush="true"/>
		</div>
	</div>	
</body>
</html>