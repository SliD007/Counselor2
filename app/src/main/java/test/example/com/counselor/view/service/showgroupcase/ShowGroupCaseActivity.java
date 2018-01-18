package test.example.com.counselor.view.service.showgroupcase;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;


/**
 * Created by Sli.D on 2017/12/21.
 */

public class ShowGroupCaseActivity extends BaseActivity implements IShowGroupCaseView {
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;


    String[] sumbit_str;
    int[] sumbit_int;
    List<String> list;
    ArrayAdapter<String> adapter;
    ShowGroupCasePersenter mShowGroupCasePersenter;
    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.textview3)
    TextView textview3;
    @BindView(R.id.textview4)
    TextView textview4;
    @BindView(R.id.textview5)
    TextView textview5;
    @BindView(R.id.textview6)
    TextView textview6;
    @BindView(R.id.textview8)
    TextView textview8;
    @BindView(R.id.textview7)
    TextView textview7;


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showgroupcase);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mShowGroupCasePersenter = new ShowGroupCasePersenter(this, this);
    }


    private void initView() {

        super.allow_quit = false;
        titleBarTv.setText("群体性案件详情");

    }


    @OnClick({R.id.backTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
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
