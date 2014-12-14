package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;
import com.hhhy.db.beans.item.HistoryKeyword;

public class HistoryAddServlet extends HttpServlet {
    /**
     * 
     */
    private static final Logger logger = Logger
            .getLogger(HistoryAddServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long uid = (Long) request.getSession().getAttribute("userid");
        if (uid == null || uid < 0) {
            response.sendRedirect("loginWeb.jsp");
        }

        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        if (start_date == null || end_date == null) {
            String kid = request.getParameter("kid");
            String keyword = null;
            HistoryKeyword word = null;
            try {
                keyword = DBUtils.getKeyWordById(Integer.parseInt(kid));
                if (keyword != null)
                    word = DBUtils.getHistoryKeyword(keyword);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (word != null) {
                request.getSession().setAttribute(
                        "start_date",
                        DateFormatUtils.formatTime(word.getBegin(),
                                DateFormatUtils.yyyyMMdd2));
                request.getSession().setAttribute(
                        "end_date",
                        DateFormatUtils.formatTime(word.getEnd(),
                                DateFormatUtils.yyyyMMdd2));
            } else {
                request.getSession().setAttribute(
                        "start_date",
                        DateFormatUtils.formatTime(System.currentTimeMillis(),
                                DateFormatUtils.yyyyMMdd2));
                request.getSession().setAttribute(
                        "end_date",
                        DateFormatUtils.formatTime(System.currentTimeMillis(),
                                DateFormatUtils.yyyyMMdd2));
                request.getSession().setAttribute("unset", "1");

            }
            request.getSession().setAttribute("kid", Integer.parseInt(kid));
            response.sendRedirect("historySet.jsp");
            return;
        }
        request.getSession().setAttribute("start_date", start_date);
        request.getSession().setAttribute("end_date", end_date);

        try {
            long start = DateFormatUtils.getTime(start_date,
                    DateFormatUtils.yyyyMMdd2);
            long end = DateFormatUtils.getTime(end_date,
                    DateFormatUtils.yyyyMMdd2);
            end += 24 * 60 * 60 * 1000 - 1;
            int kid = (Integer) request.getSession().getAttribute("kid");
            KeyWord keyword = DBUtils.getKeyWordById(kid,uid);
            DBUtils.addHistoryKeyword(keyword, start, end);
            request.getSession().setAttribute("setinfo", "设置成功");
        } catch (ParseException e) {
            logger.warn(e.getMessage());
            request.getSession().setAttribute("setinfo", "设置失败");
        } catch (SQLException e) {
            request.getSession().setAttribute("setinfo", "设置失败");
            e.printStackTrace();
        }
        response.sendRedirect("historySet.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.sendRedirect("error.jsp");
        doGet(request, response);
    }

}
