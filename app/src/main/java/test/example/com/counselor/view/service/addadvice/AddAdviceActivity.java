package test.example.com.counselor.view.service.addadvice;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddAdviceActivity extends BaseActivity {
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addadvice);
        ButterKnife.bind(this);
        super.allow_quit = false;
        titleBarTv.setText("新增建议上报");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
