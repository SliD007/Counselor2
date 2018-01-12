package test.example.com.counselor.view.service.showworklog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.util.TimeUtil;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowWorkLogActivity extends BaseActivity implements IShowWorkLogView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.item1Tv1)
    TextView item1Tv1;
    @BindView(R.id.item1Tv2)
    TextView item1Tv2;
    @BindView(R.id.item1Tv3)
    TextView item1Tv3;
    @BindView(R.id.item1Tv4)
    TextView item1Tv4;
    @BindView(R.id.item1Tv5)
    TextView item1Tv5;
    @BindView(R.id.item1Tv6)
    TextView item1Tv6;
    @BindView(R.id.item1Tv7)
    TextView item1Tv7;
    @BindView(R.id.item1Tv8)
    TextView item1Tv8;
    @BindView(R.id.item1Tv9)
    TextView item1Tv9;
    @BindView(R.id.item1Tv10)
    TextView item1Tv10;
    @BindView(R.id.item1Tv11)
    TextView item1Tv11;
    @BindView(R.id.item1Tv12)
    TextView item1Tv12;


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
        item1Tv2.setText(workLogDetialEntity.getServiceObject());
        item1Tv3.setText(workLogDetialEntity.getFromType());
        item1Tv4.setText(workLogDetialEntity.getObjectContact());
        item1Tv5.setText(workLogDetialEntity.getServiceIdentity());
        item1Tv6.setText(TimeUtil.getDateToString(workLogDetialEntity.getCreateTime(),TimeUtil.Data));
        item1Tv7.setText(TimeUtil.getDateToString(workLogDetialEntity.getCreateTime(),TimeUtil.Data));
        item1Tv8.setText(workLogDetialEntity.getServiceType());
        item1Tv9.setText(workLogDetialEntity.getObjectType());
        item1Tv10.setText(workLogDetialEntity.getLogType());
        item1Tv11.setText(workLogDetialEntity.getServiceContent());
        item1Tv12.setText("无图片");
    }

    @OnClick(R.id.backTv)
    public void onClick() {

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
