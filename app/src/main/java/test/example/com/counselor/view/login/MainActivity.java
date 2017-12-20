package test.example.com.counselor.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.loginBtn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        hideStatus(); //隐藏状态栏
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    private void hideStatus() {
        Window window = getWindow();
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
    }

    @OnClick(R.id.loginBtn)
    public void onClick() {
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
    }


}
