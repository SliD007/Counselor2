package test.example.com.counselor.view.service.addsummary;

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

public class AddSummaryActivity extends BaseActivity implements IAddSummaryView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.addSummaryTitleEt)
    EditText addSummaryTitleEt;
    @BindView(R.id.addSummaryContextEt)
    EditText addSummaryContextEt;

    AddSummaryPresenter mAddSummaryPresenter;
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addsummary);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mAddSummaryPresenter = new AddSummaryPresenter(this, this);
    }

    private void initView() {

        Intent i = getIntent();
        super.allow_quit = false;
        titleBarTv.setText("新增月度总结");

    }

    @OnClick({R.id.backTv, R.id.sumbitTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:
                String title = addSummaryTitleEt.getText().toString();
                String context_str = addSummaryContextEt.getText().toString();
                mAddSummaryPresenter.addSummary(title, context_str);
//                mAddSummaryPresenter.addsummary(title, context_str);
                break;
        }
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
