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

import com.hhhy.common.utils.StringUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

public class UserLogin extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = -3515602744561659250L;
    private static final Logger logger = Logger.getLogger(UserLogin.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        if(true)return;
        
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        if (!(StringUtils.notEmpty(username) && StringUtils.notEmpty(password))) {
            request.getSession().setAttribute("loginerror", "邮箱密码不能为空");
            response.sendRedirect("loginWeb.jsp");
//            request.getRequestDispatcher("/loginWeb.jsp").forward(request,
//                    response);
            return;
        }
        logger.info("login request: "+username+", "+password);
        
        try {
            long uid = DBUtils.loginCheck(username, password);
            if(uid<0){
                request.getSession().setAttribute("loginerror", "账号或密码错误，请重新登陆");
                logger.info("login error: "+username);
                response.sendRedirect("loginWeb.jsp");
                return;
            }
            request.getSession().removeAttribute("loginerror");
            request.getSession().setAttribute("userid", uid);
            request.getSession().setAttribute("name", username);
            List<KeyWord> keyWords = DBUtils.getUserKeyWord(uid);
            Collections.sort(keyWords);
//            request.setAttribute("keywords", keyWords);
            request.getSession().setAttribute("keywords", keyWords);
//            if(keyWords.size()>0)
//                logger.info("get keyword: "+keyWords.get(0).getKeyword());
//            request.getRequestDispatcher("/keylist.jsp").forward(request,
//                    response);
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
