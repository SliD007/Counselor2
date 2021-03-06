package test.example.com.counselor.view.service;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
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

import static com.alibaba.fastjson.JSON.parseArray;

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


    public void requestServiceData(final int current, int size, final int type){

        switch (type){
            case 0:
                HashMap<String,String> params = new HashMap<>();
                params.put("current",current+"");
                params.put("size",size+"");
                params.put("contact", MyApplication.getInstance().loginEntity.getContact());
                params.put("resultType",-1+"");
                params.put("search","");
                Log.e("params", params.toString());
                OkGo.post(Urls.WorkLogURL)
                        .params(params)
                        .cacheKey(Constants.getAppCacheFolder())
//                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .cacheTime(-1)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("requestServiceData"+type,s);
                                JSONObject object = JSON.parseObject(s);
                                if (object.getInteger("code")==0){
                                    saveValue(object,type,current);
                                    boolean hasNext = object.getJSONObject("page").getBoolean("hasNext");
                                    mIServiceView.requestServiceSuccess(hasNext,type);
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
                params1.put("type",-1+"");
                params1.put("contact", MyApplication.getInstance().loginEntity.getContact());
                params1.put("current",current+"");
                params1.put("size",size+"");
                Log.e("params1", params1.toString());
                OkGo.post(Urls.ReportURL)
                        .params(params1)
                        .cacheKey(Constants.getAppCacheFolder())
//                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .cacheTime(-1)
                        .execute(new StringCallback() {

                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("requestServiceData"+type,s);
                                JSONObject object = JSON.parseObject(s);
                                if (object.getInteger("code")==0){
                                    saveValue(object,type,current);
                                    boolean hasNext = object.getJSONObject("page").getBoolean("hasNext");
                                    mIServiceView.requestServiceSuccess(hasNext,type);
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
//            case 2:
//                HashMap<String,String> params2 = new HashMap<>();
//                params2.put("type",1+"");
//                params2.put("contact", MyApplication.getInstance().loginEntity.getContact());
//                params2.put("current",current+"");
//                params2.put("size",size+"");
//                Log.e("params2", params2.toString());
//                OkGo.post(Urls.ReportURL)
//                        .params(params2)
//                        .cacheKey(Constants.getAppCacheFolder())
////                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
//                        .cacheTime(-1)
//                        .execute(new StringCallback() {
//
//                            public void onSuccess(String s, Call call, Response response) {
////                                Log.e("requestServiceData"+type,s);
//                                JSONObject object = JSON.parseObject(s);
//                                if (object.getInteger("code")==0){
//                                    saveValue(object,type);
//                                    boolean hasNext = object.getJSONObject("page").getBoolean("hasNext");
//                                    mIServiceView.requestServiceSuccess(hasNext,type);
//                                }else {
//                                    mIServiceView.requestServiceFailed();
//                                }
//                            }
//                            @Override
//                            public void onError(Call call, Response response, Exception e) {
//                                super.onError(call, response, e);
//                                mIServiceView.requestServiceFailed();
//                            }
//                        });
//                break;
            case 3:
                HashMap<String,String> params3 = new HashMap<>();
                params3.put("contact", MyApplication.getInstance().loginEntity.getContact());
                params3.put("current",current+"");
                params3.put("size",size+"");
                Log.e("params3", params3.toString());
                OkGo.post(Urls.SummaryURL)
                        .params(params3)
                        .cacheKey(Constants.getAppCacheFolder())
//                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .cacheTime(-1)
                        .execute(new StringCallback() {

                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("requestServiceData3",s);
                                JSONObject object = JSON.parseObject(s);
                                if (object.getInteger("code")==0){
                                    saveValue(object,type,current);
                                    boolean hasNext = object.getJSONObject("page").getBoolean("hasNext");
                                    mIServiceView.requestServiceSuccess(hasNext,type);
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

    public void saveValue(JSONObject object, int type, int current){
        JSONObject page = object.getJSONObject("page");
        JSONArray listArray = page.getJSONArray("list");
//        Log.e("saveValue"+type,""+listArray.toString());
        switch (type){
            case 0:
                if(workLogEntities==null||current==1)
                    workLogEntities = parseArray(listArray.toString(),WorkLogEntity.class);
                else
                    workLogEntities.addAll(parseArray(listArray.toString(),WorkLogEntity.class));
                mServiceModel.setWorkLogEntities(workLogEntities);
//                Log.e("Entity"+type,""+workLogEntities.toString());
                break;
            case 1:
                if(adviceEntities==null||current==1)
                    adviceEntities = parseArray(listArray.toString(),AdviceEntity.class);
                else
                    adviceEntities.addAll(parseArray(listArray.toString(),AdviceEntity.class));
                mServiceModel.setAdviceEntities(adviceEntities);
//                Log.e("Entity"+type,""+adviceEntities.toString());
                break;
//            case 2:
//                classicCaseEntities = JSONArray.parseArray(listArray.toString(),ClassicCaseEntity.class);
//                mServiceModel.setClassicCaseEntities(classicCaseEntities);
////                Log.e("Entity"+type,""+adviceEntities.toString());
//                break;
            case 3:
                if(summaryEntities==null||current==1)
                    summaryEntities = parseArray(listArray.toString(),SummaryEntity.class);
                else
                    summaryEntities.addAll(JSONArray.parseArray(listArray.toString(),SummaryEntity.class));
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
