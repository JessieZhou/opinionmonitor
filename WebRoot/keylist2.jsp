<%@ page language="java" import="java.util.*,com.hhhy.db.beans.KeyWord, com.hhhy.db.DBUtils" pageEncoding="utf-8"%>
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
    //List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
    List<KeyWord> keywords = (List<KeyWord>) session.getAttribute("keywords");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>关键词设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/key/boostrap.css">
	<link rel="stylesheet" type="text/css" href="./css/key/boostrap-theme.css">
	
	<link rel="stylesheet" href="./css/style-red.css">
	
		<!--  Bootstrap css-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

		<!-- Bootstrap-->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- jQuery bootstrap.min.js  -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- Bootstrap JavaScript -->
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style type="text/css">	

</style>
  </head>
  
  <body>
  <iframe frameborder="0" style="display: none;"></iframe>
  
<div class="navbar">
    <a class="appbrand"></a>
    <button class="menu-toggle" type="button"></button>

    <ul class="topnav pull-right inline">
    <li><a href="dtAll.jsp">顶贴</a></li>
			<li><a href="loginWeb.jsp" class="top-logout" data-toggle="tooltip"
				data-placement="bottom"><i></i> <%=email %>退出</a></li>
			
	</ul>

</div>
  <br><br><br>
    <table align="center" cellspacing="10">
    <% if(keywords==null || keywords.size()==0){ %>
			<tr>
			<td>尚未添加关键词</td>
			
			</tr>
		<% } else{ %>
		
		<% for(KeyWord keyword:keywords){ %>
    	<tr>
  			<td>
  			
  			<%=keyword.getKeyword() %><!--<%=keyword.getAuxiliary()==null?"":":"+keyword.getAuxiliary() %>
  			
  			--></td>
  			<td>
  				<a href="./auxiliary?kid=<%=keyword.getId() %>">
  				<input value=附属词编辑 class="btn btn-info" type="submit">
  				</a>
  			</td>
  			<td>
  				<a href="./historySet?kid=<%=keyword.getId() %>">
  				<input value=历史时间设置 class="btn btn-info" type="submit">
  				</a>
  			</td>
  			<td>
  				<a href="./summarize?kid=<%=keyword.getId() %>" >
  				<input value=查看 class="btn btn-info" type="submit">
  				</a>
  			</td>
  			<td>
				<form action="deletekeyword" method="POST">    
                    	<input value=删除 class="btn btn-danger" type="submit">    
                    	<input type='hidden' name='kid' value="<%=keyword.getId() %>">
				</form>
			</td>
		</tr>		
		<% }} %>
    </table>
    
    
    <br><br><br><br>
    
    <div align="center">
    	<h4>添加关键词</h4>
    	<table cellspacing="10">
			<form action="addkeyword" method="POST"> 
				<tr>	
					<td><input id="label" name="keyword" type="text"></td>
					<td><input value=添加 class="btn btn-info" type="submit"></td>
				</tr>
				
       		</form>
      	</table>
    </div>
  </body>
</html>
