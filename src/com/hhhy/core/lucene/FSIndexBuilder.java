package com.hhhy.core.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.ansj.lucene4.AnsjAnalysis;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.hhhy.common.utils.StopWordsUtils;
import com.hhhy.db.beans.Article;
import com.hhhy.db.beans.item.SearchCondition;

public class FSIndexBuilder implements IndexBuilder {
    private static final Logger logger = Logger.getLogger(FSIndexBuilder.class);
    private static final int CACHE_SIZE = 1;
    private static final long MERGE_INTERVAL = 1000*60*60*24*7; // 一周merge一次
    private Analyzer ansjHeightAnalyzer = new AnsjAnalysis();
    private String indexDir;
    private Directory directory;
    private IndexWriter iWriter;
    private Analyzer analyzer;
    private List<Document> documentsCache;
    private long lastMerge;
    private static FSIndexBuilder instance;
//    private boolean firstCommit;

    private FSIndexBuilder(String indexDir) {
        this.indexDir = indexDir;
        this.lastMerge = -1l;
        documentsCache = new ArrayList<Document>(CACHE_SIZE);
        try {
            this.directory = new SimpleFSDirectory(new File(indexDir));
            analyzer = new AnsjAnalysis(StopWordsUtils.getStopWords(), false);
            IndexWriterConfig ic = new IndexWriterConfig(Version.LUCENE_4_9,
                    analyzer);
            iWriter = new IndexWriter(directory, ic);
        } catch (IOException e) {
            logger.error(e.getMessage().replaceAll("\n", ";"));
        }
    }

    static{
        instance = new FSIndexBuilder("lucene");
    }
    
    public static FSIndexBuilder getInstance(){
        return instance;
    }
    
    @Override
    public void addArticle(Article art) {
        String content = art.getContent();
        String title = art.getTitle();
        String url = art.getUrl();
        String summary = art.getSummary();
        String website = art.getWebsite();
        Document doc = new Document();
        Field c = new TextField("content", content, Field.Store.YES);
        Field t = new TextField("title", title, Field.Store.YES);
        Field u = new StringField("url",url,Field.Store.YES);
        Field w = new TextField("website", website, Field.Store.YES);
        Field s = new TextField("summary", summary, Field.Store.YES);
        doc.add(c);
        doc.add(t);
        doc.add(u);
        doc.add(w);
        doc.add(s);
        synchronized (this) {
            addDoc(doc);
        }
    }
    
    private void addDoc(Document doc){
        this.documentsCache.add(doc);
        if(this.documentsCache.size()>=FSIndexBuilder.CACHE_SIZE){
            try {
                this.iWriter.addDocuments(this.documentsCache);
                this.iWriter.commit();
            } catch (IOException e) {
                logger.warn(e.getMessage());
            } finally{
                this.documentsCache.clear();
            }
            
            if(this.lastMerge<0){
                // first commit
                this.lastMerge = System.currentTimeMillis();
            } else if(System.currentTimeMillis()-this.lastMerge>MERGE_INTERVAL){
                // TODO: merge here
                logger.info("will merge...");
                this.lastMerge = System.currentTimeMillis();
                try {
                    this.iWriter.forceMerge(3);
                } catch (IOException e) {
                    logger.warn(e.getMessage());
                }
            }
        }
    }

    @Override
    public Article queryArticle(String queryStr) {
        try {
            IndexSearcher isearcher;
            // 查询索引
            DirectoryReader reader = DirectoryReader.open(directory);
            isearcher = new IndexSearcher(reader);
            QueryParser tq = new QueryParser(Version.LUCENE_4_9, "content",
                    analyzer);
            Query query = tq.parse(queryStr);
            System.out.println(query);
            TopDocs hits = isearcher.search(query, 5);
            System.out.println(queryStr + ":共找到" + hits.totalHits + "条记录!");
            for (int i = 0; i < hits.scoreDocs.length; i++) {
                int docId = hits.scoreDocs[i].doc;
                Document document = isearcher.doc(docId);
                // System.out.println(toHighlighter(ansjHeightAnalyzer, query,
                // document));
                System.out.println(document.get("content"));
            }
        } catch (IOException e) {
            logger.warn(e);
        } catch (ParseException e) {
            logger.warn(e);
        }
        return null;
    }

    public void query(SearchCondition condition) throws ParseException{
        QueryParser tq = new QueryParser(Version.LUCENE_4_9, "content",
                analyzer);
//        Query query2 = tq.parse("和谐社会");
        
        BooleanQuery query = new BooleanQuery();
        if(condition.getMust()!=null){
//            query.add(new TermQuery(new Term("content", "和谐")),BooleanClause.Occur.MUST);
            Query query2 = tq.parse("和谐社会");
            query.add(query2, BooleanClause.Occur.MUST);
        }
        if(condition.getMustnot()!=null){
            Query query2 = tq.parse("中国共产党");
            query.add(query2,BooleanClause.Occur.MUST_NOT);
        }
        if(condition.getShould()!=null){
            query.add(tq.parse("人类"),BooleanClause.Occur.SHOULD);
        }
        
        IndexSearcher isearcher;
        // 查询索引
        DirectoryReader reader;
        try {
            reader = DirectoryReader.open(directory);
            isearcher = new IndexSearcher(reader);
            TopDocs hits = isearcher.search(query, 10);
            System.out.println("共找到" + hits.totalHits + "条记录!");
            for (int i = 0; i < hits.scoreDocs.length; i++) {
                int docId = hits.scoreDocs[i].doc;
                Document document = isearcher.doc(docId);
                // System.out.println(toHighlighter(ansjHeightAnalyzer, query,
                // document));
                System.out.println(document.get("content"));
            }
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        
        
    } 
    
    private String toHighlighter(Analyzer analyzer, Query query, Document doc) {
        String field = "text";
        try {
            SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter(
                    "<font color=\"red\">", "</font>");
            Highlighter highlighter = new Highlighter(simpleHtmlFormatter,
                    new QueryScorer(query));
            TokenStream tokenStream1 = analyzer.tokenStream("text",
                    new StringReader(doc.get(field)));
            String highlighterStr = highlighter.getBestFragment(tokenStream1,
                    doc.get(field));
            return highlighterStr == null ? doc.get(field) : highlighterStr;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private void mergeIndex(){
        try {
            this.iWriter.forceMerge(1);
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }
    
    public static void main(String[] args) throws InterruptedException,
            IOException, ParseException {
        FSIndexBuilder indexBuilder = new FSIndexBuilder("D://index");
//        indexBuilder.mergeIndex();
//        SearchCondition condition = new SearchCondition();
//        condition.setMust("d");
//        condition.setMustnot("d");
//        condition.setShould("d");
//        indexBuilder.query(condition);
        
//        indexBuilder.queryArticle("社会主义和谐社会");
//        for (int i = 0; i < 100; i++) {
//            FSIndexBuilder indexBuilder = new FSIndexBuilder("D://index");
////            indexBuilder.mergeIndex();
//            indexBuilder.addArticless();
//            indexBuilder.close();
//        }
    }

    public void close() throws IOException {
        iWriter.close();
    }

    public void addArticless() {
        Article art = new Article();
        art.setContent("和谐社会，全稱社会主义和谐社会，是中国共产党");
        art.setSummary("今天天气不错");
        art.setUrl("http:url");
        art.setWebsite("网站");
        art.setTitle("和谐社会");
        addArticle(art);
        // Thread.sleep(10000);
        art.setContent("构建社会主义和谐社会”概念的首次完整提出");
        addArticle(art);
        // Thread.sleep(10000);
        art.setContent("马克思主义政党不懈追求的一种理想。");
        addArticle(art);
        // Thread.sleep(10000);
        art.setContent("社会主义和谐社会是人类孜孜以求的一种美好社会，马克");
        addArticle(art);
        // Thread.sleep(10000);
    }

}
