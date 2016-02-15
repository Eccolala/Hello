package gyx.hello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PointActivity extends AppCompatActivity {

    private List<DataBean> listBean;
    private String term1 = "2013-2014-1";
    private String term2 = "2013-2014-2";
    private String term3 = "2014-2015-1";
    private String term4 = "2014-2015-2";
    private String term5 = "2015-2016-1";
    private String term6 = "2015-2016-2";
    private String term7 = "2016-2017-1";
    private String term8 = "2016-2017-2";
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        initView();

        listBean=(List<DataBean>)getIntent().getSerializableExtra("list");
//        Log.d("list:",listBean.get(62).getGrade());
//        Log.d("list:", String.valueOf(listBean.size()));
        List<DataBean> listTemp1 = new ArrayList<DataBean>();
        List<DataBean> listTemp2 = new ArrayList<DataBean>();
        List<DataBean> listTemp3 = new ArrayList<DataBean>();
        List<DataBean> listTemp4 = new ArrayList<DataBean>();

        for (int i = 0;i<listBean.size();i++){
            if(listBean.get(i).getDate().equals(term1)||listBean.get(i).getDate().equals(term2)){
                listTemp1.add(listBean.get(i));
            }else if(listBean.get(i).getDate().equals(term3)||listBean.get(i).getDate().equals(term4)){
                listTemp2.add(listBean.get(i));
            }else if(listBean.get(i).getDate().equals(term5)||listBean.get(i).getDate().equals(term6)){
                listTemp3.add(listBean.get(i));
            }else {
                listTemp4.add(listBean.get(i));
            }
        }
        calcuYear1(listTemp1);
        calcuYear2(listTemp2);
        calcuYear3(listTemp3);
        calcuYear4(listTemp4);
    }

    private void calcuYear1(List<DataBean> listTemp) {
//        for (int i = 0;i<listTemp.size();i++){
//            Log.d("ListTT",listTemp.get(i).getSubName());
//        }
        //计算学分绩点
        Calpoints2 calpoints = new Calpoints2();
        List<String> beginLisdt;
        beginLisdt = calpoints.remoData(listTemp);
//        for (int i = 0;i<listTemp.size();i++){
//            Log.d("beginList", beginLisdt.get(i));
//        }
        Log.d("beginList size", String.valueOf(beginLisdt.size()));
        double bsum = calpoints.calTotPoints(listTemp);
        double end = calpoints.calculatePoint(listTemp, beginLisdt,bsum);
        textView1.setText("第一学年成绩："+end);
//
//        for (int i = 0;i<listTemp.size();i++){
//            Log.d("ListTT",listTemp.get(i).getSubName());
//        }

    }
    private void calcuYear2(List<DataBean> listTemp) {
        Calpoints2 calpoints = new Calpoints2();
        List<String> beginLisdt;
        beginLisdt = calpoints.remoData(listTemp);
        double bsum = calpoints.calTotPoints(listTemp);
        double end = calpoints.calculatePoint(listTemp, beginLisdt,bsum);
        textView2.setText("第二学年成绩："+end);
    }
    private void calcuYear3(List<DataBean> listTemp) {
        Calpoints2 calpoints = new Calpoints2();
        List<String> beginLisdt;
        beginLisdt = calpoints.remoData(listTemp);
        double bsum = calpoints.calTotPoints(listTemp);
        double end = calpoints.calculatePoint(listTemp, beginLisdt,bsum);
        textView3.setText("第三学年成绩："+end);
    }
    private void calcuYear4(List<DataBean> listTemp) {
        Calpoints2 calpoints = new Calpoints2();
        List<String> beginLisdt;
        beginLisdt = calpoints.remoData(listTemp);
        double bsum = calpoints.calTotPoints(listTemp);
        double end = calpoints.calculatePoint(listTemp, beginLisdt,bsum);
        textView4.setText("第四学年成绩："+end);
    }

    private void initView() {
        textView1 = (TextView)findViewById(R.id.id_txt_one);
        textView2 = (TextView)findViewById(R.id.id_txt_two);;
         textView3 = (TextView)findViewById(R.id.id_txt_three);;
         textView4 = (TextView)findViewById(R.id.id_txt_four);;
    }
}
