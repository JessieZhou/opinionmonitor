package com.hhhy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @author chenlingpeng
 * 
 */
public class SearchServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SearchServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     * 对应关键词的增删操作
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        String start = req.getParameter("start_date");
        String end = req.getParameter("end_date");
        String[] merge = req.getParameterValues("merge");
        String source = req.getParameter("source");
        String sentiment = req.getParameter("sentiment");
        String kid = req.getParameter("topic_id");
        String include = req.getParameter("include");
        String any = req.getParameter("any");
        String exclude = req.getParameter("exclude");
        String position = req.getParameter("position");
        String perpage = req.getParameter("perpage");
        
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
