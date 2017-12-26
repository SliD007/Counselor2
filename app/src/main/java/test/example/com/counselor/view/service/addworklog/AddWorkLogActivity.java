package test.example.com.counselor.view.service.addworklog;

import android.content.Intent;
import android.os.Bundle;
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

public class AddWorkLogActivity extends BaseActivity implements IAddWorkLogView {
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    List<String> list;
    ArrayAdapter<String> adapter;
    AddWorkLogPersenter mAddWorkLogPersenter;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.editText5)
    EditText editText5;
    @BindView(R.id.spinner6)
    Spinner spinner6;
    @BindView(R.id.spinner7)
    Spinner spinner7;
    @BindView(R.id.spinner8)
    Spinner spinner8;
    @BindView(R.id.spinner9)
    Spinner spinner9;
    @BindView(R.id.spinner10)
    Spinner spinner10;
    @BindView(R.id.editText11)
    EditText editText11;

    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addworklog);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mAddWorkLogPersenter = new AddWorkLogPersenter(this, this);
    }

    private void initView() {

        Intent i = getIntent();
        super.allow_quit = false;
        titleBarTv.setText("新增工作日志");
        //S1
        list = new ArrayList<String>();
        list.add("选择");
        list.add("村民");
        list.add("村社");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(mOnItemClickListener);
        //S2
        list = new ArrayList<String>();
        list.add("选择");
        list.add("坐班");
        list.add("电话咨询");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(mOnItemClickListener);
        //S6
        list = new ArrayList<String>();
        list.add("选择");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner6.setAdapter(adapter);
        spinner6.setOnItemSelectedListener(mOnItemClickListener);
        //S7
        list = new ArrayList<String>();
        list.add("选择");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner7.setAdapter(adapter);
        spinner7.setOnItemSelectedListener(mOnItemClickListener);
        //S8
        list = new ArrayList<String>();
        list.add("选择");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner8.setAdapter(adapter);
        spinner8.setOnItemSelectedListener(mOnItemClickListener);
        //S9
        list = new ArrayList<String>();
        list.add("选择");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner9.setAdapter(adapter);
        spinner9.setOnItemSelectedListener(mOnItemClickListener);
        //S10
        list = new ArrayList<String>();
        list.add("选择");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner10.setAdapter(adapter);
        spinner10.setOnItemSelectedListener(mOnItemClickListener);
    }

    @OnClick({R.id.backTv, R.id.sumbitTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:

                mAddWorkLogPersenter.addWorkLog(0, null, null);
                break;
        }
    }

    AdapterView.OnItemSelectedListener mOnItemClickListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (parent.getId()) {
                case R.id.spinner1:
                    toast("R.id.spinner1" + parent.getSelectedItem() + position, false);
                    break;
                case R.id.spinner2:
                    toast("R.id.spinner2" + parent.getSelectedItem() + position, false);
                    break;
                case R.id.spinner6:
                    toast("R.id.spinner6" + parent.getSelectedItem() + position, false);
                    break;
                case R.id.spinner7:
                    toast("R.id.spinner7" + parent.getSelectedItem() + position, false);
                    break;
                case R.id.spinner8:
                    toast("R.id.spinner8" + parent.getSelectedItem() + position, false);
                    break;
                case R.id.spinner9:
                    toast("R.id.spinner9" + parent.getSelectedItem() + position, false);
                    break;
                case R.id.spinner10:
                    toast("R.id.spinner10" + parent.getSelectedItem() + position, false);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    @Override
    public void addSuccess() {
        toast("添加成功", false);
    }

    @Override
    public void addFailed() {
        toast("添加失败", false);
    }


}
