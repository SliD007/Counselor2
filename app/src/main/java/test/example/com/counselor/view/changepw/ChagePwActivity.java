package test.example.com.counselor.view.changepw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.view.login.LoginActivity;

public class ChagePwActivity extends BaseActivity implements IChangePwView {


    @BindView(R.id.oldPwEt)
    EditText oldPwEt;
    @BindView(R.id.newPwEt)
    EditText newPwEt;
    @BindView(R.id.new2PwEt)
    EditText new2PwEt;

    ChangePwPresenter mChangePwPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit=false;
        mChangePwPresenter = new ChangePwPresenter(this);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_changepw);
    }


    @OnClick({R.id.backTv, R.id.changePwBt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.changePwBt:
                final String oldPw = oldPwEt.getText().toString().trim();
                final String newPw = newPwEt.getText().toString().trim();
                String new2Pw = new2PwEt.getText().toString().trim();
                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher m = p.matcher(oldPw + newPw);
                if (oldPw == null || newPw == null || new2Pw == null) {
                    toast("密码不能为空！",false);
                } else if (m.find()) {
                    toast("密码不能包含汉字！",false);

                } else if(!newPw.equals(new2Pw)){
                    toast("两次输入密码不一致！",false);
                }  else {
                    mChangePwPresenter.changePw(this,oldPw,newPw);
                }
                break;
        }
    }

    @Override
    public void changePwSuccess() {
        toast("修改成功！", true);
        SharedPreferences passwordSp = getSharedPreferences("passwordSp",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = passwordSp.edit();
        editor1.putString("password", null);
        editor1.commit();
        Intent intent = new Intent(ChagePwActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void changePwFailed() {
        toast("修改失败！", true);
    }

}
