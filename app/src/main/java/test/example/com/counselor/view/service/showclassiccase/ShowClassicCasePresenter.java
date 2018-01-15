package test.example.com.counselor.view.service.showclassiccase;

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

public class ShowClassicCasePresenter {

    private ClassicCaseEntity classicCaseEntity;
    private IShowClassicCaseView mIShowClassicCaseView;
    private IShowClassicCaseModel mIShowClassicCaseModel;

    public ShowClassicCasePresenter(Context context, IShowClassicCaseView iShowClassicCaseView) {
        this.mIShowClassicCaseView = iShowClassicCaseView;
        mIShowClassicCaseModel = new ShowClassicCaseModel();
    }

    public void requestClassicCaseDetial(int id){
        Log.e("requestClassicCase","id:"+id);
        HashMap<String,String> params = new HashMap<>();
        params.put("id",id+"");
        OkGo.post(Urls.ReportConfigurationURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("requestClassicCase","response:"+response.toString());
                        Log.e("requestClassicCase","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object);
                            mIShowClassicCaseView.requestClassicCaseDetialSuccess();

                        }else {
                            mIShowClassicCaseView.requestClassicCaseDetialFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIShowClassicCaseView.requestClassicCaseDetialSuccess();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public void saveValue(JSONObject object){

        JSONObject value = object.getJSONObject("value");
//        Log.e("requestAdvice",""+value.toString());
        classicCaseEntity = JSONObject.parseObject(value.toString(), ClassicCaseEntity.class);
        mIShowClassicCaseModel.setClassicCaseDetialEntity(classicCaseEntity);
//        Log.e("EntityDetail",""+classicCaseEntity.toString());

    }

    public ClassicCaseEntity getClassicCaseDetialEntity(){
        return mIShowClassicCaseModel.getClassicCaseDetialEntity();
    }
}
