package test.example.com.counselor.view.schedule;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/26.
 */

public class SchedulePersenter {


    Context mContext;
    IScheduleView mIScheduleView;
    IScheduleModel mIScheduleModel;
    List<ScheduleEntity> scheduleEntities;
    public SchedulePersenter(Context context, IScheduleView iScheduleView) {
        this.mContext = context;
        this.mIScheduleView = iScheduleView;
        this.mIScheduleModel = new ScheduleModel();
        scheduleEntities = new ArrayList<>();
    }

    public void requestScheduleList(int current, int size){

        HashMap<String,String> params = new HashMap<>();
        params.put("current",current+"");
        params.put("size",size+"");
        params.put("counselorId", 1+"");
        OkGo.post(Urls.ScheduleURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("requestScheduleList","response"+response.toString());
                        Log.e("requestScheduleList","onSuccess"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object);
                            mIScheduleView.requestScheduleSuccess();
                        }else {
                            mIScheduleView.requestScheduleSuccess();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIScheduleView.requestScheduleFaild();
                    }
                });
    }


    public List<ScheduleEntity> getScheduleEntityList(){
        return mIScheduleModel.getScheduleEntities();
    }

    public void saveValue(JSONObject object){
        JSONObject page = object.getJSONObject("page");
        JSONArray listArray = page.getJSONArray("list");
        Log.e("requestScheduleList",""+listArray.toString());

        scheduleEntities = JSONArray.parseArray(listArray.toString(),ScheduleEntity.class);

        mIScheduleModel.setScheduleEntities(scheduleEntities);
        Log.e("requestScheduleList",""+scheduleEntities.toString());

    }
}
