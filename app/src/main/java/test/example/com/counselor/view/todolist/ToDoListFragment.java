package test.example.com.counselor.view.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
import test.example.com.counselor.view.service.addworklog.AddWorkLogActivity;
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
        if(MyApplication.getInstance().show_star_dialog){
            showDialog();
            MyApplication.getInstance().show_star_dialog=false;
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        Log.e("ToDoListFragment","加载数据");
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
                    TextView tv4 = mViewHolder.getView(R.id.itemTv1);
                    tv4.setText(toDoListEntity.getTime());
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
    public void onSelectClick(View view) {
        switch (view.getId()) {
            case R.id.backlogLeftRl:
                fragmentType=0;
                setTabSelection(0);
                initDatas();
                break;
            case R.id.backlogRightRl:
                fragmentType=1;
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
    public void requestToDoListSuccess() {
//        toast("请求成功", false);
        Log.e("ToDoListFragment","请求成功");
        initDatas();
    }

    @Override
    public void requestToDoListFaild() {
        toast("请求失败", false);
    }

    @Override
    public void showDialog(boolean show) {

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

    private void showDialog() {

        final Dialog mDialog = new Dialog(getActivity(),R.style.loading_dialog);// 创建自定义样式dialog
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_normal_layout, null);// 得到加载view
        mDialog.setContentView(v);

//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_rank);// 加载布局
//        mDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

        mDialog.show();//显示
        TextView centerTv = (TextView) v.findViewById(R.id.centerTv);
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
            @Override
            public void onClick(View paramView) {
                mDialog.dismiss();
                Intent i = new Intent(getContext(), AddWorkLogActivity.class);
                startActivity(i);
            }
        });
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        lp.y = (int) (d.getWidth() * (-0.32));
        lp.width = (int) (d.getWidth() * 0.96); // 宽度
        lp.alpha = 0.9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

    private void showCustomizeDialog() {
        final AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity()); // 先得到构造器

        View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_normal_layout,null);
        builder.setView(dialogView);
        builder.create().show();
        TextView centerTv = (TextView) dialogView.findViewById(R.id.centerTv);
        ImageView closeIm = (ImageView) dialogView.findViewById(R.id.closeIm);
        Button button1 = (Button) dialogView.findViewById(R.id.dialogBt1);
        Button button2 = (Button) dialogView.findViewById(R.id.dialogBt2);

        closeIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.create().dismiss();

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddWorkLogActivity.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddWorkLogActivity.class);
                startActivity(i);
            }
        });
    }


}
