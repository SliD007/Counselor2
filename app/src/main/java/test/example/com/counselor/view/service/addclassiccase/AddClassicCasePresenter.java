package test.example.com.counselor.view.service.addclassiccase;

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

public class AddClassicCasePresenter {

    Context mContext;
    private IAddClassicCaseView mIAddClassicCaseView;
    public AddClassicCasePresenter(Context context, IAddClassicCaseView iAddClassicCaseView) {
        this.mIAddClassicCaseView = iAddClassicCaseView;
        this.mContext = context;
    }

    public void addClassicCase(String title,String content,String vStr){
        HashMap<String,String> params = new HashMap<>();
        params.put("title",title);
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        params.put("village", vStr);
        params.put("content",content);
        params.put("toType",1+"");
        params.put("reportType",1+"");
        params.put("contact", MyApplication.getInstance().loginEntity.getContact()+"");
        params.put("office",MyApplication.getInstance().loginEntity.getOffice());
        Log.e("addClassicCase",params.toString());
        OkGo.post(Urls.ReportAddURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("addAdvice","response:"+response.toString());
                        Log.e("addAdvice","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){

                            mIAddClassicCaseView.addSuccess();
                        }else {
                            mIAddClassicCaseView.addFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIAddClassicCaseView.addFailed();
                    }
                });
    }

    public void addclassicCase(final String title, final String content) {
            StringRequest request4LoginRequest = new StringRequest(
                    Request.Method.POST, Urls.ReportAddURL, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String arg0) {
                    Log.e("loginInfo", arg0);// 打印登录返回的数据
                    try {
                        JSONObject object = JSON.parseObject(arg0);
                        if (object.getInteger("code")==0){
                            mIAddClassicCaseView.addSuccess();
                        }else {
                            mIAddClassicCaseView.addFailed();
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
                    Map<String, String> map4Login = new HashMap<String, String>();
                    map4Login.put("title",title);
                    map4Login.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
                    map4Login.put("village", MyApplication.getInstance().loginEntity.getVillageA()+" "+MyApplication.getInstance().loginEntity.getVillageB());                    map4Login.put("content",content);
                    map4Login.put("toType",1+"");
                    map4Login.put("reportType",1+"");
                    map4Login.put("contact", MyApplication.getInstance().loginEntity.getContact()+"");

                    Log.e("getParams",map4Login.toString());
                    return map4Login;
                }

            };
            MyApplication.getInstance().addToRequestQueue(request4LoginRequest, "");
        }
}
