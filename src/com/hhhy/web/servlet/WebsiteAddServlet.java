package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;
import com.hhhy.db.beans.WebSite;

public class WebsiteAddServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger.getLogger(WebsiteAddServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long uid = (Long)request.getSession().getAttribute("userid");
        if(uid==null || uid<0){
            response.sendRedirect("loginWeb.jsp");
        }
        String website = request.getParameter("website");
        if(website!=null){
            website = website.trim();
            if(website!=""){
                if(website.startsWith("http://")){
                    website = website.substring(7);
                }
                if(website.endsWith("/")){
                    website = website.substring(0,website.length()-1);
                }
                try{
                    DBUtils.insertWebSite(website);
                    List<WebSite> webs = DBUtils.getExternWebSiteList();
                    request.getSession().setAttribute("webs",webs);
                } catch (SQLException e) {
                    logger.warn(e.getMessage());
                }
            }
        }
        response.sendRedirect("addUrl.jsp");
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
