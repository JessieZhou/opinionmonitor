<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>登陆界面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<!-- Bootstrap -->
		<link href="./css/bootstrap.min.css" rel="stylesheet">
		<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

		<!-- Theme -->
		<link rel="stylesheet" href="./css/style-red.css">
		<link rel="stylesheet" href="./css/style-red-my.css">
		<style type="text/css">
		a.zhuce {
			float: left;
		}
		</style>
	</head>

	<body class="bg-login">
		<form id="login-form" action="UserLogin" method="post">
			<div class="login-box">
				<div class="login-logo">
					<h1>
						<a href=""><img src="./pic/hhhy2.png" alt="" width="230">
						</a>
					</h1>
				</div>
				<div class="login-form">
					<ul>
						<li class="user">
							<input placeholder="邮箱" name="username"
								id="LoginForm_username" type="text">
						</li>
						<li class="pwd">
							<input placeholder="密码" name="password" id="LoginForm_password"
								type="password">
						</li>
					</ul>

					<div class="login-opt clearfix">
						
						<input class="login-btn" type="submit" name="yt0" value="登录">
						<ul>
						<li><a class="zhuce" href="registerWeb.jsp">注册</a></li>
						</ul>
						<label class="remember-me">
							<% String loginerror = (String)session.getAttribute("loginerror"); %>
							<% if(loginerror!=null){ %>
							<div><%=loginerror %></div>
							<% } %>
						</label>
						
					</div>
				</div>
			</div>

		</form>
<style> 
.login-error li { padding-left: 0px; }
</style>
	</body>
</html>
