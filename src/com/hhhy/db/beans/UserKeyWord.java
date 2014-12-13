package com.hhhy.db.beans;

/**
 * 用户的关键词对应表，连接a_admin和a_keyword
 * 对应a_userkeyword表
 * 暂时不同，保持keyword表冗余
 * @author chenlingpeng
 * 
 */
public class UserKeyWord {
    private int uid;
    private int kid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

}
