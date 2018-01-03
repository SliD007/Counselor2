package test.example.com.counselor.view.todolist;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class ToDoListPresenter {

    String URL = "http://www.baidu.com";

    Context context;
    IToDoListView mIToDoListView;
    IToDoListModel mIToDoListModel;
    public ToDoListPresenter(Context context,IToDoListView iToDoListView){
        this.context = context;
        this.mIToDoListView = iToDoListView;
        mIToDoListModel = new ToDoListModel();
    }

    public void requestToDoList(int current, int size, final int type, int counselorId){


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
                        if (type==1){
                            List<ToDoListEntity> entities = new ArrayList<ToDoListEntity>();
                            entities.add(new ToDoListEntity(0,"开展深入学习十九大精神","星沙街道司法局","2018/01/02/19:47"));
                            entities.add(new ToDoListEntity(1,"开展深入学习十九大精神","星沙街道司法局","2018/01/02/9:47"));
                            mIToDoListModel.setToDoListEntities(entities);
                        }else {
                            List<DoneListEntity> entities = new ArrayList<DoneListEntity>();
                            for(int i=0;i<24;i++){
                                entities.add(new DoneListEntity(i,"开展深入学习十九大精神","星沙街道司法局","2018/01/01/"+i+":47"));

                            }
                            mIToDoListModel.setDoneListEntities(entities);

                        }
                        mIToDoListView.requestToDoListSuccess();
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIToDoListView.requestToDoListFaild();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }


    public List<ToDoListEntity> getToDoListEntityList(){
        return mIToDoListModel.getToDoListEntities();
    }
    public List<DoneListEntity> getDoneListEntityList(){
        return mIToDoListModel.getDoneListEntities();
    }
}
