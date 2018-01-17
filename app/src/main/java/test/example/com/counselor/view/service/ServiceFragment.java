package test.example.com.counselor.view.service;

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
import test.example.com.counselor.view.service.addclassiccase.AddClassicCaseActivity;
import test.example.com.counselor.view.service.addsummary.AddSummaryActivity;
import test.example.com.counselor.view.service.addworklog.AddWorkLogActivity;
import test.example.com.counselor.view.service.entity.AdviceEntity;
import test.example.com.counselor.view.service.entity.ClassicCaseEntity;
import test.example.com.counselor.view.service.entity.SummaryEntity;
import test.example.com.counselor.view.service.entity.WorkLogEntity;
import test.example.com.counselor.view.service.showadvice.ShowAdviceActivity;
import test.example.com.counselor.view.service.showclassiccase.ShowClassicCaseActivity;
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

    private int fragmentType;
    private int[] fragmentCuttent;
    ServicePresenter mServicePersenter;
    private int requestSize=20;
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
        fragmentCuttent = new int[]{0, 0, 0, 0};
        mServicePersenter = new ServicePresenter(this);
        mServicePersenter.requestServiceData(fragmentCuttent[0],requestSize,0);
        mServicePersenter.requestServiceData(fragmentCuttent[1],requestSize,1);
        mServicePersenter.requestServiceData(fragmentCuttent[2],requestSize,2);
        mServicePersenter.requestServiceData(fragmentCuttent[3],requestSize,3);
        fragmentCuttent[0]=1;
        fragmentCuttent[1]=1;
        fragmentCuttent[2]=1;
        fragmentCuttent[3]=1;
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
    }

    @Override
    protected void initEvents() {}

    @Override
    protected void initDatas() {
        Log.e("ServiceFragment","加载数据");

        if (fragmentType == 0) {
            workLogEntities = mServicePersenter.getWorkLogEntities();
            mAdapter = new CommonAdapter(mContext,workLogEntities,R.layout.item_4list,mClickListener){
                public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                    super.onBindViewHolder(viewHolder,position);

                    TextView tv1 = viewHolder.getView(R.id.itemTv1);
                    TextView tv2 = viewHolder.getView(R.id.itemTv2);
                    TextView tv3 = viewHolder.getView(R.id.itemTv3);
                    TextView tv4 = viewHolder.getView(R.id.itemTv4);
                    tv1.setText("服务对象："+workLogEntities.get(position).getServiceObject());
                    tv2.setText("服务类型："+workLogEntities.get(position).getServiceType());
                    tv3.setText("服务时间："+ TimeUtil.getDateToString(workLogEntities.get(position).getCreateTime(),TimeUtil.Data));
                    tv4.setText("服务单位："+workLogEntities.get(position).getServiceVillage());
                    LinearLayout ll = viewHolder.getView(R.id.itemLl);
                    ll.setTag(position);
                    ll.setOnClickListener(mClickListener);
                }
            };
            mRecyclerView.setAdapter(mAdapter);

        }else if(fragmentType==1){
            adviceEntities = mServicePersenter.getAdviceEntities();
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

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
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
                            toast("上滑加载1",false);
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            toast("上滑加载2",false);
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
                i = new Intent(getActivity(), AddWorkLogActivity.class);
                startActivity(i);
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

    @Override
    public void requestServiceSuccess() {
//        toast("请求成功", false);
        Log.e("ServiceFragment","请求成功");
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
            toast("" + (position), true);
            Intent i;
            switch (fragmentType){
                case 0:
                    i = new Intent(mContext, ShowWorkLogActivity.class);
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
}
