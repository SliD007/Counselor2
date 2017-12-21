package test.example.com.counselor.view.backlog;

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
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.ListAdapter;
import test.example.com.counselor.base.BaseFragment;
import test.example.com.counselor.entity.ListEntity;
import test.example.com.counselor.listener.MyLvClickListener;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ReadedBacklogFragment extends BaseFragment {


    ListEntity mEntity;
    List<ListEntity> entityList;
    ListAdapter mListAdapter;
    @BindView(R.id.commonLv)
    ListView commonLv;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_commonlist;
    }

    @Override
    protected void initViews() {
        //构造数据
        entityList = new ArrayList<ListEntity>();//空指针高发处
        for (int i = 0; i < 50; i++) {
            mEntity = new ListEntity(R.layout.item_commonlist,
                    "开展深入学校贯彻十九大精神报告会", "来源：星沙街道司法所", "11:" + (10 + i));
            entityList.add(mEntity);
        }
        //创建adapter
        mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
        commonLv.setAdapter(mListAdapter);
        //设置回调
        commonLv.setOnItemClickListener(onItemClickListener);
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
            toast("时间：" + "11:"+(10+position), true);
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
