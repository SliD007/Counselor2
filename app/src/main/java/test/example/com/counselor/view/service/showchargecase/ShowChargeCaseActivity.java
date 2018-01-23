package test.example.com.counselor.view.service.showchargecase;

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
import test.example.com.counselor.util.PDialog;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowChargeCaseActivity extends BaseActivity implements IShowChargeCaseView {

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
    @BindView(R.id.textview13)
    TextView textview13;
    @BindView(R.id.textview14)
    TextView textview14;
    @BindView(R.id.textview15)
    TextView textview15;
    @BindView(R.id.textview16)
    TextView textview16;
    @BindView(R.id.textview17)
    TextView textview17;
    @BindView(R.id.Rl14)
    RelativeLayout Rl14;
    @BindView(R.id.showImageLv)
    ListView showImageLv;
    int downloadIndex = 0;
    String[] imageName ;
    String[] imageUrl ;
    PDialog dialog;

    private ShowChargeCasePresenter mShowChargeCasePresenter;
    private ChargeCaseDetialEntity chargeCaseDetialEntity;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showchargecase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        super.allow_quit = false;
        titleBarTv.setText("另行收费案件详情");
        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        mShowChargeCasePresenter = new ShowChargeCasePresenter(this, this);
        mShowChargeCasePresenter.requestWorkLogDetial(id);
    }

    private void initView() {
        chargeCaseDetialEntity = mShowChargeCasePresenter.getChargeCaseDetialEntity();
        if (chargeCaseDetialEntity != null) {
            textview01.setText(chargeCaseDetialEntity.getServiceVillageName());
            textview02.setText(chargeCaseDetialEntity.getServiceObject());
            textview03.setText(chargeCaseDetialEntity.getObjectContact());
            textview04.setText(chargeCaseDetialEntity.getObjectAddress());
            textview05.setText(chargeCaseDetialEntity.getMatterMoney());

            textview08.setText(chargeCaseDetialEntity.getFromType());
            textview09.setText(chargeCaseDetialEntity.getServiceType());
            textview10.setText(chargeCaseDetialEntity.getMatterType());
            textview11.setText(chargeCaseDetialEntity.getSubType());
            textview12.setText(chargeCaseDetialEntity.getObjecttype());
            textview13.setText(chargeCaseDetialEntity.getServiceContent());

            textview15.setText(chargeCaseDetialEntity.getResultType());
            textview16.setText(chargeCaseDetialEntity.getResultContent());
            textview17.setText("否");
            if(chargeCaseDetialEntity.isConflict())
                textview17.setText("是");

            if(chargeCaseDetialEntity.getAccessory()!=null){
                imageUrl = chargeCaseDetialEntity.getAccessory().split("#");
                imageName = new String[imageUrl.length-1];
                textview14.setText("有"+(imageUrl.length-1)+"张图，点击查看");
            } else {
                ViewGroup.LayoutParams para = Rl14.getLayoutParams();
                para.height = 0;
                Rl14.setLayoutParams(para);
            }
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
                dialog = new PDialog(this,"正在下载第1张图片，共"+(imageUrl.length-1)+"张",false);

                for(int i=1;i<imageUrl.length;i++){
                    String fileName = imageUrl[i].split("/")[imageUrl[i].split("/").length-1];
                    imageName[i-1] = fileName;
                    if(!OpenFileUtil.fileIsExists(Constants.getAppImageFolder()+"/"+fileName)){
                        textview14.setText("正在下载,请稍等...");
                        dialog.show();
                        Log.e("downLoadImage",imageUrl.length+ " "+fileName+" "+imageUrl[i]);
                        mShowChargeCasePresenter.downLoadImage(imageUrl[i],fileName);
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
                        OpenFileUtil.openFile(ShowChargeCaseActivity.this,f);
                    }

                }
            });
        }

    }
}
