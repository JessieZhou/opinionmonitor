package com.hhhy.core.lucene;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.LockObtainFailedException;

import com.hhhy.db.beans.Article;

public class RAMIndexBuilder implements IndexBuilder{
    private static final Logger logger = Logger.getLogger(RAMIndexBuilder.class);

    public static void main(String[] args) throws CorruptIndexException,
            LockObtainFailedException, IOException, ParseException {
        RAMIndexBuilder b = new RAMIndexBuilder();
    }

    @Override
    public void addArticle(Article art) {
        
    }

    @Override
    public Article queryArticle(String queryStr) {
        return null;
    }

    
}
