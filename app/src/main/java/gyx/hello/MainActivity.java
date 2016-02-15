package gyx.hello;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.melnykov.fab.FloatingActionButton;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String html = "http://202.195.206.35:8080/jsxsd/xk/LoginToXk";
    private String html3 = "http://202.195.206.35:8080/jsxsd/kscj/cjcx_list";
    private Map<String, String> map = new HashMap<String, String>();
    private RecyclerAdapter recyclerAdapter;
    private Bundle bundle;
    private User user;
    private Toolbar mToolbar;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progress);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        String sTitle = "本学期绩点：0";
        mToolbar.setTitle(sTitle);
        setSupportActionBar(mToolbar);



        recyclerView = (RecyclerView) findViewById(R.id.id_recycler);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);

        Intent intent = this.getIntent();
        user=(User)intent.getSerializableExtra("user");

        Log.d("GGGYX", user.getDate());
        new MyAsyncTask().execute(user);


    }

    class MyAsyncTask extends AsyncTask<User, Void, List<DataBean>> {
        private String html = "http://202.195.206.35:8080/jsxsd/xk/LoginToXk";
        private String html3 = "http://202.195.206.35:8080/jsxsd/kscj/cjcx_list";
        private Map<String, String> map = new HashMap<String, String>();
        private final List<DataBean> listBean = new ArrayList<DataBean>();
        private Bundle bundle2;
        private List<DataBean> listTemp;


        @Override
        protected List<DataBean> doInBackground(User... params){

            user = params[0];
            map.put("USERNAME", user.getNumber());
            map.put("PASSWORD", user.getPassword());
            try {
                Connection conn = Jsoup.connect(html);
                conn.data(map);
                conn.timeout(30000);
                conn.method(Connection.Method.POST);
                conn.userAgent("Mozilla");
                Connection.Response response = conn.execute();
                Map<String, String> cookies = response.cookies();
                Document doc = Jsoup.connect(html3)
                        .cookies(cookies)
                        .timeout(30000)
                        .get();
                Elements allTd = doc.select("td");
                ArrayList<String> list3 = new ArrayList<String>();
                for(org.jsoup.nodes.Element element:allTd){
                    String td = element.text();
                    list3.add(td);
                    //System.out.println(td);
                }
                list3.remove(0);
                //新建10个list
                ArrayList<String> orderList = new ArrayList<String>();
                ArrayList<String> dateList = new ArrayList<String>();
                ArrayList<String> classList = new ArrayList<String>();
                ArrayList<String> subList = new ArrayList<String>();
                ArrayList<String> gradeList = new ArrayList<String>();
                ArrayList<String> pointsList = new ArrayList<String>();
                ArrayList<String> timeList = new ArrayList<String>();
                ArrayList<String> testList = new ArrayList<String>();
                ArrayList<String> attrList = new ArrayList<String>();
                ArrayList<String> proList = new ArrayList<String>();

                for(int i = 0;i<list3.size();i++){
                    if(i%10 == 0){
                        orderList.add(list3.get(i));
                    }
                    else if(i%10 == 1){
                        dateList.add(list3.get(i));
                    }
                    else if(i%10 == 2){
                        classList.add(list3.get(i));
                    }
                    else if(i%10 == 3){
                        subList.add(list3.get(i));
                    }
                    else if(i%10 == 4){
                        gradeList.add(list3.get(i));
                    }
                    else if(i%10 == 5){
                        pointsList.add(list3.get(i));
                    }
                    else if(i%10 == 6){
                        timeList.add(list3.get(i));
                    }
                    else if(i%10 == 7){
                        testList.add(list3.get(i));
                    }
                    else if(i%10 == 8){
                        attrList.add(list3.get(i));
                    }
                    else if(i%10 == 9){
                        proList.add(list3.get(i));
                    }
                }

                for(int i = 0;i<dateList.size();i++){
                    DataBean bean = new DataBean();
                    bean.setDate(dateList.get(i));
                    bean.setSubName(subList.get(i));
                    bean.setGrade(gradeList.get(i));
                    bean.setPoints(pointsList.get(i));
                    bean.setTest(testList.get(i));
                    bean.setTotalTime(timeList.get(i));
                    bean.setClassNum(classList.get(i));
                    bean.setAttr(attrList.get(i));
                    listBean.add(bean);
                }

                 bundle2 = new Bundle();
                bundle2.putSerializable("list", (Serializable)listBean);

                String quary = user.getDate();
                listTemp = new ArrayList<DataBean>();
                for (int i = 0;i<listBean.size();i++){
                    String temp = listBean.get(i).getDate();
                    if(temp.equals(quary)){
                        listTemp.add(listBean.get(i));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }



            return listTemp;
        }




        @Override
        protected void onPostExecute(final List<DataBean> listBean) {
            super.onPostExecute(listBean);

            recyclerAdapter = new RecyclerAdapter(MainActivity.this, listBean);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            RecyclerView.ItemDecoration itemDecoration = new ItemDivider();
            recyclerView.addItemDecoration(itemDecoration);

            //复制一个listBean 出来，否则不会显示删除的课程
            List<DataBean> listCopy = new ArrayList<DataBean>();
            for (int i = 0; i < listBean.size();i++){
                listCopy.add(listBean.get(i));
            }

            //计算学分绩点
            Calpoints2 calpoints = new Calpoints2();
            List<String> beginLisdt;
            beginLisdt = calpoints.remoData(listCopy);
            double bsum = calpoints.calTotPoints(listCopy);
            double end = calpoints.calculatePoint(listCopy, beginLisdt,bsum);
            String sTitle = "本学期绩点：";
            mToolbar.setTitle(sTitle + end);


            progressBar.setVisibility(View.GONE);


           FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //发送listBean到绩点activity
                    Intent intent2 = new Intent();
                    intent2.setClass(MainActivity.this, PointActivity.class);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                }
            });
        }
    }


}

