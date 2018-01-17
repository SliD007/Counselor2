package test.example.com.counselor.view.service.addworklog;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddWorkLogPersenter {

    private IAddWorkLogView mIAddWorkLogView;
    public AddWorkLogPersenter(Context context, IAddWorkLogView iAddWorkLogView) {
        this.mIAddWorkLogView = iAddWorkLogView;
    }

    public void addWorkLog(String[] str){

        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        params.put("serviceVillage", str[1]);
        params.put("inObject", str[5]);
        params.put("serviceObject", str[2]);
        params.put("serviceIdentity", str[4]);
        params.put("objectContact", str[3]);
        params.put("fromType", 0+"");
        params.put("accessory", "");
        params.put("serviceType", 0+"");
        params.put("matterType", str[10]);
        params.put("subType", str[11]);
        params.put("objectType", 0+"");
        params.put("serviceContent", str[13]);
        params.put("resultContent", "");
        params.put("resultType", 0+"");
        params.put("logType", 0+"");
        params.put("matterPlace", "");
        params.put("matterNum", 0+"");
        params.put("matterTime", "");
        params.put("objectAddress", "");
        params.put("matterMoney", 0+"");
        params.put("isConflict", false+"");

        Log.e("addWorkLog",params.toString());
        OkGo.post(Urls.WorkLogAddURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("addWorkLog","response:"+response.toString());
                        Log.e("addWorkLog","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){

                            mIAddWorkLogView.addSuccess();
                        }else {
                            mIAddWorkLogView.addFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIAddWorkLogView.addFailed();
                    }
                });
    }

    public void addworkLog(final String[] str){
        StringRequest request4LoginRequest = new StringRequest(
                Request.Method.POST, Urls.WorkLogAddURL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                Log.e("loginInfo", arg0);// 打印登录返回的数据
                try {
                    JSONObject object = JSON.parseObject(arg0);
                    if (object.getInteger("code")==0){
                        mIAddWorkLogView.addSuccess();
                    }else {
                        mIAddWorkLogView.addFailed();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e("loginInfo", arg0.toString());// 打印错误信息
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
                params.put("serviceVillage", str[1]);
                params.put("inObject", str[5]);
                params.put("serviceObject", str[2]);
                params.put("serviceIdentity", str[4]);
                params.put("objectContact", str[3]);
                params.put("fromType", 0+"");
                params.put("accessory", "");
                params.put("serviceType", 0+"");
                params.put("matterType", str[10]);
                params.put("subType", str[11]);
                params.put("objectType", 0+"");
                params.put("serviceContent", str[13]);
                params.put("resultContent", "");
                params.put("resultType", 0+"");
                params.put("logType", 0+"");
                params.put("matterPlace", "");
                params.put("matterNum", 0+"");
                params.put("matterTime", "");
                params.put("objectAddress", "");
                params.put("matterMoney", 0+"");
                params.put("isConflict", false+"");

                Log.e("getParams",params.toString());
                return params;
            }

        };
        MyApplication.getInstance().addToRequestQueue(request4LoginRequest, "");
    }
}
