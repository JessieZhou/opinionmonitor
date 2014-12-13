package com.hhhy.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.web.service.webservice.jrj.JRJUtils;

public class DingtieJRJServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger
            .getLogger(DingtieJRJServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getParameter("url");
        url = URLDecoder.decode(url, "utf-8");
        String content = request.getParameter("content");
        System.out.println(content);
        content = URLDecoder.decode(content, "utf-8");
        System.out.println(content);
        boolean flag = JRJUtils.dingtie(url, content);
//        boolean flag = DFCFDingUtil.dingtie(url, content);
        if(flag){
            response.getWriter().write("success");
        }else{
            response.getWriter().write("fail");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
