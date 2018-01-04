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
    public ServicePresenter(IServiceView serviceView) {
        this.mIServiceView = serviceView;
        this.mServiceModel = new ServiceModel();
    }

    public void requestData(int index,boolean need_request){
        String[] str1 = new String[0];
        String[] str2 = new String[0];
        String[] str3 = new String[0];

        /*
        need_request获取模式，在okgo里：
            true则使用FIRST_CACHE_THEN_REQUEST先缓存仔请求
            false则使用IF_NONE_CACHE_REQUEST缓存不在才请求
         */
        if (need_request){  //需要进行网络请求
            switch (index){
                case 0:
                    str1 = new String[]{"工作日志r", "工作日志r", "工作日志r"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 1:
                    str1 = new String[]{"关于预防金融诈骗的建议r", "关于预防虚假网络贷款的建议", "关于预防老年人被诈骗的建议"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 2:
                    str1 = new String[]{"关于预防金融诈骗的典型案件r", "关于预防虚假网络贷款的典型案件", "关于预防老年人被诈骗的典型案件"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 3:
                    str1 = new String[]{"11月总结r", "10月总结", "9月总结"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
            }
        }else { // 在本地获取
            switch (index){
                case 0:
                    str1 = new String[]{"工作日志", "工作日志", "工作日志"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 1:
                    str1 = new String[]{"关于预防金融诈骗的建议", "关于预防虚假网络贷款的建议", "关于预防老年人被诈骗的建议"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 2:
                    str1 = new String[]{"关于预防金融诈骗的典型案件", "关于预防虚假网络贷款的典型案件", "关于预防老年人被诈骗的典型案件"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 3:
                    str1 = new String[]{"11月总结", "10月总结", "9月总结"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
            }
        }
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
                            List<WorkLogEntity> entities = new ArrayList<WorkLogEntity>();
                            entities.add(new WorkLogEntity(0,"工作日志","报送至：长沙县司法局","2018/01/02/19:47"));
                            entities.add(new WorkLogEntity(1,"开展深入学习十九大精神","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setWorkLogEntities(entities);
                        }else if(type==1){
                            List<AdviceEntity> entities = new ArrayList<AdviceEntity>();
                            entities.add(new AdviceEntity(0,"关于预防金融诈骗的建议","报送至：长沙县司法局","2018/01/02/19:47"));
                            entities.add(new AdviceEntity(1,"关于预防金融诈骗的建议","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setAdviceEntities(entities);
                        }else if(type==2){
                            List<ClassicCaseEntity> entities = new ArrayList<ClassicCaseEntity>();
                            entities.add(new ClassicCaseEntity(0,"关于预防金融诈骗的典型案件","报送至：长沙县司法局","2018/01/02/19:47"));
                            entities.add(new ClassicCaseEntity(1,"关于预防金融诈骗的典型案件","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setClassicCaseEntities(entities);
                        }else {
                            List<SummaryEntity> entities = new ArrayList<SummaryEntity>();
                            entities.add(new SummaryEntity(0,"11月总结","报送至：长沙县司法局","2017/12/02/19:47"));
                            entities.add(new SummaryEntity(1,"12月总结","星沙街道司法局","2018/01/02/9:47"));
                            mServiceModel.setSummaryEntities(entities);
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
