package com.hhhy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import com.hhhy.web.service.thrift.ThriftServer;
import com.hhhy.web.service.webservice.dfcf.DFCFDingUtil;

/**
 * 
 * @author chenlingpeng
 *
 */
public class Log4jServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        String path = getInitParameter("log4j.properties");
        path = getServletContext().getRealPath(path);
        PropertyConfigurator.configure(path);
        System.out.println(path);
        DFCFDingUtil.init();
        ThriftServer.start();
    }

}
