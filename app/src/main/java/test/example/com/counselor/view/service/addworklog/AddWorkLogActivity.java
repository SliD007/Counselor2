package test.example.com.counselor.view.service.addworklog;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.CustomDatePicker;

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
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.editText5)
    EditText editText5;
    @BindView(R.id.textview6)
    TextView textview6;
    @BindView(R.id.textview7)
    TextView textview7;
    @BindView(R.id.spinner8)
    Spinner spinner8;
    @BindView(R.id.spinner9)
    Spinner spinner9;
    @BindView(R.id.spinner10)
    Spinner spinner10;
    @BindView(R.id.editText11)
    EditText editText11;

    List<String> list;
    ArrayAdapter<String> adapter;
    AddWorkLogPersenter mAddWorkLogPersenter;
    private CustomDatePicker customDatePicker;
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

        super.allow_quit = false;
        titleBarTv.setText("新增工作日志");
        //S1
        list = new ArrayList<String>();
        list.add("村民");
        list.add("村社");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(mOnItemClickListener);
        //S2
        list = new ArrayList<String>();
        list.add("坐班");
        list.add("电话咨询");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(mOnItemClickListener);
        //S8
        list = new ArrayList<String>();
        list.add("民事案件");
        list.add("刑事案件");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner8.setAdapter(adapter);
        spinner8.setOnItemSelectedListener(mOnItemClickListener);
        //S9
        list = new ArrayList<String>();
        list.add("咨询");
        list.add("指导");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner9.setAdapter(adapter);
        spinner9.setOnItemSelectedListener(mOnItemClickListener);
        //S10
        list = new ArrayList<String>();
        list.add("老年人");
        list.add("青少年");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner10.setAdapter(adapter);
        spinner10.setOnItemSelectedListener(mOnItemClickListener);
        //T6
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        textview6.setText(now);
        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textview6.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(true); // 显示时和分
        customDatePicker.setIsLoop(true); // 允许循环滚动
        //T7
        textview7.setText(now);
        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textview7.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(true); // 显示时和分
        customDatePicker.setIsLoop(true); // 允许循环滚动
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

    @OnClick({R.id.textview6, R.id.textview7})
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.textview6:
                customDatePicker.show(textview6.getText().toString());
                break;
            case R.id.textview7:
                customDatePicker.show(textview6.getText().toString());
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
