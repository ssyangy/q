<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="models/head.jsp">
	<jsp:param name="title" value="搜索" />
</jsp:include>
<script type="text/javascript">
    seajs.use('qcomcn.js', function (q) {
        var $ = q.jq;
        $(function () {
            q.Init();
        });
    });
</script>
<div class="layout grid-m0s7">
<div class="col-main"><div class="main-wrap pr10">
<h2 class='mt20'>搜索</h2>
<div id='searchpage' class="search">
    <input class="search_inp mttext_val" type="text" name="name" value="搜索圈子、信息、好友" />
    <input type="submit" class="search_btn" title="搜索" value="" />
</div>
<div class="ui-tabs mt10">
    <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix">
        <li class="ui-state-default crt2"><a href="/qcomcn/SearchWeibo">发言</a></li>
        <li class="ui-state-default crt2 ui-state-active"><a href="/qcomcn/SearchGroup">圈子</a></li>
        <li class="ui-state-default crt2"><a href="/qcomcn/SearchPeople">成员</a></li>
    </ul>
</div>
<div class='tabscont'>
    <ul class='msglist'>
        <li>
            <img src="/usersimg/avatar3.jpg" alt="Alternate Text" class="sldimg" />
            <p>Java分布式<span class='act'><a class='btn watch'>关注</a></span></p>
            <p>成员：1234人&nbsp;&nbsp;创建于：2011-04-23</p>
            <p>讨论Java分布式是什么东西</p>
        </li>
        <li>
            <img src="/usersimg/avatar3.jpg" alt="Alternate Text" class="sldimg" />
            <p>Java分布式<span class='act'><a class='btn watch'>关注</a></span></p>
            <p>成员：1234人&nbsp;&nbsp;创建于：2011-04-23</p>
            <p>讨论Java分布式是什么东西</p>
        </li>
        <li>
            <img src="/usersimg/avatar3.jpg" alt="Alternate Text" class="sldimg" />
            <p>Java分布式<span class='act'><a class='btn watch'>关注</a></span></p>
            <p>成员：1234人&nbsp;&nbsp;创建于：2011-04-23</p>
            <p>讨论Java分布式是什么东西</p>
        </li>
        <li class='end'>
            <img src="/usersimg/avatar3.jpg" alt="Alternate Text" class="sldimg" />
            <p>Java分布式<span class='act'><a class='btn watch'>关注</a></span></p>
            <p>成员：1234人&nbsp;&nbsp;创建于：2011-04-23</p>
            <p>讨论Java分布式是什么东西</p>
        </li>
    </ul>
</div>
</div></div>
<div class="col-sub pt20">
    <h3>热门搜索词</h3>
</div>
</div>



        <jsp:include page="top.jsp"/>
		<div id="page-outer">
			<div id="page-container">
				<div class="main-content" style="min-height:400px">
				<div class="search-header">
					<div class="search-header-inner">
						<h2>搜索</h2>
						<div class="search-box">
							<form action="" method="GET">
							<input type="text" id="search" name="search" class="inner-search" size="20" value="${param['search']}"><button class="button">搜索</button>
							</form>
						</div>
					</div>
				</div>
					<div class="stream-manager">
				    <div class="ui-tabs ui-widget">
						<jsp:include page="search-tag.jsp">
							<jsp:param value="group" name="tab"/>
						</jsp:include>
					</div>
                    <jsp:include page="group-list.jsp"></jsp:include>
					</div>
				</div>
				
			</div>
		</div>



