package test.example.com.counselor.view.schedule;

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

public class ScheduleFragment extends BaseFragment {


    ListEntity mEntity;
    List<ListEntity> entityList;
    ListAdapter mListAdapter;
    @BindView(R.id.scheduleLv)
    ListView scheduleLv;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_schedule;
    }

    @Override
    protected void initViews() {
        //构造数据
        entityList = new ArrayList<ListEntity>();//空指针高发处
        for (int i = 0; i < 50; i++) {
            mEntity = new ListEntity(R.layout.item_commonlist,
                    "2017/11/19", "服务单位：星沙街道新塘村", "申请修改");
            entityList.add(mEntity);
        }
        //创建adapter
        mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
        scheduleLv.setAdapter(mListAdapter);
        //设置回调
        scheduleLv.setOnItemClickListener(onItemClickListener);
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
        }
    };
    //控件响应回调
    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
            toast("确定要修改第" + (position + 1) + "条吗", true);
        }
        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };
}
