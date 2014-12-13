package com.hhhy.crawler.www_eeo_com_cn;

import com.hhhy.crawler.finance_ifeng_com.tool;

import com.hhhy.crawler.util.GetHTML;
import org.jsoup.nodes.Element;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-6-9
 * Time: 上午11:15
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://app.eeo.com.cn/?app=search&controller=index&action=searchtitle";
    public final String keyWordsLocation = "./keyWords.txt";
    public ArrayList<String> spyHistory;//the history record got earlier in today.
    public ArrayList<String> keyWordsList;
    Controller(){
        spyHistory = new ArrayList<String>();
        keyWordsList = new ArrayList<String>();
    }

    public void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HashMap<String,String> param = new HashMap<String, String>();
        param.put("a","");
        param.put("t",transKey);
        param.put("搜 索","搜 索");
        String html = GetHTML.postHtml("http://app.eeo.com.cn/?app=search&controller=index&action=searchtitle","UTF-8",param);
        System.out.println(html);

      /*  html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        String flag = document.select("div.mainContent").select("div.mainM").select("h1").text();
        if(flag.equals("找不到和您的")){
            //Todo ??
            System.out.println("没找到");
        }
        else{
            Elements tableEles = document.select("div.mainContent").select("div.mainM").select("div.searchResults");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }*/
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("p").first().text();
            if(!this.spyHistory.contains(title)){
                String orgTime = ele.select("p").get(2).text();
                String time = tool.getTimeStr(orgTime);
                String summary = ele.select("p").get(1).text();
                String url = ele.select("p").first().select("a").attr("href");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"经济观察网");
                System.out.println("----------------");
                spyHistory.add(title);
                //调接口~~~~~
            }
        }
    }
    public static void main(String[] args){
        Controller controller = new Controller();
        controller.parseBoard("国债", "");
    }
}
