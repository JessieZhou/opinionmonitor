package com.hhhy.web.service.notice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class TestMdMmsSend {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String sn = "SDK-BBX-010-21101";
        String password = "dfe6f-]]";
        String mobile = "15210835484";
        String pathjpg = "d:\\1.png";
        String title = null;
        byte[] txt = null;
        try {
            title = new String("彩信标题".getBytes(), "utf-8");
            txt = "hello彩信内容".getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        
        File filejpg = new File(pathjpg);
        byte[] tempbytejpg = null;
        try {
            InputStream fileInputStreamjpg = new FileInputStream(filejpg);
            tempbytejpg = new byte[fileInputStreamjpg.available()];
            fileInputStreamjpg.read(tempbytejpg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jpgbase64String = Base64.encode(tempbytejpg);
        String txtbase64String = Base64.encode(txt);
        
        String content = "1_1.txt," + txtbase64String;
        String stime="";
        MdMmsSend mms = new MdMmsSend(sn, password);
        String result = mms.mdMmsSend(title, mobile, content, stime);
        System.out.println(result);
    }

}
