package com.hhhy.web.service.thrift;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.hhhy.common.utils.JsonUtils;
import com.hhhy.core.service.process.ProcessChain;
import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.KeyWord;
import com.hhhy.web.service.thrift.gencode.HhhyService;

/**
 * Created by lenovo on 8/10/2014.
 */
public class ThriftRequireHandler implements HhhyService.Iface{
    private static final Logger logger = Logger.getLogger(ThriftRequireHandler.class);

    @Override
    public void addArticle(String jsonString) throws TException {
        logger.info("receive article, will process...");
        Article art = JsonUtils.fromJson(jsonString, Article.class);
        // TODO: tread running using thread pool?
        ProcessChain.process(art);
        logger.info(art.getUrl() + " process over");
    }

    @Override
    public String getKeywords() throws TException {
        logger.info("receive getKeywords request, will process...");
        try {
//            List<String> words = DBUtils.getAllKeyWord();
            List<KeyWord> keywords = DBUtils.getAllKeyWordObj();
            Map<String, String> keymap = new HashMap<String, String>();
            for(KeyWord word:keywords){
                String key = word.getKeyword()+";"+word.getUid();
                keymap.put(key,word.getAuxiliary()==null?"":word.getAuxiliary());
            }
            return JsonUtils.toJson(keymap);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

	@Override
	public String getKeywordHistory() throws TException {
        try {
            return DBUtils.getHistoryKeyword();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public String getUrls() throws TException {
		// TODO Auto-generated method stub
        try {
            return DBUtils.getExternWebSite();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
}
