package test.example.com.counselor.view.contract;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.Common1Adapter;
import test.example.com.counselor.adapter.ViewHolder1;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.base.MyLvClickListener;

public class ContractActivity extends BaseActivity implements IContractView {


    ContractPresenter mContractPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.backTv)
    TextView backTv;
    @BindView(R.id.contractVs)
    ViewStub contractVs;
    @BindView(R.id.contractLv)
    ListView contractLv;

    List<ContractEntity> rankEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mContractPresenter = new ContractPresenter(this);
        titleBarTv.setText("我的合同");

        mContractPresenter.requestContract();
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contract);
    }

    private boolean show_vs = false;

    private void initDatas() {
        Log.e("ContractActivity", "加载数据");
        rankEntities = mContractPresenter.getContractEntity();
        contractLv.setAdapter(new Common1Adapter<ContractEntity>(this, rankEntities,
                R.layout.item_contract, onItemClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, ContractEntity contractEntity, int position) {
                TextView tv1 = mViewHolder.getView(R.id.itemWorkForTv);
                tv1.setText("  " + contractEntity.getWorkFor());

            }
        });
        contractLv.setOnItemClickListener(onItemClickListener);
    }

    @OnClick({R.id.backTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
        }
    }

    @Override
    public void requestContractSuccess() {
        toast("请求成功！", true);
        initDatas();
    }

    @Override
    public void requestContractFailed() {
        toast("请求失败！", true);
    }

    @OnClick(R.id.backTv)
    public void onClick() {
    }

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toast("" + (position), true);
            ImageView im = (ImageView) view.findViewById(R.id.nextIm);
            if (!show_vs) {
                im.setImageResource(R.drawable.u608);
                contractVs.setVisibility(View.VISIBLE);
            }else {
                im.setImageResource(R.drawable.u609);
                contractVs.setVisibility(View.GONE);
            }
            show_vs = !show_vs;
        }
    };

    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
//            toast("" + (position), true);

        }

        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
//            myOnClick((Integer) v.getTag(), v);
            initDatas();
        }

    };
}
