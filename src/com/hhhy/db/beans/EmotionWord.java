package com.hhhy.db.beans;


/**
 * 情感词表
 * 对应a_emotionword表
 * @author chenlingpeng
 *
 */
public class EmotionWord {
    private int id;
    private String word;
    private int val;

    public EmotionWord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

}
