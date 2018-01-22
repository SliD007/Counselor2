package test.example.com.counselor.view.service.showworklog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowWorkLogActivity extends BaseActivity implements IShowWorkLogView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.textview01)
    TextView textview01;
    @BindView(R.id.textview02)
    TextView textview02;
    @BindView(R.id.textview03)
    TextView textview03;
    @BindView(R.id.textview04)
    TextView textview04;
    @BindView(R.id.textview05)
    TextView textview05;

    @BindView(R.id.textview08)
    TextView textview08;
    @BindView(R.id.textview09)
    TextView textview09;
    @BindView(R.id.textview10)
    TextView textview10;
    @BindView(R.id.textview11)
    TextView textview11;
    @BindView(R.id.textview12)
    TextView textview12;
    @BindView(R.id.rl13)
    RelativeLayout rl13;
    @BindView(R.id.textview13)
    TextView textview13;
    @BindView(R.id.textview14)
    TextView textview14;
    @BindView(R.id.textview15)
    TextView textview15;

    @BindView(R.id.showImageLv)
    ListView showImageLv;
    int downloadIndex = 0;
    String[] imageName ;
    String[] content ;
    ProgressDialog dialog;
    private ShowWorkLogPresenter mShowWorkLogPresenter;
    private WorkLogDetialEntity workLogDetialEntity;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showworklog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("工作记录详情");

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowWorkLogPresenter = new ShowWorkLogPresenter(this, this);
        mShowWorkLogPresenter.requestWorkLogDetial(id);

        dialog= new ProgressDialog(this);
    }

    private void initView() {
        workLogDetialEntity = mShowWorkLogPresenter.getWorkLogDetialEntity();
        if (workLogDetialEntity != null) {
            textview01.setText(workLogDetialEntity.getServiceVillageName());
            textview02.setText(workLogDetialEntity.getServiceObject());
            textview03.setText(workLogDetialEntity.getObjectContact());
            textview04.setText(workLogDetialEntity.getServiceIdentity());
            textview05.setText(workLogDetialEntity.getInObject());

            textview08.setText(workLogDetialEntity.getFromType());
            textview09.setText(workLogDetialEntity.getServiceType());
            textview10.setText(workLogDetialEntity.getMatterType());
            textview11.setText(workLogDetialEntity.getSubType());
            textview12.setText(workLogDetialEntity.getObjecttype());
            content = workLogDetialEntity.getServiceContent().split("#");
            imageName = new String[content.length-1];
            textview13.setText(content[0]);
            if(content.length>1){
                textview14.setText("有"+(content.length-1)+"张图，点击查看");

            }
            textview15.setText(workLogDetialEntity.getResultType());

        }

    }
    @OnClick({R.id.backTv,R.id.textview14})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.textview14:

                for(int i=1;i<content.length;i++){
                    String fileName = content[i].split("/")[content[i].split("/").length-1];
                    imageName[i-1] = fileName;
                    if(!OpenFileUtil.fileIsExists(Constants.getAppImageFolder()+"/"+fileName)){
                        textview14.setText("正在下载,请稍等...");

                        dialog.setMessage("正在下载");
                        dialog.show();
                        dialog.setCancelable(false);
                        mShowWorkLogPresenter.downLoadImage(content[i],fileName);
                    }else {
                        initImage();
                    }
                    downloadIndex++;
                }
                break;
        }
    }


    @Override
    public void requestWorkLogDetialSuccess() {
//        toast("请求成功", false);
        initView();
    }

    @Override
    public void requestWorkLogDetialFailed() {
        toast("请求失败", false);
    }

    @Override
    public void downloadImageSuccess() {
        dialog.dismiss();
        textview14.setText("下载完成");
        initImage();
    }

    @Override
    public void downloadImageFailed() {

    }

    private void initImage(){
        if (downloadIndex==content.length-1){
            Log.e("imageName",""+imageName.toString());
            ViewGroup.LayoutParams para = showImageLv.getLayoutParams();
            para.height = 150*imageName.length;
            showImageLv.setLayoutParams(para);
            showImageLv.setAdapter(new ArrayAdapter<String>(this,R.layout.item_1list,imageName));
            showImageLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    File f=new File(Constants.getAppImageFolder()+"/"+imageName[position]);
                    if (f == null){
                        toast("文件不存在",false);
                    }else {
                        Log.e("OpenFileUtil",""+f);
                        OpenFileUtil.openFile(ShowWorkLogActivity.this,f);
                    }

                }
            });
        }

    }
}
