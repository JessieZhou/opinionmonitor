package com.hhhy.core.service.process;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;

// duplicated
public class StorageProcessor {
    private static final Logger logger = Logger.getLogger(StorageProcessor.class);

    public static long storage(Article art) {
        long id = -1l;
         try {
            id = DBUtils.isArticleExist(art.getUrl());
            if(id<0){
                id = DBUtils.insertArticle(art);
            }
        } catch (SQLException e) {
            logger.warn("can't insert article with url: "+art.getUrl());
            logger.warn(e);
        }
        return id;
    }
}
