package test.example.com.counselor.view.service.addcommon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddCommonActivity extends BaseActivity {
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.addCommonTitleEt)
    EditText addCommonTitleEt;
    @BindView(R.id.addCommonContextEt)
    EditText addCommonContextEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addcommon);
    }

    private void initView() {

        Intent i = getIntent();
        super.allow_quit = false;
        int fragmentType = i.getIntExtra("fragmentType",0);
        if(fragmentType==2){
            titleBarTv.setText("新增典型案例");
        }else{
            titleBarTv.setText("新增月度总结");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.backPersonalTv, R.id.sumbitTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backPersonalTv:
                break;
            case R.id.sumbitTv:
                String title = addCommonTitleEt.getText().toString();
                String context_str = addCommonContextEt.getText().toString();
                sumbitAdvice(title,context_str);
                break;
        }
    }

    private void sumbitAdvice(String title, String context_str) {
        Log.e("Advice","title:"+title+",context:"+context_str);
    }


}
