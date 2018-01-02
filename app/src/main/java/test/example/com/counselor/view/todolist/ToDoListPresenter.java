package test.example.com.counselor.view.todolist;

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
 * Created by Sli.D on 2018/1/2.
 */

public class ToDoListPresenter {

    String URL = "http://www.baidu.com";

    Context context;
    IToDoListView mIToDoListView;
    public ToDoListPresenter(Context context,IToDoListView iToDoListView){
        this.context = context;
        this.mIToDoListView = iToDoListView;
    }

    public void requestToDoList(int current,int size, int type, int counselorId){
        HashMap<String,String> params = new HashMap<>();
        //String  contact  手机号码; String  password  用户登录密码
        params.put("current",current+"");
        params.put("size",size+"");
        params.put("current",type+"");
        params.put("size",counselorId+"");
        OkGo.post(URL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("s",s);
//                        JSONObject object = JSON.parseObject(s);
//                        if (object.getInteger("code")==0){
//                            mIToDoListView.requestToDoListSuccess();
//                        }else {
//                            mIToDoListView.requestToDoListFaild();
//                        }
                        mIToDoListView.requestToDoListSuccess();
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIToDoListView.requestToDoListFaild();
                    }
                });
    }
}
