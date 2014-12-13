<%@ page language="java" import="java.util.*, com.hhhy.db.beans.*, com.hhhy.db.beans.item.*" pageEncoding="utf-8"%>  
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Long userid = (Long)session.getAttribute("userid");
if(userid==null){
	response.sendRedirect("./loginWeb.jsp");
	return;
}
String name = (String)session.getAttribute("name");
Integer poscount = (Integer)session.getAttribute("poscount");
Integer negcount = (Integer)session.getAttribute("negcount");
Integer plaincount = (Integer)session.getAttribute("plaincount");
//plaincount++;

Integer poscountToday = (Integer)session.getAttribute("poscountToday");
Integer negcountToday = (Integer)session.getAttribute("negcountToday");
Integer plaincountToday = (Integer)session.getAttribute("plaincountToday");
if(poscount==null || negcount==null || plaincount==null || poscountToday==null || negcountToday==null || plaincountToday==null)
	response.sendRedirect("./login.jsp");
	
String date = (String)request.getAttribute("date");//json
//String posttrend = (String)request.getAttribute("postrend");//json
//String negtrend = (String)request.getAttribute("negtrend");//json
//少一个重要舆情
List<Article> importantArticle = (List<Article>)session.getAttribute("importarts");
List<Article> negArticle= (List<Article>)session.getAttribute("negarts");

//Map<String, Integer> mediaStatis = (Map<String, Integer>)request.getAttribute("mediaStatis");
//Map<String, Integer> sourceStatis =(Map<String, Integer>)request.getAttribute("sourceStatis");
// no importantArticle
//
Double emotionNum = 5* (poscount-negcount - 0.0)/(poscount + negcount);
String emotionDistribution = "[]";
String keyword = (String)session.getAttribute("keyword");
%>


<% 
	//String name = "username";
	//Integer poscount = 8;
	//Integer plaincount = 6;
	//Integer negcount = 4;

	//
	//Integer emotionNum = 2;
	// List<Article> importantArticle = new LinkedList<Article>(); 
	//Article a = new Article("title","content","http://weibo.com/zxlady9218","website");
	// importantArticle.add(a); 
	
	//List<Article> negArticle = new LinkedList<Article>();
	//negArticle.add(a);
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="./css/YiiTagCloud.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript"
	src="./js/highcharts.src.js"></script>
<script type="text/javascript"
	src="./js/highcharts-more.src.js"></script>
<script type="text/javascript"
	src="./js/exporting.src.js"></script>
<title>舆情概况</title>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- Theme -->
<link rel="stylesheet" href="./css/style-red.css">
<link rel="stylesheet" href="./css/style-red-my.css">

</head>
<body>
	<div class="navbar">
		<a class="appbrand" align="center" href=""><img src="./pic/hhhy.png" width="130" alt=""
			style="position: relative;top:0;"> </a>
		<button class="menu-toggle" type="button"></button>

		<!-- <div class="topnav pull-left ln">
				<div class="btn-group" id="J-toggle-site">
					<a class="btn dropdown-toggle btn-small"> name <span
						class="caret" style="display: none;"></span> </a>
					<ul class="dropdown-menu">
					</ul>
				</div>
			</div> -->

		<ul class="topnav pull-right inline">
			<li> <%=keyword %><li>
			<li><a href="dtAll.jsp">顶贴</a></li>
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a></li>
			<li><a href="loginWeb.jsp" class="top-logout" data-toggle="tooltip"
				data-placement="bottom"><i></i> 退出</a></li>
		</ul>

	</div>

	<div class="wrapper">
		<div class="hidden-phone menu" id="menu" style="display: block;">
			<div class="profile">
				<span>欢迎您：</span> <a><%=name %></a>
			</div>
			<!-- <div id="side-search" class="search-box">
				<form action="" method="get">
					<div class="side-search-downlist">
						<p class="current" id="search-type">全部</p>
						<ul id="search-type-list" style="display: none;">
							<li><a href="javascript:;" data-type="">全部</a></li>
							<li><a href="javascript:;" data-type="news">新闻</a></li>
							<li><a href="javascript:;" data-type="bbs">论坛</a></li>
							<li><a href="javascript:;" data-type="blog">博客</a></li>
							<li><a href="javascript:;" data-type="epub">电子报</a></li>
							<li><a href="javascript:;" data-type="weibo">微博</a></li>
						</ul>
					</div>
					<div class="side-search-input">
						<input class="side-search" type="text" name="include" id="">
						<button class="side-search-btn">
							<i></i>
						</button>
						<input id="search-type-flag" type="hidden" name="source" value="0">
						<input id="search-type-flag" type="hidden" name="adv" value="1">
					</div>
				</form>
			</div> -->
			<ul class="menu-lists">
				<li class="menu-list menu-general active"><a href="#"
					class="menu-title"><i></i><span>舆情概况</span> </a></li>
				<li id="more-sub-menu" class="menu-list menu-lat "><a href="sentitrend"
					class="menu-title"><i></i><span>维度分析</span> </a>
					<ul class="sub-menu-list" id="sub-menu-list">
						<li><a href="sentitrend">舆情走势</a></li>
						<li><a href="datasource">数据来源</a></li>
						<li><a href="mediasource">媒体来源</a></li>
						<li><a href="emotiontrend">情感走势</a></li>
					</ul></li>


				<li class="menu-list menu-any "><a href="monitor" class="menu-title"><i></i><span>舆情监控</span>
				</a></li>
				<li class="menu-list menu-rep "><a href="dataExport.jsp" class="menu-title"><i></i><span>数据报告</span>
				</a></li>
				<li class="menu-list menu-any  "><a href="history" class="menu-title"><i></i><span>历史舆情</span></a>
				</li>
				<!-- <li class="menu-list menu-com "><a href="" class="menu-title"><i></i><span>对比分析</span>
				</a></li> -->
			</ul>
		</div>
		<div id="content" class="content" style="margin-left: 230px;">
			<!--<div id="content">-->

			<ul class="breadcrumb">
				<li>您在这里：</li>
				<li class="color-red">舆情概况</li>
			</ul>
			<div class="innerLR">

				<!-- <div class="row-fluid">
						<div class="span12">
							<ul class="inline tab-small">
								<li class="first ">
									<a href="">今日</a>
								</li>
								<li>
									<a href="">昨日</a>
								</li>
								<li>
									<a href="">前日</a>
								</li>
							</ul>
						</div>
					</div> -->


				<div class="row-fluid">
					<div class="span3">
						<div class="widget">
							<dl class="trend-box clearfix">
								<dt>
									<p>
										<img src="./images/sentimentSummarize/yuqingzongliang.png">
									</p>
									<!--<p class="color-green">
                        +200%</p>-->
								</dt>
								<dd>
								<a href="specmonitor?today=0&trend=0">
									<h5>
										<!-- <a href=""> -->
										<%=poscount + plaincount + negcount%>
										<!-- </a> -->
									</h5>
									<p>舆情总量</p>
									</a>
								</dd>
							</dl>
						</div>
					</div>
					<div class="span3 mglf20">
						<div class="widget">
							<dl class="trend-box clearfix">
								<dt>
									<p>
										<img src="./images/sentimentSummarize/zhengmian.png">
									</p>
									<!--<p class="color-green">
                        +-</p>-->
								</dt>
								<dd>
								<a href="specmonitor?today=0&trend=1">
									<h5>
										<!-- <a href=""> -->
										<%=poscount%>
										<!-- </a> -->
									</h5>
									<p>正面</p>
									</a>
								</dd>
							</dl>
						</div>
					</div>
					 <div class="span3 mglf20">
							<div class="widget">
								<dl class="trend-box clearfix">
									<dt>
										<p>
											<img src="./images/sentimentSummarize/zhongli.png">
										</p>
										<!--  <p class="color-green">
                        +50%</p>-->
									</dt>
									<dd>
									<a href="specmonitor?today=0&trend=3">
										<h5>
											<!-- <a href="">3</a> -->
											<%=plaincount%>
										</h5>
										<p>中立</p>
										</a>
									</dd>
								</dl>
							</div>
						</div> 
					<div class="span3 mglf20">
						<div class="widget">
							<dl class="trend-box clearfix">
								<dt>
									<p>
										<img src="./images/sentimentSummarize/fumian.png">
									</p>
									<!--<p class="color-green">
                        +-</p>-->
								</dt>
								<dd>
								<a href="specmonitor?today=0&trend=2">
									<h5>
										<!-- <a href=""> -->
										<%=negcount%>
										<!-- </a> -->
									</h5>
									<p>负面</p>
									</a>
								</dd>
							</dl>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span3">
						<div class="widget">
							<dl class="trend-box clearfix">
								<dt>
									<p>
										<img src="./images/sentimentSummarize/yuqingzongliang.png">
									</p>
									<!--<p class="color-green">
                        +200%</p>-->
								</dt>
								<dd>
								<a href="specmonitor?today=1&trend=0">
									<h5>
										<!-- <a href=""> -->
										<%=poscountToday + plaincountToday + negcountToday%>
										<!-- </a> -->
									</h5>
									<p>今日舆情总量</p>
									</a>
								</dd>
							</dl>
						</div>
					</div>
					<div class="span3 mglf20">
						<div class="widget">
							<dl class="trend-box clearfix">
								<dt>
									<p>
										<img src="./images/sentimentSummarize/zhengmian.png">
									</p>
									<!--<p class="color-green">
                        +-</p>-->
								</dt>
								<dd>
								<a href="specmonitor?today=1&trend=1">
									<h5>
										<!-- <a href=""> -->
										<%=poscountToday%>
										<!-- </a> -->
									</h5>
									<p>今日正面</p>
									</a>
								</dd>
							</dl>
						</div>
					</div>
					 <div class="span3 mglf20">
							<div class="widget">
								<dl class="trend-box clearfix">
									<dt>
										<p>
											<img src="./images/sentimentSummarize/zhongli.png">
										</p>
										<!--  <p class="color-green">
                        +50%</p>-->
									</dt>
									
									<dd>
									<a href="specmonitor?today=1&trend=3">
										<h5>
											<!-- <a href="">3</a> -->
											<%=plaincountToday%>
										</h5>
										<p>今日中立</p>
										</a>
									</dd>
								</dl>
							</div>
						</div> 
					<div class="span3 mglf20">
						<div class="widget">
							<dl class="trend-box clearfix">
								<dt>
									<p>
										<img src="./images/sentimentSummarize/fumian.png">
									</p>
									<!--<p class="color-green">
                        +-</p>-->
								</dt>
								<dd>
								<a href="specmonitor?today=1&trend=2">
									<h5>
										<!-- <a href=""> -->
										<%=negcountToday%>
										<!-- </a> -->
									</h5>
									<p>今日负面</p>
									</a>
								</dd>
							</dl>
						</div>
					</div>
				</div>



<!--  
				<div class="row-fluid">
					<div class="span6">
						<div class="widget">
							<div class="widget-hd">
								<h4>正面舆情数</h4>
							</div>
							<div class="widget-bd h290">
								<div id="container" class="h220" data-highcharts-chart="0">
									<div class="highcharts-container" id="highcharts-poscount"
										style="position: relative; overflow: hidden; width: 460px; height: 220px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">

									</div>
								</div>
								<div id="today-index">
								正面舆情数： <span class="color-red"><%=poscount%></span>
								</div>
								<div id="yw0"></div>
							</div>
						</div>
					</div>
					
					<div class="span6">
						<div class="widget">
							<div class="widget-hd">
								<h4>中立舆情数</h4>
							</div>
							<div class="widget-bd h290">
								<div id="container" class="h220" data-highcharts-chart="0">
									<div class="highcharts-container" id="highcharts-plaincount"
										style="position: relative; overflow: hidden; width: 460px; height: 220px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">

									</div>
								</div>
								<div id="today-index">
									中立舆情数：<span class="color-red"><%=plaincount%></span>
								</div>
								<div id="yw0"></div>
							</div>
						</div>
					</div>
				</div>	
				
				-->
				<div class="row-fluid">
				<!-- 
					<div class="span6">
						<div class="widget">
							<div class="widget-hd">
								<h4>负面舆情数</h4>
							</div>
							<div class="widget-bd h290">
								<div id="container" class="h220" data-highcharts-chart="0">
									<div class="highcharts-container" id="highcharts-negcount"
										style="position: relative; overflow: hidden; width: 460px; height: 220px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">

									</div>
								</div>
								<div id="today-index">
									负面舆情数： <span class="color-red"><%=negcount%></span>
								</div>
								<div id="yw0"></div>
							</div>
						</div>
					</div>
				-->
				<div class="span6">
						<div class="widget">
							<div class="widget-hd">
								<h4>总体情感分布</h4>
							</div>
							<div class="widget-bd h290">
								<div id="container" class="h220" data-highcharts-chart="0">
									<div class="highcharts-container" id="highcharts-emotionDistributedAll"
										style="position: relative; width: 460px; height: 290px; text-align: left; line-height: normal; z-index: 0;">

									</div>
								</div>
								<div id="today-index">
									
								</div>
								<div id="yw0"></div>
							</div>
						</div>
					</div>
					<div class="span6 mglf20">
						<div class="widget">
							<div class="widget-hd">
								<h4>今日情感分布</h4>
							</div>
							<div class="widget-bd h290">
								<div id="pie" class="h290" data-highcharts-chart="1">
									<div class="highcharts-container"
										id="highcharts-emotionDistributed"
										style="position: relative; width: 460px; height: 290px; text-align: left; line-height: normal; z-index: 0;">

									</div>
								</div>
								<div id="yw1"></div>
							</div>
						</div>
					</div>
				</div>		

				<div class="row-fluid">
					<div class="span6">
						<div class="widget">
							<div class="widget-hd">
								<h4>重要舆情</h4>
							</div>
							<div class="widget-bd h290">
								<ul class="prefix-stopwatch">
								<%-- <% for(Article r:importantArticle){ %>
									<li><a target="_blank" href=<%=r.getUrl()%>><%=r.getTitle() %> </a>
									</li>
								<% } %> --%>
								
								<% for(Article r:importantArticle) {
										out.println("<li><a target=\"_blank\" href=" + r.getUrl() + ">["+ r.getWebsite()+"]"+r.getTitle() +"</a></li>");
								 	}%>
								</ul>
									<!-- <li><a target="_blank"
										href="http://www.jiaodong.net/news/system/more/2010300/0035/2010300_00003560.shtml">[新闻]社会新闻</a><span>胶东在线</span><span><a
											target="_blank"
											href="http://yq.adt100.com/moniter/dedup?dedup_id=30836303">转载(1)</a>
									</span></li> -->
							</div>
						</div>
					</div>
					<div class="span6 mglf20">
						<div class="widget">
							<div class="widget-hd">
								<h4>负面舆情预警</h4>
							</div>
							<div class="widget-bd h290">
								<ul class="prefix-stopwatch">
								<%-- <% for(Article r:negArticle){ %>
									<li><a target="_blank"
									href=<%=r.getUrl()%>><%=r.getTitle() %></a>
									</li>
								<% } %> --%>
								<% for(Article r:negArticle) {
										out.println("<li><a target=\"_blank\" href=" + r.getUrl() + ">["+r.getWebsite()+"]"+ r.getTitle() +"</a></li>");
								 	}%>
								
								</ul>
							</div>
						</div>
					</div>
				</div>







				<!-- <div class="row-fluid">
					


					<div class="span6 mglf20">
						<div class="widget">
							<div class="widget-hd">
								<h4>热词发现</h4>
							</div>
							<div class="widget-bd h290">

								<div id="yw3" class="YiiTagCloud">
									&nbsp; <a style="font-size: 12pt; color: #517F7E"
										target="_blank" class=" YiiTagCloudWord"
										href="http://yq.adt100.com/moniter/index?include=%E5%AF%8C%E4%BA%BA&adv=1">富人</a>
									&nbsp; &nbsp;
								</div>
							</div>
						</div>
					</div>

				</div> -->
			</div>


			<!-- highcharts -->
			<!-- emotionalNum -->
			
			<script>
				/* $(function() {

					$('#highcharts-emotionNum').highcharts({ */
					
				/*	
				$(function() {
					$('#highcharts-poscount').highcharts(

					{

						chart : {
							renderTo : 'container',
							type : 'gauge',
							plotBackgroundColor : null,
							plotBackgroundImage : null,
							plotBorderWidth : 0,
							plotShadow : false
						},
						exporting : {
							enabled : true
						},
						egend : {
							borderRadius : 0
						},
						tooltip : {
							borderRadius : 0
						},
						title : {
							text : ''
						},
						pane : {
							startAngle : -90,
							endAngle : 90,
							size : [ 30 ],
							center : [ '50%', '100%' ]
						},
						yAxis : {
							min : <%=poscount%>-5,
							max : <%=poscount%>+5,
							minorTickInterval : 'auto',
							minorTickWidth : 1,
							minorTickLength : 10,
							minorTickPosition : 'inside',
							minorTickColor : '#666',
							tickPixelInterval : 30,
							tickWidth : 2,
							tickPosition : 'inside',
							tickLength : 10,
							tickColor : '#666',
							labels : {
								step : 2,
								rotation : 'auto'
							},
							title : {
								text : ''
							},
							plotBands : [ {
								from : <%=poscount%>-5,
								to : <%=poscount%>-2.5,
								color : '#4c88a2' // green
							}, {
								from : <%=poscount%>-2.5,
								to : <%=poscount%>+2.5,
								color : '#DDDF0D' // yellow
							}, {
								from : <%=poscount%>+2.5,
								to : <%=poscount%>+5,
								color : '#d95455' // red
							} ]
						},
						series : [ {
							name : '情感指数',
							data : [
			<%=poscount%>
				],
							tooltip : {
								valueSuffix : ''
							}
						} ]
					});

				});
				
				$(function() {
					$('#highcharts-plaincount').highcharts(

					{

						chart : {
							renderTo : 'container',
							type : 'gauge',
							plotBackgroundColor : null,
							plotBackgroundImage : null,
							plotBorderWidth : 0,
							plotShadow : false
						},
						exporting : {
							enabled : true
						},
						egend : {
							borderRadius : 0
						},
						tooltip : {
							borderRadius : 0
						},
						title : {
							text : ''
						},
						pane : {
							startAngle : -90,
							endAngle : 90,
							size : [ 30 ],
							center : [ '50%', '100%' ]
						},
						yAxis : {
							min : <%=plaincount%>-5,
							max : <%=plaincount%>+5,
							minorTickInterval : 'auto',
							minorTickWidth : 1,
							minorTickLength : 10,
							minorTickPosition : 'inside',
							minorTickColor : '#666',
							tickPixelInterval : 30,
							tickWidth : 2,
							tickPosition : 'inside',
							tickLength : 10,
							tickColor : '#666',
							labels : {
								step : 2,
								rotation : 'auto'
							},
							title : {
								text : ''
							},
							plotBands : [ {
								from : <%=plaincount%>-5,
								to : <%=plaincount%>-2.5,
								color : '#4c88a2' // green
							}, {
								from : <%=plaincount%>-2.5,
								to : <%=plaincount%>+2.5,
								color : '#DDDF0D' // yellow
							}, {
								from : <%=plaincount%>+2.5,
								to : <%=plaincount%>+5,
								color : '#d95455' // red
							} ]
						},
						series : [ {
							name : '情感指数',
							data : [
			<%=plaincount%>
				],
							tooltip : {
								valueSuffix : ''
							}
						} ]
					});

				});
				
				$(function() {
					$('#highcharts-negcount').highcharts(

					{

						chart : {
							renderTo : 'container',
							type : 'gauge',
							plotBackgroundColor : null,
							plotBackgroundImage : null,
							plotBorderWidth : 0,
							plotShadow : false
						},
						exporting : {
							enabled : true
						},
						egend : {
							borderRadius : 0
						},
						tooltip : {
							borderRadius : 0
						},
						title : {
							text : ''
						},
						pane : {
							startAngle : -90,
							endAngle : 90,
							size : [ 30 ],
							center : [ '50%', '100%' ]
						},
						yAxis : {
							min : <%=negcount%>-5,
							max : <%=negcount%>+5,
							minorTickInterval : 'auto',
							minorTickWidth : 1,
							minorTickLength : 10,
							minorTickPosition : 'inside',
							minorTickColor : '#666',
							tickPixelInterval : 30,
							tickWidth : 2,
							tickPosition : 'inside',
							tickLength : 10,
							tickColor : '#666',
							labels : {
								step : 2,
								rotation : 'auto'
							},
							title : {
								text : ''
							},
							plotBands : [ {
								from : <%=negcount%>-5,
								to : <%=negcount%>-2.5,
								color : '#4c88a2' // green
							}, {
								from : <%=negcount%>-2.5,
								to : <%=negcount%>+2.5,
								color : '#DDDF0D' // yellow
							}, {
								from : <%=negcount%>+2.5,
								to : <%=negcount%>+5,
								color : '#d95455' // red
							} ]
						},
						series : [ {
							name : '情感指数',
							data : [
			<%=negcount%>
				],
							tooltip : {
								valueSuffix : ''
							}
						} ]
					});

				});

				*/


				$(function() {
						$('#highcharts-emotionDistributedAll')
							.highcharts(

									{
										chart : {
											renderTo : 'pie'
										},
										exporting : {
											enabled : true
										},
										legend : {
											borderRadius : 0
										},
										tooltip : {
											borderRadius : 0
										},
										colors : [ '#d95455', '#979080',
												'#4c88a2', '#83823f',
												'#f0c189', '#40131a' ],
										title : {
											text : ''
										},
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												dataLabels : {
													enabled: true,
                    								color: '#000000',
                    								connectorColor: '#000000',
                   									format: '<b>{point.name}</b>: {point.percentage:.1f} %'
												},
												showInLegend : true
											},
											series : {
												cursor : 'pointer',
												events : {
													click : function(e) {
														location.href = e.point.url;
													}
												}
											}
										},
										series : [ {
											type : 'pie',
											name : '舆情数量',
											data : [
													{
														name : '全部正面',
														y : <%=poscount%>,
														url : 'specmonitor?today=0&trend=1'
													},
													{
														name : '全部中立',
														y : <%=plaincount%>,
														url : 'specmonitor?today=0&trend=3'
													},
													{
														name : '全部负面',
														y : <%=negcount%>,
														url : 'specmonitor?today=0&trend=2'
													} ]
										} ]
									});
				});
				$(function() {
					$('#highcharts-emotionDistributed')
							.highcharts(

									{
										chart : {
											renderTo : 'pie'
										},
										exporting : {
											enabled : true
										},
										legend : {
											borderRadius : 0
										},
										tooltip : {
											borderRadius : 0
										},
										colors : [ '#d95455', '#979080',
												'#4c88a2', '#83823f',
												'#f0c189', '#40131a' ],
										title : {
											text : ''
										},
										plotOptions : {
											pie : {
												allowPointSelect : true,
												cursor : 'pointer',
												dataLabels : {
													enabled: true,
                    								color: '#000000',
                    								connectorColor: '#000000',
                   									format: '<b>{point.name}</b>: {point.percentage:.1f} %'
												},
												showInLegend : true
											},
											series : {
												cursor : 'pointer',
												events : {
													click : function(e) {
														location.href = e.point.url;
													}
												}
											}
										},
										series : [ {
											type : 'pie',
											name : '舆情数量',
											data : [
													{
														name : '正面',
														y : <%=poscountToday%>,
														url : 'specmonitor?today=1&trend=1'
													},
													{
														name : '中立',
														y : <%=plaincountToday%>,
														url : 'specmonitor?today=1&trend=3'
													},
													{
														name : '负面',
														y : <%=negcountToday%>,
														url : 'specmonitor?today=1&trend=2'
													} ]
										} ]
									});
				});
			</script>
			<script>
				Highcharts.setOptions({
					lang : {
						printChart : '打印图片',
						downloadJPEG : '保存为jpeg',
						downloadPNG : '保存为png',
						downloadSVG : '保存为svg',
						downloadPDF : '保存为pdf'
					}
				});
			</script>
			<!--</div>-->
			<!-- content -->
		</div>
	</div>

	<div id="pop" style="display: none;">
		<div id="popHead">
			<h2>&nbsp;</h2>
			<a id="popClose" title="关闭">关闭</a>
		</div>
		<div id="popContent">
			<dl>
				<dt id="popIntro"></dt>
				<dd id="popMore">
					<a href="http://yq.adt100.com/moniter/imp?merge=1"></a>
				</dd>
			</dl>
		</div>
	</div>

	<script type="text/javascript">
		window.jQuery || document.write('__tag_1472$66_<\/script>')
	</script>

	<script>
		$(function() {
			$.getJSON("/moniter/pop?merge=1", function(data) {
				if (data['num'] > 0) {
					var pop = new Pop(data['data'], data["num"]);
				}
			});
			//    var pop=new Pop(
			//    "请输入你的内容简介，这里是内容简介.请输入你的内容简介，这里是内容简介.请输入你的内容简介，这里是内容简介", "3");
		});
	</script>


	<!--
<script type="text/javascript" src="/apps/yuqing/theme/scripts/jquery-1.8.2.min.js"></script>
-->

	<script type="text/javascript" src="./js/pop.js"></script>
	<script type="text/javascript"
		src="./js/jquery.simInput.js"></script>
	<script type="text/javascript"
		src="./js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="./js/common.js"></script>
	<script type="text/javascript" src="./js/my.js"></script>

	<script type="text/javascript">
		$(function() {
			$('[data-toggle="checkbox"]').simInput();
			$('[data-toggle="radio"]').simInput();
		});
	</script>

	<!--[if lt IE 8]>

<script type="text/javascript" src="/apps/yuqing/theme/red/js/ie-fix.js" ></script>

<![endif]-->

	<!-- <script type="text/javascript">
		/*         */
		jQuery(window)
				.on(
						'load',
						function() {
							setTimeout(function() {
								var chart = new Highcharts.Chart({
									'chart' : {
										'renderTo' : 'container',
										'type' : 'gauge',
										'plotBackgroundColor' : null,
										'plotBackgroundImage' : null,
										'plotBorderWidth' : 0,
										'plotShadow' : false
									},
									'exporting' : {
										'enabled' : true
									},
									'legend' : {
										'borderRadius' : 0
									},
									'tooltip' : {
										'borderRadius' : 0
									},
									'title' : {
										'text' : ''
									},
									'pane' : {
										'startAngle' : -90,
										'endAngle' : 90,
										'size' : [ 30 ],
										'center' : [ '50%', '100%' ]
									},
									'yAxis' : {
										'min' : -5,
										'max' : 5,
										'minorTickInterval' : 'auto',
										'minorTickWidth' : 1,
										'minorTickLength' : 10,
										'minorTickPosition' : 'inside',
										'minorTickColor' : '#666',
										'tickPixelInterval' : 30,
										'tickWidth' : 2,
										'tickPosition' : 'inside',
										'tickLength' : 10,
										'tickColor' : '#666',
										'labels' : {
											'step' : 2,
											'rotation' : 'auto'
										},
										'title' : {
											'text' : ''
										},
										'plotBands' : [ {
											'from' : -5,
											'to' : -2.5,
											'color' : '#4c88a2'
										}, {
											'from' : -2.5,
											'to' : 2.5,
											'color' : '#DDDF0D'
										}, {
											'from' : 2.5,
											'to' : 5,
											'color' : '#d95455'
										} ]
									},
									'series' : [ {
										'name' : '情感指数',
										'data' : [ 2.5 ],
										'tooltip' : {
											'valueSuffix' : ''
										}
									} ]
								});
							}, 100);
							setTimeout(
									function() {
										var chart = new Highcharts.Chart(
												{
													'chart' : {
														'renderTo' : 'pie'
													},
													'exporting' : {
														'enabled' : true
													},
													'legend' : {
														'borderRadius' : 0
													},
													'tooltip' : {
														'borderRadius' : 0
													},
													'colors' : [ '#d95455',
															'#979080',
															'#4c88a2',
															'#83823f',
															'#f0c189',
															'#40131a' ],
													'title' : {
														'text' : ''
													},
													'plotOptions' : {
														'pie' : {
															'allowPointSelect' : true,
															'cursor' : 'pointer',
															'dataLabels' : {
																'enabled' : false
															},
															'showInLegend' : true
														},
														'series' : {
															'cursor' : 'pointer',
															'events' : {
																'click' : function(
																		e) {
																	location.href = e.point.url;
																}
															}
														}
													},
													'series' : [ {
														'type' : 'pie',
														'name' : '舆情数量',
														'data' : [
																{
																	'name' : '正面',
																	'y' : 3,
																	'url' : '/moniter/index?start_date=2014-07-21&end_date=2014-07-21&sentiment=positive'
																},
																{
																	'name' : '中立',
																	'y' : 3,
																	'url' : '/moniter/index?start_date=2014-07-21&end_date=2014-07-21&sentiment=neutral'
																},
																{
																	'name' : '负面',
																	'y' : 0,
																	'url' : '/moniter/index?start_date=2014-07-21&end_date=2014-07-21&sentiment=negative'
																} ]
													} ]
												});
									}, 100);
							setTimeout(
									function() {
										var chart = new Highcharts.Chart(
												{
													'chart' : {
														'renderTo' : 'topic'
													},
													'exporting' : {
														'enabled' : true
													},
													'legend' : {
														'borderRadius' : 0
													},
													'colors' : [ '#d95455',
															'#979080',
															'#4c88a2',
															'#83823f',
															'#f0c189',
															'#40131a' ],
													'title' : {
														'text' : ''
													},
													'yAxis' : {
														'title' : {
															'text' : ''
														}
													},
													'xAxis' : {
														'categories' : [
																'仕馨月子',
																'广州月子中心' ]
													},
													'plotOptions' : {
														'series' : {
															'cursor' : 'pointer',
															'events' : {
																'click' : function(
																		e) {
																	location.href = e.point.url;
																}
															}
														}
													},
													'series' : [ {
														'name' : '舆情数量',
														'type' : 'column',
														'data' : [
																{
																	'y' : 0,
																	'url' : '/moniter/index?start_date=2014-07-21&end_date=2014-07-21&topic_id=996'
																},
																{
																	'y' : 6,
																	'url' : '/moniter/index?start_date=2014-07-21&end_date=2014-07-21&topic_id=999'
																} ]
													} ]
												});
									}, 100);
						});
		/*   */
	</script> -->

</body>
</html>