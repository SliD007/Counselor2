package test.example.com.counselor.view.forgetpw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.view.login.LoginActivity;

public class ForgetPwActivity extends BaseActivity implements IForgetPwView {


    ForgetPwPresenter mForgetPwPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.telEt)
    EditText telEt;
    @BindView(R.id.vCodeEt)
    EditText vCodeEt;
    @BindView(R.id.newPwEt)
    EditText newPwEt;
    @BindView(R.id.vCodeBt)
    Button vCodeBt;

    private int timeInt = 90;
    private boolean click = true;

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mForgetPwPresenter = new ForgetPwPresenter(this);
        titleBarTv.setText("忘记密码");
        telEt.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        vCodeEt.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        newPwEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                timeInt--;
                vCodeBt.setText(timeInt+"s");
                if(timeInt==0){
                    timeInt = 90;
                    vCodeBt.setText("重新发送");
                    click = true;
                    thread.stop();
                }
            }

        };
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for(int i=0;i<90;i++){
                    handler.sendEmptyMessage(0);
                    thread.sleep(1000);//模拟耗时操作

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forgetpw);
    }


    @OnClick({R.id.backTv, R.id.forgetPwBt, R.id.vCodeBt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.vCodeBt:
                if(click){
                    if(!TextUtils.isEmpty(telEt.getText().toString().trim()))
                        mForgetPwPresenter.sentVCode(telEt.getText().toString().trim());
                    else
                        toast("电话不能为空！", false);
                }

                break;
            case R.id.forgetPwBt:
                String tel = telEt.getText().toString().trim();
                String vCode = vCodeEt.getText().toString().trim();
                String newPw = newPwEt.getText().toString().trim();
                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher m = p.matcher(tel + newPw);
                if (tel == null || newPw == null || vCode == null) {
                    toast("不能为空！", false);
                } else if (m.find()) {
                    toast("不能包含汉字！", false);

                } else {
                    mForgetPwPresenter.forgetPw(tel, vCode, newPw);
                }
                break;
        }
    }


    @Override
    public void resetPwSuccess() {
        toast("重置成功！", true);
        SharedPreferences passwordSp = getSharedPreferences("passwordSp",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = passwordSp.edit();
        editor1.putString("password", null);
        editor1.commit();
        Intent intent = new Intent(ForgetPwActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void resetPwFailed() {
        toast("重置失败",false);
    }

    @Override
    public void getVCodeSuccess() {
        toast("验证码已发送",false);
        click = false;
        thread.start();
    }

    @Override
    public void getVCodeFailed() {
        toast("验证码获取失败",false);
    }
}
