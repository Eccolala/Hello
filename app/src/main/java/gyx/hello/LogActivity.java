package gyx.hello;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {
    private Button buttonLogin;
    private TextView txtNum;
    private TextView txtPwd;
    private User user;
    private Spinner spinner;
    private Bundle bundle;
    private CheckBox rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        buttonLogin = (Button)findViewById(R.id.id_button_login);
        txtNum = (TextView)findViewById(R.id.id_number_txt);
        txtPwd = (TextView)findViewById(R.id.id_password_txt);
        spinner = (Spinner) findViewById(R.id.id_spinner);
        rememberPass = (CheckBox)findViewById(R.id.id_rem_chk);
        user = new User();
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //记住密码逻辑
        boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            txtNum.setText(account);
            txtPwd.setText(password);
            rememberPass.setChecked(true);
        }










        final List<String> list = new ArrayList<String>();

        list.add("2013-2014-1");
        list.add("2013-2014-2");
        list.add("2014-2015-1");
        list.add("2014-2015-2");
        list.add("2015-2016-1");
        list.add("2015-2016-2");
        list.add("2016-2017-1");
        list.add("2016-2017-2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user.setDate(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void clickLogin(View view){

        String account = txtNum.getText().toString();
        String password = txtPwd.getText().toString();
        editor = pref.edit();
        if (rememberPass.isChecked()){
            editor.putBoolean("remember_password",true);
            editor.putString("account",account);
            editor.putString("password",password);
        }else {
            editor.clear();
        }
        editor.commit();

        user.setNumber(txtNum.getText().toString());
        user.setPassword(txtPwd.getText().toString());


        Intent intent = new Intent();
        intent.setClass(LogActivity.this, MainActivity.class);
        bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        this.startActivity(intent);



    }
}

