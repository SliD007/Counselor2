package test.example.com.counselor.view.login;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.util.Constants;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class LoginPresenter extends BasePresenter{
    private ILoginModel mLoginModel;
    private ILoginView mLoginView;

    private String URL = "http:www.baidu.com";
    public LoginPresenter(ILoginView view){
        mLoginView = view;
        mLoginModel = new LoginModel();
    }

    public void loadLogin(final Context mContext, final String account, final String password){
        Log.e(account,password);
        HashMap<String,String> params = new HashMap<>();
        //String  contact  手机号码; String  password  用户登录密码
        params.put("contact",account);
        params.put("password",password);
        OkGo.post(Urls.LOGINURL)
                .params(params)
                .cacheKey(Constants.getAppCacheFolder())
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(-1)
                .execute(loginStringCallback);
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }

    StringCallback loginStringCallback = new StringCallback() {

        @Override
        public void onBefore(BaseRequest request) {
            super.onBefore(request);
//            Log.e("onBefore",request.toString());
        }

        @Override
        public void onCacheSuccess(String s, Call call) {
            super.onCacheSuccess(s, call);
//            Log.e("onCacheSuccess",s);
        }

        @Override
        public void onCacheError(Call call, Exception e) {
            super.onCacheError(call, e);
//            Log.e("onCacheError",e.toString());
        }

        @Override
        public String convertSuccess(Response response) throws Exception {
//            Log.e("convertSuccess",response.toString());
            return super.convertSuccess(response);
        }

        @Override
        public void parseError(Call call, Exception e) {
            super.parseError(call, e);
//            Log.e("parseError",e.toString());
        }

        public void onSuccess(String s, Call call, Response response) {
//            Log.e("loadLogin","onSuccess:"+s);

            JSONObject object = JSON.parseObject(s);
            if (object.getInteger("code")==0){
                saveValue(object);
                mLoginView.loginSuccess();
            }else {
                mLoginView.loginFailed();
            }

        }

        @Override
        public void onError(Call call, Response response, Exception e) {
            super.onError(call, response, e);
//            Log.e("onError",e.toString());
            mLoginView.loginFailed();
        }

        @Override
        public void onAfter(String s, Exception e) {
            super.onAfter(s, e);
//            Log.e("onAfter",s);
        }
    };



    public void saveValue(JSONObject object){
        JSONObject value = JSON.parseObject(object.getString("value"));
        LoginEntity entity = JSON.parseObject(value.toString(),LoginEntity.class);
        mLoginModel.setEntity(entity);
//        Log.e("LoginEntity",mLoginModel.getEntity().toString());

    }
}
