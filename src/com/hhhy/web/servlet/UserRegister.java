package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;

import com.hhhy.common.utils.StringUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.User;

public class UserRegister extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(UserRegister.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        String repass = request.getParameter("rePassword");
        if (!(StringUtils.notEmpty(email) && StringUtils.notEmpty(password) && StringUtils
                .notEmpty(repass))) {
            request.getSession().setAttribute("regerror", "用户名密码不能为空");
            response.sendRedirect("registerWeb.jsp");
            return;
        }
        if (!password.equals(repass)) {
            request.getSession().setAttribute("regerror", "密码不一致");
            response.sendRedirect("registerWeb.jsp");
            return;
        }
        if(!EmailValidator.getInstance().isValid(email)){
            request.getSession().setAttribute("regerror", "邮箱不合法");
            response.sendRedirect("registerWeb.jsp");
            return;
        }
        try {
            boolean flag = DBUtils.checkUserExist(email);
            if (flag) {
                request.getSession().setAttribute("regerror", "邮箱已存在");
                response.sendRedirect("registerWeb.jsp");
                return;
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            request.getSession().setAttribute("regerror", "后台数据库出错，请重试");
            response.sendRedirect("registerWeb.jsp");
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        try {
            long uid = DBUtils.createUser(user);
            request.getSession().setAttribute("userid", uid);
            request.getSession().setAttribute("name", email);
            request.getSession().removeAttribute("regerror");
            response.sendRedirect("keylist.jsp");
            // request.getRequestDispatcher("/keylist.jsp").forward(request,
            //         response);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            request.getSession().setAttribute("regerror", "创建用户时后台数据库出错，请重试");
            response.sendRedirect("registerWeb.jsp");
            return;
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
