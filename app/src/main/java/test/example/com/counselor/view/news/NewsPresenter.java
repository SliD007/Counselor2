package test.example.com.counselor.view.news;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.base.MyApplication;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class NewsPresenter extends BasePresenter{
    private INewsView mINewsView;
    private INewsModel mNewsModel;
    private String URL = "http:www.baidu.com";
    public NewsPresenter(INewsView view){
        mINewsView = view;
        mNewsModel = new NewsModel();
    }

    public void requestRank(){
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",""+ MyApplication.getInstance().loginEntity.getContact());
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("s",s);
                List<NewsEntity> entities = new ArrayList<NewsEntity>();
                entities.add(new NewsEntity("蓝田新村","2018-01-08","hahahahahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
                entities.add(new NewsEntity("星沙街道司法所","2018-01-06","hahahahahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
                entities.add(new NewsEntity("长沙县司法局","2018-01-04",
                        "hahahahahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                "hahahahahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

                mNewsModel.seNewsEntities(entities);
                mINewsView.requestNewsSuccess();

            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
//                Log.e("response",response.toString());
                mINewsView.requestNewsFailed();
            }
        });
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }

    public List<NewsEntity> getNewsEntity(){
        return mNewsModel.getNewsEntities();
    }
}
