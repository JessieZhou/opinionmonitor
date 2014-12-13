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

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.KeyWord;

/**
 * 关键词的添加，删除
 * 
 * @author chenlingpeng
 * 
 */
public class KeyWordAddServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(KeyWordAddServlet.class);
    private static final long serialVersionUID = 1L;

    /* 
     * 对应关键词的增删操作
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        Long uid = (Long)req.getSession().getAttribute("userid");
        if(uid==null){
            resp.sendRedirect("loginWeb.jsp");
            return;
        }
        String keyword = req.getParameter("keyword");
        if(keyword==null || keyword.trim().equals("")){
            // TODO: 不应该出现这种情况，页面检查是否空值
            resp.sendRedirect("keylist.jsp");
        }else{
            keyword = new String(keyword.getBytes("ISO-8859-1"), "utf-8");
            try {
                boolean flag = DBUtils.addUserKeyWord(uid, keyword);
                logger.info("add keyword: uid("+uid+"), keyword("+keyword+")");
//                if(flag)
                List<KeyWord> keyWords = DBUtils.getUserKeyWord(uid);
                Collections.sort(keyWords);
                req.getSession().setAttribute("keywords", keyWords);
                resp.sendRedirect("keylist.jsp");
            } catch (SQLException e) {
                logger.warn(e.getMessage());
                resp.sendRedirect("keylist.jsp");
            }
        }
        
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
