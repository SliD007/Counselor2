package test.example.com.counselor.view.changepw.login;

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

public class ChangePwPresenter extends BasePresenter{
    private IChangePwView mIChangePwView;

    private String URL = "http://119.29.141.240:8080/login/modifyPWD";
    public ChangePwPresenter(IChangePwView view){
        mIChangePwView = view;
    }

    public void changePw(final Context mContext, final String oldPassword, final String newPassword){
        Log.e(oldPassword,newPassword);
        HashMap<String,String> params = new HashMap<>();
        params.put("id","dj");
        params.put("oldPassword",oldPassword);
        params.put("newPassword",newPassword);
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("response",response.toString());
                Log.e("s",s);
                JSONObject object = JSON.parseObject(s);
                if (object.getBoolean("success")==true){
                    mIChangePwView.changePwSuccess();
                }else {
                    mIChangePwView.changePwFailed();
                }
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e("response",response.toString());
                mIChangePwView.changePwFailed();
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
