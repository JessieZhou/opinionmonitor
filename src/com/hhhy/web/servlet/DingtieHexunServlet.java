package com.hhhy.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.web.service.webservice.hexun.HexunGuba;

/**
 * Created by Ghost on 2014/9/24 0024.
 */
public class DingtieHexunServlet extends HttpServlet {
    private static final Logger logger = Logger
            .getLogger(DingtieHexunServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getParameter("url");
        url = URLDecoder.decode(url, "utf-8");
        System.out.println("url"+url);
        String content = request.getParameter("content");
        System.out.println("content"+content);
        content = URLDecoder.decode(content, "utf-8");
//        System.out.println(content);
        boolean flag = HexunGuba.HexunGubaHuiTie(url, content);
//        boolean flag = HexunGuba.dingtie(url, content);
        if (flag) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.sendRedirect("error.jsp");
        doGet(request, response);
    }
}
