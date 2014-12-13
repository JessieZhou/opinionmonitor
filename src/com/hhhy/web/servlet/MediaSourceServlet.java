package com.hhhy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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
public class MediaSourceServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MediaSourceServlet.class);

    private static final long serialVersionUID = 1L;

    /* 
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        Integer kid = (Integer)req.getSession().getAttribute("kid");
        try {
            if(kid!=null){
                Map<String, Integer> mediaStatis = DBUtils.getMediaSourceStatis(kid);
                req.getSession().setAttribute("mediaStatis", mediaStatis);
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        resp.sendRedirect("dimAna_mediaSource.jsp");
//        req.getRequestDispatcher("/dimAna_mediaSource.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        doGet(req, resp);
    }

}
