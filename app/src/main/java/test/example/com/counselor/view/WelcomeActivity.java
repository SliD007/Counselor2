package test.example.com.counselor.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.view.login.LoginActivity;

public class WelcomeActivity extends BaseActivity {

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
                    i = new Intent(WelcomeActivity.this,HomeActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatus(); //隐藏状态栏
        init();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_welcome);
    }

    private void init() {

        //初始化checkbox
        SharedPreferences rememberPswSp = getSharedPreferences("rememberPswSp", Context.MODE_PRIVATE);
        if (rememberPswSp.getInt("rememberPsw", 0) == 1) {
            mhandler.sendEmptyMessageDelayed(1, 2000);//将欢迎页停留5秒，并且将message设置文跳转到MainActivity
        } else {
            mhandler.sendEmptyMessageDelayed(0, 2000);//将欢迎页停留5秒，并且将message设置为跳转到登录
        }
    }

    private void hideStatus() {
        Window window = getWindow();
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
    }

}
