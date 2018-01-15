package test.example.com.counselor.view.task.showtask;

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

public class ShowTaskPresenter {

    private TaskDetialEntity taskDetialEntity;
    private IShowTaskView mIShowTaskView;
    private IShowTaskModel mIShowTaskModel;
    public ShowTaskPresenter(Context context, IShowTaskView iShowTaskView) {
        this.mIShowTaskView = iShowTaskView;
        mIShowTaskModel = new ShowTaskModel();
    }

    public void changeTaskState(int id, int fromWhere){

        HashMap<String,String> params = new HashMap<>();
        params.put("id",id+"");
        params.put("fromWhere",fromWhere+"");
        OkGo.post(Urls.ChangeTaskStateURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("changeTaskState","response:"+response.toString());
                        Log.e("changeTaskState","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            mIShowTaskView.changeTaskStateSuccess();

                        }else {
                            mIShowTaskView.changeTaskStateFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIShowTaskView.requestTaskConfigurationFailed();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public void requestTaskDetial(int id, int fromWhere){

        HashMap<String,String> params = new HashMap<>();
        params.put("id",id+"");
        params.put("fromWhere",fromWhere+"");
        OkGo.post(Urls.TASKConfigurationURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("requestTaskDetial","response:"+response.toString());
                        Log.e("requestTaskDetial","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object);
                            mIShowTaskView.requestTaskConfigurationSuccess();

                        }else {
                            mIShowTaskView.requestTaskConfigurationFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIShowTaskView.requestTaskConfigurationFailed();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public void saveValue(JSONObject object){

        JSONObject value = object.getJSONObject("value");
//        Log.e("requestTask",""+value.toString());
        taskDetialEntity = JSONObject.parseObject(value.toString(),TaskDetialEntity.class);
        mIShowTaskModel.setTaskDetialEntity(taskDetialEntity);
//        Log.e("requestTask",""+taskDetialEntity.toString());

    }

    public TaskDetialEntity getTaskDetialEntity(){
        return mIShowTaskModel.getTaskDetialEntity();
    }
}
