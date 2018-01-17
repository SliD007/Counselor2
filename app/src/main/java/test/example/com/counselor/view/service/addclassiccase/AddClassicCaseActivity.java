package test.example.com.counselor.view.service.addclassiccase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddClassicCaseActivity extends BaseActivity implements IAddClassicCaseView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;

    AddClassicCasePresenter mAddClassicCasePresenter;

    @BindView(R.id.addClassicCaseTitleEt)
    EditText addClassicCaseTitleEt;
    @BindView(R.id.addClassicCaseContextEt)
    EditText addClassicCaseContextEt;

    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addclassiccase);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mAddClassicCasePresenter = new AddClassicCasePresenter(this, this);
    }

    private void initView() {

        Intent i = getIntent();
        super.allow_quit = false;
        titleBarTv.setText("新增典型案例");

    }

    @OnClick({R.id.backTv, R.id.sumbitTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:
                String title = addClassicCaseTitleEt.getText().toString();
                String context_str = addClassicCaseContextEt.getText().toString();
//                mAddClassicCasePresenter.addClassicCase(title, context_str);
                mAddClassicCasePresenter.addclassicCase(title, context_str);
                break;
        }
    }

    private void sumbitAdvice(String title, String context_str) {
    }


    @Override
    public void addSuccess() {
        toast("添加成功", false);
        MyApplication.getInstance().refresh = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }

    @Override
    public void addFailed() {
        toast("添加失败", false);
    }
}
