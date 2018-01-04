package test.example.com.counselor.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.callback.AbsCallback;

import okhttp3.Response;

/**
 * Created by Sli.D on 2018/1/2.
 */

public abstract class JsonCallback extends AbsCallback<JSONObject> {

    @Override
    public JSONObject convertSuccess(Response response) throws Exception {
        String s = response.body().string();
        if(s==null) {
            return null;
        }else {
            JSONObject object = JSON.parseObject(s);
            return object;
        }
    }
}
