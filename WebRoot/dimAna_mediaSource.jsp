<%@ page language="java" import="java.util.*,com.hhhy.common.utils.ShowUtil" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Long userid = (Long)session.getAttribute("userid");
	if(userid==null){
		response.sendRedirect("./loginWeb.jsp");
		return;
	}
	String email = (String)session.getAttribute("name");
	Map<String, Integer> mediaStatis =(Map<String, Integer>)session.getAttribute("mediaStatis");
	String roundData = ShowUtil.dimAna_dataSourceRoundData(mediaStatis);
	String zhuSource = ShowUtil.dimAna_dataSourceZhuSource(mediaStatis);
	String zhuData=ShowUtil.dimAna_dataSourceZhuData(mediaStatis);
	String keyword = (String)session.getAttribute("keyword");
%>

<%
	///String roundData = "['Firefox',   45.0],['IE',       26.8],['Chrome',   12.8],['Safari',    8.5],['Opera',     6.2],['Others',   0.7]";
	
	//String zhuSource = "'Africa', 'America', 'Asia', 'Europe', 'Oceania'";
	//String zhuData="107, 31, 635, 203, 2";
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- <base href="<%=basePath%>"> -->

<title>媒体来源</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="./css/jquery-ui.css">
<script type="text/javascript" src="./js/jquery.js"></script>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- Theme -->
<link rel="stylesheet" href="./css/style-red.css">

<link rel="stylesheet" href="./css/style-red-my.css">

<link rel="stylesheet" type="text/css"
	href="./css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="./css/styles.css" />
<link rel="stylesheet" type="text/css"
	href="./css/pager.css" />
<script type="text/javascript"
	src="./js/jquery.js"></script>
<script type="text/javascript"
	src="./js/jquery.yii.js"></script>
<script type="text/javascript"
	src="./js/jquery.ba-bbq.js"></script>
<script type="text/javascript"
	src="./js/highcharts.src.js"></script>
<script type="text/javascript"
	src="./js/exporting.src.js"></script>
</head>

<body>

	<div class="navbar">
		<a class="appbrand" align="center" href="summarize"><img src="" width="130" alt=""
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
		<li> <%=keyword %><li>
		<!-- <li><a href="dtAll.jsp">顶贴</a></li> -->
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a></li>
			<li><a href="loginWeb.jsp" class="top-logout" data-toggle="tooltip"
				data-placement="bottom"><i></i> 退出</a></li>
		</ul>

	</div>
	<div class="loading">载入中...</div>
	<div class="wrapper">
		<div class="hidden-phone menu" id="menu">
			<div class="profile">
				<span>欢迎您：</span> <a><%=email %></a>
			</div>
			<!-- <div id="side-search" class="search-box"> -->
				<!-- <form action="" method="get">
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
						<button class="side-search-btn">
							<i></i>
						</button>
						<input id="search-type-flag" type="hidden" name="source" value="0">
						<input id="search-type-flag" type="hidden" name="adv" value="1">
					</div>
				</form> -->
			<!-- </div> -->
			<ul class="menu-lists">
				<li class="menu-list menu-general"><a href="summarize"
					class="menu-title"><i></i><span>舆情概况</span> </a></li>
				<li id="more-sub-menu" class="menu-list menu-lat active"><a href="sentitrend"
					class="menu-title"><i></i><span>维度分析</span> </a>
					<ul class="sub-menu-list" id="sub-menu-list">
						<li><a href="sentitrend">舆情走势</a></li>
						<li><a href="datasource">数据来源</a></li>
						<li class="sub-active"><a href="#">媒体来源</a></li>
						<li><a href="emotiontrend">情感走势</a></li>
					</ul></li>


				<li class="menu-list menu-any "><a href="monitor" class="menu-title"><i></i><span>舆情监控</span>
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
				<li class="color-red">维度分析/媒体来源</li>
			</ul>
			<div class="innerLR">
				<!-- <form action="http://yq.adt100.com/source/index" method="get">
					<input type="hidden" value="3" name="period" id="period"><input
						type="hidden" value="" name="author" id="author">
					<div class="row-fluid pb10">
						<div class="clearfix time-opt">
							<ul class="inline tab-small">
								<li class="first "><a
									href="http://yq.adt100.com/source/index?period=1&source=&sentiment=&topic_id=&merge=1&author=">今日</a>
								</li>
								<li><a
									href="http://yq.adt100.com/source/index?period=2&source=&sentiment=&topic_id=&merge=1&author=">昨日</a>
								</li>
								<li class="current"><a
									href="http://yq.adt100.com/source/index?period=3&source=&sentiment=&topic_id=&merge=1&author=">7天</a>
								</li>
								<li><a
									href="http://yq.adt100.com/source/index?period=4&source=&sentiment=&topic_id=&merge=1&author=">30天</a>
								</li>
								<li></li>
								<li>自定义： <input class="input-small hasDatepicker"
									readonly="readonly"
									onchange="document.getElementById(&quot;period&quot;).value=&quot;&quot;; this.form.submit();"
									id="start_date" type="text" value="2014-08-05"
									name="start_date"> <a class="i-cal" href="javascript:;"
									id="start_date_link"></a> <script>
        $(function(){
          $("#start_date_link").click(function(){
             $("#start_date").datepicker("show");
          });
        });
      </script> <input class="input-small hasDatepicker" readonly="readonly"
									onchange="document.getElementById(&quot;period&quot;).value=&quot;&quot;; this.form.submit();"
									id="end_date" type="text" value="2014-08-11" name="end_date">
									<a class="i-cal" href="javascript:;" id="end_date_link"></a> <script>
        $(function(){
          $("#end_date_link").click(function(){
             $("#end_date").datepicker("show");
          });
        });
      </script></li>
								<li><label>
										<div class="ez-checkbox ez-checked">
											<input data-toggle="checkbox" class="ez-hide"
												checked="checked" id="yt0" type="checkbox" value="1"
												name="merge">
										</div>合并相似文章 </label></li>
							</ul>
						</div>
					</div>
					<div class="row-fluid clearfix">
						<div class="control-group form-inline">

							<label class="control-label" for="">情感：</label> <select
								class="mr20" name="sentiment" id="sentiment">
								<option value="" selected="selected">全部</option>
								<option value="positive">正面</option>
								<option value="negative">负面</option>
								<option value="neutral">中立</option>
							</select> <label class="control-label" for="">话题：</label> <select
								class="mr20" name="topic_id" id="topic_id">
								<option value="" selected="selected">全部</option>
								<option value="913">观致3</option>
								<option value="914">观致五门版</option>
								<option value="982">性价比口碑</option>
							</select>
						</div>
					</div>
				</form> -->

				<div class="row-fluid">
					<div class="span12">
						<div class="widget">
							<div class="widget-hd">
								<span class="fr"><a
									class="i-export"></a>
								</span>媒体来源
							</div>
							<div class="widget-bd">

								<table width="100%">
									<tbody>
										<tr>
											<td width="50%" class="h400">
												<div id="yw0" data-highcharts-chart="0">
													<div class="highcharts-container" id="dimAna_mediaSource_1"
														style="position: relative; overflow: hidden; width: 482px; height: 350px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">
														
													</div>
												</div></td>
											<td width="50%" class="h400">
												<div id="yw1" data-highcharts-chart="1">
													<div class="highcharts-container" id="dimAna_mediaSource_2"
														style="position: relative; overflow: hidden; width: 481px; height: 350px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">
														
													</div>
												</div></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
		<script type="text/javascript" id="bdshare_js"
			data="type=tools&amp;uid=0"
			src="./js/bds_s_v2.js"></script>



		<script>
Highcharts.setOptions({
	lang: {
    printChart:'打印图片',
		downloadJPEG: '保存为jpeg',
    downloadPNG: '保存为png',
    downloadSVG: '保存为svg',
    downloadPDF: '保存为pdf'
	}
});
</script>
		<!--</div>-->
		<!-- content -->


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
	</div>

	<script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="./js/jquery.min.js"><\/script>')</script>

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

	<script type="text/javascript"
		src="./js/pop.js"></script>
	<script type="text/javascript"
		src="./js/jquery.simInput.js"></script>
	<script type="text/javascript"
		src="./js/bootstrap-dropdown.js"></script>
	<script type="text/javascript"
		src="./js/common.js"></script>
	<script type="text/javascript"
		src="./js/my.js"></script>

	<script type="text/javascript">
  
  $(function(){
    $('[data-toggle="checkbox"]').simInput();
    $('[data-toggle="radio"]').simInput();
  });

</script>
<script>
$(function () {
    $('#dimAna_mediaSource_1').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            colors : [ '#d95455', '#979080','#4c88a2', '#83823f','#f0c189', '#40131a' ],
            data: [
            <%=roundData%>
                /* ['Firefox',   45.0],
                ['IE',       26.8],
                ['Chrome',   12.8],
                ['Safari',    8.5],
                ['Opera',     6.2],
                ['QQ',		13],
                ['Others',   0.7] */
            ]
        }]
    });
});			

$(function () {                                                                
    $('#dimAna_mediaSource_2').highcharts({                                           
        chart: {                                                           
            type: 'bar'                                                    
        },                                                                 
        title: {                                                           
            text: ''                    
        },                                                                 
        subtitle: {                                                        
            text: ''                                  
        },                                                                 
        xAxis: {                                                           
            categories: [<%=zhuSource%>],
            title: {                                                       
                text: null                                                 
            }                                                              
        },                                                                 
        yAxis: {                                                           
            min: 0,                                                        
            title: {                                                       
                text: '',                             
                align: 'high'                                              
            },                                                             
            labels: {                                                      
                overflow: 'justify'                                        
            }                                                              
        },                                                                 
        tooltip: {                                                         
            valueSuffix: ' '                                       
        },                                                                 
        plotOptions: {                                                     
            bar: {                                                         
                dataLabels: {                                              
                    enabled: true                                          
                }                                                          
            }                                                              
        },                                                                 
        legend: {                                                          
            layout: 'vertical',                                            
            align: 'right',                                                
            verticalAlign: 'top',                                          
            x: -40,                                                        
            y: 100,                                                        
            floating: true,                                                
            borderWidth: 1,                                                
            backgroundColor: '#FFFFFF',                                    
            shadow: true                                                   
        },                                                                 
        credits: {                                                         
            enabled: false                                                 
        },                                                                 
        series: [{                                                         
            name: '新闻个数',                                             		 
            color:'#d95455',	
            data: [<%=zhuData%>]                                   
        }]                                                                 
    });                                                                    
});
</script>

	<!--[if lt IE 8]>

<script type="text/javascript" src="/apps/yuqing/theme/red/js/ie-fix.js" ></script>

<![endif]-->

	<script type="text/javascript"
		src="./js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="./js/jquery-ui-i18n.min.js"></script>
	<script type="text/javascript"
		src="./js/jquery.yiilistview.js"></script>

</body>
</html>
