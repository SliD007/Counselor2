package test.example.com.counselor.view.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ServiceFragment extends BaseFragment {


    ListEntity mEntity;
    List<ListEntity> entityList;
    ListAdapter mListAdapter;
    @BindView(R.id.serviceLv)
    ListView serviceLv;


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

    /*
    根据传入值设置不同listview
     */
    private void setTabSelection(int index) {
        switch (index) {
            case 0:
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 50; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "工作日志", "来源：星沙街道司法所", "13:" + (10 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                serviceLv.setAdapter(mListAdapter);
                //设置回调
                serviceLv.setOnItemClickListener(onItemClickListener);
                break;
            case 1:
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 50; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "建议上报", "来源：星沙街道司法所", "12:" + (10 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                serviceLv.setAdapter(mListAdapter);
                //设置回调
                serviceLv.setOnItemClickListener(onItemClickListener);
                break;
            case 2:
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 50; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "典型案件", "来源：星沙街道司法所", "13:" + (10 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                serviceLv.setAdapter(mListAdapter);
                //设置回调
                serviceLv.setOnItemClickListener(onItemClickListener);
                break;
            case 3:
                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 50; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "月度总结", "来源：星沙街道司法所", "12:" + (10 + i));
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
            toast("时间：" + "12:" + (10 + position), true);
        }
    };
    //控件响应回调
    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
            toast("这是第" + (position + 1) + "条", true);
        }

        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };

}
