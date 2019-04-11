package com.meihuayishu.vone.GLOBAL;

public class BaZiBean {
    private String date;
    private String sex;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BaZiBean(String date, String sex) {
        this.date = date;
        this.sex = sex;
    }
}
