package test.example.com.counselor.view.service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.view.backlog.BacklogActivity;
import test.example.com.counselor.view.backlog.ReadedBacklogFragment;
import test.example.com.counselor.view.backlog.UnreadBacklogFragment;
import test.example.com.counselor.view.personal.PersonalActivity;
import test.example.com.counselor.view.schedule.ScheduleActivity;

/**
 * Created by Sli.D on 2017/12/19.
 */

public class ServiceActivity extends BaseActivity{

    @BindView(R.id.serviceVw1)
    View serviceVw1;
    @BindView(R.id.serviceTv1)
    TextView serviceTv1;
    @BindView(R.id.serviceVw2)
    View serviceVw2;
    @BindView(R.id.serviceTv2)
    TextView serviceTv2;
    @BindView(R.id.serviceVw3)
    View serviceVw3;
    @BindView(R.id.serviceTv3)
    TextView serviceTv3;
    @BindView(R.id.serviceVw4)
    View serviceVw4;
    @BindView(R.id.serviceTv4)
    TextView serviceTv4;
    @BindView(R.id.buttomIm3)
    ImageView buttomIm3;
    @BindView(R.id.buttomTv3)
    TextView buttomTv3;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    UnreadBacklogFragment mUnreadBacklogFragment;
    ReadedBacklogFragment mReadedBacklogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        buttomTv3.setTextColor(Color.rgb(1, 160, 243));
        setTabSelection(0);//初始化显示F0r

    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_service);
    }

    @OnClick({R.id.serviceRl1, R.id.serviceRl2, R.id.serviceRl3, R.id.serviceRl4})
    public void onSwitchClick(View view) {
        switch (view.getId()) {
            case R.id.serviceRl1:
                setTabSelection(0);
                break;
            case R.id.serviceRl2:
                setTabSelection(1);
                break;
            case R.id.serviceRl3:
                setTabSelection(2);
                break;
            case R.id.serviceRl4:
                setTabSelection(3);
                break;
        }
    }

    /*
    根据传入值设置不同fragment
     */
    private void setTabSelection(int index) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (index) {
            case 0:
                if (mUnreadBacklogFragment == null) {
                    mUnreadBacklogFragment = new UnreadBacklogFragment();
                    fragmentTransaction.add(R.id.serrviceFL, mUnreadBacklogFragment);
                }
                fragmentTransaction.replace(R.id.serrviceFL, mUnreadBacklogFragment);
                break;
            case 1:

                if (mReadedBacklogFragment == null) {
                    mReadedBacklogFragment = new ReadedBacklogFragment();
                }
                fragmentTransaction.replace(R.id.serrviceFL, mReadedBacklogFragment);
                break;
            case 2:
                if (mUnreadBacklogFragment == null) {
                    mUnreadBacklogFragment = new UnreadBacklogFragment();
                }
                fragmentTransaction.replace(R.id.serrviceFL, mUnreadBacklogFragment);
                break;
            case 3:

                if (mReadedBacklogFragment == null) {
                    mReadedBacklogFragment = new ReadedBacklogFragment();
                }
                fragmentTransaction.replace(R.id.serrviceFL, mReadedBacklogFragment);
                break;
        }
        fragmentTransaction.commit();
    }


    @OnClick({R.id.buttomRl1, R.id.buttomRl2, R.id.buttomRl3, R.id.buttomRl4})
    public void onButtomBarClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.buttomRl1:
                i = new Intent(ServiceActivity.this, BacklogActivity.class);
                startActivity(i);
                break;
            case R.id.buttomRl2:
                i = new Intent(ServiceActivity.this, ScheduleActivity.class);
                startActivity(i);
                break;
            case R.id.buttomRl3:
                break;
            case R.id.buttomRl4:
                i = new Intent(ServiceActivity.this, PersonalActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }


}
