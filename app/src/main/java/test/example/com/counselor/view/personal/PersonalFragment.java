package test.example.com.counselor.view.personal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseFragment;
import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.view.changepw.ChagePwActivity;

/**
 * Created by Sli.D on 2017/12/20.
 */

public class PersonalFragment extends BaseFragment {

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        Log.e("PersonalFragment","加载数据");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.unloginRl,R.id.changePwRl})
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.unloginRl:
                toast("确定退出吗",true);
                MyApplication.getInstance().finishActivity(getActivity());
                getActivity().finish();
                break;
            case R.id.changePwRl:
                i = new Intent(getActivity(), ChagePwActivity.class);
                startActivity(i);
                break;
        }
    }
}
