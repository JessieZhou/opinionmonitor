package com.hhhy.web.service.webservice.dfcf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.EntityUtils;

import chen.bupt.httpclient.NormalHttpClient;
import chen.bupt.httpclient.method.CHttpGet;
import chen.bupt.httpclient.utils.ResponseUtils;

public class DFCFUtils {
    private static final String base = "http://guba.eastmoney.com/search_bar.aspx?search_content=";
    public static String saerchGuba(String name){
        return null;
    }
    
    public static void main(String[] args){
        saerchGuba("雅致股份");
    }
    
    
}
