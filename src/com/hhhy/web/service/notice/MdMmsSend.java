package com.hhhy.web.service.notice;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MdMmsSend {
    
    /**发送彩信的web service服务器地址*/
    private static final String SERVER_URL = "http://sdk3.entinfo.cn:8060/webservice.asmx";
    /**序列号*/
    private String sn = null;
    /**序列号加密码经MD5加密后的32位大写字符串*/
    private String pwd = null;
    
    /**构造方法*/
    public MdMmsSend(String sn,String password) {
        this.sn = sn;
        this.pwd = getMD5(sn + password);
    }
    
    /**
     * 序列号加密码32位MD5加密方法
     * @param sourceStr 序列号加密码的字符串
     * @return 加密后的字符串
     */
    public String getMD5(String sourceStr) {
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            // resultStr = new String(md5.digest());
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'A', 'B', 'C', 'D', 'E', 'F' };
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
            return resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 发彩信方法
     * @param title 标题，GBK编码
     * @param mobile 手机号，多个用英文逗号隔开
     * @param content 内容，参照文档编辑内容格式
     * @param stime 定时时间，例2013-04-03 12:23:32
     * @return 成功返回长字符串，失败返回负数
     */
    public String mdMmsSend(String title,String mobile,String content,String stime) {
        String result = null;
        String soapAction = "http://tempuri.org/mdMmsSend";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        xml += "<soap12:Body>";
        xml += "<mdMmsSend xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + pwd + "</pwd>";
        xml += "<title>" + title + "</title>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<stime>" + stime + "</stime>";
        xml += "</mdMmsSend>";
        xml += "</soap12:Body>";
        xml += "</soap12:Envelope>";
        
        URL url;
        try {
            url = new URL(SERVER_URL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes("GBK"));
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<mdMmsSendResult>(.*)</mdMmsSendResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
