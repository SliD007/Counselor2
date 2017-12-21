package test.example.com.counselor.view.login;

import android.content.Context;
import android.content.Intent;
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
import test.example.com.counselor.view.MainActivity;

public class LoginActivity extends BaseActivity implements ILoginView{

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

    private String account;
    private String password;
    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    /*
    在单击响应中完成两个实现
     */
    @OnClick({R.id.loginBtn, R.id.forgetPwTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                account = userNameEt.getText().toString().trim();
                password = passwordEt.getText().toString().trim();
                mLoginPresenter.loadLogin(LoginActivity.this,account,password);
                break;
            case R.id.forgetPwTv:

                break;
        }
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

    /*
    登录之后两个回调
     */
    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void loginFailed() {
        toast("登录失败！",true);
    }
}
