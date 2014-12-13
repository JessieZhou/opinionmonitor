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

public class AuxAddServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger.getLogger(AuxAddServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long uid = (Long)request.getSession().getAttribute("userid");
        if(uid==null || uid<0){
            response.sendRedirect("loginWeb.jsp");
        }
        KeyWord keyword = (KeyWord)request.getSession().getAttribute("keyword");
        if(keyword==null){
            response.sendRedirect("keylist.jsp");
        }

        String aux = request.getParameter("auxiliary");
        aux = new String(aux.getBytes("ISO-8859-1"),"utf-8");
        logger.info("will add aux: "+aux);
        
        try {
            if(aux==null || aux.trim().equals("")){
            } else{
                KeyWord keyWord = DBUtils.getUserKeyWord(uid, keyword.getId());
                String auxiliary = keyWord.getAuxiliary();
                if(auxiliary==null || auxiliary.equals("")){
                    auxiliary = aux+";";
                }else{
                    auxiliary+=aux+";";
                }
                keyWord.setAuxiliary(auxiliary);
                DBUtils.updateUserAuxiliary(uid, keyWord.getId(), auxiliary);
                request.getSession().setAttribute("keyword",keyWord);
            }
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
