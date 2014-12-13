package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;

/**
 * 
 * @author chenlingpeng
 * 
 */
public class EmailSetServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmailSetServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        String enable = req.getParameter("SendForm[enable_warn]");
        String email = req.getParameter("SendForm[warn_mail]");
        if(!EmailValidator.getInstance().isValid(email)){
            req.getSession().setAttribute("emailseterror", "邮箱不合法");
            resp.sendRedirect("emailSet.jsp");
            return;
        }
        Long uid = (Long)req.getSession().getAttribute("userid");
        if(uid==null){
            resp.sendRedirect("loginWeb.jsp");
            return;
        }
        try {
            DBUtils.updateWarnMail(email, enable, uid);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        resp.sendRedirect("emailSet.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
