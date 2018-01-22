package test.example.com.counselor.view.service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.CommonAdapter;
import test.example.com.counselor.base.BaseFragment;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.base.MyLvClickListener;
import test.example.com.counselor.util.TimeUtil;
import test.example.com.counselor.view.service.addadvice.AddAdviceActivity;
import test.example.com.counselor.view.service.addchargecase.AddChargeCaseActivity;
import test.example.com.counselor.view.service.addclassiccase.AddClassicCaseActivity;
import test.example.com.counselor.view.service.addgroupcase.AddGroupCaseActivity;
import test.example.com.counselor.view.service.addsummary.AddSummaryActivity;
import test.example.com.counselor.view.service.addworklog.AddWorkLogActivity;
import test.example.com.counselor.view.service.entity.AdviceEntity;
import test.example.com.counselor.view.service.entity.ClassicCaseEntity;
import test.example.com.counselor.view.service.entity.SummaryEntity;
import test.example.com.counselor.view.service.entity.WorkLogEntity;
import test.example.com.counselor.view.service.showadvice.ShowAdviceActivity;
import test.example.com.counselor.view.service.showchargecase.ShowChargeCaseActivity;
import test.example.com.counselor.view.service.showclassiccase.ShowClassicCaseActivity;
import test.example.com.counselor.view.service.showgroupcase.ShowGroupCaseActivity;
import test.example.com.counselor.view.service.showsummary.ShowSummaryActivity;
import test.example.com.counselor.view.service.showworklog.ShowWorkLogActivity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ServiceFragment extends BaseFragment implements IServiceView{

    @BindView(R.id.serviceTv1)
    TextView serviceTv1;
    @BindView(R.id.serviceTv2)
    TextView serviceTv2;
    @BindView(R.id.serviceTv3)
    TextView serviceTv3;
    @BindView(R.id.serviceTv4)
    TextView serviceTv4;
    @BindView(R.id.serviceVw1)
    View serviceVw1;
    @BindView(R.id.serviceVw2)
    View serviceVw2;
    @BindView(R.id.serviceVw3)
    View serviceVw3;
    @BindView(R.id.serviceVw4)
    View serviceVw4;
    @BindView(R.id.noneTv)
    TextView noneTv;
    ViewGroup.LayoutParams para;
    String noneStr = "加载中";
    private int fragmentType;
    private int[] fragmentCuttent;
    ServicePresenter mServicePersenter;
    private int requestSize=10;
    List<WorkLogEntity> workLogEntities;
    List<AdviceEntity> adviceEntities;
    List<ClassicCaseEntity> classicCaseEntities;
    List<SummaryEntity> summaryEntities;

    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private CommonAdapter mAdapter;
    private int refreshTime = 0;
    private int times = 0;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initPresenter() {
        fragmentType = 0;
        fragmentCuttent = new int[]{1, 1, 1, 1};
        mServicePersenter = new ServicePresenter(this);
        mServicePersenter.requestServiceData(fragmentCuttent[0],requestSize,0);
        mServicePersenter.requestServiceData(fragmentCuttent[1],requestSize,1);
        mServicePersenter.requestServiceData(fragmentCuttent[2],requestSize,2);
        mServicePersenter.requestServiceData(fragmentCuttent[3],requestSize,3);
    }

    @Override
    protected void initViews() {
        setTabSelection(fragmentType);
        if(MyApplication.getInstance().refresh){
            mServicePersenter.requestServiceData(fragmentCuttent[fragmentType],requestSize,fragmentType);
            MyApplication.getInstance().refresh = false;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        para = noneTv.getLayoutParams();
    }

    @Override
    protected void initEvents() {}

    @Override
    protected void initDatas() {
        Log.e("ServiceFragment","加载数据");

        if (fragmentType == 0) {
            workLogEntities = mServicePersenter.getWorkLogEntities();
            if(workLogEntities!=null){
                para.height = 0;
                noneTv.setLayoutParams(para);

            }else {
                para.height = 100;
                noneTv.setLayoutParams(para);
                noneTv.setText(noneStr);
            }
            mAdapter = new CommonAdapter(mContext,workLogEntities,R.layout.item_4list,mClickListener){
                public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                    super.onBindViewHolder(viewHolder,position);

                    TextView tv1 = viewHolder.getView(R.id.itemTv1);
                    TextView tv2 = viewHolder.getView(R.id.itemTv2);
                    TextView tv3 = viewHolder.getView(R.id.itemTv3);
                    TextView tv4 = viewHolder.getView(R.id.itemTv4);
                    tv1.setText("工作记录类型："+ workLogEntities.get(position).getLogType());
                    tv2.setText("服务对象："+workLogEntities.get(position).getServiceObject());
                    tv3.setText("服务单位："+workLogEntities.get(position).getServiceVillageName());
                    tv4.setText("完结状态："+workLogEntities.get(position).getResultType());
                    LinearLayout ll = viewHolder.getView(R.id.itemLl);
                    ll.setTag(position);
                    ll.setOnClickListener(mClickListener);
                }
            };
            mRecyclerView.setAdapter(mAdapter);
        }else if(fragmentType==1){
            adviceEntities = mServicePersenter.getAdviceEntities();
            if(adviceEntities!=null){
                para.height = 0;
                noneTv.setLayoutParams(para);

            }else {
                para.height = 100;
                noneTv.setLayoutParams(para);
                noneTv.setText(noneStr);
            }
            mAdapter = new CommonAdapter(mContext,adviceEntities,R.layout.item_4list,mClickListener){
                public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                    super.onBindViewHolder(viewHolder,position);

                    TextView tv1 = viewHolder.getView(R.id.itemTv1);
                    TextView tv2 = viewHolder.getView(R.id.itemTv2);
                    TextView tv3 = viewHolder.getView(R.id.itemTv3);
                    TextView tv4 = viewHolder.getView(R.id.itemTv4);
                    tv1.setText(adviceEntities.get(position).getTitle());
                    tv2.setText("报送至："+adviceEntities.get(position).getToType());
                    tv3.setText("服务时间："+ TimeUtil.getDateToString(adviceEntities.get(position).getCreateTime(),TimeUtil.Data));
                    tv4.setText("服务单位："+adviceEntities.get(position).getVillage());
                    LinearLayout ll = viewHolder.getView(R.id.itemLl);
                    ll.setTag(position);
                    ll.setOnClickListener(mClickListener);
                }
            };
            mRecyclerView.setAdapter(mAdapter);
        }else if(fragmentType==2){
            classicCaseEntities = mServicePersenter.getClassicCaseEntities();
            if(classicCaseEntities!=null){
                para.height = 0;
                noneTv.setLayoutParams(para);

            }else {
                para.height = 100;
                noneTv.setLayoutParams(para);
                noneTv.setText(noneStr);
            }
            mAdapter = new CommonAdapter(mContext,classicCaseEntities,R.layout.item_3list,mClickListener){
                public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                    super.onBindViewHolder(viewHolder,position);

                    TextView tv1 = viewHolder.getView(R.id.itemTv1);
                    TextView tv2 = viewHolder.getView(R.id.itemTv2);
                    TextView tv3 = viewHolder.getView(R.id.itemTv3);
                    tv1.setText(classicCaseEntities.get(position).getTitle());
                    tv2.setText("服务时间："+ TimeUtil.getDateToString(classicCaseEntities.get(position).getCreateTime(),TimeUtil.Data));
                    tv3.setText("服务单位："+classicCaseEntities.get(position).getVillage());
                    LinearLayout ll = viewHolder.getView(R.id.itemLl);
                    ll.setTag(position);
                    ll.setOnClickListener(mClickListener);
                }
            };
            mRecyclerView.setAdapter(mAdapter);
        }else {
            summaryEntities = mServicePersenter.getSummaryEntities();
            if(summaryEntities!=null){
                para.height = 0;
                noneTv.setLayoutParams(para);

            }else {
                para.height = 100;
                noneTv.setLayoutParams(para);
                noneTv.setText(noneStr);
            }
            mAdapter = new CommonAdapter(mContext,summaryEntities,R.layout.item_3list,mClickListener){
                public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                    super.onBindViewHolder(viewHolder,position);

                    TextView tv1 = viewHolder.getView(R.id.itemTv1);
                    TextView tv2 = viewHolder.getView(R.id.itemTv2);
                    TextView tv3 = viewHolder.getView(R.id.itemTv3);
                    tv1.setText(summaryEntities.get(position).getTitle());
                    tv2.setText("时间："+ TimeUtil.getDateToString(summaryEntities.get(position).getCreateTime(),TimeUtil.Data));
                    tv3.setText("服务单位："+summaryEntities.get(position).getVillage());
                    LinearLayout ll = viewHolder.getView(R.id.itemLl);
                    ll.setTag(position);
                    ll.setOnClickListener(mClickListener);
                }
            };
            mRecyclerView.setAdapter(mAdapter);
        }
        noneStr = "没有内容";
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        if(fragmentCuttent[fragmentType]>1)
                            fragmentCuttent[fragmentType]--;
                        mServicePersenter.requestServiceData(fragmentCuttent[fragmentType],requestSize,fragmentType);

                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            if(hasNests[fragmentType]){
                                fragmentCuttent[fragmentType]++;
                                mServicePersenter.requestServiceData(fragmentCuttent[fragmentType],requestSize,fragmentType);
                            }else {
                                toast("没有更多了",false);
                            }


                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if(hasNests[fragmentType]){
                                fragmentCuttent[fragmentType]++;
                                mServicePersenter.requestServiceData(fragmentCuttent[fragmentType],requestSize,fragmentType);
                            }else {
                                toast("没有更多了",false);
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times ++;
            }
        });
    }

    @OnClick({R.id.serviceRl1, R.id.serviceRl2, R.id.serviceRl3, R.id.serviceRl4})
    public void onSelectClick(View view) {
        clearStatus();
        switch (view.getId()) {
            case R.id.serviceRl1:
                fragmentType = 0;
                break;
            case R.id.serviceRl2:
                fragmentType = 1;
                break;
            case R.id.serviceRl3:
                fragmentType = 2;
                break;
            case R.id.serviceRl4:
                fragmentType = 3;
        }
        setTabSelection(fragmentType);
        initDatas();
    }

    @OnClick({ R.id.serviceRl5})
    public void onAddClick(View view) {
        Intent i;
        switch (fragmentType) {
            case 0:
                showAddWorkLogDialog();
                break;
            case 1:
                i = new Intent(getActivity(), AddAdviceActivity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(getActivity(), AddClassicCaseActivity.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(getActivity(), AddSummaryActivity.class);
                startActivity(i);
                break;
        }
    }

    private void clearStatus() {
        serviceTv1.setTextColor(Color.rgb(144, 144, 144));
        serviceTv2.setTextColor(Color.rgb(144, 144, 144));
        serviceTv3.setTextColor(Color.rgb(144, 144, 144));
        serviceTv4.setTextColor(Color.rgb(144, 144, 144));
        serviceVw1.setBackgroundColor(Color.rgb(48,49,53));
        serviceVw2.setBackgroundColor(Color.rgb(48,49,53));
        serviceVw3.setBackgroundColor(Color.rgb(48,49,53));
        serviceVw4.setBackgroundColor(Color.rgb(48,49,53));
    }

    public void setTabSelection(int index) {
        clearStatus();
        switch (index) {
            case 0:
                serviceTv1.setTextColor(Color.rgb(255, 255, 255));
                serviceVw1.setBackgroundColor(Color.rgb(1,160,243));
                break;
            case 1:
                serviceTv2.setTextColor(Color.rgb(255, 255, 255));
                serviceVw2.setBackgroundColor(Color.rgb(1,160,243));
                break;
            case 2:
                serviceTv3.setTextColor(Color.rgb(255, 255, 255));
                serviceVw3.setBackgroundColor(Color.rgb(1,160,243));
                break;
            case 3:
                serviceTv4.setTextColor(Color.rgb(255, 255, 255));
                serviceVw4.setBackgroundColor(Color.rgb(1,160,243));
                break;
        }
    }
    boolean[] hasNests = new boolean[]{false,false,false,false} ;
    @Override
    public void requestServiceSuccess(boolean hasNext) {
//        toast("请求成功", false);
//        Log.e("ServiceFragment","请求成功");
        hasNests[fragmentType] = hasNext;
        initDatas();
    }

    @Override
    public void requestServiceFailed() {
        toast("请求失败", false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    //控件响应回调
    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
//            toast("" + (position), true);
            Intent i;
            switch (fragmentType){
                case 0:
                    if(workLogEntities.get(position).getLogType().equals("普通")){
                        i = new Intent(mContext, ShowWorkLogActivity.class);
                    }else if(workLogEntities.get(position).getLogType().equals("群体事件")){
                        i = new Intent(mContext, ShowGroupCaseActivity.class);
                    }else {
                        i = new Intent(mContext, ShowChargeCaseActivity.class);
                    }

                    i.putExtra("id",workLogEntities.get(position).getId());
                    startActivity(i);
                    break;
                case 1:
                    i = new Intent(mContext, ShowAdviceActivity.class);
                    i.putExtra("id",adviceEntities.get(position).getId());
                    startActivity(i);
                    break;
                case 2:
                    i = new Intent(mContext, ShowClassicCaseActivity.class);
                    i.putExtra("id",classicCaseEntities.get(position).getId());
                    startActivity(i);
                    break;
                case 3:
                    i = new Intent(mContext, ShowSummaryActivity.class);
                    i.putExtra("id",summaryEntities.get(position).getId());
                    startActivity(i);
                    break;
            }
        }
        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };

    public void showAddWorkLogDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // 先得到构造器
        builder.setTitle("选择新增日志类型"); // 设置标题
        builder.setNeutralButton("普通日志", new DialogInterface.OnClickListener() { // 设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(mContext,AddWorkLogActivity.class);
                startActivity(i);
                dialog.dismiss(); // 关闭dialog
            }
        });
        builder.setNegativeButton("群体性案件", new DialogInterface.OnClickListener() { // 设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(mContext,AddGroupCaseActivity.class);
                startActivity(i);
                dialog.dismiss(); // 关闭dialog
            }
        });
        builder.setPositiveButton("另行收费案件", new DialogInterface.OnClickListener() { // 设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(mContext,AddChargeCaseActivity.class);
                startActivity(i);
                dialog.dismiss(); // 关闭dialog
            }
        });
        builder.create().show();
    }
    public void showReportDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // 先得到构造器
        builder.setTitle("选择新增上报类型"); // 设置标题

        builder.setNegativeButton("建议上报", new DialogInterface.OnClickListener() { // 设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(mContext,AddGroupCaseActivity.class);
                startActivity(i);
                dialog.dismiss(); // 关闭dialog
            }
        });
        builder.setPositiveButton("典型案件上报", new DialogInterface.OnClickListener() { // 设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(mContext,AddChargeCaseActivity.class);
                startActivity(i);
                dialog.dismiss(); // 关闭dialog
            }
        });
        builder.create().show();
    }

}
