package test.example.com.counselor.view.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.adapter.CommonAdapter;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.TimeUtil;

public class NewsActivity extends BaseActivity implements INewsView {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.noneTv)
    TextView noneTv;
    ViewGroup.LayoutParams para;
    String noneStr = "加载中";
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private CommonAdapter mAdapter;
    private int refreshTime = 0;
    private int times = 0;
    NewsPresenter mNewsPresenter;
    List<NewsEntity> newsEntities;
    private int current=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        mNewsPresenter = new NewsPresenter(this);
        titleBarTv.setText("消息");
        mNewsPresenter.requestNews(current,20);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        para = noneTv.getLayoutParams();
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news);
    }

    private void initDatas() {
        Log.e("AssessmentActivity", "加载数据");
        newsEntities = mNewsPresenter.getNewsEntity();
        if (newsEntities!=null){
            para.height = 0;
            noneTv.setLayoutParams(para);
            mAdapter = new CommonAdapter(this,newsEntities,R.layout.item_news){
                public void onBindViewHolder(ViewHolder viewHolder,final int position) {
                    super.onBindViewHolder(viewHolder,position);

                    TextView itemTv1 = viewHolder.getView(R.id.itemTv1);
                    TextView itemTv2 = viewHolder.getView(R.id.itemTv2);
                    TextView itemTv3 = viewHolder.getView(R.id.itemTv3);

                    itemTv1.setText(newsEntities.get(position).getTitle());
                    itemTv2.setText(newsEntities.get(position).getContent());
                    itemTv3.setText(TimeUtil.getDateToString(newsEntities.get(position).getCreateTime(),TimeUtil.Data));
                }
            };
            mRecyclerView.setAdapter(mAdapter);
        }else {
            para.height = 100;
            noneTv.setLayoutParams(para);
            noneTv.setText(noneStr);
        }

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        if(current>1)
                            current--;
                        mNewsPresenter.requestNews(current,20);

                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            current++;
                            mNewsPresenter.requestNews(current,20);
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            current++;
                            mNewsPresenter.requestNews(current,20);
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times ++;
            }
        });
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
//        toast("请求成功！", true);
        newsEntities = mNewsPresenter.getNewsEntity();
        noneStr = "没有内容";
        if(newsEntities!=null){
            noneStr = "加载中";
        }
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

}
