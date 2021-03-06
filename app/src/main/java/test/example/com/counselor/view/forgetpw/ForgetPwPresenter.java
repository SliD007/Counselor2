package test.example.com.counselor.view.forgetpw;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class ForgetPwPresenter extends BasePresenter{
    private IForgetPwView mIForgetPwView;

    public ForgetPwPresenter(IForgetPwView view){
        mIForgetPwView = view;
    }

    public void sentVCode(String contact){
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",contact);
        OkGo.post(Urls.GetCodeURL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("s",s);
                JSONObject object = JSON.parseObject(s);
                if(object.getInteger("code")==0) {
                    mIForgetPwView.getVCodeSuccess();
                }else {
                    mIForgetPwView.getVCodeFailed();
                }

            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e("response",response.toString());
                mIForgetPwView.getVCodeFailed();
            }
        });
    }

    public void forgetPw(String contact, String code, final String password){
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",contact);
        params.put("code",code);
        params.put("password",password);
        OkGo.post(Urls.FindPWURL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("s",s);
                JSONObject object = JSON.parseObject(s);
                if(object.getInteger("code")==0) {
                    mIForgetPwView.resetPwSuccess();
                }else {
                    mIForgetPwView.resetPwFailed();
                }
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e("response",response.toString());
                mIForgetPwView.resetPwFailed();
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
