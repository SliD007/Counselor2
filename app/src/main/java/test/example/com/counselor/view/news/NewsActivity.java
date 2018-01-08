package test.example.com.counselor.view.news;

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

public class NewsActivity extends BaseActivity implements INewsView {


    NewsPresenter mNewsPresenter;
    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.newsLv)
    ListView newsLv;

    List<NewsEntity> newsEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mNewsPresenter = new NewsPresenter(this);
        titleBarTv.setText("消息");
        mNewsPresenter.requestRank();
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news);
    }

    private void initDatas() {
        Log.e("ContractActivity", "加载数据");
        newsEntities = mNewsPresenter.getNewsEntity();
        newsLv.setAdapter(new Common1Adapter<NewsEntity>(this, newsEntities,
                R.layout.item_news) {
            @Override
            protected void convertView(ViewHolder1 mViewHolder, View item, NewsEntity newsEntity, int position) {
                TextView itemTv1 = mViewHolder.getView(R.id.itemTv1);
                TextView itemTv2 = mViewHolder.getView(R.id.itemTv2);
                TextView itemTv3 = mViewHolder.getView(R.id.itemTv3);

                itemTv1.setText(newsEntity.getFrom());
                itemTv2.setText(newsEntity.getContext());
                itemTv3.setText(newsEntity.getTime());

            }
        });
        newsLv.setOnItemClickListener(onItemClickListener);
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
    public void requestNewsSuccess() {
        toast("请求成功！", true);
        initDatas();
    }

    @Override
    public void requestNewsFailed() {
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
