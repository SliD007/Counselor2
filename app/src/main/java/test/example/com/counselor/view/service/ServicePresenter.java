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
import test.example.com.counselor.base.MyApplication;
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
            case 1:
                HashMap<String,String> params1 = new HashMap<>();
                params1.put("type",1+"");
                params1.put("contact", MyApplication.getInstance().loginEntity.getContact());
                params1.put("current",current+"");
                params1.put("size",size+"");
                OkGo.post(Urls.AdviceURL)
                        .params(params1)
                        .cacheKey(Constants.getAppCacheFolder())
                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .cacheTime(-1)
                        .execute(new StringCallback() {

                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("requestServiceData1",response.toString());
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
            case 2:
                HashMap<String,String> params2 = new HashMap<>();
                params2.put("type",1+"");
                params2.put("contact", MyApplication.getInstance().loginEntity.getContact());
                params2.put("current",current+"");
                params2.put("size",size+"");
                OkGo.post(Urls.AdviceURL)
                        .params(params2)
                        .cacheKey(Constants.getAppCacheFolder())
                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .cacheTime(-1)
                        .execute(new StringCallback() {

                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("requestServiceData2",response.toString());
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
            case 3:
                HashMap<String,String> params3 = new HashMap<>();
                params3.put("contact", MyApplication.getInstance().loginEntity.getContact());
                params3.put("current",current+"");
                params3.put("size",size+"");
                Log.e("requestServiceData3",params3.toString());
                OkGo.post(Urls.SummaryURL)
                        .params(params3)
                        .cacheKey(Constants.getAppCacheFolder())
                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .cacheTime(-1)
                        .execute(new StringCallback() {

                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("requestServiceData3",s);
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
//                                Log.e("requestServiceData3",response.toString()+e.toString());
                                mIServiceView.requestServiceFailed();
                            }
                        });
                break;
        }


    }

    public void saveValue(JSONObject object, int type){
        JSONObject page = object.getJSONObject("page");
        JSONArray listArray = page.getJSONArray("list");
//        Log.e("requestData"+type,""+listArray.toString());
        switch (type){
            case 0:
                workLogEntities = JSONArray.parseArray(listArray.toString(),WorkLogEntity.class);
                for(int j=0;j<5;j++)
                    workLogEntities.add(workLogEntities.get(0));
                mServiceModel.setWorkLogEntities(workLogEntities);
//                Log.e("Entity"+type,""+workLogEntities.toString());
                break;
            case 1:
                adviceEntities = JSONArray.parseArray(listArray.toString(),AdviceEntity.class);
                mServiceModel.setAdviceEntities(adviceEntities);
                Log.e("Entity"+type,""+adviceEntities.toString());
                break;
            case 2:
                classicCaseEntities = JSONArray.parseArray(listArray.toString(),ClassicCaseEntity.class);
                mServiceModel.setClassicCaseEntities(classicCaseEntities);
//                Log.e("Entity"+type,""+adviceEntities.toString());
                break;
            case 3:
                summaryEntities = JSONArray.parseArray(listArray.toString(),SummaryEntity.class);
                mServiceModel.setSummaryEntities(summaryEntities);
//                Log.e("Entity"+type,""+adviceEntities.toString());
                break;
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
