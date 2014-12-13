package com.hhhy.web.service.webservice.dfcf;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hhhy.db.beans.PostArt;
import com.hhhy.web.service.webservice.HttpConnectionManager;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JessieZhou on 9/18/2014.
 */
public class DFCFCrawler {

    private String urlBase = "http://guba.eastmoney.com";
    private List<PostArt> posts;
    
    public DFCFCrawler(){
    	posts = new ArrayList<PostArt>();
    }
    
    
    public void parserDFCF(){
        HttpClient client = HttpConnectionManager.getHttpClient();
        HttpGet get = new HttpGet(urlBase);
        try {
            HttpResponse getResponse = client.execute(get);
            if(getResponse.getStatusLine().getStatusCode() != 200) return;
            String s = EntityUtils.toString(getResponse.getEntity());
            Document doc = Jsoup.parse(new String(s.getBytes("ISO-8859-1"),"utf-8"));
            Elements es = doc.getElementsByClass("newlist");
//            PostArt post = new PostArt();
            for(Element e : es){
//                System.out.println(e);
                Elements uls = e.select("li");
                for(Element ul : uls) {
//                    System.out.println(ul.text());
                	PostArt post = new PostArt();
                	post.setTitle(ul.select("a.note").attr("title"));
                	post.setUrl(urlBase + ul.select("a.note").attr("href"));
                	posts.add(post);
                    //System.out.println(ul.select("a.note").attr("title"));
                    //System.out.println( urlBase + ul.select("a.note").attr("href"));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public List<PostArt> getPosts(){
    	return posts;
    }
    public static void main(String[] args) {
        DFCFCrawler dfcf = new DFCFCrawler();
        dfcf.parserDFCF();
        System.out.println(dfcf.getPosts().size());
    }
}
