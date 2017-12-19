package test.example.com.counselor.login;

import org.json.JSONObject;

import mvp.com.mvptest.bean.LoginBean;

/**
 * Created by Sli.D on 2017/5/17.
 */

public interface ILoginModel {
    JSONObject getValue();
    void setValue(JSONObject value);
    LoginBean getLoginBean();
}
