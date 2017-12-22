package test.example.com.counselor.view.login;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class LoginModel implements ILoginModel{

    private LoginEntity mLoginEntity;

    private JSONObject value;

    private int id;
    private String account;
    private String username;
    private String avatar;
    private String email;
    private String tel;
    private String role;
    private int parentId;
    private String parentName;
    private JSONObject department;
    private int departmentId;
    private String departmentName;
    private String departmentDescription;
    private int departmentTotalNum;

    @Override
    public JSONObject getValue() {
        return value;
    }

    @Override
    public void setValue(JSONObject value) {
        this.value = value;
        try {

            Log.e("","value:"+value);
            Log.e("","this.value:"+this.value);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //getLoginBean();
    }

    public LoginEntity getLoginBean() {
        Log.e("LoginModel","loginBean------------->"+ mLoginEntity.getEmail());
        return mLoginEntity;
    }
}
