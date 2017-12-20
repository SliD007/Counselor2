package test.example.com.counselor.view.login;

import org.json.JSONObject;

/**
 * Created by Sli.D on 2017/5/17.
 */

public interface ILoginModel {
    JSONObject getValue();
    void setValue(JSONObject value);
    LoginBean getLoginBean();
}
