package test.example.com.counselor.view.task;

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
import test.example.com.counselor.view.news.NewsActivity;
import test.example.com.counselor.view.rank.RankActivity;
import test.example.com.counselor.view.service.addworklog.AddWorkLogActivity;
import test.example.com.counselor.view.task.entity.DoneTasskEntity;
import test.example.com.counselor.view.task.entity.ToDoTaskEntity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class TaskFragment extends BaseFragment implements ITaskView {

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

    TaskPresenter mTaskPresenter;
    List<ToDoTaskEntity> toDoListEntities;
    List<DoneTasskEntity> doneListEntities;
    private int fragmentType;
    private int[] fragmentCuttent;
    private int requestSize=20;
    private int star;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_backlog;
    }


    @Override
    protected void initPresenter() {
        fragmentType = 0;
        fragmentCuttent = new int[]{0, 0};
        mTaskPresenter = new TaskPresenter(getActivity(), this);
        star = mTaskPresenter.requestStar();
        mTaskPresenter.requestTask(fragmentCuttent[0], requestSize, 1, MyApplication.getInstance().loginEntity.getId());
        mTaskPresenter.requestTask(fragmentCuttent[1], requestSize, 0, MyApplication.getInstance().loginEntity.getId());
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
        Log.e("TaskFragment","加载数据");
        if (fragmentType == 0) {
            toDoListEntities = mTaskPresenter.getToDoListEntityList();
            backlogLv.setAdapter(new Common1Adapter<ToDoTaskEntity>(super.mContext, toDoListEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, ToDoTaskEntity toDoTaskEntity, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(toDoTaskEntity.getTitle());
                    tv2.setText(toDoTaskEntity.getFrom());
                    tv3.setText(toDoTaskEntity.getTime());

                }
            });
            backlogLv.setOnItemClickListener(onItemClickListener);

        } else {
            doneListEntities = mTaskPresenter.getDoneListEntityList();
            backlogLv.setAdapter(new Common1Adapter<DoneTasskEntity>(super.mContext, doneListEntities,
                    R.layout.item_commonlist, onItemClickListener) {
                @Override
                protected void convertView(ViewHolder1 mViewHolder, View item, DoneTasskEntity toDoListEntity, int position) {
                    TextView tv1 = mViewHolder.getView(R.id.itemTv1);
                    TextView tv2 = mViewHolder.getView(R.id.itemTv2);
                    TextView tv3 = mViewHolder.getView(R.id.itemTv3);
                    tv1.setText(toDoListEntity.getTitle());
                    tv2.setText(toDoListEntity.getFrom());
                    tv3.setText(toDoListEntity.getTime());
                }
            });
            backlogLv.setOnItemClickListener(onItemClickListener);
        }
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
    public void requestToDoListSuccess() {
//        toast("请求成功", false);
        Log.e("TaskFragment","请求成功");
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
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toast("" + (position), true);
            mTaskPresenter.requestTask(fragmentCuttent[fragmentType],requestSize,
                    fragmentType, MyApplication.getInstance().loginEntity.getId());

        }
    };

    private void showDialog() {

        final Dialog mDialog = new Dialog(getActivity(),R.style.loading_dialog);// 创建自定义样式dialog
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_normal_layout, null);// 得到加载view
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
        lp.y = (int) (d.getWidth() * (-0.32));
        lp.width = (int) (d.getWidth() * 0.96); // 宽度
        lp.alpha = 0.9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

}
