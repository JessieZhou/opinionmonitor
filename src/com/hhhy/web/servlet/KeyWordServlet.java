package com.hhhy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 关键词的添加，删除
 * 
 * @author chenlingpeng
 * 
 */
public class KeyWordServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(KeyWordServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     * 对应关键词的增删操作
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        // 有一个隐藏域是type
//        http://bbs.csdn.net/topics/390586006
        String type = req.getParameter("type");
        if (type == null) {
            logger.error("type missing");
        } else {
            if(type.equals("add")){
//                TODO: 添加关键词，然后列出现有关键词
            }else if(type.equals("delete")){
//                TODO: 删除关键词，然后列出现有关键词
            }else if(type.equals("list")){
//                TODO: 列出关键词
            }
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
