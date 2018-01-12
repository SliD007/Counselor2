package test.example.com.counselor.view.service;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;
import test.example.com.counselor.view.service.entity.AdviceEntity;
import test.example.com.counselor.view.service.entity.ClassicCaseEntity;
import test.example.com.counselor.view.service.entity.SummaryEntity;
import test.example.com.counselor.view.service.entity.WorkLogEntity;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class ServicePresenter extends BasePresenter {

    IServiceView mIServiceView;
    IServiceModel mServiceModel;
    List<WorkLogEntity> workLogEntities;
    List<AdviceEntity> adviceEntities;
    List<ClassicCaseEntity> classicCaseEntities;
    List<SummaryEntity> summaryEntities;
    public ServicePresenter(IServiceView serviceView) {
        this.mIServiceView = serviceView;
        this.mServiceModel = new ServiceModel();
        workLogEntities = new ArrayList<WorkLogEntity>();
        adviceEntities = new ArrayList<AdviceEntity>();
        classicCaseEntities = new ArrayList<ClassicCaseEntity>();
        summaryEntities = new ArrayList<SummaryEntity>();
    }


    public void requestServiceData(int current, int size, final int type, int counselorId){

        switch (type){
            case 0:
                HashMap<String,String> params = new HashMap<>();
                //String  contact  手机号码; String  password  用户登录密码
                params.put("current",current+"");
                params.put("size",size+"");
                params.put("contact",111111+"");
                params.put("resultType",1+"");
                params.put("search","");
                OkGo.post(Urls.WorkLogURL)
                        .params(params)
                        .cacheKey(Constants.getAppCacheFolder())
                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .cacheTime(-1)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
//                        Log.e("requestServiceData",s);
                                JSONObject object = JSON.parseObject(s);
                                if (object.getInteger("code")==0){
                                    saveValue(object,type);
                                    mIServiceView.requestServiceSuccess();
                                }else {
                                    mIServiceView.requestServiceFailed();
                                }
                            }
                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                mIServiceView.requestServiceFailed();
                            }
                        });
                break;
        }


    }

    public void saveValue(JSONObject object, int type){
        JSONObject page = object.getJSONObject("page");
        JSONArray listArray = page.getJSONArray("list");
        Log.e("requestTask",""+listArray.toString());
        if (type==0){
            workLogEntities = JSONArray.parseArray(listArray.toString(),WorkLogEntity.class);
            for(int j=0;j<5;j++)
                workLogEntities.add(workLogEntities.get(0));
            mServiceModel.setWorkLogEntities(workLogEntities);
            Log.e("requestTask",""+workLogEntities.toString());
        }
        else {
        }
    }

    public List<WorkLogEntity> getWorkLogEntities(){
        return mServiceModel.getWorkLogEntities();
    }
    public List<AdviceEntity> getAdviceEntities(){
        return mServiceModel.getAdviceEntities();
    }
    public List<ClassicCaseEntity> getClassicCaseEntities(){
        return mServiceModel.getClassicCaseEntities();
    }
    public List<SummaryEntity> getSummaryEntities(){
        return mServiceModel.getSummaryEntities();
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
}
