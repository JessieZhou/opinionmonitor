package com.hhhy.db.beans.item;


public class Condition{
	// 起止时间
	private long start;
	private long end;
	// 是否合并相似文章
//	private boolean merge;
	// 来源类型
	private String[] sources;
	// 情感，0：正面，1：负面，2：无情感
	private int[] sentiments;
	// 关键词
	private int keyword;
//	// 导出字段
//	private Set<String> fields;
	// 导出条数
	private int size;
	
	public Condition(){
	    sentiments = new int[3];
//	    merge=false;
	    start=0l;
	    end = System.currentTimeMillis();
	}
	
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
//	public boolean isMerge() {
//		return merge;
//	}
//	public void setMerge(boolean merge) {
//		this.merge = merge;
//	}
	public String[] getSources() {
		return sources;
	}
	public void setSources(String[] sources) {
		this.sources = sources;
	}
	public int[] getSentiments() {
		return sentiments;
	}
	public void setSentiments(int[] sentiments) {
		this.sentiments = sentiments;
	}


public int getKeyword() {
        return keyword;
    }

    public void setKeyword(int keyword) {
        this.keyword = keyword;
    }

    //	public Set<String> getFields() {
//		return fields;
//	}
//	public void setFields(Set<String> fields) {
//		this.fields = fields;
//	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean posNeed(){
	    return this.sentiments[0]==1;
	}
	public void needPos(){
	    this.sentiments[0]=1;
	}
	public boolean negNeed(){
	    return this.sentiments[1]==1;
	}
	public void needNeg(){
	    this.sentiments[1]=1;
	}
	public boolean plainNeed(){
	    return this.sentiments[2]==1;
	}
	public void needPlain(){
	    this.sentiments[2]=1;
	}
}