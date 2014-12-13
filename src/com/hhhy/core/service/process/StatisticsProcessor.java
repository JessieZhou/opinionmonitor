package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.KeyWordPage;

/**
 * 关键词各项统计
 * 
 * @author chenlingpeng
 * 
 */
public class StatisticsProcessor {
    private static final Logger logger = Logger
            .getLogger(StatisticsProcessor.class);
    private static Map<String, Integer> keywordsCache = new ConcurrentHashMap<String, Integer>();

    public static void statistics(Article art) {
        // statisticsOpinionType(art);
        // statisticsSrcType(art);
        // statisticsMediaType(art);
        String keyword = art.getKeyword();
        try {
            int kid = getKidFromCache(keyword);
            if(kid==-1) return;
            KeyWordPage trend = new KeyWordPage();
            trend.setPid(art.getId());
            trend.setCtime(art.getTime());
            trend.setEmotion(art.getEmotion());
            trend.setKid(kid);
            trend.setType(art.getType());
            trend.setUrl(art.getUrl());
            trend.setWebsite(art.getWebsite());
            if (!DBUtils.addTrend(trend)) {
                logger.info("duplicate trend: " + trend.getKid() + " "
                        + trend.getPid());
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }

    }

    public static int getKidFromCache(String keyword) throws SQLException{
    	Integer kid = keywordsCache.get(keyword);
        if (kid == null) {
            kid = DBUtils.getKeyWordId(keyword);
        }
        if (kid == null || kid <= 0)
            return -1;
        keywordsCache.put(keyword, kid);
        return kid;
    }
}
