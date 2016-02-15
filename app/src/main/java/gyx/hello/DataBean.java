package gyx.hello;


import java.io.Serializable;

public class DataBean implements Serializable {
    private String order;
    private String date;
    private String classNum;
    private String subName;
    private String grade;
    private String points;
    private String totalTime;
    private String test;
    private String attr;
    private String proper;



    public String getOrder() {
        return order;
    }



    public void setOrder(String order) {
        this.order = order;
    }



    public String getDate() {
        return date;
    }



    public void setDate(String date) {
        this.date = date;
    }



    public String getClassNum() {
        return classNum;
    }



    public void setClassNum(String classNum) {
        this.classNum = classNum;
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



    public String getTotalTime() {
        return totalTime;
    }



    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
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



    public String getProper() {
        return proper;
    }



    public void setProper(String proper) {
        this.proper = proper;
    }


//    @Override
//    public boolean equals(Object obj) {
//        // TODO Auto-generated method stub
//        if(this == obj){
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if(getClass() != obj.getClass()){
//            return false;
//        }
//        final DataBean bean = (DataBean)obj;
//        if (this.getSubName() != bean.getSubName()) {
//            return false;
//        }
//
//        return true;
//    }

}
