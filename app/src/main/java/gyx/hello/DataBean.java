package gyx.hello;

import java.io.Serializable;

public class DataBean implements Serializable {
    private String date;
    private String subName;
    private String grade;
    private String points;
    private String test;
    private String attr;


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getSubName() {
        return subName;
    }


    public void setSubName(String subName) {
        this.subName = subName;
    }


    public String getGrade() {
        return grade;
    }


    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getPoints() {
        return points;
    }


    public void setPoints(String points) {
        this.points = points;
    }


    public String getTest() {
        return test;
    }


    public void setTest(String test) {
        this.test = test;
    }


    public String getAttr() {
        return attr;
    }


    public void setAttr(String attr) {
        this.attr = attr;
    }

}
