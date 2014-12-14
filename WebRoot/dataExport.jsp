<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Long userid = (Long) session.getAttribute("userid");

	if (userid == null) {
		response.sendRedirect("./loginWeb.jsp");
		return;
	}
	String name = (String) session.getAttribute("name");
	String keyword = (String)session.getAttribute("keyword");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>数据导出</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->
<script type="text/javascript" src="./js/jquery.js"></script>
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- Theme -->
<link rel="stylesheet" href="./css/style-red.css">
<link rel="stylesheet" href="./css/style-red-my.css">

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
</head>

<body>
	<div class="navbar">
		<a class="appbrand" align="center" href="summarize"><img src="" width="130" alt=""
			style="position: relative;top:0;"> </a>
		<button class="menu-toggle" type="button"></button>
		<ul class="topnav pull-right inline">
			<li> <%=keyword %><li>
			<!-- <li><a href="dtAll.jsp">顶贴</a></li> -->
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a>
			</li>
			<li><a href="loginWeb.jsp" class="top-logout"
				data-toggle="tooltip" data-placement="bottom"><i></i> 退出</a>
			</li>
		</ul>
	</div>

	<div class="loading">载入中...</div>
	<div class="wrapper">
		<div class="hidden-phone menu" id="menu">
			<div class="profile">
				<span>欢迎您：</span> <a><%=name%></a>
			</div>
			<ul class="menu-lists">
				<li class="menu-list menu-general"><a href="summarize"
					class="menu-title"><i></i><span>舆情概况</span> </a>
				</li>
				<li id="more-sub-menu" class="menu-list menu-lat "><a
					href="sentitrend" class="menu-title"><i></i><span>维度分析</span> </a>
					<ul class="sub-menu-list" id="sub-menu-list">
						<li><a href="sentitrend">舆情走势</a>
						</li>
						<li><a href="datasource">数据来源</a>
						</li>
						<li><a href="mediasource">媒体来源</a>
						</li>
						<li><a href="emotiontrend">情感走势</a>
						</li>
					</ul></li>
				<li class="menu-list menu-any "><a href="monitor"
					class="menu-title"><i></i><span>舆情监控</span> </a>
				</li>
				<li class="menu-list menu-rep active"><a href="dataExport.jsp"
					class="menu-title"><i></i><span>数据报告</span> </a>
				</li>
				<li class="menu-list menu-any"><a href="history" class="menu-title"><i></i><span>历史舆情</span></a>
				</li>
				<!-- <li class="menu-list menu-com "><a href="" class="menu-title"><i></i><span>对比分析</span>
				</a></li> -->
			</ul>
		</div>

		<div id="content" class="content">
			<!--<div id="content">-->
			<ul class="breadcrumb">
				<li>您在这里：</li>
				<li class="color-red">数据报告/导出数据</li>
			</ul>
			<div class="innerLR">
				<div class="row-fluid">
					<div class="tabbable white-bg-padding">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#">导出数据</a></li>
							<li><a href="emailset">预警邮件设置</a></li>
							<li><a href="phoneset">预警短信设置</a></li>
						</ul>
						<div class="tab-content pd40">
							<form action="export" method="get">
								<input type="hidden" value="" name="period" id="period">
								<input type="hidden" value="" name="author" id="author">
								<div class="row-fluid">
									<div class="clearfix time-opt">
										<ul class="inline tab-small">
											<li style="padding-left:0px;">自定义： 
											<input id="start_date" type="text" name="start_date">
												<!-- <a class="i-cal" href="javascript:;" id="start_date_link"></a>  -->
												<script>
													$(function() {
														$("#start_date")
																.datepicker();
													});
												</script> 
												<input id="end_date" type="text"
												name="end_date"> <!-- <a class="i-cal" href="javascript:;" id="end_date_link"> </a>  -->
												<script>
													$(function() {
														$("#end_date")
																.datepicker();
													});
												</script>
											</li>
											<li>
												<div class="ez-checkbox">
													<input data-toggle="checkbox" class="ez-hide"
														type="checkbox" value="1" name="merge" id="merge">
												</div>合并相似文章</li>
										</ul>
									</div>
								</div>
								<div class="row-fluid">
									<div class="export-option">
										<h2>来源</h2>
										<span id="source"><label>
												<div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="news"
														id="source_0" checked="checked" type="checkbox"
														name="source[]">
												</div> 新闻 </label> <label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="bbs"
														id="source_1" checked="checked" type="checkbox"
														name="source[]">
												</div>论坛</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="blog"
														id="source_2" checked="checked" type="checkbox"
														name="source[]">
												</div>博客</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide"
														value="search" id="source_3" checked="checked"
														type="checkbox" name="source[]">
												</div>搜索引擎</label> </span>
									</div>
								</div>

								<div class="row-fluid">
									<div class="export-option">
										<h2>情感</h2>
										<span id="sentiment"><label><div
													class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide"
														value="positive" id="sentiment_0" checked="checked"
														type="checkbox" name="sentiment[]">
												</div>正面</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide"
														value="negative" id="sentiment_1" checked="checked"
														type="checkbox" name="sentiment[]">
												</div>负面</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide"
														value="neutral" id="sentiment_2" checked="checked"
														type="checkbox" name="sentiment[]">
												</div>中立</label> </span>
									</div>
								</div>
								<div class="row-fluid">
									<div class="export-option">
										<h2>数据字段</h2>
										<span id="field"><label><div
													class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="topic"
														id="field_0" checked="checked" type="checkbox"
														name="field[]">
												</div>关键字</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="title"
														id="field_1" checked="checked" type="checkbox"
														name="field[]">
												</div>标题</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide"
														value="summary" id="field_2" checked="checked"
														type="checkbox" name="field[]">
												</div>摘要</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="url"
														id="field_3" checked="checked" type="checkbox"
														name="field[]">
												</div>URL</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide"
														value="source" id="field_8" checked="checked"
														type="checkbox" name="field[]">
												</div>来源</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="media"
														id="field_9" checked="checked" type="checkbox"
														name="field[]">
												</div>媒体</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide"
														value="sentiment" id="field_11" checked="checked"
														type="checkbox" name="field[]">
												</div>情感</label><label><div class="ez-checkbox ez-checked">
													<input data-toggle="checkbox" class="ez-hide" value="time2"
														id="field_12" checked="checked" type="checkbox"
														name="field[]">
												</div>时间</label> </span>
									</div>
								</div>

								<div class="row-fluid">
									<div class="export-option">
										<h2>条数</h2>
										<span id="limit"><label><div
													class="ez-radio ez-selected">
													<input data-toggle="checkbox" class="ez-hide" value="100"
														id="limit_0" checked="checked" type="radio" name="limit">
												</div>前100条</label><label><div class="ez-radio">
													<input data-toggle="checkbox" class="ez-hide" value="500"
														id="limit_1" type="radio" name="limit">
												</div>前500条</label><label><div class="ez-radio">
													<input data-toggle="checkbox" class="ez-hide" value="1000"
														id="limit_2" type="radio" name="limit">
												</div>前1000条</label><label><div class="ez-radio">
													<input data-toggle="checkbox" class="ez-hide" value="2000"
														id="limit_3" type="radio" name="limit">
												</div>前2000条</label> </span>
									</div>
								</div>

								<div class="row-fluid">
									<div class="export-submit">
										<input class="btn-red" type="submit" name="yt0" value="导出">
									</div>
									<script>
										$(function() {
											$("#period").val("");
										});
									</script>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
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
		<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
		<script type="text/javascript"
			src="./js/jquery-ui-i18n.min.js"></script>
		<script type="text/javascript">
			/*         */
			jQuery(function($) {
				jQuery('#start_date').datepicker(jQuery.extend({
					showMonthAfterYear : false
				}, jQuery.datepicker.regional['zh_cn'], {
					'dateFormat' : 'yy-mm-dd'
				}));
				jQuery('#end_date').datepicker(jQuery.extend({
					showMonthAfterYear : false
				}, jQuery.datepicker.regional['zh_cn'], {
					'dateFormat' : 'yy-mm-dd'
				}));
			});
			/*   */
		</script>


	</div>
	<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div>
</body>
</html>
