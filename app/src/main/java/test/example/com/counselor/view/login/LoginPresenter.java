package test.example.com.counselor.view.login;

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
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.util.Constants;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class LoginPresenter extends BasePresenter{
    private ILoginModel mLoginModel;
    private ILoginView mLoginView;

    private String URL = "http://119.29.141.240:8080/login/login";
    public LoginPresenter(ILoginView view){
        mLoginView = view;
        mLoginModel = new LoginModel();
    }

    public void saveValue(JSONObject object){
        JSONObject value = JSON.parseObject(object.getString("value"));
        LoginEntity entity = JSON.parseObject(value.toString(),LoginEntity.class);
        mLoginModel.setEntity(entity);
        Log.e("LoginEntity",mLoginModel.getEntity().toString());

    }

    public void loadLogin(final Context mContext, final String account, final String password){
        Log.e(account,password);
        HashMap<String,String> params = new HashMap<>();
        params.put("account",account);
        params.put("password",password);
        OkGo.post(URL)
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
        public String convertSuccess(Response response) throws Exception {
            return super.convertSuccess(response);
        }

        public void onSuccess(String s, Call call, Response response) {
//            Log.e("onSuccess",response.toString());
            JSONObject object = JSON.parseObject(s);
            if (object.getBoolean("success")==true){
                mLoginView.loginSuccess();
                saveValue(object);
            }else {
                mLoginView.loginFailed();
            }
        }

        @Override
        public void onError(Call call, Response response, Exception e) {
            super.onError(call, response, e);
            Log.e("onError",response.toString());
            mLoginView.loginFailed();
        }

        @Override
        public void onCacheSuccess(String s, Call call) {
            super.onCacheSuccess(s, call);
            Log.e("onCacheSuccess",s);
        }

        @Override
        public void onCacheError(Call call, Exception e) {
            super.onCacheError(call, e);
            Log.e("onCacheError",e.toString());
        }
    };
}
