package test.example.com.counselor.view.service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.Common1Adapter;
import test.example.com.counselor.adapter.ViewHolder1;
import test.example.com.counselor.base.BaseFragment;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.view.service.addadvice.AddAdviceActivity;
import test.example.com.counselor.view.service.addclassiccase.AddClassicCaseActivity;
import test.example.com.counselor.view.service.addsummary.AddSummaryActivity;
import test.example.com.counselor.view.service.addworklog.AddWorkLogActivity;
import test.example.com.counselor.view.service.entity.AdviceEntity;
import test.example.com.counselor.view.service.entity.ClassicCaseEntity;
import test.example.com.counselor.view.service.entity.SummaryEntity;
import test.example.com.counselor.view.service.entity.WorkLogEntity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ServiceFragment extends BaseFragment implements IServiceView{

    @BindView(R.id.serviceLv)
    ListView serviceLv;
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
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initPresenter() {
        fragmentType = 0;
        fragmentCuttent = new int[]{0, 0, 0, 0};
        mServicePersenter = new ServicePresenter(this);
        mServicePersenter.requestServiceData(fragmentCuttent[0],requestSize,0, MyApplication.getInstance().loginEntity.getId());
        mServicePersenter.requestServiceData(fragmentCuttent[1],requestSize,1, MyApplication.getInstance().loginEntity.getId());
        mServicePersenter.requestServiceData(fragmentCuttent[2],requestSize,2, MyApplication.getInstance().loginEntity.getId());
        mServicePersenter.requestServiceData(fragmentCuttent[3],requestSize,3, MyApplication.getInstance().loginEntity.getId());
        fragmentCuttent[0]=1;
        fragmentCuttent[1]=1;
        fragmentCuttent[2]=1;
        fragmentCuttent[3]=1;
    }

    @Override
    protected void initViews() {
        setTabSelection(fragmentType);
        if(MyApplication.getInstance().refresh){
            mServicePersenter.requestServiceData(fragmentCuttent[fragmentType],
                    requestSize,fragmentType, MyApplication.getInstance().loginEntity.getId());
            MyApplication.getInstance().refresh = false;
        }
    }


    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        if (fragmentType == 0) {
            workLogEntities = mServicePersenter.getWorkLogEntities();
            serviceLv.setAdapter(new Common1Adapter<WorkLogEntity>(super.mContext, workLogEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, WorkLogEntity workLogEntities, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(workLogEntities.getTitle());
                    tv2.setText(workLogEntities.getFrom());
                    tv3.setText(workLogEntities.getTime());
                }
            });
            serviceLv.setOnItemClickListener(onItemClickListener);
        }else if(fragmentType==1){
            adviceEntities = mServicePersenter.getAdviceEntities();
            serviceLv.setAdapter(new Common1Adapter<AdviceEntity>(super.mContext, adviceEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, AdviceEntity adviceEntity, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(adviceEntity.getTitle());
                    tv2.setText(adviceEntity.getFrom());
                    tv3.setText(adviceEntity.getTime());
                }
            });
            serviceLv.setOnItemClickListener(onItemClickListener);
        }else if(fragmentType==2){
            classicCaseEntities = mServicePersenter.getClassicCaseEntities();
            serviceLv.setAdapter(new Common1Adapter<ClassicCaseEntity>(super.mContext, classicCaseEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, ClassicCaseEntity classicCaseEntity, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(classicCaseEntity.getTitle());
                    tv2.setText(classicCaseEntity.getFrom());
                    tv3.setText(classicCaseEntity.getTime());
                }
            });
            serviceLv.setOnItemClickListener(onItemClickListener);
        }else {
            summaryEntities = mServicePersenter.getSummaryEntities();
            serviceLv.setAdapter(new Common1Adapter<SummaryEntity>(super.mContext, summaryEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, SummaryEntity summaryEntity, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(summaryEntity.getTitle());
                    tv2.setText(summaryEntity.getFrom());
                    tv3.setText(summaryEntity.getTime());
                }
            });
            serviceLv.setOnItemClickListener(onItemClickListener);
        }
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
            case 3:
                i = new Intent(getActivity(), AddSummaryActivity.class);
                startActivity(i);
        }
    }

    private void clearStatus() {
        serviceTv1.setTextColor(Color.rgb(102, 102, 102));
        serviceTv2.setTextColor(Color.rgb(102, 102, 102));
        serviceTv3.setTextColor(Color.rgb(102, 102, 102));
        serviceTv4.setTextColor(Color.rgb(102, 102, 102));
        serviceVw1.setBackgroundColor(Color.rgb(48,49,53));
        serviceVw2.setBackgroundColor(Color.rgb(48,49,53));
        serviceVw3.setBackgroundColor(Color.rgb(48,49,53));
        serviceVw4.setBackgroundColor(Color.rgb(48,49,53));
    }

    /*
    根据传入值设置不同listview
     */
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
        toast("请求成功", false);
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

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toast("" + (position), true);
            mServicePersenter.requestServiceData(fragmentCuttent[fragmentType],requestSize,fragmentType, MyApplication.getInstance().loginEntity.getId());
        }
    };

}
