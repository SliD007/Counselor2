package test.example.com.counselor.view.service.showclassiccase;

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

public class ShowClassicCaseActivity extends BaseActivity implements IShowClassicCaseView {


    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.showClassicCaseTitleTv)
    TextView showClassicCaseTitleTv;
    @BindView(R.id.showClassicCaseContextTv)
    TextView showClassicCaseContextTv;
    @BindView(R.id.showVillageTv)
    TextView showVillageTv;


    private ShowClassicCasePresenter mShowClassicCasePresenter;
    private ClassicCaseEntity classicCaseEntity;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showclassiccase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("典型案件详情");

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowClassicCasePresenter = new ShowClassicCasePresenter(this, this);
        mShowClassicCasePresenter.requestClassicCaseDetial(id);
    }

    private void initView() {
        classicCaseEntity = mShowClassicCasePresenter.getClassicCaseDetialEntity();
        if (classicCaseEntity != null) {
            showClassicCaseTitleTv.setText(classicCaseEntity.getTitle());
            showClassicCaseContextTv.setText(classicCaseEntity.getContent());
            showVillageTv.setText(classicCaseEntity.getVillage());
        }
    }

    @OnClick(R.id.backTv)
    public void onClick() {
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }


    @Override
    public void requestClassicCaseDetialSuccess() {
//        toast("请求成功", false);
        initView();
    }

    @Override
    public void requestClassicCaseDetialFailed() {
        toast("请求失败", false);
    }
}
