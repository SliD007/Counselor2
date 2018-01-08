package test.example.com.counselor.view.assessment;

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

public class AssessmentPresenter extends BasePresenter{
    private IAssessmentView mIAssessmentView;
    private IAssessmentModel mAssessmentModel;
    private String URL = "http:www.baidu.com";
    public AssessmentPresenter(IAssessmentView view){
        mIAssessmentView = view;
        mAssessmentModel = new AssessmentModel();
    }

    public void requestAssessment(){
        HashMap<String,String> params = new HashMap<>();
        params.put("contact",""+ MyApplication.getInstance().loginEntity.getContact());
        OkGo.post(URL).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.e("s",s);
                List<AssessmentEntity> entities = new ArrayList<AssessmentEntity>();
                entities.add(new AssessmentEntity("一、服务信息公开制度（15分）","1","适配结果：良好\n" +
                        "本轮测试，适配机型 50 部，完成 49 部（未测试 1 部），问题机型 0 部，适配通过率 100.0%。\n" +
                        "发现问题总量 0 个，影响用户量 0万，占比 0.0% 。\n" +
                        "严重级别以上的 0 个，占总问题数比例 0% ，影响用户量 0万 ，占比 0.0% 。 ","适配结果：良好\n" +
                        "本轮测试，适配机型 50 部，完成 49 部（未测试 1 部），问题机型 0 部，适配通过率 100.0%。\n" +
                        "发现问题总量 0 个，影响用户量 0万，占比 0.0% 。\n" +
                        "严重级别以上的 0 个，占总问题数比例 0% ，影响用户量 0万 ，占比 0.0% 。 "));
                entities.add(new AssessmentEntity("二、定期服务机制（15分）","22","适配结果：良好\n" +
                        "本轮测试，适配机型 50 部，完成 49 部（未测试 1 部），问题机型 0 部，适配通过率 100.0%。\n" +
                        "发现问题总量 0 个，影响用户量 0万，占比 0.0% 。\n" +
                        "严重级别以上的 0 个，占总问题数比例 0% ，影响用户量 0万 ，占比 0.0% 。 ","适配结果：良好\n" +
                        "本轮测试，适配机型 50 部，完成 49 部（未测试 1 部），问题机型 0 部，适配通过率 100.0%。\n" +
                        "发现问题总量 0 个，影响用户量 0万，占比 0.0% 。\n" +
                        "严重级别以上的 0 个，占总问题数比例 0% ，影响用户量 0万 ，占比 0.0% 。 "));
                mAssessmentModel.setAssessmentEntity(entities);
                mIAssessmentView.requestAssessmentSuccess();

            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
//                Log.e("response",response.toString());
                mIAssessmentView.requestAssessmentFailed();
            }
        });
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }

    public List<AssessmentEntity> getAssessmentEntity(){
        return mAssessmentModel.getAssessmentEntity();
    }
}
