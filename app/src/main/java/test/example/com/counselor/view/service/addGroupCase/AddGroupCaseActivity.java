package test.example.com.counselor.view.service.addgroupcase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.CustomDatePicker;
import test.example.com.counselor.util.GlideImageLoader;
import test.example.com.counselor.util.PDialog;


/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddGroupCaseActivity extends BaseActivity implements IAddGroupCaseView {
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textview4)
    TextView textview4;
    @BindView(R.id.spinner5)
    Spinner spinner5;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.textview7)
    TextView textview7;
    @BindView(R.id.spinner8)
    Spinner spinner8;
    @BindView(R.id.editText9)
    EditText editText9;

    String[] sumbit_str;
    int[] sumbit_int;
    List<String> list;
    ArrayAdapter<String> adapter;
    AddGroupCasePersenter mAddGroupCasePersenter;

    private CustomDatePicker customDatePicker1;
    PDialog dialog ;
    private ArrayList<ImageItem> imageItems;

    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addgroupcase);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mAddGroupCasePersenter = new AddGroupCasePersenter(this, this);
    }

    String[] str = new String[]{"劳动争议","环境污染","山林归属","征地拆迁","物业管理","村（居）自治管理","其他"};
    private void initView() {

        super.allow_quit = false;
        titleBarTv.setText("新增群体性案件");
        sumbit_str = new String[10];
        sumbit_int = new int[10];
        //S1
        list = new ArrayList<String>();
        if (MyApplication.getInstance().loginEntity.getCommunityA()!=null)
            list.add(MyApplication.getInstance().loginEntity.getCommunityA().getString("username"));
        if (MyApplication.getInstance().loginEntity.getCommunityB()!=null)
            list.add(MyApplication.getInstance().loginEntity.getCommunityB().getString("username"));
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(mOnItemClickListener);
        //E03
        editText3.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //T4
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        textview4.setText(now);
        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textview4.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(true); // 显示时和分
        customDatePicker1.setIsLoop(true); // 允许循环滚动
        //S5
        list = new ArrayList<String>();
        for(int i=0;i<str.length;i++){
            list.add(str[i]);
        }
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner5.setAdapter(adapter);
        spinner5.setOnItemSelectedListener(mOnItemClickListener);
        //S8
        list = new ArrayList<String>();
        list.add("未完结");
        list.add("已完结");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner8.setAdapter(adapter);
        spinner8.setOnItemSelectedListener(mOnItemClickListener);
    }

    @OnClick({R.id.textview4,R.id.textview7})
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.textview4:
                customDatePicker1.show(textview4.getText().toString());
                break;
            case R.id.textview7:
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setImageLoader(new GlideImageLoader());
                imagePicker.setMultiMode(true);   //多选
                imagePicker.setShowCamera(true);  //显示拍照按钮
                imagePicker.setSelectLimit(9);    //最多选择9张
                imagePicker.setCrop(false);       //不进行裁剪
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    //图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (imageItems != null && imageItems.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < imageItems.size(); i++) {
                        if (i == imageItems.size() - 1)
                            sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).path);
                        else
                            sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).path).append("\n");
                    }
                    textview7.setText(sb.toString());
                    textview7.setTextSize(10);
                } else {
                    textview7.setText("--");
                }
            } else {
                Toast.makeText(this, "没有选择图片", Toast.LENGTH_SHORT).show();
                textview7.setText("--");
            }
        }
    }

    AdapterView.OnItemSelectedListener mOnItemClickListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (parent.getId()) {
                case R.id.spinner1:
                    sumbit_str[1] = parent.getSelectedItem().toString();
                    if(position==0){
                        sumbit_int[1] = MyApplication.getInstance().loginEntity.getVillageAId();
                    }else {
                        sumbit_int[1] = MyApplication.getInstance().loginEntity.getVillageBId();
                    }
                    break;
                case R.id.spinner5:
                    sumbit_int[5] = position;
                    break;
                case R.id.spinner8:
                    sumbit_int[8] = position;
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    @OnClick({R.id.backTv, R.id.sumbitTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:
                sumbit_str[2] = editText2.getText().toString();
                sumbit_str[3] = editText3.getText().toString();
                sumbit_str[4] = textview4.getText().toString();
                sumbit_str[6] = editText6.getText().toString();
                sumbit_str[9] = editText9.getText().toString();
                if(imageItems!=null){
                    dialog = new PDialog(this,"正在上传图片",false);
                    dialog.show();
                    mAddGroupCasePersenter.addImage(imageItems);
                }
                else if(TextUtils.isEmpty(sumbit_str[2])||TextUtils.isEmpty(sumbit_str[6])||
                        TextUtils.isEmpty(sumbit_str[9])||TextUtils.isEmpty(sumbit_str[3])){
                    toast("带星号的输入不能为空",false);
                }else {
                    sumbit_int[3] =Integer.valueOf(sumbit_str[3]).intValue();
                    sumbit(sumbit_str,sumbit_int);
                }
                break;
        }
    }


    @Override
    public void addSuccess() {
        toast("添加成功", false);
        dialog.dismiss();
        MyApplication.getInstance().refresh[0] = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }

    @Override
    public void addFailed() {
        toast("添加失败", false);
    }

    int index=0;
    @Override
    public void addImageSuccess(String imageUrl) {
        index++;
        sumbit_str[7] += "#"+imageUrl;
        dialog.setMessage("正在上传第"+(index+1)+"张图片，共"+(imageItems.size())+"张");
        if(index==imageItems.size()) {
            dialog.dismiss();
            sumbit(sumbit_str,sumbit_int);
        }
    }

    @Override
    public void addImageFailed() {
        toast("上传图片第"+index+1+"张时出错，仅提交其余内容", false);
        dialog.dismiss();
        sumbit(sumbit_str,sumbit_int);
    }

    private void sumbit(String[] sumbit_str, int[] sumbit_int){
        dialog = new PDialog(this,"正在提交",false);
        dialog.show();
        mAddGroupCasePersenter.addGroupCase(sumbit_str,sumbit_int);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (dialog != null) {
            if (dialog.showDialog) {
                dialog.dismiss();
                toast("现在正在上传图片，强行退出可能导致上传失败！", true);
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    };
}
