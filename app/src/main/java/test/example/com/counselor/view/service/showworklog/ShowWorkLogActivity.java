package test.example.com.counselor.view.service.showworklog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.TimeUtil;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowWorkLogActivity extends BaseActivity implements IShowWorkLogView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.textview01)
    TextView textview01;
    @BindView(R.id.textview02)
    TextView textview02;
    @BindView(R.id.textview03)
    TextView textview03;
    @BindView(R.id.textview04)
    TextView textview04;
    @BindView(R.id.textview05)
    TextView textview05;
    @BindView(R.id.textview06)
    TextView textview06;
    @BindView(R.id.textview07)
    TextView textview07;
    @BindView(R.id.textview08)
    TextView textview08;
    @BindView(R.id.textview09)
    TextView textview09;
    @BindView(R.id.textview10)
    TextView textview10;
    @BindView(R.id.textview11)
    TextView textview11;
    @BindView(R.id.textview12)
    TextView textview12;
    @BindView(R.id.rl13)
    RelativeLayout rl13;
    @BindView(R.id.textview13)
    TextView textview13;
    @BindView(R.id.textview14)
    TextView textview14;
    @BindView(R.id.textview15)
    TextView textview15;

    private ShowWorkLogPresenter mShowWorkLogPresenter;
    private WorkLogDetialEntity workLogDetialEntity;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showworklog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("工作记录详情");

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowWorkLogPresenter = new ShowWorkLogPresenter(this, this);
        mShowWorkLogPresenter.requestWorkLogDetial(id);
    }

    private void initView() {
        workLogDetialEntity = mShowWorkLogPresenter.getWorkLogDetialEntity();
        if (workLogDetialEntity != null) {
            textview01.setText(workLogDetialEntity.getServiceVillage());
            textview02.setText(workLogDetialEntity.getServiceObject());
            textview03.setText(workLogDetialEntity.getObjectContact());
            textview04.setText(workLogDetialEntity.getServiceIdentity());
            textview05.setText(workLogDetialEntity.getInObject());
            textview06.setText(TimeUtil.getDateToString(workLogDetialEntity.getCreateTime(),TimeUtil.DataTime));
            textview07.setText(TimeUtil.getDateToString(workLogDetialEntity.getCreateTime(),TimeUtil.DataTime));
            textview08.setText(workLogDetialEntity.getFromType());
            textview09.setText(workLogDetialEntity.getServiceType());
            textview10.setText(workLogDetialEntity.getMatterType());
            textview11.setText(workLogDetialEntity.getSubType());
            textview12.setText(workLogDetialEntity.getObjecttype());
            textview13.setText(workLogDetialEntity.getServiceContent());
            textview14.setText("无图");
            textview15.setText(workLogDetialEntity.getResultType());
        }
    }

    @OnClick(R.id.backTv)
    public void onClick() {
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }


    @Override
    public void requestWorkLogDetialSuccess() {
        toast("请求成功", false);
        initView();
    }

    @Override
    public void requestWorkLogDetialFailed() {
        toast("请求失败", false);
    }
}
