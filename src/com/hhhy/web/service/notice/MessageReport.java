package com.hhhy.web.service.notice;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.hhhy.db.beans.Article;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class MessageReport {
    private static final Logger logger = Logger.getLogger(MessageReport.class);
    private static final String sn = "SDK-BBX-010-21101";
    private static final String pwd="dfe6f-]]";
//    private static MdMmsSend mms = null;
    
    static {
//        mms = new MdMmsSend(sn, pwd);
    }
    
    public static String sendMessage(String number, String content) throws UnsupportedEncodingException{
//        String sn = "SDK-BBX-010-21101";
//        String password = "dfe6f-]]";
        String title = null;
        byte[] txt = null;
        String keyword = "您有重要舆情需要关注";
        try {
            title = new String(keyword.getBytes(), "utf-8");
            txt = content.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String txtbase64String = Base64.encode(txt);
        content = "1_1.txt," + txtbase64String;
        String stime="";
        MdMmsSend mms = new MdMmsSend(sn, pwd);
        String result = mms.mdMmsSend(title, number, content, stime);
        System.out.println("message send result: "+result);
        return result;
    }

    /**
     * @param args
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        MessageReport.sendMessage("15210837731", "升值加薪");
//        Article art = new Article();
//        art.setTitle("title");
//        art.setUrl("url");
//        art.setSummary("summ");
//        String content="标题: "+art.getTitle()+
//        "摘要: "+art.getSummary()+"\nURL: "+art.getUrl()+"\n"+"情感：负面\n\n\n";
//        System.out.println(content);
    }

}
