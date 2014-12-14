<%@ page language="java" import="java.util.*, com.hhhy.db.beans.KeyWord"
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
	KeyWord kw = (KeyWord) session.getAttribute("keyword");
	String keyword = kw.getKeyword();
	String aux = kw.getAuxiliary();
	List<String> auxis = new ArrayList();
	if (aux != null && !"".equals(aux.trim())) {
		String[] tmps = aux.split(";");
		for (String tmp : tmps) {
			if (tmp != null && !"".equals(tmp.trim())) {
				auxis.add(tmp);
			}
		}
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>附属关键词列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
		<a class="appbrand" align="center" href=""><img src="./pic/hhhy.png" width="130" alt=""
			style="position: relative;top:0;"> </a>
		<button class="menu-toggle" type="button"></button>

		<ul class="topnav pull-right inline">
<!-- <li><a href="dtAll.jsp">顶贴</a></li> -->
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a></li>
			<li><a href="loginWeb.jsp" class="top-logout"
				data-toggle="tooltip" data-placement="bottom"><i></i> 退出</a></li>
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
					<li class="menu-list menu-rep"><a href="dtAll.jsp"
					class="menu-title"><i></i><span>顶贴</span> </a>
				</li>
			</ul>
		</div>
		<div id="content" class="content">
			<ul class="breadcrumb">
				<li>您在这里：</li>
				<li class="color-red">设置/关键词设置</li>
			</ul>
			<br>
			<br>
			<br>
			<table align="center" cellspacing="10">
				<%
					if (auxis == null || auxis.size() == 0) {
				%>
				<tr>
					<td>尚未添加<%=keyword%>的附属关键词</td>

				</tr>
				<%
					} else {
				%>

				<%
					for (String auxi : auxis) {
				%>
				<tr>
					<td><%=auxi%></td>
					<td>
						<!-- 
		  				<a href="./summarize?kid=<%=kw.getId()%>" >
		  				<input value=查看 class="btn btn-info" type="submit">
		  				</a>
		  				 --></td>
					<td>
						<form action="delaux" method="POST">
							<input value=删除 class="btn btn-danger" type="submit"> <input
								type='hidden' name='auxiliary' value="<%=auxi%>">
						</form></td>
				</tr>
				<%
					}
					}
				%>
			</table>


			<br>
			<br>
			<br>
			<br>

			<div align="center">
				<h4>
					添加<%=keyword%>的附属关键词
				</h4>
				<table cellspacing="10">
					<form action="addaux" method="POST">
						<tr>
							<td><input id="label" name="auxiliary" type="text">
							</td>
							<td><input value=添加 class="btn btn-info" type="submit">
							</td>
						</tr>

					</form>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
