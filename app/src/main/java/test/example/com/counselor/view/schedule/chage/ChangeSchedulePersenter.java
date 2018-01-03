package test.example.com.counselor.view.schedule.chage;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;

/**
 * Created by Sli.D on 2017/12/26.
 */

public class ChangeSchedulePersenter {

    String URL = "http://www.baidu.com";

    private Context mContext;
    private IChangeSchedule mIChangeSchedule;
    public ChangeSchedulePersenter(Context context,IChangeSchedule iChangeSchedule) {
        this.mContext = context;
        this.mIChangeSchedule = iChangeSchedule;
    }

    public void changeSchedule(int id,String time,String workWay){
        Log.e("changeSchedule:","id:"+id+"time:"+time+"workWay:"+workWay);
        HashMap<String,String> params = new HashMap<>();
        //String  contact  手机号码; String  password  用户登录密码
        params.put("id",id+"");
        params.put("changeTime",time+"");
        params.put("jobType",workWay+"");
        OkGo.post(URL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("s",s);
//                        JSONObject object = JSON.parseObject(s);
//                        if (object.getInteger("code")==0){
//                            mIToDoListView.requestToDoListSuccess();
//                        }else {
//                            mIToDoListView.requestToDoListFaild();
//                        }
                        mIChangeSchedule.changeSuccess();
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIChangeSchedule.changeFailed();
                    }
                });
    }
}
