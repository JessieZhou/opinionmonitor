package com.hhhy.db.beans;

/**
 * 用户类 对应a_admin表
 * 
 * @author chenlingpeng
 * 
 */
public class User {
    private long id;
    private String email;
    private String password;
    private String reportphone;
    private String reportemail;
    // need email, message, or alert
    private int needemail;
    private int needphone;
    private int needalert;

    public User() {
        this.id = -1l;
    }

    public User(String nickname, String email, String password) {
        this.id = -1l;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNeedemail() {
        return needemail;
    }

    public void setNeedemail(int needemail) {
        this.needemail = needemail;
    }

    public int getNeedphone() {
        return needphone;
    }

    public void setNeedphone(int needphone) {
        this.needphone = needphone;
    }

    public int getNeedalert() {
        return needalert;
    }

    public void setNeedalert(int needalert) {
        this.needalert = needalert;
    }

    public String getReportphone() {
        return reportphone;
    }

    public void setReportphone(String reportphone) {
        this.reportphone = reportphone;
    }

    public String getReportemail() {
        return reportemail;
    }

    public void setReportemail(String reportemail) {
        this.reportemail = reportemail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + "]";
    }

}
