package test.example.com.counselor.view.service.addworklog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

        list = new ArrayList<String>();
        list.add("村民");
        list.add("村社");
        adapter=new ArrayAdapter<String>(this, R.layout.spinner_show_worklog,list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(mOnItemClickListener);
        list = new ArrayList<String>();
        list.add("坐班");
        list.add("电话咨询");
        adapter=new ArrayAdapter<String>(this, R.layout.spinner_show_worklog,list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(mOnItemClickListener);

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
            Log.e("view.getId():"+view.getId(),"R.id.spinner1:"+R.id.spinner1);
            toast(view.getId()+" "+id+" "+position,true);
            switch(view.getId()){
                case R.id.spinner1:
                    toast("R.id.spinner1:"+id+" "+position,false);
                    break;
                case R.id.spinner2:
                    toast("R.id.spinner2:"+id+" "+position,false);
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
