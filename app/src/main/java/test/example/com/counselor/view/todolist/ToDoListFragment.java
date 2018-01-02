package test.example.com.counselor.view.todolist;

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
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.entity.ListEntity;
import test.example.com.counselor.listener.MyLvClickListener;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ToDoListFragment extends BaseFragment implements IToDoListView{


    ListEntity mEntity;
    List<ListEntity> entityList;
    ListAdapter mListAdapter;
    @BindView(R.id.backlogLv)
    ListView backlogLv;
    @BindView(R.id.backlogLeftVw)
    View backlogLeftVw;
    @BindView(R.id.backlogLeftTv)
    TextView backlogLeftTv;
    @BindView(R.id.backlogRightVw)
    View backlogRightVw;
    @BindView(R.id.backlogRightTv)
    TextView backlogRightTv;
    ToDoListPresenter mToDoListPresenter;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_backlog;
    }


    @Override
    protected void initPresenter() {
        mToDoListPresenter = new ToDoListPresenter(getActivity(),this);
        mToDoListPresenter.requestToDoList(0,20,1,MyApplication.getInstance().loginEntity.getId());
    }

    @Override
    protected void initViews() {
        setTabSelection(0);//初始化显示wq未读List
    }


    @OnClick({R.id.backlogLeftRl, R.id.backlogRightRl})
    public void onSwitchClick(View view) {
        switch (view.getId()) {
            case R.id.backlogLeftRl:
                setTabSelection(0);
                break;
            case R.id.backlogRightRl:
                setTabSelection(1);
                break;
        }
    }
    /*
    根据传入值设置不同listview
     */
    private void setTabSelection(int index) {
        switch (index){
            case 0:
                backlogLeftTv.setTextColor(Color.rgb(255, 255, 255));
                backlogLeftVw.setBackgroundColor(Color.rgb(1, 160, 243));
                backlogRightTv.setTextColor(Color.rgb(102, 102, 102));
                backlogRightVw.setBackgroundColor(Color.rgb(48, 49, 53));

                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 20; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "开展深入学校贯彻十九大精神报告会", "来源：星沙街道司法所", "13:" + (10 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                backlogLv.setAdapter(mListAdapter);
                //设置回调
                backlogLv.setOnItemClickListener(onItemClickListener);
                break;
            case 1:
                backlogLeftTv.setTextColor(Color.rgb(102, 102, 102));
                backlogLeftVw.setBackgroundColor(Color.rgb(48, 49, 53));
                backlogRightTv.setTextColor(Color.rgb(255, 255, 255));
                backlogRightVw.setBackgroundColor(Color.rgb(1, 160, 243));

                //构造数据
                entityList = new ArrayList<ListEntity>();//空指针高发处
                for (int i = 0; i < 50; i++) {
                    mEntity = new ListEntity(R.layout.item_commonlist,
                            "开展深入学校贯彻十九大精神报告会", "来源：星沙街道司法所", "12:" + (10 + i));
                    entityList.add(mEntity);
                }
                //创建adapter
                mListAdapter = new ListAdapter(super.mContext, entityList, mClickListener, onItemClickListener);
                backlogLv.setAdapter(mListAdapter);
                //设置回调
                backlogLv.setOnItemClickListener(onItemClickListener);
                break;
        }
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
            toast(""+(position), true);
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
    public void requestToDoListSuccess() {
        toast("请求成功",false);
    }

    @Override
    public void requestToDoListFaild() {
        toast("请求失败",false);
    }

    @Override
    public void showDialog(boolean show) {

    }
}
