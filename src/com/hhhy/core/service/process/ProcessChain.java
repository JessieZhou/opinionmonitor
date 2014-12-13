package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hhhy.common.utils.DateFormatUtils;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;

public class ProcessChain {
	private static final Logger logger = Logger.getLogger(ProcessChain.class);

	public static void process(Article art) {
		// logger.info("split word for article: "+art.getUrl());
		if (art.getTitle().length() > 50) {
			art.setTitle(art.getTitle().substring(0, 45) + "...");
		}
		if (art.getUrl().length() > 200)
			return;

		 if(!DateFormatUtils.isToday(art.getTime())){
			 logger.info("process as history");
			 HistoryProcessor.process(art);
			 return;
		 }

		long id = -1l;
		try {
			id = DBUtils.isArticleExist(art.getUrl());
			if (id <= 0) {
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
				id = DBUtils.insertArticle(art);
				if (id <= 0) {
					logger.info("insert error: " + art.getUrl());
					return;
				}

				art.setId(id);
				logger.info("artid: " + art.getId());
				// no need for repeat index if url already exist
				// TODO
				// IndexProcessor.addIndex(art);
				if (score != 0 && art.getType() == 1) { // only report the first
														// time for news
					ReportProcessor.reportProcess(art);
				}
			} else {
				logger.info("duplicated url: " + art.getUrl());
			}
		} catch (SQLException e) {
			logger.warn("can't insert article with url: " + art.getUrl());
			logger.warn(e);
		}

		if (art.getId() <= 0) {
			return;
		}

		StatisticsProcessor.statistics(art);
	}
}
