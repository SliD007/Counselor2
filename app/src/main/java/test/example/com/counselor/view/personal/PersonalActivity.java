package test.example.com.counselor.view.personal;

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

/**
 * Created by Sli.D on 2017/12/19.
 */

public class PersonalActivity extends BaseActivity {


    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.lawFirmTv)
    TextView lawFirmTv;
    @BindView(R.id.versionTv)
    TextView versionTv;

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.buttomIm4)
    ImageView buttomIm4;
    @BindView(R.id.buttomTv4)
    TextView buttomTv4;

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        buttomTv4.setTextColor(Color.rgb(1,160,243));
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal);
    }

    @OnClick({R.id.rankListRl, R.id.assessRl, R.id.contractRl, R.id.changePwRl, R.id.versionTv, R.id.unloginRl})
    public void onListClick(View view) {
        switch (view.getId()) {
            case R.id.rankListRl:
                toast("rankListRl", false);
                break;
            case R.id.assessRl:
                toast("assessRl", false);
                break;
            case R.id.contractRl:
                toast("contractRl", false);
                break;
            case R.id.changePwRl:
                toast("changePwRl", false);
                break;
            case R.id.versionTv:
                toast("versionTv", false);
                break;
            case R.id.unloginRl:
                toast("unloginRl", false);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.buttomRl1, R.id.buttomRl2, R.id.buttomRl3, R.id.buttomRl4})
    public void onButtomBarClick(View view) {
        switch (view.getId()) {
            case R.id.buttomRl1:
                i = new Intent(PersonalActivity.this, BacklogActivity.class);
                startActivity(i);
                break;
            case R.id.buttomRl2:
                toast("buttomRl2", false);
                break;
            case R.id.buttomRl3:
                toast("buttomRl3", false);
                break;
            case R.id.buttomRl4:
                toast("buttomRl4", false);
                break;
            default:
                break;
        }
    }
}
