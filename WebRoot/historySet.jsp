<%@ page language="java"
	import="java.util.*,com.hhhy.db.beans.KeyWord, com.hhhy.db.DBUtils"
	pageEncoding="utf-8"%>
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
	String email = (String) session.getAttribute("name");
	//List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
	String startTime = (String) session.getAttribute("start_date");
	String endTime = (String) session.getAttribute("end_date");
	String info = (String) session.getAttribute("setinfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>历史时间设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- <link rel="stylesheet" href="./css/style-red.css">
<link rel="stylesheet" href="./css/style-red-my.css">

<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link href="./css/bootstrap.min.css" rel="stylesheet">
 -->

<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
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
		<a class="appbrand" align="center" href=""><img src="./pic/hhhy.png" width="130" alt=""
			style="position: relative;top:0;"> </a>
		<button class="menu-toggle" type="button"></button>

		<ul class="topnav pull-right inline">
<li><a href="dtAll.jsp">顶贴</a></li>
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a>
			</li>
			<li><a href="loginWeb.jsp" class="top-logout"
				data-toggle="tooltip" data-placement="bottom"><i></i> 退出</a>
			</li>
		</ul>

	</div>
	<div class="wrapper">
		<div class="hidden-phone menu" id="menu">
			<div class="profile">
				<span>欢迎您：</span> <a><%=name%></a>
			</div>

			<ul class="menu-lists">

				<li class="menu-list menu-any active"><a href="keylist"
					class="menu-title"><i></i><span>关键词设置</span> </a></li>
				<li class="menu-list menu-rep"><a href="spiderurl"
					class="menu-title"><i></i><span>自添加爬虫列表</span> </a></li>
			</ul>
		</div>
		<div id="content" class="content">
			<ul class="breadcrumb">
				<li>您在这里：</li>
				<li class="color-red">关键词设置/历史监控时间设置</li>
			</ul>
			<br> <br> <br> <br> <br> <br>

			<form action="historyadd" method="get" align="center"
				cellspacing="10">
				<div class="row-fluid">
					<div class="search-filter-box">
						<!-- div class="normal-search-box clearfix"> -->
						<div class="clearfix time-opt span10">
							<div class="control-group form-inline">
								<ul class="inline tab-small">
									<li style="padding-left:0px;">设置历史舆情监控时间： <input
										id="start_date" type="text" name="start_date"
										value="<%=startTime%>"> <!-- <a class="i-cal" href="javascript:;" id="start_date_link"></a>  -->
										<script>
											$(function() {
												$("#start_date").datepicker({ dateFormat: 'yy/mm/dd' });
											});
										</script> <input id="end_date" type="text" name="end_date"
										value="<%=endTime%>"> <!-- <a class="i-cal" href="javascript:;" id="end_date_link"> </a>  -->
										<script>
											$(function() {
												$("#end_date").datepicker({ dateFormat: 'yy/mm/dd' });
											});
										</script> 
										</li>
								</ul>
							</div>
						</div>
						<div class="export-submit">
							<input class="btn-red" type="submit" name="historyset" value="设置">
						</div>
						<!-- </div> -->
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- <script type="text/javascript">
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
		</script> -->
</body>
</html>
