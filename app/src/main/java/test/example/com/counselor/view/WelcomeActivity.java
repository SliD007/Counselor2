package test.example.com.counselor.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.view.login.ILoginView;
import test.example.com.counselor.view.login.LoginActivity;
import test.example.com.counselor.view.login.LoginPresenter;

public class WelcomeActivity extends BaseActivity{

    Handler mhandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent i;
            switch (msg.what){
                case 0:
                    i = new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(i);
                    break;
                case 1:
                    //现请求登录信息

                    i = new Intent(WelcomeActivity.this,HomeActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        hideStatus(); //隐藏状态栏
        init();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_welcome);
    }
    String account = "";
    String password = "";
    private void init() {

        //初始化checkbox
        SharedPreferences rememberPswSp = getSharedPreferences("rememberPswSp", Context.MODE_PRIVATE);
        Log.e("WelcomeActivity",""+rememberPswSp.getInt("rememberPsw", 0));
        if (rememberPswSp.getInt("rememberPsw", 0) == 1) {
            //初始化用户名
            SharedPreferences userNameSp = getSharedPreferences("userNameSp", Context.MODE_PRIVATE);
            if(userNameSp.getString("userName", null)!=null){
                 account = userNameSp.getString("userName", null);
            }else {//如果用户名出错还是去登录页吧
                mhandler.sendEmptyMessageDelayed(0, 2000);//将欢迎页停留5秒，完成登录并将message设置文跳转到MainActivity
            }
            // 初始化密码
            SharedPreferences passwordSp = getSharedPreferences("passwordSp",Context.MODE_PRIVATE);
            if (passwordSp.getString("password", null)!=null) {
                password = passwordSp.getString("password", null);
            }else {//如果密码出错还是去登录页吧
                mhandler.sendEmptyMessageDelayed(0, 2000);//将欢迎页停留5秒，完成登录并将message设置文跳转到MainActivity
            }
            LoginPresenter mLoginPresenter = new LoginPresenter(mContext,iLoginView);
            mLoginPresenter.loadLogin(mContext,account,password);
        } else {
            mhandler.sendEmptyMessageDelayed(0, 5000);//将欢迎页停留5秒，并且将message设置为跳转到登录
        }
    }

    private void hideStatus() {
        Window window = getWindow();
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
    }

    ILoginView iLoginView = new ILoginView() {
        @Override
        public void loginSuccess() {
            //登录成功前往主页
            mhandler.sendEmptyMessageDelayed(1, 2000);//将欢迎页停留5秒，完成登录并将message设置文跳转到MainActivity
        }

        @Override
        public void loginFailed() {
            //如果登录失败还是去登录页吧
            mhandler.sendEmptyMessageDelayed(0, 2000);//将欢迎页停留5秒，完成登录并将message设置文跳转到MainActivity
        }
    };
}
