package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.StringUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

public class StockAddServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger.getLogger(StockAddServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long uid = (Long)request.getSession().getAttribute("userid");
        if(uid==null || uid<0){
            response.sendRedirect("loginWeb.jsp");
        }
        
        String stockName = request.getParameter("shareName");
        String stockNum = request.getParameter("shareNum");
        stockName = new String(stockName.getBytes("ISO-8859-1"),"utf-8");
        stockNum = new String(stockNum.getBytes("ISO-8859-1"),"utf-8");
        if(!(StringUtils.notEmpty(stockName) && StringUtils.notEmpty(stockNum))){
            request.getSession().setAttribute("stockerror", "不能有空值");
            response.sendRedirect("dtAll.jsp");
            return;
        }
        if(!StringUtils.isNumber(stockNum)){
            request.getSession().setAttribute("stockerror", "股票代号错误");
            response.sendRedirect("dtAll.jsp");
            return;
        }
        
        request.getSession().removeAttribute("stockerror");
        
        try {
            DBUtils.addStock(stockName, stockNum, uid);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        response.sendRedirect("dtAll.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
