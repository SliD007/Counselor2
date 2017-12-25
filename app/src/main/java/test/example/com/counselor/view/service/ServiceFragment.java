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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.ListAdapter;
import test.example.com.counselor.base.BaseFragment;
import test.example.com.counselor.entity.ListEntity;
import test.example.com.counselor.listener.MyLvClickListener;
import test.example.com.counselor.view.service.addadvice.AddAdviceActivity;
import test.example.com.counselor.view.service.addcommon.AddCommonActivity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ServiceFragment extends BaseFragment implements IServiceView{


    ListEntity mEntity;
    List<ListEntity> entityList;
    ListAdapter mListAdapter;
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
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initViews() {
        setTabSelection(0);//初始化显示wq未读List
    }

    @OnClick({R.id.serviceRl1, R.id.serviceRl2, R.id.serviceRl3, R.id.serviceRl4})
    public void onClick(View view) {
        clearStatus();
        switch (view.getId()) {
            case R.id.serviceRl1:
                setTabSelection(0);
                break;
            case R.id.serviceRl2:
                setTabSelection(1);
                break;
            case R.id.serviceRl3:
                setTabSelection(2);
                break;
            case R.id.serviceRl4:
                setTabSelection(3);
                break;
        }
    }
    @OnClick({ R.id.serviceRl5})
    public void onAddClick(View view) {
        Intent i;
        switch (fragmentType) {
            case 1:
                i = new Intent(getActivity(), AddAdviceActivity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(getActivity(), AddCommonActivity.class);
                i.putExtra("fragmentType",fragmentType);
                startActivity(i);
            case 3:
                i = new Intent(getActivity(), AddCommonActivity.class);
                i.putExtra("fragmentType",fragmentType);
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
    private void setTabSelection(int index) {
        fragmentType = index;
        switch (index) {
            case 0:
                serviceTv1.setTextColor(Color.rgb(255, 255, 255));
                serviceVw1.setBackgroundColor(Color.rgb(1,160,243));
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 30; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "服务对象：李本忠", "未完结", "2017/11/" + (1 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                serviceLv.setAdapter(mListAdapter);
                //设置回调
                serviceLv.setOnItemClickListener(onItemClickListener);
                break;
            case 1:
                serviceTv2.setTextColor(Color.rgb(255, 255, 255));
                serviceVw2.setBackgroundColor(Color.rgb(1,160,243));
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 30; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "关于预防老年人被金融诈骗的建议", "报送值：长沙县司法局", "2017/10/" + (1 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                serviceLv.setAdapter(mListAdapter);
                //设置回调
                serviceLv.setOnItemClickListener(onItemClickListener);
                break;
            case 2:
                serviceTv3.setTextColor(Color.rgb(255, 255, 255));
                serviceVw3.setBackgroundColor(Color.rgb(1,160,243));
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 30; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "预防老年人被金融诈骗的典型案例", "报送值：长沙县司法局", "2017/9/" + (1 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                serviceLv.setAdapter(mListAdapter);
                //设置回调
                serviceLv.setOnItemClickListener(onItemClickListener);
                break;
            case 3:
                serviceTv4.setTextColor(Color.rgb(255, 255, 255));
                serviceVw4.setBackgroundColor(Color.rgb(1,160,243));
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 12; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "2017年新塘村" + (12 - i) + "月度总结", "报送值：星沙街道司法所", "2017/" + (12 - i) + "/30");
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                serviceLv.setAdapter(mListAdapter);
                //设置回调
                serviceLv.setOnItemClickListener(onItemClickListener);
                break;
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

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
        }
    };
    //控件响应回调
    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
        }

        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };

    @Override
    public void setItem(int index, String str1, String str2, String str3) {

    }
}
