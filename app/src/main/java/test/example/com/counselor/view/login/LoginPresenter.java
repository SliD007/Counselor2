package test.example.com.counselor.view.login;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

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
        mLoginView.loginSuccess();
    }
    public void getParams(){
        Log.e("LoginPresenter","loginean------------->");
//        mUnloginView.setName(mLoginModel.getLoginBean().getUsername());
    }
}
