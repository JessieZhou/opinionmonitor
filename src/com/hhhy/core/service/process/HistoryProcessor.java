package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.HistoryBean;

public class HistoryProcessor {
	private static final Logger logger = Logger.getLogger(HistoryProcessor.class);
	
	public static void process(Article art){
		int score = 0;
		int titleScore = 0;
		String key = art.getKeyword();
		if (key.length() > 2)
			key = key.substring(0, 2);
		if (!art.getTitle().contains(key)) {
			art.setEmotion(0);
		} else {
			score = EmotionAnalysisProcessor.unSegEmotionParser(art
					.getTitle());
			if (score == 0) {
				List<String> titleWords = WordSplitProcessor.split(art
						.getTitle());
				titleScore = EmotionAnalysisProcessor
						.emotionParser(titleWords);
				if (titleScore < 0) {
					score = titleScore * 2;
				} else if (titleScore > 0) {
					score = titleScore;
				} else {
					List<String> contentWords = WordSplitProcessor
							.split(art.getSummary());
					int contentScore = EmotionAnalysisProcessor
							.emotionParser(contentWords);
					score = contentScore;
				}
			}

			art.setEmotion(score);
		}
		try {
			int kid = StatisticsProcessor.getKidFromCache(art.getKeyword());
			if(kid==-1) return;
			HistoryBean bean = new HistoryBean(kid,art.getTime(), art.getTitle(), art.getUrl());
			bean.setEmotion(art.getEmotion());
			bean.setSource(art.getWebsite());
			bean.setSummary(art.getSummary());
			DBUtils.insertHistoryArticle(bean);
		} catch (SQLException e) {
			logger.warn(e.getMessage());
			logger.warn(e);
			e.printStackTrace();
		}
		
	}

}
