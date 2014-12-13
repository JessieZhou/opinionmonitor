<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%--<%--%>
<%--String path = request.getContextPath();--%>
<%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%--<base href="<%=basePath%>">--%>
    <meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />

    <title>login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="login page">

	<link rel="stylesheet" type="text/css" href="./css/login.css">
    <link rel="stylesheet" type="text/css" href="./css/font.css">
    <link rel="stylesheet" type="text/css" href="./plugins/scojs/css/scojs.css">


  </head>
  
  <body>
    <div class="container">
    	<div class="login-container">	
    		<div class="logo" title="login">
    			Login
   			</div>
	   		<div class="action-btn">
	   			<span id="loginBtn" class="icon-lock select" title="登陆"></span>
	   			
	   		</div>	
		
			<div class="action-box active" id="loginBox">
				<div class="input-line">
					<div class="label">
						<span class="icon-user" title="用户名" alt="用户名"></span>
					</div>						
					<div class="input">
						<input id="username" type="text" placeholder="您的用户名" title="请输入用户名"/>
					</div>
				</div>	
				
				<div class="input-line">
					<div class="label">
						<span class="icon-key" title="密码" alt="密码"></span>
					</div>						
					<div class="input">
						<input id="password" type="password" placeholder="您的密码" title="请输入密码"/>
					</div>
				</div>
				<div class="remember-line">
					<input id="remember" type="checkbox" value="remember"/>&nbsp;&nbsp;记住密码
				</div>
				<div class="input-line">
					<div id="submitBtn" class="login-btn" data-url="actionLogin">登录</div>
				</div>		
	    	</div>
    	</div>
    </div>
  </body>
</html>
