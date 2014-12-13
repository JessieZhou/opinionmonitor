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
import com.hhhy.db.beans.Article;

public class SpecMonitorServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SpecMonitorServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String kid = (String)request.getParameter("kid");
        if(kid==null || "".equals(kid)){
            Integer k = (Integer)request.getSession().getAttribute("kid");
            if(k!=null){
                kid=k+"";
            }
        }else{
            request.getSession().setAttribute("kid", Integer.parseInt(kid));
        }

        logger.info("will summarize for kid: "+kid);
        if(kid==null || "".equals(kid)) response.sendRedirect("loginWeb.jsp");
        try {
            // TODO: 需要加所有舆情文章
            String time = request.getParameter("today");
            String trend = request.getParameter("trend");
            if(time==null) time = "0";
            if(trend==null) trend="0";
            List<Article> arts = DBUtils.getUserArticleWithFilter(Integer.parseInt(kid), time, trend);
            
//            List<Article> arts = DBUtils.getUserArticle(Integer.parseInt(kid));
            request.getSession().setAttribute("all", arts);
            response.sendRedirect("sentimentMonitor.jsp");
//            request.getRequestDispatcher("/sentimentMonitor.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
            response.sendRedirect("keylist.jsp");
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            response.sendRedirect("keylist.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
