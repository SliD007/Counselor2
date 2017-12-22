package test.example.com.counselor.view.login;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Sli.D on 2017/5/17.
 */

public interface ILoginModel {
    JSONObject getValue();
    void setValue(JSONObject value);
    LoginEntity getLoginBean();
}
