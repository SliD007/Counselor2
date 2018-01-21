package test.example.com.counselor.view.service.addgroupcase;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddGroupCasePersenter {

    private IAddGroupCaseView mIAddGroupCaseView;
    public AddGroupCasePersenter(Context context, IAddGroupCaseView iAddGroupCaseView) {
        this.mIAddGroupCaseView = iAddGroupCaseView;
    }


    public void addGroupCase(String[] str, int[] inter){

        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        params.put("logType", 1+"");

        params.put("serviceVillage", inter[1]+"");
        params.put("matterPlace", str[2]);
        params.put("matterNum", inter[3]+"");
        params.put("matterTime", str[4]);

        params.put("objectType", inter[5]+8+"");
        params.put("serviceContent", str[6]);
        params.put("resultType", inter[8]+"");
        //图片

        //多余的接口字段
        params.put("serviceObject","");
        params.put("objectContact", "");
        params.put("serviceIdentity", "");
        params.put("inObject", "");
        params.put("fromType", 0+"");
        params.put("serviceType", 0+"");
        params.put("matterType", "");
        params.put("subType", "");
        params.put("accessory", "");
        params.put("resultContent", "");
        params.put("objectAddress", "");
        params.put("matterMoney", 0+"");
        params.put("isConflict", false+"");

        Log.e("addGroupCase",params.toString());
        OkGo.post(Urls.WorkLogAddURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
//                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("addWorkLog","response:"+response.toString());
                        Log.e("addWorkLog","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getInteger("code")==0){

                            mIAddGroupCaseView.addSuccess();
                        }else {
                            mIAddGroupCaseView.addFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIAddGroupCaseView.addFailed();
                    }
                });
    }

    public void addgroupCase(final String[] str, final int[] inter){


        StringRequest request4LoginRequest = new StringRequest(
                Request.Method.POST, Urls.WorkLogAddURL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                Log.e("loginInfo", arg0);// 打印登录返回的数据
                try {
                    JSONObject object = JSON.parseObject(arg0);
                    if (object.getInteger("code")==0){
                        mIAddGroupCaseView.addSuccess();
                    }else {
                        mIAddGroupCaseView.addFailed();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e("loginInfo", arg0.toString());// 打印错误信息
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
                params.put("logType", 1+"");

                params.put("serviceVillage", str[1]);
                params.put("matterPlace", str[2]);
                params.put("matterNum", inter[3]+"");
                params.put("matterTime", str[4]);

                params.put("objectType", inter[5]+8+"");
                params.put("serviceContent", str[6]);
                params.put("resultType", inter[8]+"");
                //图片

                //多余的接口字段
                params.put("serviceObject","");
                params.put("objectContact", "");
                params.put("serviceIdentity", "");
                params.put("inObject", "");
                params.put("fromType", 0+"");
                params.put("serviceType", 0+"");
                params.put("matterType", "");
                params.put("subType", "");
                params.put("accessory", "");
                params.put("resultContent", "");
                params.put("objectAddress", "");
                params.put("matterMoney", 0+"");
                params.put("isConflict", false+"");

                Log.e("getParams",params.toString());
                return params;
            }

        };
        MyApplication.getInstance().addToRequestQueue(request4LoginRequest, "");
    }

    public void addImage(ArrayList<ImageItem> imageItems){
        ArrayList<File> files = new ArrayList<>();
        if (imageItems != null && imageItems.size() > 0) {
            for (int i = 0; i < imageItems.size(); i++) {
                files.add(new File(imageItems.get(i).path));
            }
        }
        OkGo.post(Urls.UpdataFileURL)
                .tag(this)//
                .headers("header1", "headerValue1")//
                .headers("header2", "headerValue2")//
                .params("param1", "paramValue1")//
                .params("param2", "paramValue2")//
//                .params("file1",new File("文件路径"))   //这种方式为一个key，对应一个文件
//                .params("file2",new File("文件路径"))
//                .params("file3",new File("文件路径"))
                .addFileParams("file", files)           // 这种方式为同一个key，上传多个文件
                .execute(new StringCallback() {
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("addWorkLog","response:"+response.toString());
                        Log.e("addWorkLog","onSuccess:"+s);
                        JSONObject object = JSON.parseObject(s);
                        if (object.getString("result").equals("success")){
                            mIAddGroupCaseView.addImageSuccess(object.getString("url"));
                        }else {
                            mIAddGroupCaseView.addImageFailed();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mIAddGroupCaseView.addImageFailed();
                    }
                });
    }
}
