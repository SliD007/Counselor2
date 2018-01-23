package test.example.com.counselor.view.schedule.chage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import test.example.com.counselor.util.TimeUtil;

/**
 * Created by Sli.D on 2017/12/26.
 */

public class ChangeScheduleActivity extends BaseActivity implements IChangeSchedule {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.workForTv)
    TextView workForTv;
    @BindView(R.id.workTimeTv)
    TextView workTimeTv;
    @BindView(R.id.workWaySp)
    Spinner workWaySp;

    List<String> list;
    ArrayAdapter<String> adapter;
    private int ScheduleId;
    private int jobType;
    String workWay;

    ChangeSchedulePersenter mChangeSchedulePersenter;
    private CustomDatePicker customDatePicker;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_changeschedule);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mChangeSchedulePersenter = new ChangeSchedulePersenter(this, this);
    }

    private void initView() {
        super.allow_quit = false;
        titleBarTv.setText("修改工作安排");
        Intent i = getIntent();
        ScheduleId = i.getIntExtra("id",0);
        String workfor = i.getStringExtra("workfor");
        Long worktime = i.getLongExtra("worktime",0);
        String jobType = i.getStringExtra("jobType");
        workForTv.setText(workfor);
        workTimeTv.setText(TimeUtil.getDateToString(worktime,TimeUtil.Data));
        //T
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());

        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                workTimeTv.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(false); // 不允许循环滚动
        //S
        list = new ArrayList<String>();
        list.add("坐班");
        list.add("讲座");
        list.add("电话");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        workWaySp.setAdapter(adapter);
        workWaySp.setOnItemSelectedListener(mOnItemClickListener);
        int k= adapter.getCount();
        for(int j=0;j<k;j++){
            if(jobType.equals(adapter.getItem(j).toString())){
                workWaySp.setSelection(j,true);// 默认选中项
                break;
            }
        }
    }

    @Override
    public void changeSuccess() {
        toast("修改请求已发送，等待审核", false);
        MyApplication.getInstance().finishActivity(this);
        this.finish();

    }

    @Override
    public void changeFailed() {
        toast("修改失败", false);
    }

    @OnClick({R.id.backTv, R.id.sumbitTv, R.id.workTimeTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:
                String newtime = workTimeTv.getText().toString();
                mChangeSchedulePersenter.changeSchedule(ScheduleId,newtime,jobType);
                break;
            case R.id.workTimeTv:
                Log.e("workTimeTv",""+workTimeTv.getText().toString());
                customDatePicker.show(workTimeTv.getText().toString());
                break;
        }
    }

    AdapterView.OnItemSelectedListener mOnItemClickListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            workWay = parent.getSelectedItem().toString();
            jobType = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            workWay = "default";
        }
    };

}
