package test.example.com.counselor.view.service.addadvice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.PDialog;

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
    @BindView(R.id.spinner01)
    Spinner spinner01;
    int rbId = -1;
    String vStr = "";
    List<String> list;
    ArrayAdapter<String> adapter;
    AddAdvicePresenter mAddAdvicePersenter;
    PDialog dialog;
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
        //S01
        list = new ArrayList<String>();
        if (MyApplication.getInstance().loginEntity.getCommunityA()!=null)
            list.add(MyApplication.getInstance().loginEntity.getCommunityA().getString("username"));
        if (MyApplication.getInstance().loginEntity.getCommunityB()!=null)
            list.add(MyApplication.getInstance().loginEntity.getCommunityB().getString("username"));
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner01.setAdapter(adapter);
        spinner01.setOnItemSelectedListener(mOnItemClickListener);

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
                if(TextUtils.isEmpty(title)||TextUtils.isEmpty(context_str)||rbId==-1){
                    toast("标题或内容不能为空",false);
                }else {
                    dialog = new PDialog(this,"正在提交",false);
                    dialog.show();
                    mAddAdvicePersenter.addAdvice(title, context_str, rbId, vStr);
                }
                break;
        }
    }


    @Override
    public void addSuccess() {
        toast("添加成功，下拉刷新列表", false);
        dialog.dismiss();
        MyApplication.getInstance().refresh[1] = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();

    }

    @Override
    public void addFailed() {
        toast("添加失败",false);
    }

    AdapterView.OnItemSelectedListener mOnItemClickListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            vStr = parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
