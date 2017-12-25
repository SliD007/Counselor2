package test.example.com.counselor.view.service.addadvice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddAdviceActivity extends BaseActivity implements IAddAdviceView{
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.addAdviceTitleEt)
    EditText addAdviceTitleEt;
    @BindView(R.id.addAdviceContextEt)
    EditText addAdviceContextEt;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    int rbId;
    AddAdvicePersenter mAddAdvicePersenter;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addadvice);
        ButterKnife.bind(this);
        super.allow_quit = false;
        titleBarTv.setText("新增建议上报");
        initView();
        mAddAdvicePersenter = new AddAdvicePersenter(this,this);
    }

    private void initView() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        rbId = 0;
                        break;
                    case R.id.radioButton2:
                        rbId = 1;
                        break;
                    case R.id.radioButton3:
                        rbId = 3;
                        break;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                String title = addAdviceTitleEt.getText().toString();
                String context_str = addAdviceContextEt.getText().toString();
                mAddAdvicePersenter.addAdvice(title,context_str,rbId);
                break;
        }
    }



    @Override
    public void addSuccess() {
        toast("添加成功",false);
//        Intent i = new Intent(AddAdviceActivity.this, HomeActivity.class);
//        i.putExtra("fragmentId",3);
//        startActivity(i);

    }

    @Override
    public void addFailed() {
        toast("添加失败",false);
    }
}
