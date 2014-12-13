package com.hhhy.db.beans.item;



public class HistoryKeyword{
	private long id;
	private String keyword;
	private int flag;
	private long begin;
	private long end;
	private String aux;
	
	
    public String getAux() {
        return aux;
    }
    public void setAux(String aux) {
        this.aux = aux;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public long getBegin() {
        return begin;
    }
    public void setBegin(long begin) {
        this.begin = begin;
    }
    public long getEnd() {
        return end;
    }
    public void setEnd(long end) {
        this.end = end;
    }
	
	
}