package test.example.com.counselor.view.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.base.BasePresenter;
import test.example.com.counselor.base.MyApplication;

import static test.example.com.counselor.util.Urls.RankURL;

/**
 * Created by Sli.D on 2017/5/17.
 */

public class RankPresenter extends BasePresenter{

    private IRankView mIRankView;
    private IRankModel mRankModel;

    public RankPresenter(IRankView view){
        mIRankView = view;
        mRankModel = new RankModel();
    }

    public void requestRank(){
        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId", MyApplication.getInstance().loginEntity.getId()+"");
        OkGo.post(RankURL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

//                Log.e("requestRank","onSuccess:"+s);
                JSONObject object = JSONObject.parseObject(s);
                if(object.getInteger("code")==0){
                    saveValue(object);
                    mIRankView.requestRankSuccess();
                }else {
                    mIRankView.requestRankFailed();
                }

            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
//                Log.e("response",response.toString());
                mIRankView.requestRankFailed();
            }
        });
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
    public void saveValue(JSONObject object){
        List<RankEntity> rankEntities = new ArrayList<>();
        JSONObject value = JSON.parseObject(object.getString("value"));
        for(int i=0;i<11;i++){
            try{
                JSONObject counselor = value.getJSONObject(String.valueOf(i));
                RankEntity rankEntity =  JSON.parseObject(counselor.toString(),RankEntity.class);
                rankEntities.add(rankEntity);
//                Log.e("LoginEntity",rankEntity.toString());
            }catch (Exception e){
                continue;
            }

        }
        mRankModel.setRankEntities(rankEntities);

    }
    public List<RankEntity> getRankEntity(){
        return mRankModel.getRankEntities();
    }
}
