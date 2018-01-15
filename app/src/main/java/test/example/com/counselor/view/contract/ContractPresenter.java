package test.example.com.counselor.view.contract;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.util.Urls;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class ContractPresenter extends BasePresenter{

    private IContractView mIContractView;
    private IContractModel mContractModel;
    private List<ContractEntity> contractEntities;


    public ContractPresenter(IContractView view){
        mIContractView = view;
        mContractModel = new ContractModel();
    }

    public void requestContract(){
        HashMap<String,String> params = new HashMap<>();
        params.put("current",1+"");
        params.put("size",20+"");
        params.put("counselor",""+ MyApplication.getInstance().loginEntity.getId());
        OkGo.post(Urls.ContractURL)
                .params(params)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("requestContract",s);
                JSONObject object = JSON.parseObject(s);
                if (object.getInteger("code")==0){
                    saveValue(object);
                    mIContractView.requestContractSuccess();
                }else {
                    mIContractView.requestContractFailed();
                }


            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
//                Log.e("response",response.toString());
                mIContractView.requestContractFailed();
            }
        });
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }

    public List<ContractEntity> getContractEntity(){
        return mContractModel.getContractEntity();
    }

    public void saveValue(JSONObject object){
        JSONObject page = object.getJSONObject("page");
        JSONArray listArray = page.getJSONArray("list");
        Log.e("requestContract",""+listArray.toString());

        contractEntities = JSONArray.parseArray(listArray.toString(),ContractEntity.class);
        mContractModel.setContractEntity(contractEntities);
        Log.e("requestScheduleList",""+contractEntities.toString());

    }
}
