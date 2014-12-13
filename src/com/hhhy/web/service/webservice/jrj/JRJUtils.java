package com.hhhy.web.service.webservice.jrj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
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

public class JRJUtils {
    
    
    public static DefaultHttpClient login(String name, String psw) throws IOException{
        NormalHttpClient client = new NormalHttpClient();
        CHttpPost post = new CHttpPost("http://sso.jrj.com.cn/sso/ssologin");
//        post.addHeader("Host", "sso.jrj.com.cn");
//        post.addHeader("Origin", "http://sso.jrj.com.cn");
//        post.addHeader("Referer", "http://sso.jrj.com.cn/sso/ssologin?ReturnURL=http://www.jrj.com.cn");
        post.addFormParam("isVerifyCode","false");
        post.addFormParam("ReturnURL","http://www.jrj.com.cn");
        post.addFormParam("LoginID",name);
        post.addFormParam("Passwd",psw);
        HttpResponse respones = client.sendRequest(post.getHttpPost());
        System.out.println(ResponseUtils.getResponseStatus(respones));
        System.out.println(ResponseUtils.getRedirectionURL(respones));
        System.out.println(ResponseUtils.getResponseContent(respones));
        System.out.println(client.getCookie());
        return client.getHttpClient();
    }
    
    public static boolean fatie(String title, String content, String code) throws IOException{
        NormalHttpClient client = new NormalHttpClient();
//        DefaultHttpClient client = login("ghostghostinmatrix","password1");
//        client.setCookieStore(null);
        CHttpPost post = new CHttpPost("http://istock.jrj.com.cn/topicaddsingle.jspa");
        post.addFormParam("anonym","");
        post.addFormParam("forumid",code);
        post.addFormParam("upfilename","");
        post.addFormParam("upfilelink","");
        post.addFormParam("Detail",content);
        post.addFormParam("upfilepath","");
        post.addFormParam("showMessage","0");
        post.addFormParam("Title",title);
        post.addFormParam("detail","");
//        post.addFormParam("isShow","on");
        post.setUrlEncoder("GBK");
        HttpResponse respones = client.sendRequest(post.getHttpPost());
//        HttpResponse respones = client.execute(post.getHttpPost());
        System.out.println(ResponseUtils.getResponseStatus(respones));
//        System.out.println(ResponseUtils.getRedirectionURL(respones));
        System.out.println(InputStreamUtils.entity2String(respones.getEntity(), "GBK"));
        return true;
    }
    
    private static final String reg01 = ".*,(.*),(.*)\\.html";
    private static final Pattern pattern01 = Pattern.compile(reg01);
    
    public static boolean dingtie(String url, String content) throws IOException{
        Matcher match01 = pattern01.matcher(url);
        String code = null;
        String topic_id = null;
        if (match01.find()) {
            code = match01.group(1);
            topic_id = match01.group(2);
        }
        if (code == null || topic_id == null)
            return false;
        NormalHttpClient client = new NormalHttpClient();
        CHttpPost post = new CHttpPost("http://istock.jrj.com.cn/postadd.jspa");
        post.addFormParam("anonym","0");
        post.addFormParam("forumid",code);
        post.addFormParam("TopicID",topic_id);
        post.addFormParam("hiddenYinYong","");
        post.addFormParam("Detail",content);
        post.addFormParam("upfilepath","");
        post.addFormParam("upfilelink","");
        post.addFormParam("upfilename","");
        post.addFormParam("Title","嘿嘿");
//        post.addFormParam("Title",title);
//        post.addFormParam("detail","");
        post.setUrlEncoder("GBK");
        HttpResponse respones = client.sendRequest(post.getHttpPost());
        System.out.println(ResponseUtils.getResponseStatus(respones));
//        System.out.println(ResponseUtils.getRedirectionURL(respones));
        System.out.println(InputStreamUtils.entity2String(respones.getEntity(), "GBK"));
        return true;
    }
    
    public static List<PostArt> getPosts(String code) throws IOException{
        String url = "http://istock.jrj.com.cn/list,"+code+".html";
        NormalHttpClient client = new NormalHttpClient();
        CHttpGet httpGet = new CHttpGet(url);
        HttpResponse response = client.sendRequest(httpGet.getHttpGet());
        String content = InputStreamUtils.entity2String(response.getEntity(), "gb2312");
//        System.out.println(content);
        List<PostArt> arts= new ArrayList<PostArt>();
        Document doc = Jsoup.parse(content);
//        Element e = doc.select("tbody").first();
        Elements items = doc.select("tr[name=titlehb]");
//        System.out.println(items.size());
//        System.out.println(doc.select("tbody").size());
        for(int i=0;i<items.size();i++){
            Element item = items.get(i);
            String title = item.select(".tl").select("a").text();
            String purl = item.select(".tl").select("a").attr("href");
            PostArt art = new PostArt();
            art.setTitle(title);
            art.setUrl(purl);
            arts.add(art);
        }
        return arts;
    }
    
    public static void main(String[] args) throws IOException{
//        DefaultHttpClient client = login("ghostghostinmatrix2","password1");
        fatie("雅致股份不错title222","content","002314");
//        dingtie("http://istock.jrj.com.cn/article,002314,26765324.html","哈哈对");
//        List<PostArt> posts= getPosts("002314");
//        for(PostArt post:posts){
//            System.out.println(post.getTitle()+": "+post.getUrl());
//        }
    }

}
