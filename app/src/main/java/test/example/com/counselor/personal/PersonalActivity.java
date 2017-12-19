package test.example.com.counselor.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal);
    }

    @OnClick({R.id.rankListRl, R.id.assessRl, R.id.contractRl, R.id.changePwRl, R.id.versionTv, R.id.unloginRl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rankListRl:
                toast("rankListRl",false);
                break;
            case R.id.assessRl:
                toast("assessRl",false);
                break;
            case R.id.contractRl:
                toast("contractRl",false);
                break;
            case R.id.changePwRl:
                toast("changePwRl",false);
                break;
            case R.id.versionTv:
                toast("versionTv",false);
                break;
            case R.id.unloginRl:
                toast("unloginRl",false);
                break;
            default:
                break;
        }
    }
}
