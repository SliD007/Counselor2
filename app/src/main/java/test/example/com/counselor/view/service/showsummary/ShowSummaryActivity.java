package test.example.com.counselor.view.service.showsummary;

import android.content.Intent;
import android.os.Bundle;
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

public class ShowSummaryActivity extends BaseActivity implements IShowSummaryView {


    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.showSummaryTitleTv)
    TextView showSummaryTitleTv;
    @BindView(R.id.showSummaryContextTv)
    TextView showSummaryContextTv;
    @BindView(R.id.showVillageTv)
    TextView showVillageTv;

    private ShowSummaryPresenter mShowSummaryPresenter;
    private SummaryEntity summaryEntity;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showsummary);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("月度总结详情");

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowSummaryPresenter = new ShowSummaryPresenter(this, this);
        mShowSummaryPresenter.requestSummaryDetial(id);
    }

    private void initView() {
        summaryEntity = mShowSummaryPresenter.getSummaryDetialEntity();
        if (summaryEntity != null) {
            showSummaryTitleTv.setText(summaryEntity.getTitle());
            showSummaryContextTv.setText(summaryEntity.getContent());
            showVillageTv.setText(summaryEntity.getVillage());
        }
    }

    @OnClick(R.id.backTv)
    public void onClick() {
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }


    @Override
    public void requestSummaryDetialSuccess() {
//        toast("请求成功", false);
        initView();
    }

    @Override
    public void requestSummaryDetialFailed() {
        toast("请求失败", false);
    }
}
