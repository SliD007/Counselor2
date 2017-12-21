package test.example.com.counselor.view.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseFragment;
import test.example.com.counselor.view.login.LoginActivity;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.unloginRl)
    public void onClick() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
    }
}
