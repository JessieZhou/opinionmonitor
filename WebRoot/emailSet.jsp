<%@ page language="java"
	import="java.util.*, com.hhhy.db.DBUtils, com.hhhy.db.beans.User"
	pageEncoding="UTF-8"%>
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
	User user = DBUtils.getUserById(userid);
	String reportrmail = "";
	int report = 0;
	if (user != null) {
		reportrmail = user.getReportemail();
		if (reportrmail == null)
			reportrmail = "";
		report = user.getNeedemail();
	}
	String keyword = (String) session.getAttribute("keyword");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- <base href="<%=basePath%>"> -->

<title>邮件设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
<script type="text/javascript" src="./js/jquery.js"></script>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- Theme -->
<link rel="stylesheet" href="./css/style-red.css">

<link rel="stylesheet" href="./css/style-red-my.css">
</head>

<body>

	<div class="navbar">
		<a class="appbrand" align="center" href="summarize"><img
			src="./pic/hhhy.png" width="130" alt=""
			style="position: relative;top:0;"> </a>
		<button class="menu-toggle" type="button"></button>

		<!-- <div class="topnav pull-left ln">
        <div class="btn-group" id="J-toggle-site">
                          <a class="btn dropdown-toggle btn-small" href="">
                 name
                  <span class="caret" style="display: none;"></span>
              </a>            <ul class="dropdown-menu">
                        </ul>
        </div>
    </div> -->

		<ul class="topnav pull-right inline">
			<li><%=keyword%>
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
			<!-- <div id="side-search" class="search-box">
    <form action="" method="get">
						<div class="side-search-downlist">
							<p class="current" id="search-type">
								全部
							</p>
							<ul id="search-type-list" style="display: none;">
								<li>
									<a href="javascript:;" data-type="">全部</a>
								</li>
								<li>
									<a href="javascript:;" data-type="news">新闻</a>
								</li>
								<li>
									<a href="javascript:;" data-type="bbs">论坛</a>
								</li>
								<li>
									<a href="javascript:;" data-type="blog">博客</a>
								</li>
								<li>
									<a href="javascript:;" data-type="epub">电子报</a>
								</li>
								<li>
									<a href="javascript:;" data-type="weibo">微博</a>
								</li>
							</ul>
						</div>
						<div class="side-search-input">
							<input class="side-search" type="text" name="include" id="">
							<button class="side-search-btn">
								<i></i>
							</button>
							<input id="search-type-flag" type="hidden" name="source"
								value="0">
							<input id="search-type-flag" type="hidden" name="adv" value="1">
						</div>
					</form>
		</div> -->
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
					</ul>
				</li>


				<li class="menu-list menu-any "><a href="monitor"
					class="menu-title"><i></i><span>舆情监控</span> </a>
				</li>
				<li class="menu-list menu-rep active"><a href="dataExport.jsp"
					class="menu-title"><i></i><span>数据报告</span> </a>
				</li>
				<li class="menu-list menu-any "><a href="history"
					class="menu-title"><i></i><span>历史舆情</span>
				</a></li>
				<!-- <li class="menu-list menu-com "><a href="" class="menu-title"><i></i><span>对比分析</span>
				</a></li> -->
			</ul>
		</div>
		<div id="content" class="content">
			<!--<div id="content">-->
			<ul class="breadcrumb">
				<li>您在这里：</li>
				<li class="color-red">数据报告/预警邮件设置</li>
			</ul>
			<div class="innerLR">
				<form id="send-form" action="emailset" method="post">
					<div class="row-fluid">
						<div class="tabbable white-bg-padding">
							<ul class="nav nav-tabs">
								<li><a href="./dataExport.jsp">导出数据</a>
								</li>
								<li class="active"><a href="">预警邮件设置</a>
								</li>
								<li><a href="./shortMessageSet.jsp">预警短信设置</a>
								</li>
							</ul>
							<div class="tab-content pd40">
								<div class="control-group form-horizontal">

									<label class="control-label" for="email" style="width:80px">发送邮箱：</label>
									<div class="controls" style="margin-left:80px">
										<input value="<%=reportrmail%>" name="SendForm[warn_mail]"
											id="SendForm_warn_mail" type="text" />
										<div class="errorMessage" id="SendForm_email_em_"
											style="display:none"></div>
									</div>
								</div>
								<div class="control-group form-horizontal">
									<label class="control-label" for="hour" style="width:80px">是否发送：</label>
									<div class="controls" style="margin-left:80px">
										<!-- <input id="ytSendForm_enable_warn" type="hidden" value="" name="SendForm[enable_warn]" /> -->
										<span id="SendForm_enable_warn"><label><input
												data-toggle="checkbox" class="ez-hide"
												id="SendForm_enable_warn_0" value="0" <%if (report == 0) {%>
												checked="checked" <%}%> type="radio"
												name="SendForm[enable_warn]" />否</label><label><input
												data-toggle="checkbox" class="ez-hide"
												id="SendForm_enable_warn_1" value="1" <%if (report == 1) {%>
												checked="checked" <%}%> type="radio"
												name="SendForm[enable_warn]" />是</label>
										</span>
									</div>
								</div>

								<div class="control-group form-horizontal">
									<label class="control-label" for="" style="width:80px">&nbsp;</label>
									<div class="controls" style="margin-left:80px">
										<input id="sendForm" class="btn-red" type="submit" name="yt0"
											value="设置" />
									</div>
								</div>

							</div>
						</div>
					</div>
				</form>
			</div>


			<!--</div>-->
			<!-- content -->
		</div>
	</div>

	<div id="pop" style="display:none;">
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
		window.jQuery
				|| document
						.write('<script type="text/javascript" src="./js/jquery.min.js"><\/script>')
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
	<script type="text/javascript" src="./js/jquery.simInput.js"></script>
	<script type="text/javascript" src="./js/bootstrap-dropdown.js"></script>
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

	<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="./js/jquery-ui-i18n.min.js"></script>
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
	<div id="ui-datepicker-div"
		class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div>

</body>
</html>
