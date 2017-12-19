package test.example.com.counselor.login;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class LoginModel implements ILoginModel{

    private LoginBean mLoginBean;

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
        Log.e("","value:"+value);
        Log.e("","this.value:"+this.value);
        try {
            this.id = value.getInt("id");
            this.account = value.getString("account");
            this.username = value.getString("username");
            this.avatar = value.getString("avatar");
            this.email = value.getString("email");
            this.tel = value.getString("tel");
            this.role = value.getString("role");

            this.parentId = value.getInt("parentId");
            this.parentName = value.getString("parentName");
            this.department = value.getJSONObject("department");

            this.departmentId = this.department.getInt("id");
            this.departmentName = this.department.getString("name");
            this.departmentDescription = this.department.getString("description");
            this.departmentTotalNum = this.department.getInt("totalNum");
            mLoginBean = new LoginBean(
                    this.id,
                    this.account,
                    this.username,
                    this.avatar,
                    this.email,
                    this.tel,
                    this.role,
                    this.parentId,
                    this.parentName,
                    this.departmentId,
                    this.departmentName,
                    this.departmentDescription,
                    this.departmentTotalNum);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //getLoginBean();
    }

    public LoginBean getLoginBean() {
        Log.e("LoginModel","loginBean------------->"+mLoginBean.getEmail());
        return mLoginBean;
    }
}
