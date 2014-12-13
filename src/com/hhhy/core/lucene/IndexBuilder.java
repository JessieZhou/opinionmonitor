package com.hhhy.core.lucene;

import com.hhhy.db.beans.Article;

public interface IndexBuilder {
    
    public void addArticle(Article art);

    public Article queryArticle(String queryStr);

}
