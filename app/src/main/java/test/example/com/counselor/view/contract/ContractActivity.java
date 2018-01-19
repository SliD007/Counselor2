package test.example.com.counselor.view.contract;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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
//    @BindView(R.id.contractVs)
//    ViewStub contractVs;
    @BindView(R.id.contractLv)
    ListView contractLv;
    ArrayList<Map<String,Integer>> mData= new ArrayList<Map<String,Integer>>();
    List<ContractEntity> contractEntities;
    Context mContext;
    TextView downloadTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mContractPresenter = new ContractPresenter(this);
        titleBarTv.setText("我的合同");
        mContext=this;
        mContractPresenter.requestContract();
        initDatas();
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contract);
    }


    private void initDatas() {
        Log.e("AssessmentActivity", "加载数据");
        contractEntities = mContractPresenter.getContractEntity();
        contractLv.setAdapter(new Common1Adapter<ContractEntity>(this, contractEntities,
                R.layout.item_contract, onItemClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, ContractEntity contractEntity, final int position) {
                TextView tv1 = mViewHolder.getView(R.id.itemWorkForTv);
                TextView tv2 = mViewHolder.getView(R.id.tv2);
                TextView tv3 = mViewHolder.getView(R.id.tv3);
                TextView tv4 = mViewHolder.getView(R.id.tv4);
                TextView tv5 = mViewHolder.getView(R.id.tv5);
                TextView tv6 = mViewHolder.getView(R.id.tv6);
                TextView tv7 = mViewHolder.getView(R.id.tv7);
                TextView tv8 = mViewHolder.getView(R.id.tv8);
                downloadTv = mViewHolder.getView(R.id.tv9);
                if(contractEntities!=null){
                    final String village = contractEntities.get(position).getVillage().getString("username");
                    tv1.setText("服务村社："+contractEntities.get(position).getVillage().getString("username"));
                    tv2.setText("法律顾问："+contractEntities.get(position).getCounselor().getString("username"));
                    tv3.setText("村社法人："+contractEntities.get(position).getRepresentative());
                    tv4.setText("顾问机构："+contractEntities.get(position).getOffice());
                    tv5.setText("服务年度："+contractEntities.get(position).getServiceYear());
                    tv6.setText("合同期限："+contractEntities.get(position).getDeadLine());
                    tv7.setText("合同金额："+contractEntities.get(position).getMoney()+"");
                    tv8.setText("合同状态："+contractEntities.get(position).getContractStatus());
                    downloadTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            downloadTv.setText("下载中");
                            mContractPresenter.downLoadContract(village+"合同.pdf");
                        }
                    });
                }

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

    @Override
    public void downloadContractSuccess() {
        if(downloadTv!=null){
            downloadTv.setText("已下载，点击查看");
            downloadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    OpenFileUtil.openFile(this, Constants.getAppDownloadFolder()+"合同.pdf");
                }
            });
        }
    }

    @Override
    public void downloadContractFailed() {
        toast("下载失败",false);
        if(downloadTv!=null)
            downloadTv.setText("下载合同");
    }

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

}
