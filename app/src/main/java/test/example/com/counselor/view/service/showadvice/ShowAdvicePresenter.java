package test.example.com.counselor.view.service.showadvice;

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
 * Created by Sli.D on 2017/12/25.
 */

public class ShowAdvicePresenter {

    private AdviceEntity adviceEntity;
    private IShowAdviceView mIShowAdviceView;
    private IShowAdviceModel mIShowAdviceModel;
    public ShowAdvicePresenter(Context context, IShowAdviceView iShowAdviceView) {
        this.mIShowAdviceView = iShowAdviceView;
        mIShowAdviceModel = new ShowAdviceModel();
    }


    public void requestAdviceDetial(int id){
        Log.e("requestAdviceDetial","id:"+id);
        HashMap<String,String> params = new HashMap<>();
        params.put("id",1+"");
        OkGo.post(Urls.ReportConfigurationURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("requestTaskDetial","response:"+response.toString());
                        Log.e("requestTaskDetial","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object);
                            mIShowAdviceView.requestWorkLogDetialSuccess();

                        }else {
                            mIShowAdviceView.requestWorkLogDetialFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIShowAdviceView.requestWorkLogDetialFailed();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public void saveValue(JSONObject object){

        JSONObject value = object.getJSONObject("value");
        Log.e("requestAdvice",""+value.toString());
        adviceEntity = JSONObject.parseObject(value.toString(), AdviceEntity.class);
        mIShowAdviceModel.setAdviceDetialEntity(adviceEntity);
        Log.e("EntityDetail",""+adviceEntity.toString());

    }

    public AdviceEntity getAdviceDetialEntity(){
        return mIShowAdviceModel.getAdviceDetialEntity();
    }
}
