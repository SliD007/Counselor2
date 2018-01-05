package test.example.com.counselor.view.forgetpw;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class ForgetPwPresenter extends BasePresenter{
    private IForgetPwView mIForgetPwView;

    private String URL = "http:www.baidu.com";
    public ForgetPwPresenter(IForgetPwView view){
        mIForgetPwView = view;
    }

    public void sentVCode(String contact){
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",contact);
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("s",s);
                mIForgetPwView.getVCodeSuccess();

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
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("s",s);
                mIForgetPwView.resetPwSuccess();

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
