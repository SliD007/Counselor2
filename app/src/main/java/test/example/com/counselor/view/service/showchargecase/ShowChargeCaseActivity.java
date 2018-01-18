package test.example.com.counselor.view.service.showchargecase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowChargeCaseActivity extends BaseActivity implements IShowChargeCaseView {

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
    @BindView(R.id.textview13)
    TextView textview13;
    @BindView(R.id.textview14)
    TextView textview14;
    @BindView(R.id.textview15)
    TextView textview15;
    @BindView(R.id.textview16)
    TextView textview16;
    @BindView(R.id.textview17)
    TextView textview17;

    private ShowChargeCasePresenter mShowChargeCasePresenter;
    private ChargeCaseDetialEntity chargeCaseDetialEntity;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showchargecase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("另行收费案件详情");

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowChargeCasePresenter = new ShowChargeCasePresenter(this, this);
        Log.e("onCreate","mShowChargeCasePresenter");
        mShowChargeCasePresenter.requestWorkLogDetial(id);
    }

    private void initView() {
        chargeCaseDetialEntity = mShowChargeCasePresenter.getChargeCaseDetialEntity();
        if (chargeCaseDetialEntity != null) {
            textview01.setText(chargeCaseDetialEntity.getServiceVillage());
            textview17.setText("否");
            textview02.setText(chargeCaseDetialEntity.getServiceObject());
            textview03.setText(chargeCaseDetialEntity.getObjectContact());
            textview04.setText(chargeCaseDetialEntity.getServiceIdentity());
            textview05.setText(chargeCaseDetialEntity.getInObject());
            textview08.setText(chargeCaseDetialEntity.getFromType());
            textview09.setText(chargeCaseDetialEntity.getServiceType());
            textview10.setText(chargeCaseDetialEntity.getMatterType());
            textview11.setText(chargeCaseDetialEntity.getSubType());
            textview12.setText(chargeCaseDetialEntity.getObjecttype());
            textview13.setText(chargeCaseDetialEntity.getServiceContent());
            textview15.setText("在处理");
            textview15.setText(chargeCaseDetialEntity.getResultType());
            textview14.setText("无图");
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
