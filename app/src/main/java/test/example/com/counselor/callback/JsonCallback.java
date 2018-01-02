package test.example.com.counselor.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;

import okhttp3.Response;

/**
 * Created by Sli.D on 2018/1/2.
 */

public abstract class JsonCallback extends AbsCallback<JSONObject> {

    @Override
    public JSONObject convertSuccess(Response response) throws Exception {
        String s = StringConvert.create().convertSuccess(response);
        if(s==null) {
            return null;
        }else {
            JSONObject object = JSON.parseObject(s);
            return object;
        }
    }
}
