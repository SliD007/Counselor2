package test.example.com.counselor.view.service.showgroupcase;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class ShowGroupCasePersenter {

    GroupCaseDetialEntity groupCaseDetialEntity;
    private IShowGroupCaseView mIShowGroupCaseView;
    private IShowGroupCaseModel mIShowGroupCaseModel;
    public ShowGroupCasePersenter(Context context, IShowGroupCaseView iShowGroupCaseView) {
        this.mIShowGroupCaseView = iShowGroupCaseView;
        this.mIShowGroupCaseModel = new ShowGroupCaseModel();
    }
    public void requestGroupCaseDetial(int id){

        HashMap<String,String> params = new HashMap<>();
        params.put("id",id+"");
        OkGo.post(Urls.WorkLogConfigurationURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("requestWorklogDetial","response:"+response.toString());
                        Log.e("requestWorklogDetial","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){
                            saveValue(object);
                            mIShowGroupCaseView.requestGroupCaseSuccess();

                        }else {
                            mIShowGroupCaseView.requestGroupCaseFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIShowGroupCaseView.requestGroupCaseFailed();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                });
    }

    public void downLoadImage(String url, String fileName){

//        Log.e("downLoadContract","fileName:"+Constants.getAppDownloadFolder());

        OkGo.<File>get(url)//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new FileCallback(Constants.getAppImageFolder(),fileName) {

                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        Log.e("downLoadContract","onSuccess:"+response.toString());
                        mIShowGroupCaseView.downloadImageSuccess();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        Log.e("downLoadContract","/storage/emulated/0/download/:"+response.toString());
                        mIShowGroupCaseView.downloadImageFailed();
                    }
                });
    }

    public void saveValue(JSONObject object){

        JSONObject value = object.getJSONObject("value");
//        Log.e("requestWorkLog",""+value.toString());
        groupCaseDetialEntity = JSONObject.parseObject(value.toString(),GroupCaseDetialEntity.class);
        mIShowGroupCaseModel.setGroupCaseDetialEntity(groupCaseDetialEntity);
        Log.e("EntityDetail",""+groupCaseDetialEntity.toString());

    }

    public GroupCaseDetialEntity getGroupCaseDetialEntity(){
        return mIShowGroupCaseModel.getGroupCaseDetialEntity();
    }

}
