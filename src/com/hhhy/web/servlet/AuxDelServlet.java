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

public class AuxDelServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger.getLogger(AuxDelServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        if(true)return;
        
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
        logger.info("will del aux: "+aux);
        
        try {
            KeyWord keyWord = DBUtils.getUserKeyWord(uid, keyword.getId());
            String auxiliary = keyWord.getAuxiliary();
            String[] tmps = auxiliary.split(";");
            String aux2 = "";
            for(String tmp:tmps){
                if(!tmp.equals(aux)){
                    aux2+=tmp+";";
                }
            }
            keyWord.setAuxiliary(aux2);
            DBUtils.updateUserAuxiliary(uid, keyWord.getId(), aux2);
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
