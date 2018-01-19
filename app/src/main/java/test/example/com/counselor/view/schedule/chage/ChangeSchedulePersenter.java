package test.example.com.counselor.view.schedule.chage;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/26.
 */

public class ChangeSchedulePersenter {

    private Context mContext;
    private IChangeSchedule mIChangeSchedule;
    public ChangeSchedulePersenter(Context context,IChangeSchedule iChangeSchedule) {
        this.mContext = context;
        this.mIChangeSchedule = iChangeSchedule;
    }

    public void changeSchedule(int id,String time,int jobType){
//        Log.e("changeSchedule",""+id+time+jobType);
        HashMap<String,String> params = new HashMap<>();
        params.put("id",id+"");
        params.put("changeTime",time+"");
        params.put("jobType",jobType+"");
        Log.e("params",params.toString());
        OkGo.post(Urls.ChangeScheduleURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("changeSchedule","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            mIChangeSchedule.changeSuccess();
                        }else {
                            mIChangeSchedule.changeFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIChangeSchedule.changeFailed();
                    }
                });
    }
}
