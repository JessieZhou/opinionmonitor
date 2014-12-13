package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

public class AuxiliaryServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = -3515602744561659250L;
    private static final Logger logger = Logger.getLogger(AuxiliaryServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        if(true)return;
        
        Long uid = (Long)request.getSession().getAttribute("userid");
        if(uid==null || uid<0){
            response.sendRedirect("loginWeb.jsp");
        }
        String kid = request.getParameter("kid");
        if(kid==null){
            response.sendRedirect("keylist.jsp");
        }
        
        try {
            KeyWord keyWord = DBUtils.getUserKeyWord(uid, Integer.parseInt(kid));
            request.getSession().setAttribute("keyword",keyWord);
            response.sendRedirect("auxiliary.jsp");
//            request.getRequestDispatcher("/auxiliary.jsp").forward(request,
//                    response);
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
