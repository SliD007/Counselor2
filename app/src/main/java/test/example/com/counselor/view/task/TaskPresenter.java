package test.example.com.counselor.view.task;

import android.content.Context;
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
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;
import test.example.com.counselor.view.task.entity.DoneTaskEntity;
import test.example.com.counselor.view.task.entity.ToDoTaskEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class TaskPresenter {

    Context context;
    ITaskView mITaskView;
    ITaskModel mITaskModel;
    List<ToDoTaskEntity> toDoTaskEntities;
    List<DoneTaskEntity> doneTaskEntities;
    public TaskPresenter(Context context, ITaskView iTaskView){
        this.context = context;
        this.mITaskView = iTaskView;
        mITaskModel = new TaskModel();
        toDoTaskEntities = new ArrayList<ToDoTaskEntity>();
        doneTaskEntities = new ArrayList<DoneTaskEntity>();
    }

    public void requestTask(int current, int size, final int type, int counselorId){

        HashMap<String,String> params = new HashMap<>();
        //String  contact  手机号码; String  password  用户登录密码
        params.put("current",current+"");
        params.put("size",size+"");
        params.put("type",type+"");
        params.put("counselorId",counselorId+"");
        OkGo.post(Urls.TASKURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("requestTask","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object,type);

                            mITaskView.requestTaskSuccess();
                        }else {
                            mITaskView.requestTaskFaild();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mITaskView.requestTaskFaild();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public int requestStar(){
        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        OkGo.post(Urls.TASKURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("requestStar","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
//                            saveValue(object,type);

                            mITaskView.requestStarSuccess();
                        }else {
                            mITaskView.requestStarFaild();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mITaskView.requestStarFaild();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
        return 2;
    }


    public List<ToDoTaskEntity> getToDoTaskEntity(){
        return mITaskModel.getToDoTaskEntity();
    }
    public List<DoneTaskEntity> getDoneTaskEntityList(){
        return mITaskModel.getDoneTaskEntity();
    }

    public void saveValue(JSONObject object, int type){
        JSONObject page = object.getJSONObject("page");
        JSONArray listArray = page.getJSONArray("list");
        Log.e("saveValue",""+listArray.toString());
        if (type==0){
            toDoTaskEntities = JSONArray.parseArray(listArray.toString(),ToDoTaskEntity.class);
            mITaskModel.setToDoTaskEntity(toDoTaskEntities);
            Log.e("saveValue",""+toDoTaskEntities.toString());
        }else {
            doneTaskEntities = JSONArray.parseArray(listArray.toString(),DoneTaskEntity.class);
            mITaskModel.setDoneTaskEntity(doneTaskEntities);
//            Log.e("saveValue",""+doneTaskEntities.toString());
        }
    }
}
