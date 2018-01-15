package test.example.com.counselor.view.service.addsummary;

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

public class AddSummaryPresenter {

    private IAddSummaryView mIAddSummaryView;
    public AddSummaryPresenter(Context context, IAddSummaryView iAddSummaryView) {
        this.mIAddSummaryView = iAddSummaryView;
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

}
