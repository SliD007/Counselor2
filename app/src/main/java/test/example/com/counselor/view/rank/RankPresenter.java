package test.example.com.counselor.view.rank;

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

public class RankPresenter extends BasePresenter{
    private IRankView mIRankView;
    private IRankModel mRankModel;
    private String URL = "http:www.baidu.com";
    public RankPresenter(IRankView view){
        mIRankView = view;
        mRankModel = new RankModel();
    }

    public void requestRank(){
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",""+ MyApplication.getInstance().loginEntity.getContact());
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("s",s);
                List<RankEntity> entities = new ArrayList<RankEntity>();
                entities.add(new RankEntity("黄可","西递","1","20","10","10","5","20","5","20"));
                entities.add(new RankEntity("绿山","宏村","2","20","10","10","5","20","5","20"));
                mRankModel.setRankEntities(entities);
                mIRankView.requestRankSuccess();

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

    public List<RankEntity> getRankEntity(){
        return mRankModel.getRankEntities();
    }
}
