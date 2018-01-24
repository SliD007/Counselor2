package test.example.com.counselor.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Urls;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isCreated) {
            return;
        }

        if (isVisibleToUser) {
//            Log.e("BaseFragment","setUserVisibleHint"+getFragmentLayoutId()+isVisibleToUser);
//            initDatas();
        }else {
//            Log.e("BaseFragment","setUserVisibleHint"+getFragmentLayoutId()+isVisibleToUser);
        }

    }
    protected boolean isCreated = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        initPresenter();
        isCreated = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initViews();
        initDatas();

//        Log.e("BaseFragment","onStart"+getFragmentLayoutId());
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.e("BaseFragment","onResume"+getFragmentLayoutId());
    }

    @Override
    public void onPause() {
        super.onPause();
//        Log.e("BaseFragment","onPause"+getFragmentLayoutId());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getFragmentLayoutId();
    protected abstract void initPresenter();
    protected abstract void initViews();
    protected abstract void initEvents();
    protected abstract void initDatas();

    /*
toast封装
 */
    protected void toast(String str, boolean is_long){
        if(is_long)
            Toast.makeText(mContext, str,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(mContext, str,Toast.LENGTH_SHORT).show();
    }

    public void clock(){
        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId",MyApplication.getInstance().loginEntity.getId()+"");
        params.put("villageId", MyApplication.getInstance().clockVillage+"");
        OkGo.post(Urls.ClockURL)
                .params(params)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("requestScheduleList","response"+response.toString());
                        Log.e("requestScheduleList","onSuccess"+s);
                        toast("打卡成功",false);
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("打卡失败",false);
                    }
                });
    }


}
