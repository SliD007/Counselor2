package test.example.com.counselor.view.schedule;

/**
 * Created by Sli.D on 2017/12/20.
 */


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.view.backlog.BacklogActivity;
import test.example.com.counselor.view.personal.PersonalActivity;
import test.example.com.counselor.view.service.ServiceActivity;

public class ScheduleActivity extends BaseActivity{

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.buttomIm2)
    ImageView buttomIm2;
    @BindView(R.id.buttomTv2)
    TextView buttomTv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        buttomTv2.setTextColor(Color.rgb(1,160,243));
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_schedule);
    }

    @OnClick({R.id.buttomRl1, R.id.buttomRl2, R.id.buttomRl3, R.id.buttomRl4})
    public void onButtomBarClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.buttomRl1:
                i = new Intent(ScheduleActivity.this, BacklogActivity.class);
                startActivity(i);
                break;
            case R.id.buttomRl2:
                break;
            case R.id.buttomRl3:
                i = new Intent(ScheduleActivity.this, ServiceActivity.class);
                startActivity(i);
                break;
            case R.id.buttomRl4:
                i = new Intent(ScheduleActivity.this, PersonalActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
