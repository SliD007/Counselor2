package test.example.com.counselor.view.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.view.MainActivity;

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
                    i = new Intent(WelcomeActivity.this,MainActivity.class);
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
        SharedPreferences sf=getSharedPreferences("data", MODE_PRIVATE);//判断是否是第一次进入
        boolean isFirstIn=sf.getBoolean("isFirstIn", true);
        SharedPreferences.Editor editor=sf.edit();
        if(isFirstIn) {     //若为true，则是第一次进入
            editor.putBoolean("isFirstIn", false);
            mhandler.sendEmptyMessageDelayed(0, 2000);//将欢迎页停留5秒，并且将message设置为跳转到登录
        }else{
                mhandler.sendEmptyMessageDelayed(1,2000);//将欢迎页停留5秒，并且将message设置文跳转到MainActivity
            }
            editor.commit();
        }

    private void hideStatus() {
        Window window = getWindow();
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
    }

}
