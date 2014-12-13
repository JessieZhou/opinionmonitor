package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

public class KeylistServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = -3515602744561659250L;
    private static final Logger logger = Logger.getLogger(KeylistServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long uid = (Long)request.getSession().getAttribute("userid");
        if(uid==null || uid<0){
            response.sendRedirect("loginWeb.jsp");
        }
        
        try {
           
            request.getSession().removeAttribute("loginerror");
            List<KeyWord> keyWords = DBUtils.getUserKeyWord(uid);
            Collections.sort(keyWords);
            request.getSession().setAttribute("keywords", keyWords);
            response.sendRedirect("keylist.jsp");
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
