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

public class SummarizeServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SummarizeServlet.class);

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
        if(kid==null || "".equals(kid)) response.sendRedirect("login.jsp");
        try {
            int[] counts = DBUtils.getEmotionStatisCount(Integer.parseInt(kid));
            request.getSession().setAttribute("poscount", counts[0]);
            request.getSession().setAttribute("negcount", counts[1]);
            request.getSession().setAttribute("plaincount", counts[2]);
            
            int[] counts2 = DBUtils.getTodayEmotionStatisCount(Integer.parseInt(kid));
            request.getSession().setAttribute("poscountToday", counts2[0]);
            request.getSession().setAttribute("negcountToday", counts2[1]);
            request.getSession().setAttribute("plaincountToday", counts2[2]);

            List<Article> arts = DBUtils.getNegArticles(Integer.parseInt(kid));
            request.getSession().setAttribute("negarts", arts);
            List<Article> importArts = DBUtils.getRecentArticles(Integer.parseInt(kid));
            request.getSession().setAttribute("importarts", importArts);
            String keyword = DBUtils.getKeyWordById(Integer.parseInt(kid));
            request.getSession().setAttribute("keyword",keyword);
            response.sendRedirect("sentimentSummarize.jsp");
            // request.getRequestDispatcher("/sentimentSummarize.jsp").forward(request, response);
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
