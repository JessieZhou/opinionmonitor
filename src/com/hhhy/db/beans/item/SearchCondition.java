package com.hhhy.db.beans.item;

public class SearchCondition {
    private String must;
    private String mustnot;
    private String should;
    private String position;
    
    private String start;
    private String end;
    private String type;
    private String emotion;
    private String keyword;
    
    public String getMust() {
        return must;
    }
    public void setMust(String must) {
        this.must = must;
    }
    public String getMustnot() {
        return mustnot;
    }
    public void setMustnot(String mustnot) {
        this.mustnot = mustnot;
    }
    public String getShould() {
        return should;
    }
    public void setShould(String should) {
        this.should = should;
    }
    
    

}
