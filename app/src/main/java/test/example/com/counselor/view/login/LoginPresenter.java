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
import test.example.com.counselor.util.Urls;

import static cn.jpush.android.api.JPushInterface.setAlias;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class LoginPresenter extends BasePresenter{
    private ILoginModel mLoginModel;
    private ILoginView mLoginView;
    Context mContext;

    public LoginPresenter(Context context,ILoginView view){
        mLoginView = view;
        mLoginModel = new LoginModel();
        this.mContext = context;
    }

    public void loadLogin(final Context context, final String account, final String password){
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
        setAlias(mContext, 0, value.getString("id")+"");
        LoginEntity entity = JSON.parseObject(value.toString(),LoginEntity.class);
        mLoginModel.setEntity(entity);
//        Log.e("LoginEntity",mLoginModel.getEntity().toString());

    }
}
