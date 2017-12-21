package test.example.com.counselor.view.schedule;

/**
 * Created by Sli.D on 2017/12/20.
 */


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.ListAdapter;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.entity.ListEntity;
import test.example.com.counselor.listener.MyLvClickListener;
import test.example.com.counselor.view.backlog.BacklogActivity;
import test.example.com.counselor.view.personal.PersonalActivity;
import test.example.com.counselor.view.service.ServiceActivity;

public class ScheduleActivity extends BaseActivity {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.buttomIm2)
    ImageView buttomIm2;
    @BindView(R.id.buttomTv2)
    TextView buttomTv2;

    ListEntity mEntity;
    List<ListEntity> entityList;
    ListAdapter mListAdapter;
    @BindView(R.id.scheduleLv)
    ListView scheduleLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        buttomTv2.setTextColor(Color.rgb(1, 160, 243));
        setItem();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_schedule);
    }

    private void setItem() {
        //构造数据
        entityList = new ArrayList<ListEntity>();//空指针高发处
        for (int i = 0; i < 50; i++) {
            mEntity = new ListEntity(R.layout.item_commonlist,
                    "开展深入学校贯彻十九大精神报告会", "来源：星沙街道司法所", "11:" + (10 + i));
            entityList.add(mEntity);
        }
        //创建adapter
        mListAdapter = new ListAdapter(this, entityList, mClickListener, onItemClickListener);
        scheduleLv.setAdapter(mListAdapter);
        //设置回调
        scheduleLv.setOnItemClickListener(onItemClickListener);
    }

    @OnClick({R.id.buttomRl1, R.id.buttomRl2, R.id.buttomRl3, R.id.buttomRl4})
    public void onButtomBarClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.buttomRl1:
                i = new Intent(ScheduleActivity.this, BacklogActivity.class);
                startActivity(i);
                break;
            case R.id.buttomRl2:
                break;
            case R.id.buttomRl3:
                i = new Intent(ScheduleActivity.this, ServiceActivity.class);
                startActivity(i);
                break;
            case R.id.buttomRl4:
                i = new Intent(ScheduleActivity.this, PersonalActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toast("时间：" + "11:"+(10+position), true);
        }
    };
    //控件响应回调
    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
            toast("这是第" + (position + 1) + "条", true);
        }
        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
            myOnClick((Integer) v.getTag(), v);
        }

    };
}
