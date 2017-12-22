package test.example.com.counselor.view.login;

import test.example.com.counselor.base.MyApplication;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class LoginModel implements ILoginModel{

    @Override
    public LoginEntity getEntity() {
        return MyApplication.getInstance().getLoginEntity();
    }

    @Override
    public void setEntity(LoginEntity entity) {
        MyApplication.getInstance().setLoginEntity(entity);
    }
}
