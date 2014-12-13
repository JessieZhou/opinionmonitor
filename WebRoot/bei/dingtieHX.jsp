<%@ page language="java" import="java.util.*,com.hhhy.db.beans.PostArt, com.hhhy.db.DBUtils,com.hhhy.web.service.webservice.hexun.HexunCrawler" pageEncoding="utf-8"%>
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
    String number = request.getParameter("sid");
    List<PostArt> posts = HexunCrawler.parseHexun(number);
    //List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
    //List<PostArt> posts = (List<PostArt>) session.getAttribute("DFCFPosts");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>和讯股吧顶贴</title>
    
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
			<li><a href="loginWeb.jsp" class="top-logout" data-toggle="tooltip"
				data-placement="bottom"><i></i> <%=email %>退出</a></li>
			
	</ul>

</div>
  <br><br><br>
    <table align="center" cellspacing="10">
    <% if(posts==null || posts.size()==0){ %>
			<tr>
			<td>没有帖子显示</td>
			
			</tr>
		<% } else{ %>
		
		<% for(PostArt post:posts){ %>
    	<tr>
  			<td>			
  			<a href="<%= post.getUrl() %>>"><%=post.getTitle() %></a>
  			</td>
  			
  			<td>
  			<input type="text" placeholder="在此输入顶贴内容">
  			</td>
  			<td>
  				<a href="">
  				<input value=顶该贴 class="btn btn-info" type="submit">
  				</a>
  			</td>
  			
		</tr>		
		<% }} %>
    </table>
  </body>
</html>
