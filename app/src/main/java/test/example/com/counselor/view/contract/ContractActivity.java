package test.example.com.counselor.view.contract;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.Common1Adapter;
import test.example.com.counselor.adapter.ViewHolder1;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;

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
    ArrayList<Map<String,Integer>> mData= new ArrayList<Map<String,Integer>>();
    List<ContractEntity> contractEntities;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mContractPresenter = new ContractPresenter(this);
        titleBarTv.setText("我的合同");
        mContext=this;
        mContractPresenter.requestContract();

    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contract);
    }

    private boolean show_vs = false;

    private void initDatas() {
        Log.e("AssessmentActivity", "加载数据");
        contractEntities = mContractPresenter.getContractEntity();
        contractLv.setAdapter(new Common1Adapter<ContractEntity>(this, contractEntities,
                R.layout.item_contract, onItemClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, ContractEntity contractEntity, int position) {
                TextView tv1 = mViewHolder.getView(R.id.itemWorkForTv);
                tv1.setText("  " + contractEntity.getVillage().getString("username"));

            }
        });
        contractLv.setOnItemClickListener(onItemClickListener);

        for(int i =0; i < 3; i++) {
            Map<String,Integer> item = new HashMap<String,Integer>();
            item.put("image", R.drawable.logo);
            mData.add(item);
        }
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
            ImageView nextIm = (ImageView) view.findViewById(R.id.nextIm);
            if (!show_vs) {
                nextIm.setImageResource(R.drawable.u608);
//                contractVs.inflate();
                contractVs.setVisibility(View.VISIBLE);
                initViewSub(position);

            }else {
                nextIm.setImageResource(R.drawable.u609);
                contractVs.setVisibility(View.GONE);
            }
            show_vs = !show_vs;
        }
    };

    private void initViewSub(int position){
        Log.e("initViewSub",""+position);
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        TextView tv4 = (TextView) findViewById(R.id.tv4);
        TextView tv5 = (TextView) findViewById(R.id.tv5);
        TextView tv6 = (TextView) findViewById(R.id.tv6);
        TextView tv7 = (TextView) findViewById(R.id.tv7);
        TextView tv8 = (TextView) findViewById(R.id.tv8);

        tv1.setText(contractEntities.get(position).getVillage().getString("username"));
        tv2.setText(contractEntities.get(position).getCounselor().getString("username"));
        tv3.setText(contractEntities.get(position).getRepresentative());
        tv4.setText(contractEntities.get(position).getOffice());
        tv5.setText(contractEntities.get(position).getServiceYear());
        tv6.setText(contractEntities.get(position).getDeadLine());
        tv7.setText(contractEntities.get(position).getMoney()+"");
        tv8.setText(contractEntities.get(position).getContractStatus());

        ListView contractImLV = (ListView) findViewById(R.id.contractImLV);
        contractImLV.setAdapter(new SimpleAdapter(mContext, mData, R.layout.item_contract_image_list
                , new String[]{"image"},new int[]{R.id.contractIm}));
    }

}
