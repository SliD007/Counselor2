package test.example.com.counselor.view.task.showtask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.OpenFileUtil;
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
    Context mContext;
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
        mContext = this;
        super.allow_quit = false;
        titleBarTv.setText("待办事项详情");

        Intent i =getIntent();
        int id = i.getIntExtra("id",0);
        int fromWhere = i.getIntExtra("fromWhere",0);

        mShowTaskPresenter = new ShowTaskPresenter(this,this);

        mShowTaskPresenter.changeTaskState(id,fromWhere);
        mShowTaskPresenter.requestTaskDetial(id,fromWhere);
    }
    String fileName ;
    private void initView() {
        taskDetialEntity = mShowTaskPresenter.getTaskDetialEntity();
        if (taskDetialEntity != null){
            taskTitleTv.setText(taskDetialEntity.getTitle());
            taskFromWhereTv.setText("来源："+taskDetialEntity.getFromWhere());
            taskCreateTimeTv.setText(""+TimeUtil.getDateToString(taskDetialEntity.getCreateTime(),TimeUtil.DataTime));
            taskContextTv.setText(taskDetialEntity.getContent());
            if (taskDetialEntity.getAccesory()!=null){
                fileName = taskDetialEntity.getTitle()+taskDetialEntity.getAccesory().split(".")[taskDetialEntity.getAccesory().split(".").length-1];
                taskFileTv.setText("点击查看附件");

                taskFileTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(OpenFileUtil.fileIsExists(Constants.getAppDownloadFolder()+"/"+fileName)){
                            File file = new File(Constants.getAppDownloadFolder()+"/"+fileName);
                            OpenFileUtil.openFile(mContext,file);
                        }else {
                            taskFileTv.setText("下载附件中，请稍候...");
                            mShowTaskPresenter.downLoadTaskFile(taskDetialEntity.getAccesory(),fileName);
                        }

                    }
                });
            }
        }


    }


    @OnClick(R.id.backTv)
    public void onClick() {
        MyApplication.getInstance().refresh[0] = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }

    @Override
    public void requestTaskConfigurationSuccess() {
//        toast("请求成功",false);
        initView();
    }

    @Override
    public void requestTaskConfigurationFailed() {
        toast("请求失败",false);
    }

    @Override
    public void changeTaskStateSuccess() {
//        toast("修改成功",false);
    }

    @Override
    public void changeTaskStateFailed() {
        toast("修改失败",false);
    }

    @Override
    public void downloadTaskFileSuccess() {
        toast("下载成功",false);
        File file = new File(Constants.getAppDownloadFolder()+"/"+fileName);
        OpenFileUtil.openFile(mContext,file);
    }

    @Override
    public void downloadTaskFileFialed() {
        toast("下载失败",false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            MyApplication.getInstance().refresh[0] = true;
            MyApplication.getInstance().finishActivity(this);
            this.finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
