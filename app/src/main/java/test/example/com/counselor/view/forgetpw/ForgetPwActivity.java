package test.example.com.counselor.view.forgetpw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mForgetPwPresenter = new ForgetPwPresenter(this);
        titleBarTv.setText("忘记密码");
    }

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
                if(!TextUtils.isEmpty(telEt.getText().toString().trim()))
                    mForgetPwPresenter.sentVCode(telEt.getText().toString().trim());
                else
                    toast("电话不能为空！", false);
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
        vCodeBt.setText("验证码已发送");
    }

    @Override
    public void getVCodeFailed() {
        toast("验证码获取失败",false);
    }
}
