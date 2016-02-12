package gyx.hello;

public class TransToDouble {

    private String you = "优";
    private String liang = "良";
    private String zhong = "中";
    private String fail = "不及格";
    private String pass = "通过";



    public double transtoInt(String grade){
        if(grade.equals(you)){
            return 95.0;
        }else if(grade.equals(liang)){
            return 85;
        }else if(grade.equals(zhong)){
            return 75;
        }else if(grade.equals(fail)){
            return 50;
        }else if(grade.equals(pass)){
            return 85;
        }else {
            return Double.parseDouble(grade);
        }
    }
}
