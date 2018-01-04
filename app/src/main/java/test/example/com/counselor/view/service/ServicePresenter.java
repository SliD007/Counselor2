package test.example.com.counselor.view.service;

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
import test.example.com.counselor.view.service.entity.AdviceEntity;
import test.example.com.counselor.view.service.entity.ClassicCaseEntity;
import test.example.com.counselor.view.service.entity.SummaryEntity;
import test.example.com.counselor.view.service.entity.WorkLogEntity;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class ServicePresenter extends BasePresenter {

    String URL = "http://www.baidu.com";
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


        HashMap<String,String> params = new HashMap<>();
        //String  contact  手机号码; String  password  用户登录密码
        params.put("current",current+"");
        params.put("size",size+"");
        params.put("type",type+"");
        params.put("counselorId",counselorId+"");
        OkGo.post(URL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("s",s);
//                        JSONObject object = JSON.parseObject(s);
//                        if (object.getInteger("code")==0){
//                            mIToDoListView.requestToDoListSuccess();
//                        }else {
//                            mIToDoListView.requestToDoListFaild();
//                        }
                        if (type==0){
                            workLogEntities.add(new WorkLogEntity(0,"工作日志","报送至：长沙县司法局","2018/01/02/19:47"));
                            workLogEntities.add(new WorkLogEntity(1,"开展深入学习十九大精神","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setWorkLogEntities(workLogEntities);
                        }else if(type==1){
                            adviceEntities.add(new AdviceEntity(0,"关于预防金融诈骗的建议","报送至：长沙县司法局","2018/01/02/19:47"));
                            adviceEntities.add(new AdviceEntity(1,"关于预防金融诈骗的建议","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setAdviceEntities(adviceEntities);
                        }else if(type==2){
                            classicCaseEntities.add(new ClassicCaseEntity(0,"关于预防金融诈骗的典型案件","报送至：长沙县司法局","2018/01/02/19:47"));
                            classicCaseEntities.add(new ClassicCaseEntity(1,"关于预防金融诈骗的典型案件","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setClassicCaseEntities(classicCaseEntities);
                        }else {
                            summaryEntities.add(new SummaryEntity(0,"11月总结","报送至：长沙县司法局","2017/12/02/19:47"));
                            summaryEntities.add(new SummaryEntity(1,"12月总结","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setSummaryEntities(summaryEntities);
                        }

                    mIServiceView.requestServiceSuccess();
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIServiceView.requestServiceFailed();
                    }
                });
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
