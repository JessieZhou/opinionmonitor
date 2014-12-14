<%@ page language="java" import="java.util.*, com.hhhy.db.beans.*,com.hhhy.common.utils.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Long userid = (Long)session.getAttribute("userid");
if(userid==null){
	response.sendRedirect("./loginWeb.jsp");
	return;
}
String name = (String)session.getAttribute("name");
String keyword = (String)session.getAttribute("keyword");
%>

<%
	List<Article> articles = (List<Article>)session.getAttribute("all");
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="./css/sentimentMonitor/jquery-ui-timepicker-addon.css">
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/pager.css">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.yii.js"></script>
    <script type="text/javascript" src="./js/jquery.ba-bbq.js"></script>
    <title>全部舆情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Theme -->
    <link rel="stylesheet" href="./css/style-red.css">

    <!--[if lt IE 7]>
    <link rel="stylesheet" href="/apps/yuqing/theme/red/css/ie6-my.css" />
    <![endif]-->

    <!--[if lt IE 8]>
    <link rel="stylesheet" href="/apps/yuqing/theme/red/css/ie6-fix.css" />
    <![endif]-->

    <link rel="stylesheet" href="http://yq.adt100.com/apps/yuqing/theme/red/css/style-red-my.css">


    <link href="./css/bdsstyle.css" rel="stylesheet" type="text/css">
    <script src="./js/logger.js"></script>
</head>

<body>
<iframe frameborder="0" style="display: none;"></iframe><div id="bdshare_s" style="display: block;"><iframe id="bdsIfr" style="position:absolute;display:none;z-index:9999;" frameborder="0"></iframe><div id="bdshare_l" style="display: none;"><div id="bdshare_l_c"><h6>分享到</h6><ul><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_mshare mshare">一键分享</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_qzone qqkj">QQ空间</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tsina xlwb">新浪微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_bdysc bdysc">百度云收藏</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_renren rrw">人人网</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tqq txwb">腾讯微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_bdxc bdxc">百度相册</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_kaixin001 kxw">开心网</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tqf txpy">腾讯朋友</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tieba bdtb">百度贴吧</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_douban db">豆瓣网</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tsohu shwb">搜狐微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_bdhome bdhome">百度新首页</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_sqq sqq">QQ好友</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_thx thx">和讯微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_more">更多...</a></li></ul><p><a href="http://yq.adt100.com/moniter/index?merge=1#" class="goWebsite">百度分享</a></p></div></div></div>
<div class="navbar">
    <a class="appbrand" align="center" href="summarize"><img src="" width="130" alt=""
			style="position: relative;top:0;"> </a>
    <button class="menu-toggle" type="button"></button>
    
    <!-- <div class="topnav pull-left ln">
        <div class="btn-group" id="J-toggle-site">
            <a class="btn dropdown-toggle btn-small">
                name
                <span class="caret" style="display: none;"></span>
            </a>
            <ul class="dropdown-menu"></ul>
        </div>
    </div> -->
    
    <ul class="topnav pull-right inline">
<li> <%=keyword %><li>
<!-- <li><a href="dtAll.jsp">顶贴</a></li> -->
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a></li>
			<li><a href="loginWeb.jsp" class="top-logout" data-toggle="tooltip"
				data-placement="bottom"><i></i> 退出</a></li>
		</ul>

</div>

<div class="wrapper">
<div class="hidden-phone menu" id="menu">
    <div class="profile">
        <span>欢迎您：</span>
        <a><%=name %></a>
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
                <button class="side-search-btn"><i></i></button>
                <input id="search-type-flag" type="hidden" name="source" value="0">
                <input id="search-type-flag" type="hidden" name="adv" value="1">
            </div>
        </form>
    </div> -->
    <ul class="menu-lists">
				<li class="menu-list menu-general"><a href="summarize"
					class="menu-title"><i></i><span>舆情概况</span> </a></li>
				<li id="more-sub-menu" class="menu-list menu-lat "><a href="sentitrend"
					class="menu-title"><i></i><span>维度分析</span> </a>
					<ul class="sub-menu-list" id="sub-menu-list">
						<li><a href="sentitrend">舆情走势</a></li>
						<li><a href="datasource">数据来源</a></li>
						<li><a href="mediasource">媒体来源</a></li>
						<li><a href="emotiontrend">情感走势</a></li>
					</ul></li>


				<li class="menu-list menu-any  active"><a href="#" class="menu-title"><i></i><span>舆情监控</span>
				</a></li>
				<li class="menu-list menu-rep "><a href="dataExport.jsp" class="menu-title"><i></i><span>数据报告</span>
				</a></li>
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
    <li class="color-red">全部舆情</li>
</ul>
<div class="innerLR">

<!-- form action="search" method="get"><input type="hidden" value="3" name="period" id="period"><input type="hidden" value="" name="author" id="author"><input type="hidden" value="" name="adv" id="adv"><input type="hidden" value="" name="imp" id="imp"><div class="row-fluid">
    <div class="search-filter-box">
        <div class="normal-search-box clearfix">
            <div class="clearfix time-opt span10">
                <div class="control-group form-inline">
                    <ul class="inline tab-small">
                        <li class="first ">
                            <a class="moniter-period" href="">今日</a>            </li>
                        <li>
                            <a class="moniter-period" href="">昨日</a>            </li>
                        <li class="current">
                            <a class="moniter-period" href="">7天</a>
                        </li>
                        <li>
                            <a class="moniter-period" href="">30天</a>
                        </li>
                        <li></li>
                        <li style="padding-left:0px;">自定义：
                            <input class="input-small hasDatepicker" style="width:120px;margin-right:5px;" id="start_date" name="start_date" type="text" value="2014-07-15 00:00"> - <input class="input-small hasDatepicker" style="width:120px;margin-left:5px;" id="end_date" name="end_date" type="text" value="2014-07-21 23:59">
                        </li>
                        <li>
                            <div class="ez-checkbox ez-checked"><input data-toggle="checkbox" class="ez-hide" checked="checked" id="yt0" type="checkbox" value="1" name="merge"></div>合并相似文章
                        </li>
                    </ul>
                </div>
                <div class="control-group form-inline">

                    <label class="control-label" for="">来源：</label>          <select class="mr20" name="source" id="source">
                    <option value="all" selected="selected">全部</option>
                    <option value="news">新闻</option>
                    <option value="bbs">论坛</option>
                    <option value="blog">博客</option>
                    <option value="search">搜索引擎</option>
                </select>

                    <label class="control-label" for="">情感：</label>          <select class="mr20" name="sentiment" id="sentiment">
                    <option value="all" selected="selected">全部</option>
                    <option value="positive">正面</option>
                    <option value="negative">负面</option>
                    <option value="neutral">中立</option>
                </select>
                    <label class="control-label" for="">话题：</label>          <select class="mr20" name="topic_id" id="topic_id">
                    <option value="" selected="selected">全部</option>
                    <option value="996">name</option>
                    
                </select>
                </div>
            </div>
            <div class="span2">
                <p>
                    <a href="javascript:;" id="show-advance-search">高级搜索</a>
                </p>
                <p>

                    <a href="javascript:void(0);" onclick="jQuery.ajax({
              &#39;url&#39;:&#39;/moniter/history&#39;,
              &#39;cache&#39;:false,
              &#39;success&#39;:function(html){
                  $(&#39;#history_result&#39;).html(html);
                  $(&#39;#history_dialog&#39;).dialog(&#39;open&#39;);
                  return false;
                }
            });">
                        历史筛选器
                    </a>

                </p>
            </div>
        </div>
        normal

        <div class="advance-search-box J-advance-search-box">
            <div class="control-group form-horizontal">
                <label class="control-label">搜索结果：</label>
                <div class="controls">
                    <div class="control-group form-inline">
                        <p class="mb10">
                            <label class="control-label">包含以下<strong>全部</strong>关键词：</label>
                            <input type="text" value="" name="include" id="include">            </p>
                        <p class="mb10">
                            <label class="control-label">包含以下<strong>任一</strong>关键词：</label>
                            <input type="text" value="" name="any" id="any">            </p>
                        <p class="mb10">
                            <label class="control-label"><strong>不包含</strong>以下关键词：</label>
                            <input type="text" value="" name="exclude" id="exclude">            </p>
                    </div>
                </div>
                <label class="control-label">关键词位置：</label>
                <div class="controls s-radio">
                    <span id="position"><label><div class="ez-radio ez-selected"><input data-toggle="checkbox" value="0" id="position_0" checked="checked" type="radio" name="position" class="ez-hide"></div> 不限位置</label><label><div class="ez-radio"><input data-toggle="checkbox" value="1" id="position_1" type="radio" name="position" class="ez-hide"></div> 仅限标题中</label><label><div class="ez-radio"><input data-toggle="checkbox" value="2" id="position_2" type="radio" name="position" class="ez-hide"></div> 仅限URL中</label></span>        </div>
                
                <div class="mb10">
                    <label class="control-label">结果条数：</label>
                    <div class="controls">
                        <select name="perpage" id="perpage">
                            <option value="10">每页10条</option>
                            <option value="30">每页30条</option>
                            <option value="50">每页50条</option>
                        </select>        </div>
                </div>
                <div class="mb10 search-advance-btn">
                    <label class="control-label">&nbsp;</label>
                    <div class="controls">
                        <input class="btn-red" type="submit" name="yt1" value="高级搜索">          </div>
                </div>
            </div>
        </div>
        <div class="toggle-advance-search"><a href="javascript:;" id="hide-advance-search">隐藏高级搜索</a></div>
        adv


    </div>

</div>
</form> -->

<div class="row-fluid">
<div class="span12">
<div class="widget-tab">
<div class="widget-tab-hd">
    <ul class="clearfix tab-hd" id="moniter-sort-move">
		<li class="current">
            <a class="moniter-period" href="specmonitor?today=1&trend=0">今天全部</a>
        </li>
        <li class="current">
            <a class="moniter-period" href="specmonitor?today=1&trend=1">今天正面</a>
        </li>
        <li class="current">
            <a class="moniter-period" href="specmonitor?today=1&trend=3">今天中立</a>
        </li>
        <li class="current">
            <a class="moniter-period" href="specmonitor?today=1&trend=2">今天负面</a>
        </li>
        <li class="current">
            <a class="moniter-period" href="specmonitor?today=0&trend=0">全部</a>
        </li>
        <li class="current">
            <a class="moniter-period" href="specmonitor?today=0&trend=1">全部正面</a>
        </li>
        <li class="current">
            <a class="moniter-period" href="specmonitor?today=0&trend=3">全部中立</a>
        </li>
        <li class="current">
            <a class="moniter-period" href="specmonitor?today=0&trend=2">全部负面</a>
        </li>
        

        <!-- <li>

            <a class="moniter-period" href="">正面</a>
        </li>

        <li>

            <a class="moniter-period" href="">中立</a>
        </li>

        <li>

            <a class="moniter-period" href="">负面</a>
        </li> -->


      <!--   <li class="moniter-sort-li"><a class="i-seq desc" href=""><span>发布时间</span></a></li>
        <li class="moniter-sort-li"><a class="i-seq" href=""><span>转载数</span></a></li> -->
    </ul>
</div>
<div class="widget-tab-bd">

<div class="row-fluid" id="info_list">

<!-- <ul class="m-news-filter"> -->

	<% for(Article aa : articles) {
		out.println("<ul class=\"m-news-filter\">" +
		"<li class=\"m-news-filter-item clearfix\">" +
    "<div class=\"m-i-flag\">");
		if(aa.getEmotion() > 0 ) {
			out.println("<span class=\"bg-sapn-red\">正</span>    </div>");
		}else if(aa.getEmotion() < 0){
			out.println("<span class=\"bg-sapn-green\">负</span>    </div>");		
		}else if(aa.getEmotion() == 0) {
			out.println("<span class=\"bg-sapn-gray\">中</span>    </div>");	
		}
		
		out.println("<div class=\"m-news-main\">"+
        "<div class=\"m-news-intro \">"+
            "<div class=\"news-intro-hd\">");
            
        out.println("<strong><a target=\"_blank\" href=\"" + aa.getUrl() + "\">["+aa.getWebsite()+"]" + aa.getTitle() + "</a> "+DateFormatUtils.formatTime(aa.getTime(), "yyyy-MM-dd HH:mm")+"</strong>");
        out.println("</div>"+
            "<div class=\"news-intro-bd\">"+ aa.getSummary() +"</div></div></div></li></ul>");
            }
	 %>

<!-- </ul> -->
<br><div class="pager"><div id="yw0" class="page-count">  <strong class="mr20" id="total-search-count"><%=articles.size() %>条搜索结果</strong></div>
<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=0" src="./js/bds_s_v2.js"></script>

<script type="text/javascript">
    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script>
</div>
</div>
 <div id="changpage"></div>
</div>
</div>


</div>
<script>
    $(function(){
        $("#moniter-sort-move").find('li[class=moniter-sort-li]').each(function(){$(this).remove();});
        $("#moniter-sort").find('th').each(function(){
            $("#moniter-sort-move").append('<li class=moniter-sort-li>'+ $(this).html() + '</li>');
        });
    });
</script>    
<script> 
var obj,j; 
var page=0; 
var nowPage=0;//当前页 
var listNum=15;//每页显示<ul>数 
var PagesLen;//总页数 
var PageNum=4;//分页链接接数(5个) 
onload=function(){ 
obj=document.getElementById("info_list").getElementsByTagName("ul"); 
j=obj.length 
PagesLen=Math.ceil(j/listNum); 
upPage(0) 
} 
function upPage(p){ 
nowPage=p 
//内容变换 
for (var i=0;i<j;i++){ 
obj[i].style.display="none" 
} 
for (var i=p*listNum;i<(p+1)*listNum;i++){ 
if(obj[i])obj[i].style.display="block" 
} 
//分页链接变换 
strS='<a href="###" onclick="upPage(0)">首页</a>  ' 
var PageNum_2=PageNum%2==0?Math.ceil(PageNum/2)+1:Math.ceil(PageNum/2) 
var PageNum_3=PageNum%2==0?Math.ceil(PageNum/2):Math.ceil(PageNum/2)+1 
var strC="",startPage,endPage; 
if (PageNum>=PagesLen) {startPage=0;endPage=PagesLen-1} 
else if (nowPage<PageNum_2){startPage=0;endPage=PagesLen-1>PageNum?PageNum:PagesLen-1}//首页 
else {startPage=nowPage+PageNum_3>=PagesLen?PagesLen-PageNum-1: nowPage-PageNum_2+1;var t=startPage+PageNum;endPage=t>PagesLen?PagesLen-1:t} 
for (var i=startPage;i<=endPage;i++){ 
if (i==nowPage)strC+='<a href="###" style="color:red;font-weight:700;" onclick="upPage('+i+')">'+(i+1)+'</a> ' 
else strC+='<a href="###" onclick="upPage('+i+')">'+(i+1)+'</a> ' 
} 
strE=' <a href="###" onclick="upPage('+(PagesLen-1)+')">尾页</a>  ' 
strE2=nowPage+1+"/"+PagesLen+"页"+"  共"+j+"条" 
document.getElementById("changpage").innerHTML=strS+strC+strE+strE2 
} 
</script> 


<!--</div>--><!-- content -->
</div>
</div>

<div id="pop" style="display:none;">
    <div id="popHead"><h2>&nbsp;</h2><a id="popClose" title="关闭">关闭</a> </div>
    <div id="popContent">
        <dl>
            <dt id="popIntro"></dt>
            <dd id="popMore"><a href=""></a></dd>
        </dl>
    </div>
</div>

<script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="/apps/yuqing/theme/scripts/jquery-1.8.2.min.js"><\/script>')</script>

<script>
    $(function(){
        $.getJSON("/moniter/pop?merge=1", function(data){
            if(data['num'] > 0) {
                var pop=new Pop(data['data'], data["num"]);
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

    $(function(){
        $('[data-toggle="checkbox"]').simInput();
        $('[data-toggle="radio"]').simInput();
    });

</script>

<!--[if lt IE 8]>

<script type="text/javascript" src="/apps/yuqing/theme/red/js/ie-fix.js" ></script>

<![endif]-->

<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="./js/jquery.yiilistview.js"></script>
<script type="text/javascript">
    /*<![CDATA[*/
    jQuery(function($) {

        jQuery('#start_date').datetimepicker(jQuery.extend({showMonthAfterYear:false}, jQuery.datepicker.regional['en_us'], {'dateFormat':'yy-mm-dd','onClose':function(dateText, inst) {
            document.getElementById("period").value=""; this.form.submit();
        }}));
        jQuery('#end_date').datetimepicker(jQuery.extend({showMonthAfterYear:false}, jQuery.datepicker.regional['en_us'], {'dateFormat':'yy-mm-dd','onClose':function(dateText, inst) {
            document.getElementById("period").value=""; this.form.submit();
        }}));
        jQuery('body').on('click','#yt0',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('body').on('change','#source',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('body').on('change','#sentiment',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('body').on('change','#topic_id',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('#info_list').yiiListView({'ajaxUpdate':[],'ajaxVar':'ajax','pagerClass':'pager','loadingClass':'list-view-loading','sorterClass':'sorter','enableHistory':false});
        jQuery('#mail_dialog').dialog({'title':'分享到邮件','autoOpen':false,'modal':true,'width':'500','height':'400','buttons':[{'text':'发送','click':function(){
            $.post(
                    $("#mail_form").attr("action"),
                    $("#mail_form").serialize(),
                    function(html){
                        $("#mail_dialog").dialog("close");
                        $("#hint_result").html(html);
                        $("#hint_dialog").dialog("open");
                    }
            );
        }},{'text':'取消','click':function(){$(this).dialog("close");}}]});
        jQuery('#share_dialog').dialog({'title':'分享到媒体','autoOpen':false,'modal':true,'width':'500','height':'400'});
        jQuery('#hint_dialog').dialog({'title':'操作结果','autoOpen':false,'modal':true,'width':'200','height':'200'});
        jQuery('#transmit_dialog').dialog({'title':'发送私信','autoOpen':false,'modal':true,'width':'500','height':'400','buttons':[{'text':'发送','click':function(){
            $.post(
                    $("#transmit_form").attr("action"),
                    $("#transmit_form").serialize(),
                    function(html){
                        $("#transmit_dialog").dialog("close");
                        $("#hint_result").html(html);
                        $("#hint_dialog").dialog("open");
                    }
            );
        }},{'text':'取消','click':function(){$(this).dialog("close");}}]});
        jQuery('#history_dialog').dialog({'title':'历史筛选器','autoOpen':false,'modal':true,'width':'600','height':'200'});
    });
    /*]]>*/
</script>


<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable ui-dialog-buttons" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-1"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-1" class="ui-dialog-title">分享到邮件</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="mail_dialog" class="ui-dialog-content ui-widget-content">
    <form id="mail_form" action="http://yq.adt100.com/info/mail" method="post">  <input type="hidden" name="info_id" id="info_id">
        <div class="winbox-share">
            <div class="share-content">
                <strong id="org_title"></strong>
                <div id="org_summary"></div>
            </div>
            <label>邮件地址：</label>
            <div class="">
                <input type="text" value="" name="mail_to" id="mail_to">
            </div>
            <label>邮件正文：</label>
            <div class="share-input">
                <textarea name="mail_content" id="mail_content"></textarea>
            </div>
        </div></form></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div><div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix"><div class="ui-dialog-buttonset"><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">发送</span></button><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">取消</span></button></div></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-2"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-2" class="ui-dialog-title">分享到媒体</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="share_dialog" class="ui-dialog-content ui-widget-content">
    <div class="winbox-share">
        <div class="share-content">
            <strong id="share_org_title"></strong>
            <div id="share_org_summary"></div>
        </div>
        <div>
            <!-- Baidu Button BEGIN -->
            <%--<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare">--%>
                <%--<a class="bds_qzone" title="分享到QQ空间" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_tsina" title="分享到新浪微博" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_tqq" title="分享到腾讯微博" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_renren" title="分享到人人网" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_t163" title="分享到网易微博" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<span class="bds_more"></span>--%>
                <%--<a class="shareCount" href="http://yq.adt100.com/moniter/index?merge=1#" title="累计分享0次">0</a>--%>
            <%--</div>--%>

            <!-- Baidu Button END -->
        </div>
    </div></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-3"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-3" class="ui-dialog-title">操作结果</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="hint_dialog" class="ui-dialog-content ui-widget-content">
    <div id="hint_result"></div></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable ui-dialog-buttons" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-4"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-4" class="ui-dialog-title">发送私信</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="transmit_dialog" class="ui-dialog-content ui-widget-content">
    <%--<form id="transmit_form" action="http://yq.adt100.com/info/msg" method="post"><input type="hidden" name="info_id" id="minfo_id">--%>
        <%--<div class="winbox-share">--%>
            <%--<div class="share-content">--%>
                <%--<strong id="morg_title"></strong>--%>
                <%--<div id="morg_summary"></div>--%>
            <%--</div>--%>
            <%--<label>私信给：</label>--%>
            <%--<div class="">--%>
                <%--<select name="msg_to" id="msg_to">--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<label>私信正文：</label>--%>
            <%--<div class="share-input">--%>
                <%--<textarea name="msg_content" id="msg_content"></textarea>--%>
            <%--</div>--%>
        <%--</div></form></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div><div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix"><div class="ui-dialog-buttonset"><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">发送</span></button><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">取消</span></button></div></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable" tabindex="-1" role="dialog" aria-labelledby="ui-id-5" style="display: none; outline: 0px; z-index: 1000; position: absolute;"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-5" class="ui-dialog-title">历史筛选器</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div id="history_dialog" class="ui-dialog-content ui-widget-content">--%>
    <%--<div id="history_result"></div></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div></div>--%>

</body>
</html>