package test.example.com.counselor.view.rank;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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

public class RankActivity extends BaseActivity implements IRankView {


    RankPresenter mRankPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.rankLv)
    ListView rankLv;

    List<RankEntity> rankEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mRankPresenter = new RankPresenter(this);
        titleBarTv.setText("我的排行");

        mRankPresenter.requestRank();
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rank);
    }

    private void initDatas() {
        Log.e("RankActivity","加载数据");
        rankEntities = mRankPresenter.getRankEntity();
        rankLv.setAdapter(new Common1Adapter<RankEntity>(this, rankEntities,
                R.layout.item_rank,onItemClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, RankEntity rankEntity, int position) {
                TextView tv1 = mViewHolder.getView(R.id.itemRankTv);
                TextView tv2 = mViewHolder.getView(R.id.itemWorkForTv);
                tv1.setText("第"+rankEntity.getRank()+"名  "+rankEntity.getName());
                tv2.setText("服务村社："+rankEntity.getWorkFor());
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
        toast("请求成功！", true);
        initDatas();
    }

    @Override
    public void requestRankFailed() {
        toast("请求失败！", true);
    }

    @OnClick(R.id.backTv)
    public void onClick() {
    }
    private boolean change = true;
    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toast("" + (position), true);
            ImageView im = (ImageView) view.findViewById(R.id.nextIm);
            if(change){
                im.setImageResource(R.drawable.u608);
                change = false;
            }else {
                im.setImageResource(R.drawable.u609);
                change = true;
            }

        }
    };
}
