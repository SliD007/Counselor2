package test.example.com.counselor.view.service.addsummary;

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

public class AddSummaryPresenter {

    Context mContext;
    private IAddSummaryView mIAddSummaryView;
    public AddSummaryPresenter(Context context, IAddSummaryView iAddSummaryView) {
        this.mIAddSummaryView = iAddSummaryView;
        this.mContext = context;
    }

    public void addSummary(String title,String content){
        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        params.put("village", MyApplication.getInstance().loginEntity.getCommunityA()+MyApplication.getInstance().loginEntity.getCommunityB()+"");
        params.put("office",MyApplication.getInstance().loginEntity.getOrganization());
        params.put("contact", MyApplication.getInstance().loginEntity.getContact()+"");
        params.put("title",title);
        params.put("content",content);
        Log.e("addSummary",params.toString());
        OkGo.post(Urls.SummaryAddURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public String convertSuccess(Response response) throws Exception {
                        Log.e("addAdvice","convertSuccess:"+response.toString());
                        return super.convertSuccess(response);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("addAdvice","response:"+response.toString());
                        Log.e("addAdvice","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){

                            mIAddSummaryView.addSuccess();
                        }else {
                            mIAddSummaryView.addFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIAddSummaryView.addFailed();
                    }
                });
    }

    public void addsummary(final String title, final String content) {


        String SummaryAddURL = "http://law.d9lab.net/pad/monthly/addMonthly";

        StringRequest request4LoginRequest = new StringRequest(
                Request.Method.POST, SummaryAddURL, new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                // TODO Auto-generated method stub
                Log.e("loginInfo", arg0);// 打印登录返回的数据
                try {
                    JSONObject object = JSON.parseObject(arg0);
                    if (object.getInteger("code")==0){

                        mIAddSummaryView.addSuccess();
                    }else {
                        mIAddSummaryView.addFailed();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }

            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                Log.e("loginInfo", arg0.toString());// 打印错误信息
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub

                Map<String, String> map4Login = new HashMap<String, String>();

				map4Login.put("counselorId","2");
				map4Login.put("village", "lwt");
				map4Login.put("office","");
				map4Login.put("contact", "111111");
				map4Login.put("title","月");
				map4Login.put("content","月月月");
                Log.e("getParams",map4Login.toString());
                return map4Login;
            }

        };
        MyApplication.getInstance().addToRequestQueue(request4LoginRequest, "");
    }

}
