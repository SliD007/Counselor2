package test.example.com.counselor.view.todolist;

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
import test.example.com.counselor.listener.MyLvClickListener;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ToDoListFragment extends BaseFragment implements IToDoListView{


    List<ToDoListEntity> toDoListEntities;
    List<DoneListEntity> doneListEntities;
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
    private int fragmentType;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_backlog;
    }


    @Override
    protected void initPresenter() {
        mToDoListPresenter = new ToDoListPresenter(getActivity(),this);
        mToDoListPresenter.requestToDoList(0,20,1,MyApplication.getInstance().loginEntity.getId());
        mToDoListPresenter.requestToDoList(0,20,0,MyApplication.getInstance().loginEntity.getId());
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
                fragmentType=0;
                backlogLeftTv.setTextColor(Color.rgb(255, 255, 255));
                backlogLeftVw.setBackgroundColor(Color.rgb(1, 160, 243));
                backlogRightTv.setTextColor(Color.rgb(102, 102, 102));
                backlogRightVw.setBackgroundColor(Color.rgb(48, 49, 53));
                initDatas();
                break;
            case 1:
                fragmentType=1;
                backlogLeftTv.setTextColor(Color.rgb(102, 102, 102));
                backlogLeftVw.setBackgroundColor(Color.rgb(48, 49, 53));
                backlogRightTv.setTextColor(Color.rgb(255, 255, 255));
                backlogRightVw.setBackgroundColor(Color.rgb(1, 160, 243));
                initDatas();
                break;
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        if(fragmentType==0){
            toDoListEntities = mToDoListPresenter.getToDoListEntityList();
            backlogLv.setAdapter(new Common1Adapter<ToDoListEntity>(super.mContext, toDoListEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, ToDoListEntity toDoListEntity, int position) {
                    TextView tv1 = (TextView) mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = (TextView) mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = (TextView) mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(toDoListEntity.getTitle());
                    tv2.setText(toDoListEntity.getFrom());
                    tv3.setText(toDoListEntity.getTime());
                }
            });
            backlogLv.setOnItemClickListener(onItemClickListener);
        }else {
            doneListEntities = mToDoListPresenter.getDoneListEntityList();
            backlogLv.setAdapter(new Common1Adapter<DoneListEntity>(super.mContext, doneListEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, DoneListEntity toDoListEntity, int position) {
                    TextView tv1 = (TextView) mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = (TextView) mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = (TextView) mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(toDoListEntity.getTitle());
                    tv2.setText(toDoListEntity.getFrom());
                    tv3.setText(toDoListEntity.getTime());
                }
            });
            backlogLv.setOnItemClickListener(onItemClickListener);
        }

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
        initDatas();
    }

    @Override
    public void requestToDoListFaild() {
        toast("请求失败",false);
    }

    @Override
    public void showDialog(boolean show) {

    }
}
