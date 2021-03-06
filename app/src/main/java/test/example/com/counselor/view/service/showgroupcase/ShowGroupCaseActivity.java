package test.example.com.counselor.view.service.showgroupcase;

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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.OpenFileUtil;
import test.example.com.counselor.util.PDialog;

import static test.example.com.counselor.R.id.Rl14;


/**
 * Created by Sli.D on 2017/12/21.
 */

public class ShowGroupCaseActivity extends BaseActivity implements IShowGroupCaseView {
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;

    String[] sumbit_str;
    int[] sumbit_int;
    List<String> list;
    ArrayAdapter<String> adapter;
    ShowGroupCasePersenter mShowGroupCasePersenter;
    GroupCaseDetialEntity groupCaseDetialEntity;
    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.textview3)
    TextView textview3;
    @BindView(R.id.textview4)
    TextView textview4;
    @BindView(R.id.textview5)
    TextView textview5;
    @BindView(R.id.textview6)
    TextView textview6;
    @BindView(R.id.textview7)
    TextView textview7;
    @BindView(R.id.textview8)
    TextView textview8;
    @BindView(R.id.showImageLv)
    ListView showImageLv;
    @BindView(R.id.Rl8)
    RelativeLayout Rl8;
    @BindView(R.id.textview9)
    TextView textview9;

    int downloadIndex = 0;
    String[] imageName ;
    String[] imageUrl ;
    PDialog dialog;
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showgroupcase);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("群体性案件详情");
        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowGroupCasePersenter = new ShowGroupCasePersenter(this, this);
        mShowGroupCasePersenter.requestGroupCaseDetial(id);
        initView();
    }


    private void initView() {

        groupCaseDetialEntity = mShowGroupCasePersenter.getGroupCaseDetialEntity();
        if (groupCaseDetialEntity != null) {
            textview1.setText(groupCaseDetialEntity.getServiceVillageName());
            textview2.setText(groupCaseDetialEntity.getMatterPlace());
            textview3.setText(groupCaseDetialEntity.getMatterNum()+"");
            textview4.setText(groupCaseDetialEntity.getMatterTime());
            textview5.setText(groupCaseDetialEntity.getObjecttype());
            textview6.setText(groupCaseDetialEntity.getServiceContent());
            textview7.setText(groupCaseDetialEntity.getResultType());
            textview9.setText(groupCaseDetialEntity.getResultContent());

            if(groupCaseDetialEntity.getAccessory()!=null){
                imageUrl = groupCaseDetialEntity.getAccessory().split("#");
                Log.e("imageUrl",""+imageUrl);
                if(imageUrl.length-1!=0){
                    imageName = new String[imageUrl.length-1];
                    textview8.setText("有"+(imageUrl.length-1)+"张图，点击查看");
                }else {
                    ViewGroup.LayoutParams para = Rl8.getLayoutParams();
                    para.height = 0;
                    Rl8.setLayoutParams(para);
                }

            } else {
                ViewGroup.LayoutParams para = Rl8.getLayoutParams();
                para.height = 0;
                Rl8.setLayoutParams(para);
            }

        }

    }

    @OnClick({R.id.backTv, R.id.textview8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.textview8:
                dialog = new PDialog(this,"正在下载第1张图片，共"+(imageUrl.length-1)+"张",false);

                for(int i=1;i<imageUrl.length;i++){
                    String fileName = imageUrl[i].split("/")[imageUrl[i].split("/").length-1];
                    imageName[i-1] = fileName;
                    if(!OpenFileUtil.fileIsExists(Constants.getAppImageFolder()+"/"+fileName)){
                        textview8.setText("正在下载,请稍等...");
                        dialog.show();
                        Log.e("downLoadImage",imageUrl.length+ " "+fileName+" "+imageUrl[i]);
                        mShowGroupCasePersenter.downLoadImage(imageUrl[i],fileName);
                    }else {
                        initImage();
                    }
                    downloadIndex++;
                }
                break;
        }
    }

    private void initImage(){
        if (downloadIndex==imageName.length){
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
                        OpenFileUtil.openFile(ShowGroupCaseActivity.this,f);
                    }

                }
            });
        }

    }

    @Override
    public void requestGroupCaseSuccess() {
        initView();
    }

    @Override
    public void requestGroupCaseFailed() {

    }

    int index=0;
    @Override
    public void downloadImageSuccess() {
        index++;
        Log.e("downLoadImage",imageUrl.length+ " "+index+" "+imageUrl[index]);
        dialog.setMessage("正在下载第"+(index+1)+"张图片，共"+(imageUrl.length-1)+"张");
        if(index == imageName.length){
            dialog.dismiss();
            textview8.setText("下载完成");
            initImage();
        }
    }

    @Override
    public void downloadImageFailed() {

    }
}
