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
import test.example.com.counselor.view.todolist.entity.DoneListEntity;
import test.example.com.counselor.view.todolist.entity.ToDoListEntity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class ToDoListFragment extends BaseFragment implements IToDoListView {

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
    @BindView(R.id.requestMoreToDoListTv)
    TextView requestMoreToDoListTv;
    private int fragmentType;
    private int[] fragmentCuttent;
    List<ToDoListEntity> toDoListEntities;
    List<DoneListEntity> doneListEntities;
    private int requestSize=20;
    private int requestToDoCurrent=0;
    private int requestDoneCurrent=0;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_backlog;
    }


    @Override
    protected void initPresenter() {
        fragmentType = 0;
        fragmentCuttent = new int[]{0, 0};
        mToDoListPresenter = new ToDoListPresenter(getActivity(), this);
        mToDoListPresenter.requestToDoList(fragmentCuttent[0], requestSize, 1, MyApplication.getInstance().loginEntity.getId());
        mToDoListPresenter.requestToDoList(fragmentCuttent[1], requestSize, 0, MyApplication.getInstance().loginEntity.getId());
        fragmentCuttent[0]=1;
        fragmentCuttent[1]=1;
    }

    @Override
    protected void initViews() {
        setTabSelection(fragmentType);//初始化显示wq未读List
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

        if (fragmentType == 0) {

            toDoListEntities = mToDoListPresenter.getToDoListEntityList();
            backlogLv.setAdapter(new Common1Adapter<ToDoListEntity>(super.mContext, toDoListEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, ToDoListEntity toDoListEntity, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(toDoListEntity.getTitle());
                    tv2.setText(toDoListEntity.getFrom());
                    tv3.setText(toDoListEntity.getTime());
                }
            });
            backlogLv.setOnItemClickListener(onItemClickListener);
            if(toDoListEntities!=null){
                if(toDoListEntities.size()<requestSize){
                    requestMoreToDoListTv.setText("没有更多");
                }else {
                    requestMoreToDoListTv.setText("点击加载更多");
                }
            }
        } else {
            doneListEntities = mToDoListPresenter.getDoneListEntityList();
            backlogLv.setAdapter(new Common1Adapter<DoneListEntity>(super.mContext, doneListEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, DoneListEntity toDoListEntity, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(toDoListEntity.getTitle());
                    tv2.setText(toDoListEntity.getFrom());
                    tv3.setText(toDoListEntity.getTime());
                }
            });
            backlogLv.setOnItemClickListener(onItemClickListener);
            if(doneListEntities.size()<requestSize){
                requestMoreToDoListTv.setText("没有更多");
            }else {
                requestMoreToDoListTv.setText("点击加载更多");}
        }

    }


    @OnClick({R.id.backlogLeftRl, R.id.backlogRightRl,R.id.requestMoreToDoListTv})
    public void onSwitchClick(View view) {
        switch (view.getId()) {
            case R.id.backlogLeftRl:
                setTabSelection(0);
                initDatas();
                break;
            case R.id.backlogRightRl:
                setTabSelection(1);
                initDatas();
                break;
            case R.id.requestMoreToDoListTv:
                if(fragmentType==0 & toDoListEntities!=null){
                    if(toDoListEntities.size()<requestSize){
                        toast("没有更多了",false);
                    }else {
                        mToDoListPresenter.requestToDoList(requestToDoCurrent, requestSize, 1, MyApplication.getInstance().loginEntity.getId());
                    }
                }else if(fragmentType==1 & doneListEntities!=null){
                    if(doneListEntities.size()<requestSize){
                        toast("没有更多了",false);
                    }else {
                        mToDoListPresenter.requestToDoList(requestDoneCurrent, requestSize, 0, MyApplication.getInstance().loginEntity.getId());
                    }
                }
                break;
        }
    }

    /*
    根据传入值设置不同listview
     */
    private void setTabSelection(int index) {
        switch (index) {
            case 0:
                fragmentType = 0;
                backlogLeftTv.setTextColor(Color.rgb(255, 255, 255));
                backlogLeftVw.setBackgroundColor(Color.rgb(1, 160, 243));
                backlogRightTv.setTextColor(Color.rgb(102, 102, 102));
                backlogRightVw.setBackgroundColor(Color.rgb(48, 49, 53));
                break;
            case 1:
                fragmentType = 1;
                backlogLeftTv.setTextColor(Color.rgb(102, 102, 102));
                backlogLeftVw.setBackgroundColor(Color.rgb(48, 49, 53));
                backlogRightTv.setTextColor(Color.rgb(255, 255, 255));
                backlogRightVw.setBackgroundColor(Color.rgb(1, 160, 243));
                break;
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
            toast("" + (position), true);
            mToDoListPresenter.requestToDoList(fragmentCuttent[fragmentType],requestSize,
                    fragmentType, MyApplication.getInstance().loginEntity.getId());

        }
    };

    @Override
    public void requestToDoListSuccess() {
        toast("请求成功", false);
        initDatas();
    }

    @Override
    public void requestToDoListFaild() {
        toast("请求失败", false);
    }

    @Override
    public void showDialog(boolean show) {

    }
}
