package test.example.com.counselor.view.task;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import test.example.com.counselor.view.news.NewsActivity;
import test.example.com.counselor.view.rank.RankActivity;
import test.example.com.counselor.view.service.addworklog.AddWorkLogActivity;
import test.example.com.counselor.view.task.entity.DoneTaskEntity;
import test.example.com.counselor.view.task.entity.ToDoTaskEntity;
import test.example.com.counselor.view.task.showtask.ShowTaskActivity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class TaskFragment extends BaseFragment implements ITaskView {

    @BindView(R.id.backlogLeftVw)
    View backlogLeftVw;
    @BindView(R.id.backlogLeftTv)
    TextView backlogLeftTv;
    @BindView(R.id.backlogRightVw)
    View backlogRightVw;
    @BindView(R.id.backlogRightTv)
    TextView backlogRightTv;
    @BindView(R.id.noneTv)
    TextView noneTv;
    ViewGroup.LayoutParams para;
    String noneStr = "加载中";
    TaskPresenter mTaskPresenter;
    List<ToDoTaskEntity> toDoListEntities;
    List<DoneTaskEntity> doneListEntities;
    private int fragmentType;
    private int[] fragmentCuttent;
    private int requestSize=10;
    private int star;

    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private CommonAdapter mAdapter;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_task;
    }


    @Override
    protected void initPresenter() {
        fragmentType = 0;
        fragmentCuttent = new int[]{1, 1};
        mTaskPresenter = new TaskPresenter(getActivity(), this);
        star = MyApplication.getInstance().loginEntity.getStar();
        //查看状态: 0/已查看 1/未查看
        mTaskPresenter.requestTask(fragmentCuttent[0], requestSize, 1, MyApplication.getInstance().loginEntity.getId());
        mTaskPresenter.requestTask(fragmentCuttent[1], requestSize, 0, MyApplication.getInstance().loginEntity.getId());

    }

    @Override
    protected void initViews() {
        setTabSelection(fragmentType);//初始化显示wq未读List
        if(MyApplication.getInstance().show_star_dialog){
            showDialog();
            MyApplication.getInstance().show_star_dialog=false;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        para = noneTv.getLayoutParams();
    }

    @Override
    protected void initEvents() {}

    @Override
    protected void initDatas() {
        Log.e("TaskFragment","加载数据"+fragmentType);
        if (fragmentType == 0) {
            toDoListEntities = mTaskPresenter.getToDoTaskEntity();
            if(toDoListEntities!=null){
                para.height = 0;
                noneTv.setLayoutParams(para);
                mAdapter = new CommonAdapter(mContext,toDoListEntities,R.layout.item_2list,mClickListener){
                    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                        super.onBindViewHolder(viewHolder,position);
//                    Log.e("mAdapter","加载数据"+toDoListEntities.size());
                        TextView tv1 = viewHolder.getView(R.id.itemTv1);
                        tv1.setText("主题："+toDoListEntities.get(position).getTitle());
                        TextView tv2 = viewHolder.getView(R.id.itemTv2);
                        tv2.setText("来源于："+toDoListEntities.get(position).getFromWhere());
                        TextView tv3 = viewHolder.getView(R.id.itemTv3);
                        tv3.setText(TimeUtil.getDateToString(toDoListEntities.get(position).getTime(),TimeUtil.Time));
                        RelativeLayout rl = viewHolder.getView(R.id.itemRl);
                        rl.setTag(position);
                        rl.setOnClickListener(mClickListener);
                    }
                };
                mRecyclerView.setAdapter(mAdapter);
            }else {
                para.height = 100;
                noneTv.setLayoutParams(para);
                    noneTv.setText(noneStr);
                }

        } else {
            doneListEntities = mTaskPresenter.getDoneTaskEntityList();
            if(doneListEntities!=null){
                para.height = 0;
                noneTv.setLayoutParams(para);
                mAdapter = new CommonAdapter(mContext,doneListEntities,R.layout.item_2list,mClickListener){
                    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                        TextView tv1 = viewHolder.getView(R.id.itemTv1);
                        tv1.setText("主题："+doneListEntities.get(position).getTitle());
                        TextView tv2 = viewHolder.getView(R.id.itemTv2);
                        tv2.setText("来源于："+doneListEntities.get(position).getFromWhere());
                        TextView tv3 = viewHolder.getView(R.id.itemTv3);
                        tv3.setText(TimeUtil.getDateToString(doneListEntities.get(position).getTime(),TimeUtil.Time));
                        RelativeLayout rl = viewHolder.getView(R.id.itemRl);
                        rl.setTag(position);
                        rl.setOnClickListener(mClickListener);
                    }
                };
                mRecyclerView.setAdapter(mAdapter);
            }else {
                para.height = 100;
                noneTv.setLayoutParams(para);
                noneTv.setText(noneStr);
            }

        }

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        if(fragmentCuttent[fragmentType]>1)
                            fragmentCuttent[fragmentType]--;
                        mTaskPresenter.requestTask(fragmentCuttent[fragmentType], requestSize, fragmentType, MyApplication.getInstance().loginEntity.getId());

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
                            fragmentCuttent[fragmentType]++;
                            mTaskPresenter.requestTask(fragmentCuttent[fragmentType], requestSize, fragmentType, MyApplication.getInstance().loginEntity.getId());
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            fragmentCuttent[fragmentType]++;
                            mTaskPresenter.requestTask(fragmentCuttent[fragmentType], requestSize, fragmentType, MyApplication.getInstance().loginEntity.getId());
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times ++;
            }
        });
    }


    @OnClick({R.id.backlogLeftTv, R.id.backlogRightTv,R.id.newsRl})
    public void onSelectClick(View view) {
        switch (view.getId()) {
            case R.id.backlogLeftTv:
                fragmentType=0;
                setTabSelection(0);
                initDatas();
                break;
            case R.id.backlogRightTv:
                fragmentType=1;
                setTabSelection(1);
                initDatas();
                break;
            case R.id.newsRl:
                Intent i = new Intent(getContext(), NewsActivity.class);
                startActivity(i);
                break;
        }
    }

    private void setTabSelection(int index) {
        switch (index) {
            case 0:
                fragmentType = 0;
                backlogLeftTv.setTextColor(Color.rgb(255, 255, 255));
                backlogLeftVw.setBackgroundColor(Color.rgb(1, 160, 243));
                backlogRightTv.setTextColor(Color.rgb(144, 144, 144));
                backlogRightVw.setBackgroundColor(Color.rgb(48, 49, 53));
                break;
            case 1:
                fragmentType = 1;
                backlogLeftTv.setTextColor(Color.rgb(144, 144, 144));
                backlogLeftVw.setBackgroundColor(Color.rgb(48, 49, 53));
                backlogRightTv.setTextColor(Color.rgb(255, 255, 255));
                backlogRightVw.setBackgroundColor(Color.rgb(1, 160, 243));
                break;
        }
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
            Intent i = new Intent(mContext, ShowTaskActivity.class);
            int id = 0;
            String fromWhere = "";
            if(fragmentType==1){
                id = doneListEntities.get(position).getId();
                fromWhere = doneListEntities.get(position).getFromWhere();
            }else {
                id = toDoListEntities.get(position).getId();
            }

            i.putExtra("fromWhere",position);
            i.putExtra("id",id);
            startActivity(i);

        }
        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };

    private void showDialog() {

        final Dialog mDialog = new Dialog(getActivity(),R.style.loading_dialog);// 创建自定义样式dialog
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_star_layout, null);// 得到加载view
        mDialog.setContentView(v);
        mDialog.show();//显示
        TextView centerTv = (TextView) v.findViewById(R.id.centerTv);
        ImageView starIm1 = (ImageView) v.findViewById(R.id.starIm1);
        ImageView starIm2 = (ImageView) v.findViewById(R.id.starIm2);
        ImageView starIm3 = (ImageView) v.findViewById(R.id.starIm3);
        switch (star){
            case 1:
                centerTv.setText("您的得分 1颗星！");
                starIm1.setImageResource(R.drawable.u181);
                break;
            case 2:
                centerTv.setText("您的得分 2颗星！");
                starIm1.setImageResource(R.drawable.u181);
                starIm2.setImageResource(R.drawable.u181);
                break;
            case 3:
                centerTv.setText("您的得分 3颗星！");
                starIm1.setImageResource(R.drawable.u181);
                starIm2.setImageResource(R.drawable.u181);
                starIm3.setImageResource(R.drawable.u181);
                break;
        }
        ImageView closeIm = (ImageView) v.findViewById(R.id.closeIm);
        Button button1 = (Button) v.findViewById(R.id.dialogBt1);
        Button button2 = (Button) v.findViewById(R.id.dialogBt2);
        closeIm.setOnClickListener(new View.OnClickListener() { // 设置确定按钮
            @Override
            public void onClick(View paramView) {
                mDialog.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() { // 设置确定按钮
            public void onClick(View paramView) {
                mDialog.dismiss();
                Intent i = new Intent(getContext(), AddWorkLogActivity.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // 设置确定按钮
            @Override
            public void onClick(View paramView) {
                mDialog.dismiss();
                Intent i = new Intent(getContext(), RankActivity.class);
                startActivity(i);
            }
        });
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);

        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        lp.y = (int) (d.getWidth() * (-0.33));
        lp.width = (int) (d.getWidth() * 0.96); // 宽度
        lp.alpha = 0.9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void requestTaskSuccess() {
//        toast("请求成功",false);
//        Log.e("TaskFragment","请求成功");
        noneStr = "没有内容";
        initDatas();
    }

    @Override
    public void requestTaskFaild() {
        toast("请求失败", false);
    }

    @Override
    public void requestStarSuccess() {

    }

    @Override
    public void requestStarFaild() {

    }

    //时间戳转化
}
