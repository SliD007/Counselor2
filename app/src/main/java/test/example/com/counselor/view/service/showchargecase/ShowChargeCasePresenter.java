package test.example.com.counselor.view.service.showchargecase;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class ShowChargeCasePresenter {

    private ChargeCaseDetialEntity chargeCaseDetialEntity;
    private IShowChargeCaseView mIShowChargeCaseView;
    private IShowChargeCaseModel mIShowChargeCaseModel;
    public ShowChargeCasePresenter(Context context, IShowChargeCaseView iShowChargeCaseView) {
        this.mIShowChargeCaseView = iShowChargeCaseView;
        mIShowChargeCaseModel = new ShowChargeCaseModel();
    }


    public void requestWorkLogDetial(int id){

        HashMap<String,String> params = new HashMap<>();
        params.put("id",id+"");
        OkGo.post(Urls.WorkLogConfigurationURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("requestWorklogDetial","response:"+response.toString());
                        Log.e("requestWorklogDetial","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object);
                            mIShowChargeCaseView.requestWorkLogDetialSuccess();

                        }else {
                            mIShowChargeCaseView.requestWorkLogDetialFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIShowChargeCaseView.requestWorkLogDetialFailed();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public void saveValue(JSONObject object){

        JSONObject value = object.getJSONObject("value");
        Log.e("requestWorkLog",""+value.toString());
        chargeCaseDetialEntity = JSONObject.parseObject(value.toString(),ChargeCaseDetialEntity.class);
        mIShowChargeCaseModel.setChargeCaseDetialEntity(chargeCaseDetialEntity);
        Log.e("EntityDetail",""+ chargeCaseDetialEntity.toString());

    }

    public ChargeCaseDetialEntity getChargeCaseDetialEntity(){
        return mIShowChargeCaseModel.getChargeCaseDetialEntity();
    }
}
