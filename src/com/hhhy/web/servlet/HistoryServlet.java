package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.HistoryBean;

public class HistoryServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger.getLogger(HistoryServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Integer kid = (Integer)request.getSession().getAttribute("kid");
    	try {
            if(kid!=null){
            	String start_date = request.getParameter("start_date");
                String end_date = request.getParameter("end_date");

                long begin=0l;
                long end = System.currentTimeMillis();
                if(start_date!=null && end_date!=null){
                	request.getSession().setAttribute("start_date",start_date);
                	request.getSession().setAttribute("end_date",end_date);
                	try {
                        begin = DateFormatUtils.getTime(start_date,
                                DateFormatUtils.yyyyMMdd2);
                         end = DateFormatUtils.getTime(end_date,
                                DateFormatUtils.yyyyMMdd2);
                        end+=24*60*60*1000-1;
                    } catch (ParseException e) {
                        logger.warn(e.getMessage());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
               List<HistoryBean> beans  = DBUtils.getHistoryBeans(kid, begin, end);
                logger.info("history size: dateSize: ");
                // TODO: test needed
                	request.getSession().setAttribute("history", beans);
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    	response.sendRedirect("historyNews.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
