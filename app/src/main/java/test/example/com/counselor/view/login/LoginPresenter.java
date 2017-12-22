package test.example.com.counselor.view.login;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class LoginPresenter {
    private ILoginModel mLoginModel;
    private ILoginView mLoginView;

    public LoginPresenter(ILoginView view){
        mLoginView = view;
        mLoginModel = new LoginModel();

    }

    public void saveValue(JSONObject value){
        mLoginModel.setValue(value);
    }

    public void loadLogin(final Context mContext, final String account, final String password){
        Log.i("loginInfo", "account=" + account + ",password=" + password);
        getNet("https://www.baidu.com");
    }
    public void getParams(){
        Log.e("LoginPresenter","loginean------------->");
//        mUnloginView.setName(mLoginModel.getLoginBean().getUsername());
    }

    private void getNet(String url){
        OkGo.get(url) // 请求方式和请求url
//                .tag(this) // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("cacheKey") // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.DEFAULT) // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {// s 即为所需要的结果
                        Log.e("gsc",s);
                        mLoginView.loginFailed();
                    }


                });
    }
}
