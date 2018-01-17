package test.example.com.counselor.view.service.addworklog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import test.example.com.counselor.view.service.addGroupCase.AddGroupCaseActivity;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class AddWorkLogActivity extends BaseActivity implements IAddWorkLogView {
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;

    String[] sumbit_str;
    List<String> list;
    ArrayAdapter<String> adapter;
    AddWorkLogPersenter mAddWorkLogPersenter;
    @BindView(R.id.spinner01)
    Spinner spinner01;
    @BindView(R.id.editText02)
    EditText editText02;
    @BindView(R.id.editText03)
    EditText editText03;
    @BindView(R.id.editText04)
    EditText editText04;
    @BindView(R.id.editText05)
    EditText editText05;
    @BindView(R.id.textview06)
    TextView textview06;
    @BindView(R.id.textview07)
    TextView textview07;
    @BindView(R.id.spinner08)
    Spinner spinner08;
    @BindView(R.id.spinner09)
    Spinner spinner09;
    @BindView(R.id.spinner10)
    Spinner spinner10;
    @BindView(R.id.spinner11)
    Spinner spinner11;
    @BindView(R.id.spinner12)
    Spinner spinner12;
    @BindView(R.id.rl13)
    RelativeLayout rl13;
    @BindView(R.id.editText13)
    EditText editText13;
    @BindView(R.id.textview14)
    TextView textview14;
    private CustomDatePicker customDatePicker1, customDatePicker2;

    private ArrayList<ImageItem> imageItems;

    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addworklog);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mAddWorkLogPersenter = new AddWorkLogPersenter(this, this);
    }


    String[][][] str = new String[][][]{
            {{"出具专业法律意见"},
                    {"审查合同", "出具法律意见", "制定或修改村规民约", "协助选举", "参与谈判签约", "代写文书", "其他"},
                    {"镇街党委政府", "村（居）委", "其他"}},
            {{"法律咨询", "人民调解", "法律援助"},
                    {"民事案件", "刑事案件", "行政", "公证", "其他"},
                    {"老年人", "未成年", "残疾", "妇女", "外来务工人员", "镇街党委政府", "村（居）委", "其他"}},
            {{"法治宣传", "人民调解", "法律援助"},
                    {"法治宣传"},
                    {"老年人", "未成年", "残疾", "妇女", "农民", "下岗职工", "外来务工人员", "企业主", "村（居）委干部", "村（居）民", "其他"}}
    };


    private void initView() {

        super.allow_quit = false;
        titleBarTv.setText("新增工作日志");
        sumbit_str = new String[15];

        //S01
        list = new ArrayList<String>();
        if (MyApplication.getInstance().loginEntity.getCommunityA()!=null)
            list.add(MyApplication.getInstance().loginEntity.getCommunityA());
        if (MyApplication.getInstance().loginEntity.getCommunityB()!=null)
            list.add(MyApplication.getInstance().loginEntity.getCommunityB());
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner01.setAdapter(adapter);
        spinner01.setOnItemSelectedListener(mOnItemClickListener);
        //S08
        list = new ArrayList<String>();
        list.add("村民");
        list.add("村社");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner08.setAdapter(adapter);
        spinner08.setOnItemSelectedListener(mOnItemClickListener);
        //S09
        list = new ArrayList<String>();
        list.add("坐班");
        list.add("电话咨询");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner09.setAdapter(adapter);
        spinner09.setOnItemSelectedListener(mOnItemClickListener);
        //S10
        list = new ArrayList<String>();
        list.add("民事案件");
        list.add("刑事案件");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner10.setAdapter(adapter);
        spinner10.setOnItemSelectedListener(mOnItemClickListener);
        //S11
        list = new ArrayList<String>();
        list.add("咨询");
        list.add("指导");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner11.setAdapter(adapter);
        spinner11.setOnItemSelectedListener(mOnItemClickListener);
        //S12
        list = new ArrayList<String>();
        list.add("老年人");
        list.add("青少年");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_show_worklog, list);
        adapter.setDropDownViewResource(R.layout.spinner_item_worklog);
        spinner12.setAdapter(adapter);
        spinner12.setOnItemSelectedListener(mOnItemClickListener);
        //T6
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        textview06.setText(now);
        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textview06.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(true); // 显示时和分
        customDatePicker1.setIsLoop(true); // 允许循环滚动
        //T7
        textview07.setText(now);
        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textview07.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动


    }

    @OnClick({R.id.textview06, R.id.textview07, R.id.textview14})
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.textview06:
                customDatePicker1.show(textview06.getText().toString());
                break;
            case R.id.textview07:
                customDatePicker2.show(textview07.getText().toString());
                break;
            case R.id.textview14:
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
                    textview14.setText(sb.toString());
                    textview14.setTextSize(10);
                } else {
                    textview14.setText("--");
                }
            } else {
                Toast.makeText(this, "没有选择图片", Toast.LENGTH_SHORT).show();
                textview14.setText("--");
            }
        }
    }

    AdapterView.OnItemSelectedListener mOnItemClickListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (parent.getId()) {
                case R.id.spinner01:
                    sumbit_str[1] = parent.getSelectedItem().toString();
                    break;
                case R.id.spinner08:
                    sumbit_str[8] = parent.getSelectedItem().toString();
                    break;
                case R.id.spinner09:
                    sumbit_str[9] = parent.getSelectedItem().toString();
                    break;
                case R.id.spinner10:
                    sumbit_str[10] = parent.getSelectedItem().toString();
                    break;
                case R.id.spinner11:
                    sumbit_str[11] = parent.getSelectedItem().toString();
                    break;
                case R.id.spinner12:
                    sumbit_str[12] = parent.getSelectedItem().toString();
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
                Intent i = new Intent(AddWorkLogActivity.this, AddGroupCaseActivity.class);
                startActivity(i);
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
            case R.id.sumbitTv:
                //E3
                sumbit_str[2] = editText02.getText().toString();
                sumbit_str[3] = editText03.getText().toString();
                sumbit_str[4] = editText04.getText().toString();
                sumbit_str[5] = editText05.getText().toString();
                sumbit_str[6] = textview06.getText().toString();
                sumbit_str[7] = textview07.getText().toString();
                sumbit_str[13] = editText13.getText().toString();
                mAddWorkLogPersenter.addworkLog(sumbit_str);
                break;
        }
    }


    @Override
    public void addSuccess() {
        toast("添加成功", false);
        MyApplication.getInstance().refresh = true;
        MyApplication.getInstance().finishActivity(this);
        this.finish();
    }

    @Override
    public void addFailed() {
        toast("添加失败", false);
    }

}
