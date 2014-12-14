<%@ page language="java"
	import="java.util.*,com.hhhy.db.beans.*, com.hhhy.db.DBUtils"
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
	String email = (String) session.getAttribute("name"); 
	String error = (String)session.getAttribute("stockerror");
	//List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
	//List<KeyWord> keywords = (List<KeyWord>) session.getAttribute("keywords");
	List<Stock> shares = DBUtils.getStockByUser(userid);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>顶贴关键词设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 
	<link rel="stylesheet" type="text/css" href="./css/key/boostrap.css">
	<link rel="stylesheet" type="text/css" href="./css/key/boostrap-theme.css">
	
	<link rel="stylesheet" href="./css/style-red.css">
	
		 Bootstrap css
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

		Bootstrap
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

jQuery bootstrap.min.js 
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

Bootstrap JavaScript
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->

<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
<script type="text/javascript" src="./js/jquery.js"></script>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- Theme -->
<link rel="stylesheet" href="./css/style-red.css">

<link rel="stylesheet" href="./css/style-red-my.css">
<style type="text/css">
</style>
</head>

<body>
	<iframe frameborder="0" style="display: none;"></iframe>

	<div class="navbar">
		<a class="appbrand" align="center" href="keylist"><img src="" width="130" alt=""
			style="position: relative;top:0;"> </a>
		<button class="menu-toggle" type="button"></button>

		<ul class="topnav pull-right inline">
		<!-- <li><a href="dtAll.jsp">顶贴</a></li> -->
		<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a>
			</li>
			<li><a href="loginWeb.jsp" class="top-logout"
				data-toggle="tooltip" data-placement="bottom"><i></i> <%=email%>退出</a>
			</li>

		</ul>

	</div>
	
	<div class="wrapper">
		<div class="hidden-phone menu" id="menu">
			<div class="profile">
				<span>欢迎您：</span> <a><%=email%></a>
			</div>

			<ul class="menu-lists">

				<li class="menu-list menu-rep"><a href="keylist"
					class="menu-title"><i></i><span>关键词设置</span> </a>
				</li>
				<li class="menu-list menu-rep"><a href="spiderurl"
					class="menu-title"><i></i><span>自添加爬虫列表</span> </a>
				</li>
				<li class="menu-list menu-any active"><a href="#"
					class="menu-title"><i></i><span>顶贴</span> </a>
				</li>
			</ul>
		</div>
		<div id="content" class="content">
			<ul class="breadcrumb">
				<li>您在这里：</li>
				<li class="color-red">设置/顶贴</li>
			</ul>
			<br>
			<br>
			<br>
			<div align="center">
				<h4>添加关键词</h4>
				<table cellspacing="10">
					<form action="stockadd" method="POST">
						<tr>
							<td><input id="shareName" name="shareName" type="text" placeholder="股票名称">
							</td>
							<td><input id="shareNum" name="shareNum" type="text" placeholder="股票代码">
							</td>
							<td><input value=添加 class="btn btn-info" type="submit">
							</td>
						</tr>
		
					</form>
				</table>
			</div>
			<br>
			<br>
			<br>
			<br>
			<table align="center" cellspacing="10">
				<%
					if (shares == null || shares.size() == 0) {
				%>
				<tr>
					<td>尚无股票片名添加</td>
		
				</tr>
				<%
					} else {
				%>
		
				<%
					for (Stock share : shares) {
				%>
				<tr>
					<td><%=share.getStockname() %></td>
					<td><a href="./dingtieDFCF.jsp?sid=<%=share.getStockid() %>"> <input value=东方财富股吧 class="btn btn-info"
							type="submit"> </a></td>
					<td><a href="./dingtieHX.jsp?sid=<%=share.getStockid() %>"> <input value=和讯股吧 class="btn btn-info"
							type="submit"> </a></td>
					<td><a href="./dingtieJRJ.jsp?sid=<%=share.getStockid() %>"> <input value=金融界股吧 class="btn btn-info"
							type="submit"> </a></td>
					<td>
						<form action="stockdel" method="POST">
							<input value=删除 class="btn btn-danger" type="submit"> <input
								type='hidden' name='kid' value="<%=share.getId() %>">
						</form></td>
				</tr>
				<%
					}
					}
				%>
			</table>		
			
			</div>
	</div>
</body>
</html>
