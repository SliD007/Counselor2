package test.example.com.counselor.view.schedule;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;

/**
 * Created by Sli.D on 2017/12/26.
 */

public class SchedulePersenter {

    String URL = "http://www.baidu.com";

    Context mContext;
    IScheduleView mIScheduleView;
    IScheduleModel mIScheduleModel;
    public SchedulePersenter(Context context, IScheduleView iScheduleView) {
        this.mContext = context;
        this.mIScheduleView = iScheduleView;
        this.mIScheduleModel = new ScheduleModel();
    }

    public void requestScheduleList(int current, int size, int counselorId){

        HashMap<String,String> params = new HashMap<>();
        //String  contact  手机号码; String  password  用户登录密码
        params.put("current",current+"");
        params.put("size",size+"");
        params.put("counselorId",counselorId+"");
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
                        List<ScheduleEntity> entities = new ArrayList<ScheduleEntity>();
                        entities.add(new ScheduleEntity(0,"2018-01-12","星沙街道新塘村"));
                        entities.add(new ScheduleEntity(1,"2018-02-12","星沙街道新塘村"));
                        entities.add(new ScheduleEntity(2,"2018-03-12","星沙街道新塘村"));
                        entities.add(new ScheduleEntity(3,"2018-04-12","星沙街道新塘村"));
                        entities.add(new ScheduleEntity(4,"2018-05-12","星沙街道新塘村"));
                        entities.add(new ScheduleEntity(5,"2018-06-12","星沙街道新塘村"));
                        entities.add(new ScheduleEntity(6,"2018-07-12","星沙街道新塘村"));
                        entities.add(new ScheduleEntity(7,"2018-08-12","星沙街道新塘村"));
                        mIScheduleModel.setScheduleEntities(entities);
                        mIScheduleView.requestScheduleSuccess();
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
}