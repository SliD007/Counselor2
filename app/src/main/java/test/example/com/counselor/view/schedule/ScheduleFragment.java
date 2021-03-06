package test.example.com.counselor.view.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.Common1Adapter;
import test.example.com.counselor.adapter.ViewHolder1;
import test.example.com.counselor.base.BaseFragment;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.base.MyLvClickListener;
import test.example.com.counselor.util.LocaltionUtil;
import test.example.com.counselor.util.TimeUtil;
import test.example.com.counselor.view.schedule.chage.ChangeScheduleActivity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ScheduleFragment extends BaseFragment implements IScheduleView{


    @BindView(R.id.scheduleLv)
    ListView scheduleLv;
    private List<ScheduleEntity> entityList;
    public SchedulePersenter mSchedulePersenter;
    LocaltionUtil localtionUtil;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_schedule;
    }

    @Override
    protected void initPresenter() {

        mSchedulePersenter = new SchedulePersenter(getActivity(),this);
        mSchedulePersenter.requestScheduleList(1,12);
        if(MyApplication.getInstance().clock)   {
            clock();
        }else {
            toast("打开失败",false);
        }
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
//        Log.e("ScheduleFragment","加载数据");
        entityList = mSchedulePersenter.getScheduleEntityList();
        scheduleLv.setAdapter(new Common1Adapter<ScheduleEntity>(super.mContext, entityList,
                R.layout.item_schedulelist, mClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, ScheduleEntity scheduleEntity, int position) {
                TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                tv1.setText(TimeUtil.getDateToString(scheduleEntity.getPlacementTime(),TimeUtil.Data));
                tv2.setText("服务单位："+scheduleEntity.getVillage());

                tv3.setTag(position);
                tv3.setOnClickListener(mClickListener);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    //控件响应回调
    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
            Intent i = new Intent(getActivity(),ChangeScheduleActivity.class);
            i.putExtra("id",entityList.get(position).getId());
            i.putExtra("workfor",entityList.get(position).getVillage());
            i.putExtra("worktime",entityList.get(position).getPlacementTime());
            i.putExtra("jobType",entityList.get(position).getJobType());
            startActivity(i);

        }
        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };

    @Override
    public void requestScheduleSuccess() {
//        toast("请求成功",false);
//        Log.e("ScheduleFragment","请求成功");
        initDatas();
        //定位打卡弄一弄
        localtionUtil = new LocaltionUtil(getActivity(),entityList);
        Log.e("sHA1", ""+localtionUtil.sHA1(getActivity()));
        localtionUtil.startLocation();
    }

    @Override
    public void requestScheduleFaild() {
        toast("请求失败",false);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        localtionUtil.stopLocation();
    }
}
