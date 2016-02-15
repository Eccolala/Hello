package gyx.hello;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Calpoints2 {
    String random = "任选";
    String pe1 = "体育1";
    String pe2 = "体育2";
    String pe3 = "体育3";
    String pe4 = "体育4";
    String pePro = "体育专项";
    double atemp = 0;
    double asum = 0;






    public List<String> remoData(List<DataBean> listTemp){

        List<String> listRemove = new ArrayList<String>();

        for (int i = 0;i < listTemp.size();i++){
            //第一个if按名字排除体育和任选课和学分为0，第二个if排除挂过科的课程
            if (listTemp.get(i).getSubName().equals(pe1)||listTemp.get(i).getSubName().equals(pe2)||
                    listTemp.get(i).getSubName().equals(pe3)||listTemp.get(i).getSubName().equals(pe4)||
                    listTemp.get(i).getSubName().equals(pePro)||listTemp.get(i).getAttr().equals(random)
                    ||listTemp.get(i).getPoints().equals(0)){
                listRemove.add(listTemp.get(i).getSubName());

            }
            String tr = listTemp.get(i).getGrade();
            TransToDouble transToInt = new TransToDouble();
            double tr2 = transToInt.transtoInt(tr);
            if(tr2<60) {
                listRemove.add(listTemp.get(i).getSubName());
            }
        }
        for (int i = 0;i<listRemove.size();i++){
            Log.d("listRemove :",listRemove.get(i));
        }
        return  listRemove;
    }
    public double calculatePoint(List<DataBean> listTemp,List<String> listRemove,double bsum){

        for(int i = 0;i < listTemp.size();i++){

            String subName = listTemp.get(i).getSubName();
            for (int j = 0;j < listRemove.size();j++){
                String rovName = listRemove.get(j);
                if (subName.equals(rovName)){
                    listTemp.remove(i);
                    i--;
                    break;
                }
            }
        }



        for(int i = 0;i < listTemp.size();i++){
            String tr = listTemp.get(i).getGrade();
            TransToDouble transToInt = new TransToDouble();
            double tr2 = transToInt.transtoInt(tr);
            double td = Double.parseDouble(listTemp.get(i).getPoints());
            atemp = (tr2- 50)/10*td;
            asum += atemp;
            //bsum += td;

        }

        return asum/bsum;
    }

    public double calTotPoints(List<DataBean> listTemp){

        for (int i = 0;i < listTemp.size();i++) {
            //第一个if按名字排除体育和任选课和学分为0，第二个if排除挂过科的课程
            if (listTemp.get(i).getSubName().equals(pe1) || listTemp.get(i).getSubName().equals(pe2) ||
                    listTemp.get(i).getSubName().equals(pe3) || listTemp.get(i).getSubName().equals(pe4) ||
                    listTemp.get(i).getSubName().equals(pePro) || listTemp.get(i).getAttr().equals(random)
                    || listTemp.get(i).getPoints().equals(0)) {
                listTemp.remove(i);
                i--;
                continue;
            }

        }
        for(int i = 0;i < listTemp.size();i++){
            Log.d("listTemp :",listTemp.get(i).getSubName());
        }

        //任务：去除重复项
        ArrayList<DataBean> listCopy = new ArrayList<DataBean>();
        for (DataBean bean:listTemp){
            if (!isDuplicate( listCopy,bean)) {
                listCopy.add(bean);
               String ss = bean.getSubName();
            }
        }
        for(int i = 0;i < listCopy.size();i++){
            Log.d("listCopy :",listCopy.get(i).getSubName());
        }
        double bsum = 0;
        for(int i = 0;i < listCopy.size();i++){
            double td = Double.parseDouble(listCopy.get(i).getPoints());
            bsum += td;
        }
        return bsum;
    }
    boolean isDuplicate(ArrayList<DataBean> list,DataBean b){
        for (DataBean elem:list){
            if(elem.getSubName().equals(b.getSubName())) return true;
        }

        return false;
    }
}
