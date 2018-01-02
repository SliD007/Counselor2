package test.example.com.counselor.callback;

import com.lzy.okgo.callback.AbsCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class EntityCallBack<T> extends AbsCallback {
    @Override
    public void onSuccess(Object o, Call call, Response response) {

    }

    @Override
    public Object convertSuccess(Response response) throws Exception {
        return null;
    }
}
