package com.hhhy.web.service.webservice.dfcf;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hhhy.db.beans.PostArt;

import chen.bupt.httpclient.NormalHttpClient;
import chen.bupt.httpclient.method.CHttpGet;
import chen.bupt.httpclient.method.CHttpPost;
import chen.bupt.httpclient.utils.InputStreamUtils;
import chen.bupt.httpclient.utils.ResponseUtils;

public class DFCFDingUtil {
    private static Logger logger = Logger.getLogger(DFCFDingUtil.class);
    private static final String reg01 = ".*,(.*),(.*)\\.html";
    private static final Pattern pattern01 = Pattern.compile(reg01);
    private static Random r = new Random();
    private static int next=0;
    
    private static ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
    
    public static void init(){
        logger.info("*************************************************************************1");
        scheduledThreadPool.scheduleAtFixedRate(new DingtieWorker(), 1, 5, TimeUnit.MINUTES);
    }
    
    private static class Taskpair{
        public Taskpair(String url, String cont) {
            this.url = url;
            this.cont = cont;
        }
        public String url;
        public String cont;
    }
    
    private static ConcurrentLinkedQueue<Taskpair> q = new ConcurrentLinkedQueue<Taskpair>();
    
    
    private static class DingtieWorker implements Runnable{

        @Override
        public void run() {
            logger.info("开始定时顶贴任务");
            Taskpair pair = q.poll();
            if(pair!=null){
                try {
                    logger.info("顶贴："+pair.url+" "+pair.cont);
                    dingtie2(pair.url, pair.cont);
                    logger.info("顶贴完成");
                } catch (IOException e) {
                    logger.warn("DFCF顶贴失败");
                    e.printStackTrace();
                }
            }
        }
        
    }

    private static String[] usernames = {"wuxiu9218@sina.com","abcddcba2331","15568411120","1330493726@qq.com","18610061662"};
    private static String[] psws = {"920108","password1!","cai317903131","cai317903131","pop789789"};
//    private static String[] usernames = {"晓不点"};
//    private static String[] psws = {"123456"};
//    private static String[] usernames = {"wuxiu9218@sina.com","远平灭日","洛德兰"};
//    private static String[] psws = {"920108","daichao1","daichao1"};
    private static NormalHttpClient login() throws IOException {
    	int ranInd = next;
    	next = (next+1)%usernames.length;
//        int ranInd = r.nextInt(1);
        String username = usernames[ranInd];
        String psw = psws[ranInd];

        username = URLEncoder.encode(username, "utf-8");
        psw = URLEncoder.encode(psw, "utf-8");
        System.out.println(username);
        System.out.println(psw);
        CHttpGet get = new CHttpGet(
                "http://passport.eastmoney.com/guba/AjaxAction.ashx?cb=jQuery18309460038826800883_1411024516265&op=login&dlm="+username+"&mm="+psw+"&vcode=&_=1411024534921");
        get.addHeader("Referer", "http://guba.eastmoney.com/");
        get.addHeader("Host", "passport.eastmoney.com");
        NormalHttpClient client = new NormalHttpClient();
        HttpResponse response = client.sendRequest(get.getHttpGet());
        EntityUtils.consume(response.getEntity());
        return client;
    }

    public static boolean dingtie2(String url, String cont) throws IOException {
        Matcher match01 = pattern01.matcher(url);
        String code = null;
        String topic_id = null;
        if (match01.find()) {
            code = match01.group(1);
            topic_id = match01.group(2);
        }
        if (code == null || topic_id == null)
            return false;
        NormalHttpClient client = login();

        CHttpPost post = new CHttpPost("http://guba.eastmoney.com/action.aspx");
        post.addHeader("Host", "guba.eastmoney.com");
        post.addHeader("Origin", "http://guba.eastmoney.com");
        post.addHeader("Referer", url);
        post.addHeader("X-Requested-With", "XMLHttpRequest");
        post.addFormParam("action", "review3");
        post.addFormParam("topic_id", topic_id);
        post.addFormParam("huifu_id", "");
        post.addFormParam("text", cont);
        post.addFormParam("code", code);
        post.addFormParam("yzm", "");
        post.addFormParam("yzm_id", "");
        HttpResponse response = client.sendRequest(post.getHttpPost());
        String content = InputStreamUtils.entity2String(response.getEntity());
        int status = ResponseUtils.getResponseStatus(response);
        EntityUtils.consume(response.getEntity());
        client.releaseConnection();
        if (status != 200 || !content.contains("true"))
            return false;
        return true;
    }

    public static boolean dingtie(String url, String cont) throws IOException{
        Taskpair p = new Taskpair(url, cont);
        q.offer(p);
        return true;
    }
    
    public static boolean fatie(String number, String title, String content)
            throws IOException {
        System.out.println(number);
        System.out.println(title);
        System.out.println(content);
        NormalHttpClient client = login();
        CHttpPost post = new CHttpPost("http://guba.eastmoney.com/action.aspx");
        // NormalHttpClient client = new NormalHttpClient();
        String host = "guba.eastmoney.com";
        String origin = "http://guba.eastmoney.com";
        String ref = "http://guba.eastmoney.com/list," + number + ".html";
        post.addHeader("Host", host);
        post.addHeader("Origin", origin);
        post.addHeader("Referer", ref);
        post.addHeader("X-Requested-With", "XMLHttpRequest");
        post.addFormParam("action", "add3");
        post.addFormParam("yuan_id", "0");
        post.addFormParam("title", title);
        post.addFormParam("text", content);
        post.addFormParam("code", number);
        post.addFormParam("pdf", "");
        post.addFormParam("pic", "");
        post.addFormParam("postvalid", "1");
        post.addFormParam("yzm_id", "");
        post.addFormParam("yzm", "");
        post.addFormParam("quanxian", "0");
        try {
            HttpResponse response = client.sendRequest(post.getHttpPost());
            String retC = ResponseUtils.getResponseContent(response)
                    .toLowerCase();
            int retCode = ResponseUtils.getResponseStatus(response);
            EntityUtils.consume(response.getEntity());
            client.releaseConnection();
            if (retCode == 200 && retC.contains("true")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<PostArt> getPosts(String code) throws IOException{
        String url = "http://guba.eastmoney.com/list,"+code+".html";
        NormalHttpClient client = new NormalHttpClient();
        CHttpGet httpGet = new CHttpGet(url);
        HttpResponse response = client.sendRequest(httpGet.getHttpGet());
        String content = InputStreamUtils.entity2String(response.getEntity());
        List<PostArt> arts= new ArrayList<PostArt>();
        Document doc = Jsoup.parse(content);
        Elements eles = doc.select(".articleh");
        for(Element ele:eles){
            PostArt art = new PostArt();
            String title = ele.select(".l3").select("a").attr("title");
            String url2 = "http://guba.eastmoney.com/"+ele.select(".l3").select("a").attr("href");
            art.setTitle(title);
            art.setUrl(url2);
            arts.add(art);
        }
        return arts;
    }
    
    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
//         dingtie("http://guba.eastmoney.com/news,cjpl,123781934.html","顶3");
//        System.out.println("=======================");
//        System.out.println(fatie("002314", "标题要长啊啊啊啊啊啊啊啊啊啊啊啊啊22", "大家好"));
//      List<PostArt> posts= getPosts("002314");
//      for(PostArt post:posts){
//          System.out.println(post.getTitle()+": "+post.getUrl());
//      }
    	for(int i=0;i<usernames.length;i++){
        	
    		System.out.println(dingtie2("http://guba.eastmoney.com/news,601225,130150568.html","额，不太对"));
        	
        	Thread.sleep(10000);
    	}
    }

}
