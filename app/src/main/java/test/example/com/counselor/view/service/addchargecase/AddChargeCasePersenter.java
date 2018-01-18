package test.example.com.counselor.view.service.addchargecase;

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

public class AddChargeCasePersenter {

    private IAddChargeCaseView mIAddChargeCaseView;
    public AddChargeCasePersenter(Context context, IAddChargeCaseView iAddChargeCaseView) {
        this.mIAddChargeCaseView = iAddChargeCaseView;
    }


    public void addChargeCase(String[] str, int[] inter){

        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        params.put("logType", 2+"");
        params.put("serviceVillage", str[1]);
        if(inter[17]==0){
            params.put("isConflict", false+"");
        }else {
            params.put("isConflict", true+"");
        }
        params.put("serviceObject", str[2]);
        params.put("objectContact", str[3]);
        params.put("objectAddress", str[4]);
        params.put("matterMoney", inter[5]+"");
        //开始时间
        //结束时间
        params.put("fromType", inter[8]+"");
        params.put("serviceType", inter[9]+"");
        params.put("matterType", str[10]);
        params.put("subType", str[11]);
        params.put("objectType", inter[12]+"");
        params.put("serviceContent", str[13]);
        params.put("resultContent", str[16]);
        //图片
        params.put("resultType", inter[15]+"");

        //多余的接口字段
        params.put("accessory", "");
        params.put("matterPlace", "");
        params.put("matterNum", 0+"");
        params.put("matterTime", "");
        params.put("inObject", "");
        params.put("serviceIdentity", "");


        Log.e("getParams",params.toString());
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

                            mIAddChargeCaseView.addSuccess();
                        }else {
                            mIAddChargeCaseView.addFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIAddChargeCaseView.addFailed();
                    }
                });
    }

    public void addchargeCase(final String[] str, final int[] inter){


        StringRequest request4LoginRequest = new StringRequest(
                Request.Method.POST, Urls.WorkLogAddURL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                Log.e("loginInfo", arg0);// 打印登录返回的数据
                try {
                    JSONObject object = JSON.parseObject(arg0);
                    if (object.getInteger("code")==0){
                        mIAddChargeCaseView.addSuccess();
                    }else {
                        mIAddChargeCaseView.addFailed();
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
                params.put("logType", 2+"");
                params.put("serviceVillage", str[1]);
                if(inter[17]==0){
                    params.put("isConflict", false+"");
                }else {
                    params.put("isConflict", true+"");
                }
                params.put("serviceObject", str[2]);
                params.put("objectContact", str[3]);
                params.put("objectAddress", str[4]);
                params.put("matterMoney", inter[5]+"");
                //开始时间
                //结束时间
                params.put("fromType", inter[8]+"");
                params.put("serviceType", inter[9]+"");
                params.put("matterType", str[10]);
                params.put("subType", str[11]);
                params.put("objectType", inter[12]+"");
                params.put("serviceContent", str[13]);
                params.put("resultContent", str[16]);
                //图片
                params.put("resultType", inter[15]+"");

                //多余的接口字段
                params.put("accessory", "");
                params.put("matterPlace", "");
                params.put("matterNum", 0+"");
                params.put("matterTime", "");
                params.put("inObject", "");
                params.put("serviceIdentity", "");

                Log.e("getParams",params.toString());
                return params;
            }

        };
        MyApplication.getInstance().addToRequestQueue(request4LoginRequest, "");
    }
}
