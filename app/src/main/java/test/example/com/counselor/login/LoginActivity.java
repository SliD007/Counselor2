package test.example.com.counselor.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.util.Md5Util;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.userNameEt)
    EditText userNameEt;
    @BindView(R.id.passwordEt)
    EditText passwordEt;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.rememberPwCb)
    CheckBox rememberPwCb;
    @BindView(R.id.forgetPwTv)
    TextView forgetPwTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }


    /*
    登录初始化
    策略：记住密码：显示用户名和密码
          不记住：只显示用户名
          暂不实现自动登录
     */
    public void initView() {
        // TODO Auto-generated method stub
        //开启记住密码自动登录检测

        rememberPwCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                SharedPreferences rememberPswSp = getSharedPreferences("rememberPswSp", MODE_PRIVATE);
                SharedPreferences.Editor editor = rememberPswSp.edit();
                if(isChecked){

                    editor.putInt("rememberPsw", 1);
                    Log.e("rememberPsw","rememberPsw------------>"+rememberPswSp.getInt("rememberPsw", 0));
                }else{
                    editor.putInt("rememberPsw", 0);
                }
                editor.commit();
            }
        });

        //初始化checkbox
        SharedPreferences rememberPswSp = getSharedPreferences("rememberPswSp", Context.MODE_PRIVATE);
        if(rememberPswSp.getInt("rememberPsw", 0)==1){
            rememberPwCb.setChecked(true);
        }else{
            rememberPwCb.setChecked(false);
        }

        //初始化用户名
        SharedPreferences userNameSp = getSharedPreferences("userNameSp", Context.MODE_PRIVATE);
        if(userNameSp.getString("userName", null)!=null){
            userNameEt.setText(userNameSp.getString("userName", null));
        }else{
            Log.e("userName","-----------null");
        }

        //初始化密码
        SharedPreferences passwordSp = getSharedPreferences("passwordSp", Context.MODE_PRIVATE);
        if((rememberPswSp.getInt("rememberPsw", 0)==1)){
            Log.e("password","-----------"+passwordSp.getString("password", null));
            passwordEt.setText(passwordSp.getString("password", null));
        }else{
            Log.e("password","-----------something wrong");
        }
        //初始化自动登陆
        if((rememberPswSp.getInt("rememberPsw", 0)==1)){
            Log.e("password","-----------"+passwordSp.getString("password", null));
            passwordEt.setText(passwordSp.getString("password", null));
        }else{
            Log.e("password","-----------something wrong");
        }
    }


    @OnClick({R.id.loginBtn, R.id.forgetPwTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                String id = userNameEt.getEditableText().toString();
                String psw = userNameEt.getEditableText().toString();

                //每次保存账号密码
                SharedPreferences userNameSp = getSharedPreferences("userNameSp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = userNameSp.edit();
                editor.putString("userName", id);
                editor.commit();
                SharedPreferences passwordSp = getSharedPreferences("passwordSp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = passwordSp.edit();
                editor1.putString("password", psw);
                editor1.commit();
                psw = Md5Util.md5(psw);
                Log.i("loginInfo", "id=" + id + ",psw=" + psw);
                login(id, psw);
                break;
            case R.id.forgetPwTv:
                break;
        }
    }

    private void login(final String id, final String psw) {
        Log.i("loginInfo", "id=" + id + ",psw=" + psw);
    }
}
