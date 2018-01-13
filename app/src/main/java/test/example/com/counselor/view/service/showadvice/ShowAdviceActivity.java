package test.example.com.counselor.view.service.showadvice;

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

public class ShowAdviceActivity extends BaseActivity implements IShowAdviceView {


    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.showAdviceTitleTv)
    TextView showAdviceTitleTv;
    @BindView(R.id.showAdviceContextTv)
    TextView showAdviceContextTv;
    @BindView(R.id.showAdvicesentToTv)
    TextView showAdvicesentToTv;

    private ShowAdvicePresenter mShowAdvicePresenter;
    private AdviceEntity adviceEntity;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showadvice);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("建议上报详情");

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowAdvicePresenter = new ShowAdvicePresenter(this, this);
        mShowAdvicePresenter.requestAdviceDetial(id);
    }

    private void initView() {
        adviceEntity = mShowAdvicePresenter.getAdviceDetialEntity();


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
