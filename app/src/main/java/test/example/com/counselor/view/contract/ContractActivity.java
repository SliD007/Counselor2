package test.example.com.counselor.view.contract;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.Common1Adapter;
import test.example.com.counselor.adapter.ViewHolder1;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.base.MyLvClickListener;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.OpenFileUtil;
import test.example.com.counselor.util.PDialog;

import static com.lzy.imagepicker.ui.ImageGridActivity.REQUEST_PERMISSION_STORAGE;

public class ContractActivity extends BaseActivity implements IContractView {


    ContractPresenter mContractPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;

    @BindView(R.id.contractLv)
    ListView contractLv;
    @BindView(R.id.contractWv)
    WebView contractWv;
    @BindView(R.id.noneTv)
    TextView noneTv;

    ViewGroup.LayoutParams para;

    String noneStr = "加载中";

    ArrayList<Map<String,Integer>> mData= new ArrayList<Map<String,Integer>>();
    List<ContractEntity> contractEntities;
    Context mContext;
    TextView downloadTv;
    TextView showTv;
    private String contractUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mContractPresenter = new ContractPresenter(this);
        titleBarTv.setText("我的合同");
        mContext=this;
        para = noneTv.getLayoutParams();
        mContractPresenter.requestContract();
        initDatas();
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contract);
    }

    private void initDatas() {
        Log.e("AssessmentActivity", "加载数据");
        contractEntities = mContractPresenter.getContractEntity();
        if(contractEntities!=null){
            if(contractEntities.size()!=0){
                para.height = 0;
                noneTv.setLayoutParams(para);
            }else {
                para.height = 100;
                noneTv.setLayoutParams(para);
                noneTv.setText(noneStr);
            }
        }else {
            para.height = 100;
            noneTv.setLayoutParams(para);
            noneTv.setText(noneStr);
        }
        contractLv.setAdapter(new Common1Adapter<ContractEntity>(this, contractEntities,
                R.layout.item_contract,mClickListener, onItemClickListener ) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, ContractEntity contractEntity, final int position) {
                TextView tv1 = mViewHolder.getView(R.id.itemWorkForTv);
                TextView tv2 = mViewHolder.getView(R.id.tv2);
                TextView tv3 = mViewHolder.getView(R.id.tv3);
                TextView tv4 = mViewHolder.getView(R.id.tv4);
                TextView tv5 = mViewHolder.getView(R.id.tv5);
                TextView tv6 = mViewHolder.getView(R.id.tv6);
                TextView tv7 = mViewHolder.getView(R.id.tv7);
                TextView tv8 = mViewHolder.getView(R.id.tv8);
                downloadTv = mViewHolder.getView(R.id.tv9);
                showTv = mViewHolder.getView(R.id.tv10);
                if(contractEntities!=null){
                    final String village = contractEntities.get(position).getVillage().getString("username");
                    tv1.setText("服务村社："+contractEntities.get(position).getVillage().getString("username"));
                    tv2.setText("法律顾问："+contractEntities.get(position).getCounselor().getString("username"));
                    tv3.setText("村社法人："+contractEntities.get(position).getRepresentative());
                    tv4.setText("顾问机构："+contractEntities.get(position).getOffice());
                    tv5.setText("服务年度："+contractEntities.get(position).getServiceYear());
                    tv6.setText("合同期限："+contractEntities.get(position).getDeadLine());
                    tv7.setText("合同金额："+contractEntities.get(position).getMoney()+"");
                    tv8.setText("合同状态："+contractEntities.get(position).getContractStatus());

                    contractUrl = contractEntities.get(position).getAccessory();
                    if(contractUrl==null){
//                        downloadTv.setText("");
                    }else {
                        downloadTv.setText("点击查看合同");
                        downloadTv.setTag(position);
                        downloadTv.setOnClickListener(mClickListener);
                    }

                }


            }
        });
        contractLv.setOnItemClickListener(onItemClickListener);

    }

    @OnClick({R.id.backTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
        }
    }

    @Override
    public void requestContractSuccess() {
//        toast("请求成功！", true);
        noneStr = "没有内容";
        initDatas();
    }

    @Override
    public void requestContractFailed() {
        toast("请求失败！", true);
    }

    @Override
    public void downloadContractSuccess() {
        dialog.dismiss();
        toast("下载已完成",false);
        initDatas();
    }
    PDialog dialog ;
    @Override
    public void downloadContractFailed() {
        toast("下载失败",false);
    }

    //控件响应回调
    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
            if(contractUrl.split(":")[0].equals("httsp")){

                String filePath = contractEntities.get(position).getVillage().getString("username")+"服务合同.pdf";
                if(OpenFileUtil.fileIsExists(Constants.getAppDownloadFolder()+"/"+filePath)){
                    File f=new File(Constants.getAppDownloadFolder()+"/"+contractEntities.get(position).getVillage().getString("username")+"服务合同.pdf");
                    if (f == null){
                        toast("文件不存在",false);
                    }else {
                        Log.e("OpenFileUtil",""+f);
                        OpenFileUtil.openFile(ContractActivity.this,f);
                    }
                }else {
                    mContractPresenter.downLoadContract(contractUrl,filePath);
                    TextView tv = (TextView) view;
                    tv.setText("下载中");
                    dialog = new PDialog(mContext,"正在下载合同",false);
                    dialog.show();
                }


            }else {
                contractWv.loadUrl("http://www.baidu.com/");
                //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//                contractWv.setWebViewClient(new WebViewClient(){
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        // TODO Auto-generated method stub
//                        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                        view.loadUrl(url);
//                        return true;
//                    }
//                });
            }


        }
        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //获取权限
            } else {
                toast("权限被禁止，无法下载文件！", false);
            }
        }
    }


}
