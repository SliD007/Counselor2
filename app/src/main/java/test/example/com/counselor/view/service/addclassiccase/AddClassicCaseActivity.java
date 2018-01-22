package test.example.com.counselor.view.service.addclassiccase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddClassicCaseActivity extends BaseActivity implements IAddClassicCaseView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.addClassicCaseTitleEt)
    EditText addClassicCaseTitleEt;
    @BindView(R.id.addClassicCaseContextEt)
    EditText addClassicCaseContextEt;
    @BindView(R.id.spinner01)
    Spinner spinner01;
    String vStr = "";
    List<String> list;
    ArrayAdapter<String> adapter;
    AddClassicCasePresenter mAddClassicCasePresenter;

    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addclassiccase);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mAddClassicCasePresenter = new AddClassicCasePresenter(this, this);
    }

    private void initView() {

        Intent i = getIntent();
        super.allow_quit = false;
        titleBarTv.setText("新增典型案例");
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
    }

    @OnClick({R.id.backTv, R.id.sumbitTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:
                String title = addClassicCaseTitleEt.getText().toString();
                String context_str = addClassicCaseContextEt.getText().toString();
                if(TextUtils.isEmpty(title)||TextUtils.isEmpty(context_str)){
                    toast("标题或内容不能为空",false);
                }else {
                    mAddClassicCasePresenter.addClassicCase(title, context_str, vStr);
                }
                break;
        }
    }


    @Override
    public void addSuccess() {
        toast("添加成功，下拉刷新列表", false);
        MyApplication.getInstance().refresh = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }

    @Override
    public void addFailed() {
        toast("添加失败", false);
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
