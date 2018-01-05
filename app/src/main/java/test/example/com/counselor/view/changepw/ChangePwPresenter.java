package test.example.com.counselor.view.changepw;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.base.MyApplication;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class ChangePwPresenter extends BasePresenter{
    private IChangePwView mIChangePwView;

    private String URL = "http:www.baidu.com";
    public ChangePwPresenter(IChangePwView view){
        mIChangePwView = view;
    }

    public void changePw(final Context mContext, final String oldPassword, final String newPassword){
        Log.e(oldPassword,newPassword);
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",""+ MyApplication.getInstance().loginEntity.getContact());
        params.put("olderpassword",oldPassword);
        params.put("newPassword",newPassword);
        params.put("userType","1");
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("s",s);

                mIChangePwView.changePwSuccess();

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
