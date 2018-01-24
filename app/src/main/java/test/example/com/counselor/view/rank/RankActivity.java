package test.example.com.counselor.view.rank;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import test.example.com.counselor.base.MyLvClickListener;

public class RankActivity extends BaseActivity implements IRankView {


    RankPresenter mRankPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.rankLv)
    ListView rankLv;
    @BindView(R.id.noneTv)
    TextView noneTv;

    ViewGroup.LayoutParams para;

    String noneStr = "加载中";
    List<RankEntity> rankEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mRankPresenter = new RankPresenter(this);
        titleBarTv.setText("我的排行");
        para = noneTv.getLayoutParams();
        mRankPresenter.requestRank();
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rank);
    }

    private void initDatas() {
        rankEntities = mRankPresenter.getRankEntity();
        Log.e("AssessmentActivity", "加载数据"+(rankEntities.size()));
        if(rankEntities!=null){
            if(rankEntities.size()!=0){
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
        rankLv.setAdapter(new Common1Adapter<RankEntity>(this, rankEntities,
                R.layout.item_rank, onItemClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, RankEntity rankEntity, int position) {
                TextView itemRankTv = mViewHolder.getView(R.id.itemRankTv);
                TextView itemNameTv = mViewHolder.getView(R.id.itemNameTv);
                TextView itemWorkForTv = mViewHolder.getView(R.id.itemWorkForTv);

                TextView itemNumRankTv1 = mViewHolder.getView(R.id.itemNumRankTv1);
                TextView itemNumRankTv3 = mViewHolder.getView(R.id.itemNumRankTv3);
                TextView itemNumRankTv4 = mViewHolder.getView(R.id.itemNumRankTv4);
                TextView itemNumRankTv5 = mViewHolder.getView(R.id.itemNumRankTv5);
                TextView itemNumRankTv6 = mViewHolder.getView(R.id.itemNumRankTv6);
                if(position==0){
                    itemRankTv.setText("我");
                }else {
                    itemRankTv.setText("排名 " + rankEntity.getRank());
                }
                itemNameTv.setText(rankEntity.getCounselorName());
                itemWorkForTv.setText(rankEntity.getVillage());

                itemNumRankTv1.setText(rankEntity.getConsultCount() + "次" );
                itemNumRankTv3.setText(rankEntity.getAidCount()+"次");
                itemNumRankTv4.setText(rankEntity.getMediateCount()+"次");
                itemNumRankTv5.setText(rankEntity.getPublicityCount()+"次");
                itemNumRankTv6.setText(rankEntity.getChairCount()+"次");

            }
        });
        rankLv.setOnItemClickListener(onItemClickListener);
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
    public void requestRankSuccess() {
//        toast("请求成功！", true);
        noneStr = "没有内容";
        initDatas();
    }

    @Override
    public void requestRankFailed() {
        toast("当前律师数量较少，暂时无法显示！", true);
    }

    @OnClick(R.id.backTv)
    public void onClick() {
    }

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            toast("" + (position), true);
        }
    };

    MyLvClickListener mClickListener = new MyLvClickListener() {
        @Override
        public void myOnClick(int position, View view) {
//            toast("" + (position), true);

        }

        public void onClick(View v) {   //先响应onclick(权限高) 可以将响应移交出去
//            myOnClick((Integer) v.getTag(), v);
            initDatas();
        }

    };
}
