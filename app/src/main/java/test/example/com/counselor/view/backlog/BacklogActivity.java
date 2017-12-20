package test.example.com.counselor.view.backlog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.view.personal.PersonalActivity;

/**
 * Created by Sli.D on 2017/12/19.
 */

public class BacklogActivity extends BaseActivity {


    @BindView(R.id.backlogLeftVw)
    View backlogLeftVw;
    @BindView(R.id.backlogLeftTv)
    TextView backlogLeftTv;
    @BindView(R.id.backlogRightVw)
    View backlogRightVw;
    @BindView(R.id.backlogRightTv)
    TextView backlogRightTv;
    @BindView(R.id.buttomIm1)
    ImageView buttomIm1;
    @BindView(R.id.buttomTv1)
    TextView buttomTv1;
    @BindView(R.id.backlogFL)
    FrameLayout backlogFL;

    UnreadBacklogFragment mUnreadBacklogFragment;
    ReadedBacklogFragment mReadedBacklogFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        buttomTv1.setTextColor(Color.rgb(1, 160, 243));
        setTabSelection(0);//初始化显示F0
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_backlog);
    }

    /*
    根据传入值设置不同fragment
     */
    private void setTabSelection(int index) {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (index){
            case 0:
                backlogLeftTv.setTextColor(Color.rgb(255, 255, 255));
                backlogLeftVw.setBackgroundColor(Color.rgb(1, 160, 243));
                backlogRightTv.setTextColor(Color.rgb(102, 102, 102));
                backlogRightVw.setBackgroundColor(Color.rgb(48, 49, 53));
                if(mUnreadBacklogFragment==null){
                    mUnreadBacklogFragment = new UnreadBacklogFragment();
                    fragmentTransaction.add(R.id.backlogFL,mUnreadBacklogFragment);
                }
                fragmentTransaction.replace(R.id.backlogFL,mUnreadBacklogFragment);
                break;
            case 1:
                backlogLeftTv.setTextColor(Color.rgb(102, 102, 102));
                backlogLeftVw.setBackgroundColor(Color.rgb(48, 49, 53));
                backlogRightTv.setTextColor(Color.rgb(255, 255, 255));
                backlogRightVw.setBackgroundColor(Color.rgb(1, 160, 243));
                if(mReadedBacklogFragment==null){
                    mReadedBacklogFragment = new ReadedBacklogFragment();
                }
                fragmentTransaction.replace(R.id.backlogFL,mReadedBacklogFragment);
                break;
        }
        fragmentTransaction.commit();
    }

        @OnClick({R.id.backlogLeftRl, R.id.backlogRightRl})
    public void onSwitchClick(View view) {
        switch (view.getId()) {
            case R.id.backlogLeftRl:
                setTabSelection(0);
                break;
            case R.id.backlogRightRl:
                setTabSelection(1);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.buttomRl1, R.id.buttomRl2, R.id.buttomRl3, R.id.buttomRl4})
    public void onButtomBarClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.buttomRl1:
                break;
            case R.id.buttomRl2:
                toast("buttomRl2", false);
                break;
            case R.id.buttomRl3:
                toast("buttomRl3", false);
                break;
            case R.id.buttomRl4:
                i = new Intent(BacklogActivity.this, PersonalActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

}
