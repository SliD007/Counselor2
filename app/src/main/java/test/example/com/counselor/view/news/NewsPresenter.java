package test.example.com.counselor.view.news;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class NewsPresenter extends BasePresenter{
    private INewsView mINewsView;
    private INewsModel mNewsModel;
    List<NewsEntity> newsEntities;
    public NewsPresenter(INewsView view){
        mINewsView = view;
        mNewsModel = new NewsModel();
    }

    public void requestNews(int current, int size){
        HashMap<String,String> params = new HashMap<>();
        params.put("current",""+ current);
        params.put("size",""+ size);
        params.put("userId",""+ MyApplication.getInstance().loginEntity.getId());
        params.put("pushType","");
        Log.e("requestNews",""+params.toString());
        OkGo.post(Urls.NewsURL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("requestNews","onSuccess:"+s);
                JSONObject object = JSON.parseObject(s);
                if (object.getInteger("code")==0){
                    saveValue(object);
                    boolean hasNext = object.getJSONObject("page").getBoolean("hasNext");
                    mINewsView.requestNewsSuccess(hasNext);
                }else {
                    mINewsView.requestNewsFailed();
                }

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
    public void saveValue(JSONObject object){
        JSONObject page = object.getJSONObject("page");
        JSONArray listArray = page.getJSONArray("list");
//        Log.e("requestTask",""+listArray.toString());
        newsEntities = JSONArray.parseArray(listArray.toString(),NewsEntity.class);
        mNewsModel.seNewsEntities(newsEntities);
//            Log.e("requestTask",""+toDoTaskEntities.toString());
    }

    public List<NewsEntity> getNewsEntity(){
        return mNewsModel.getNewsEntities();
    }
}
