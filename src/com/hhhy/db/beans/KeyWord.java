package com.hhhy.db.beans;

import java.io.Serializable;
import java.text.Collator;

/**
 * 关键词类
 * 对应a_keyword表
 * @author chenlingpeng
 * 
 */
public class KeyWord implements Serializable, Comparable<KeyWord>{
    private int id;
    private long uid;
    private String keyword;
    private String auxiliary;

    public KeyWord(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAuxiliary() {
        return auxiliary;
    }

    public void setAuxiliary(String auxiliary) {
        this.auxiliary = auxiliary;
    }
    
    @Override
    public int compareTo(KeyWord arg0) {
        return Collator.getInstance(java.util.Locale.CHINA).compare(this.getKeyword(), arg0.getKeyword());
    }
}
