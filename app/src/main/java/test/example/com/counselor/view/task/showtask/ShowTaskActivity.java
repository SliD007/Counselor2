package test.example.com.counselor.view.task.showtask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.util.TimeUtil;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowTaskActivity extends BaseActivity implements IShowTaskView{

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.taskTitleTv)
    TextView taskTitleTv;
    @BindView(R.id.taskFromWhereTv)
    TextView taskFromWhereTv;
    @BindView(R.id.taskCreateTimeTv)
    TextView taskCreateTimeTv;
    @BindView(R.id.taskContextTv)
    TextView taskContextTv;
    @BindView(R.id.taskFileTv)
    TextView taskFileTv;

    private ShowTaskPresenter mShowTaskPresenter;
    private TaskDetialEntity taskDetialEntity;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showtask);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("待办事项详情");

        Intent i =getIntent();
        int id = i.getIntExtra("id",0);
        int fromWhere = i.getIntExtra("fromWhere",0);
        mShowTaskPresenter = new ShowTaskPresenter(this,this);
        mShowTaskPresenter.requestTaskDetial(id,0);
    }

    private void initView() {
        taskDetialEntity = mShowTaskPresenter.getTaskDetialEntity();
        taskTitleTv.setText(taskDetialEntity.getTitle());
        taskFromWhereTv.setText("来源："+taskDetialEntity.getFromWhere());
        taskCreateTimeTv.setText("时间："+TimeUtil.getDateToString(taskDetialEntity.getCreateTime(),TimeUtil.DataTime));
        taskContextTv.setText(taskDetialEntity.getContent());
        taskFileTv.setText("无附件");
    }

    @OnClick(R.id.backTv)
    public void onClick() {

    }

    @Override
    public void requestTaskConfigurationSuccess() {
        toast("请求成功",false);
        initView();
    }

    @Override
    public void requestTaskConfigurationFailed() {
        toast("请求失败",false);
    }
}
