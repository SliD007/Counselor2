package test.example.com.counselor.view.rank;

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
import test.example.com.counselor.base.MyLvClickListener;

public class RankActivity extends BaseActivity implements IRankView {


    RankPresenter mRankPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.rankLv)
    ListView rankLv;

    List<RankEntity> rankEntities;
    @BindView(R.id.backTv)
    TextView backTv;

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

    private boolean show_vs = false;

    private void initDatas() {
        Log.e("ContractActivity", "加载数据");
        rankEntities = mRankPresenter.getRankEntity();
        rankLv.setAdapter(new Common1Adapter<RankEntity>(this, rankEntities,
                R.layout.item_rank, onItemClickListener) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, RankEntity rankEntity, int position) {
                TextView itemRankTv = mViewHolder.getView(R.id.itemRankTv1);
                TextView itemWorkForTv = mViewHolder.getView(R.id.itemWorkForTv);
                TextView itemNumRankTv1 = mViewHolder.getView(R.id.itemNumRankTv1);
                TextView itemNumRankTv2 = mViewHolder.getView(R.id.itemNumRankTv2);
                TextView itemNumRankTv3 = mViewHolder.getView(R.id.itemNumRankTv3);
                TextView itemNumRankTv4 = mViewHolder.getView(R.id.itemNumRankTv4);
                TextView itemNumRankTv5 = mViewHolder.getView(R.id.itemNumRankTv5);
                TextView itemNumRankTv6 = mViewHolder.getView(R.id.itemNumRankTv6);
                itemRankTv.setText("第" + rankEntity.getRank() + "名  " + rankEntity.getName());
                itemWorkForTv.setText("服务村社：" + rankEntity.getWorkFor());
                itemNumRankTv1.setText("现场咨询：" + rankEntity.getStr11() + "次" );
                itemNumRankTv2.setText("电话咨询：" + rankEntity.getStr12()+"次");
                itemNumRankTv3.setText("法律援助：" + rankEntity.getStr2()+"次");
                itemNumRankTv4.setText("参与调解：" + rankEntity.getStr3()+"次");
                itemNumRankTv5.setText("法制宣传：" + rankEntity.getStr4()+"次");
                itemNumRankTv6.setText("法制讲座：" + rankEntity.getStr5()+"次");

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

    //Item响应回调
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            toast("" + (position), true);
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
