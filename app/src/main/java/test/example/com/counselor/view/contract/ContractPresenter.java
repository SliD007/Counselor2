package test.example.com.counselor.view.contract;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.base.MyApplication;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class ContractPresenter extends BasePresenter{
    private IContractView mIContractView;
    private IContractModel mContractModel;
    private String URL = "http:www.baidu.com";
    public ContractPresenter(IContractView view){
        mIContractView = view;
        mContractModel = new ContractModel();
    }

    public void requestContract(){
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",""+ MyApplication.getInstance().loginEntity.getContact());
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("s",s);
                List<ContractEntity> entities = new ArrayList<ContractEntity>();
                entities.add(new ContractEntity(0,"星沙街道西递"));
                entities.add(new ContractEntity(1,"星沙街道宏村"));
                mContractModel.setContractEntity(entities);
                mIContractView.requestContractSuccess();

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
}
