package test.example.com.counselor.view.assessment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.Common1Adapter;
import test.example.com.counselor.adapter.ViewHolder1;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;

public class AssessmentActivity extends BaseActivity implements IAssessmentView {


    AssessmentPresenter mAssessmentPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;

    @BindView(R.id.assessmentLv)
    ListView assessmentLv;
    List<AssessmentEntity> assessmentEntities;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mAssessmentPresenter = new AssessmentPresenter(this);
        titleBarTv.setText("我的合同");
        mContext=this;
        mAssessmentPresenter.requestAssessment();

    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_assessment);
    }

    private boolean show_vs = false;

    private void initDatas() {
        Log.e("AssessmentActivity", "加载数据");
        assessmentEntities = mAssessmentPresenter.getAssessmentEntity();
        assessmentLv.setAdapter(new Common1Adapter<AssessmentEntity>(this, assessmentEntities,
                R.layout.item_assessment, onItemClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, AssessmentEntity assessmentEntity, int position) {
                TextView tv1 = mViewHolder.getView(R.id.assessItemTv1);
                TextView tv2 = mViewHolder.getView(R.id.assessItemTv2);
                TextView tv3 = mViewHolder.getView(R.id.assessItemTv3);
                TextView tv4 = mViewHolder.getView(R.id.assessItemTv4);
                tv1.setText(assessmentEntity.getStr1());
                tv2.setText(assessmentEntity.getStr2());
                tv3.setText(assessmentEntity.getStr3());
                tv4.setText(assessmentEntity.getStr4());

            }
        });
        assessmentLv.setOnItemClickListener(onItemClickListener);

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
    public void requestAssessmentSuccess() {
        toast("请求成功！", true);
        initDatas();
    }

    @Override
    public void requestAssessmentFailed() {
        toast("请求失败！", true);
    }

    @OnClick(R.id.backTv)
    public void onClick() {
    }

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toast("" + (position), true);
        }
    };

}
