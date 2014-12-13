package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;

/**
 * 
 * @author chenlingpeng
 * 
 */
public class PhoneSetServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PhoneSetServlet.class);

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        String enable = req.getParameter("SendForm[enable_warn]");
        String phone = req.getParameter("SendForm[warn_shortMessage]");
        Long uid = (Long)req.getSession().getAttribute("userid");
        if(uid==null){
            resp.sendRedirect("loginWeb.jsp");
            return;
        }
        try {
            DBUtils.updateWarnPhone(phone, enable, uid);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        resp.sendRedirect("shortMessageSet.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
