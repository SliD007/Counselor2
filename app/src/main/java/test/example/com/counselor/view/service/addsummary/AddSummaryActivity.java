package test.example.com.counselor.view.service.addsummary;

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
import test.example.com.counselor.util.PDialog;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddSummaryActivity extends BaseActivity implements IAddSummaryView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.addSummaryTitleEt)
    EditText addSummaryTitleEt;
    @BindView(R.id.addSummaryContextEt)
    EditText addSummaryContextEt;
    @BindView(R.id.spinner01)
    Spinner spinner01;

    PDialog dialog;

    List<String> list;
    String vStr = "";

    ArrayAdapter<String> adapter;
    AddSummaryPresenter mAddSummaryPresenter;
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addsummary);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mAddSummaryPresenter = new AddSummaryPresenter(this, this);
    }

    private void initView() {

        Intent i = getIntent();
        super.allow_quit = false;
        titleBarTv.setText("新增月度总结");
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
                String title = addSummaryTitleEt.getText().toString();
                String context_str = addSummaryContextEt.getText().toString();
                if(TextUtils.isEmpty(title)||TextUtils.isEmpty(context_str)){
                    toast("标题或内容不能为空",false);
                }else {
                    dialog = new PDialog(this,"正在提交",false);
                    dialog.show();
                    mAddSummaryPresenter.addSummary(title, context_str,vStr);
                }

                break;
        }
    }
    AdapterView.OnItemSelectedListener mOnItemClickListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (parent.getId()) {
                case R.id.spinner01:
                    vStr = parent.getSelectedItem().toString();
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void addSuccess() {
        dialog.dismiss();
        toast("添加成功", false);
        MyApplication.getInstance().refresh[3] = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }

    @Override
    public void addFailed() {
        toast("添加失败", false);
    }
}
