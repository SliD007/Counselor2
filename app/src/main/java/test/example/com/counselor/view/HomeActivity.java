package test.example.com.counselor.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.FragmentAdapter;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.view.task.TaskFragment;
import test.example.com.counselor.view.personal.PersonalFragment;
import test.example.com.counselor.view.schedule.ScheduleFragment;
import test.example.com.counselor.view.service.ServiceFragment;

/**
 * Created by Sli.D on 2017/12/19.
 */

public class HomeActivity extends BaseActivity {

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
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private int fragmentId;
    TaskFragment mTaskFragment;
    ScheduleFragment mScheduleFragment;
    ServiceFragment mServiceFragment;
    PersonalFragment mPersonalFragment;
    private List<Fragment> list;// 声明一个list集合存放Fragment（数据源）
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent i = getIntent();
        fragmentId = i.getIntExtra("fragmentId",0);
        initView();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
    }

    private void initView(){
        buttomTv1.setTextColor(Color.rgb(14, 130, 193));
        buttomIm1.setImageResource(R.drawable.u2336);
        list = new ArrayList<Fragment>();
        mTaskFragment = new TaskFragment();
        mScheduleFragment = new ScheduleFragment();
        mServiceFragment = new ServiceFragment();
        mPersonalFragment = new PersonalFragment();
        list.add(mTaskFragment);
        list.add(mScheduleFragment);
        list.add(mServiceFragment);
        list.add(mPersonalFragment);
        //设置适配器
        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),list);
        viewpager.setAdapter(mFragmentAdapter);
        //设置滑动监听
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                clearStatus();
                switch (position) {
                    case 0:
                        buttomTv1.setTextColor(Color.rgb(14, 130, 193));
                        buttomIm1.setImageResource(R.drawable.u2336);
                        break;
                    case 1:
                        buttomTv2.setTextColor(Color.rgb(14, 130, 193));
                        buttomIm2.setImageResource(R.drawable.u2346);
                        break;
                    case 2:
                        buttomTv3.setTextColor(Color.rgb(14, 130, 193));
                        buttomIm3.setImageResource(R.drawable.u2349);
                        break;
                    case 3:
                        buttomTv4.setTextColor(Color.rgb(14, 130, 193));
                        buttomIm4.setImageResource(R.drawable.u1741);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.buttomRl1, R.id.buttomRl2, R.id.buttomRl3, R.id.buttomRl4})
    public void onButtomBarClick(View view) {
        switch (view.getId()) {
            case R.id.buttomRl1:
                viewpager.setCurrentItem(0);
                break;
            case R.id.buttomRl2:
                viewpager.setCurrentItem(1);
                break;
            case R.id.buttomRl3:
                viewpager.setCurrentItem(2);
                break;
            case R.id.buttomRl4:
                viewpager.setCurrentItem(3);
                break;
        }
    }

    private void clearStatus() {
        buttomTv1.setTextColor(Color.rgb(255, 255, 255));
        buttomTv2.setTextColor(Color.rgb(255, 255, 255));
        buttomTv3.setTextColor(Color.rgb(255, 255, 255));
        buttomTv4.setTextColor(Color.rgb(255, 255, 255));
        buttomIm1.setImageResource(R.drawable.u2335);
        buttomIm2.setImageResource(R.drawable.u2345);
        buttomIm3.setImageResource(R.drawable.u2348);
        buttomIm4.setImageResource(R.drawable.u1740);
    }

}
