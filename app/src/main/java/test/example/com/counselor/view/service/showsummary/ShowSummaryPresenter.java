package test.example.com.counselor.view.service.showsummary;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class ShowSummaryPresenter {

    private SummaryEntity summaryEntity;
    private IShowSummaryView mIShowSummaryView;
    private IShowSummaryModel mIShowSummaryModel;
    public ShowSummaryPresenter(Context context, IShowSummaryView iShowSummaryView) {
        this.mIShowSummaryView = iShowSummaryView;
        mIShowSummaryModel = new ShowSummaryModel();
    }


    public void requestSummaryDetial(int id){
        Log.e("requestSummaryDetial","id:"+id);
        HashMap<String,String> params = new HashMap<>();
        params.put("id",id+"");
        OkGo.post(Urls.SummaryConfigurationURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        Log.e("requestSummary","request:"+request.toString());
                        super.onBefore(request);
                    }

                    @Override
                    public String convertSuccess(Response response) throws Exception {
                        Log.e("requestSummary","convertSuccess:"+response.toString());
                        return super.convertSuccess(response);
                    }

                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("requestSummary","response:"+response.toString());
                        Log.e("requestSummary","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object);
                            mIShowSummaryView.requestSummaryDetialSuccess();

                        }else {
                            mIShowSummaryView.requestSummaryDetialFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIShowSummaryView.requestSummaryDetialSuccess();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public void saveValue(JSONObject object){

        JSONObject value = object.getJSONObject("value");
        Log.e("requestSummary",""+value.toString());
        summaryEntity = JSONObject.parseObject(value.toString(), SummaryEntity.class);
        mIShowSummaryModel.setSummaryDetialEntity(summaryEntity);
        Log.e("EntityDetail",""+ summaryEntity.toString());

    }

    public SummaryEntity getSummaryDetialEntity(){
        return mIShowSummaryModel.getSummaryDetialEntity();
    }
}
