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
import test.example.com.counselor.base.MyApplication;

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
    AddAdvicePresenter mAddAdvicePersenter;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addadvice);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        titleBarTv.setText("新增建议上报");
        initView();
        mAddAdvicePersenter = new AddAdvicePresenter(this,this);
    }

    private void initView() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        rbId = 2;
                        break;
                    case R.id.radioButton2:
                        rbId = 1;
                        break;
                    case R.id.radioButton3:
                        rbId = 0;
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.backTv, R.id.sumbitTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:
                String title = addAdviceTitleEt.getText().toString();
                String context_str = addAdviceContextEt.getText().toString();
                mAddAdvicePersenter.addAdvice(title,context_str,rbId);
                mAddAdvicePersenter.addadvice(title,context_str,rbId);
                break;
        }
    }


    @Override
    public void addSuccess() {
        toast("添加成功",false);
        MyApplication.getInstance().refresh = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();

    }

    @Override
    public void addFailed() {
        toast("添加失败",false);
    }
}
