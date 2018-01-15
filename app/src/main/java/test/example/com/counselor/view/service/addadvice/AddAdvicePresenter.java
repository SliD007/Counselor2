package test.example.com.counselor.view.service.addadvice;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddAdvicePresenter {

    private IAddAdviceView mIAddAdviceView;
    public AddAdvicePresenter(Context context, IAddAdviceView iAddAdviceView) {
        this.mIAddAdviceView = iAddAdviceView;
    }

    public void addAdvice(String title,String content, int rbId){
        HashMap<String,String> params = new HashMap<>();
        params.put("title",title);
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        params.put("village", MyApplication.getInstance().loginEntity.getCommunityA()+MyApplication.getInstance().loginEntity.getCommunityB()+"");
        params.put("content",content);
        params.put("toType",rbId+"");
        params.put("reportType",0+"");
        params.put("contact", MyApplication.getInstance().loginEntity.getContact()+"");

        Log.e("Advice",params.toString());
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

                            mIAddAdviceView.addSuccess();
                        }else {
                            mIAddAdviceView.addFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIAddAdviceView.addFailed();
                    }
                });
    }

}
