package test.example.com.counselor.view.login;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;

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
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("response",response.toString());
//                Log.e("s",s);
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
                Log.e("response",response.toString());
                mLoginView.loginFailed();
            }
        });
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
}
