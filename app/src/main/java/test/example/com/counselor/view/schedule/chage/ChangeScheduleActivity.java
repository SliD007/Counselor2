package test.example.com.counselor.view.schedule.chage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.CustomDatePicker;

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
        String workfor = i.getStringExtra("workfor");
        String worktime = i.getStringExtra("worktime");
        workForTv.setText(workfor);
        workTimeTv.setText(worktime);

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
    }

    @Override
    public void changeSuccess() {
        toast("修改成功！",false);

    }

    @Override
    public void changeFailed() {
        toast("修改失败！",false);
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
                mChangeSchedulePersenter.changeSchedule(newtime);
                MyApplication.getInstance().refresh = true;
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.workTimeTv:
                customDatePicker.show(workTimeTv.getText().toString());
                break;
        }
    }
}
