package com.example.administrator.mvpsample.bean;


import java.io.Serializable;

public class HistoryBean implements Serializable {
    private String title;
    private int month;
    private String img;
    private String year;
    private int day;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "HistoryBean{" +
                "title='" + title + '\'' +
                ", month=" + month +
                ", img='" + img + '\'' +
                ", year='" + year + '\'' +
                ", day=" + day +
                '}';
    }
}
