package test.example.com.counselor.view;

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
import test.example.com.counselor.view.backlog.BacklogFragment;
import test.example.com.counselor.view.schedule.ScheduleFragment;
import test.example.com.counselor.view.service.ServiceFragment;

/**
 * Created by Sli.D on 2017/12/19.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.buttomIm1)
    ImageView buttomIm1;
    @BindView(R.id.buttomTv1)
    TextView buttomTv1;
    @BindView(R.id.buttomIm2)
    ImageView buttomIm2;
    @BindView(R.id.buttomTv2)
    TextView buttomTv2;
    @BindView(R.id.buttomIm3)
    ImageView buttomIm3;
    @BindView(R.id.buttomTv3)
    TextView buttomTv3;
    @BindView(R.id.buttomIm4)
    ImageView buttomIm4;
    @BindView(R.id.buttomTv4)
    TextView buttomTv4;

    BacklogFragment mBacklogFragment;
    ScheduleFragment mScheduleFragment;
    ServiceFragment mServiceFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        buttomTv1.setTextColor(Color.rgb(1, 160, 243));
        //初始化第一个F
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        mBacklogFragment = new BacklogFragment();
        fragmentTransaction.add(R.id.mainFL,mBacklogFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }


    @OnClick({R.id.buttomRl1, R.id.buttomRl2, R.id.buttomRl3, R.id.buttomRl4})
    public void onButtomBarClick(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.buttomRl1:
                break;
            case R.id.buttomRl2:
                buttomTv2.setTextColor(Color.rgb(1, 160, 243));
                if(mScheduleFragment==null){
                    mScheduleFragment = new ScheduleFragment();
                }
                fragmentTransaction.replace(R.id.mainFL,mScheduleFragment);
                break;
            case R.id.buttomRl3:
                buttomTv3.setTextColor(Color.rgb(1, 160, 243));
                if(mServiceFragment==null){
                    mServiceFragment = new ServiceFragment();
                }
                fragmentTransaction.replace(R.id.mainFL,mServiceFragment);
                break;
            case R.id.buttomRl4:
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

}
