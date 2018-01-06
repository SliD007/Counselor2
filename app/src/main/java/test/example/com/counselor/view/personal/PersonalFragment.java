package test.example.com.counselor.view.personal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
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

    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.lawFirmTv)
    TextView lawFirmTv;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViews() {
        nameTv.setText(MyApplication.getInstance().loginEntity.getName());
        addressTv.setText(MyApplication.getInstance().loginEntity.getCommunityA()+","+MyApplication.getInstance().loginEntity.getCommunityB());
        lawFirmTv.setText(MyApplication.getInstance().loginEntity.getOrganization());
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        Log.e("PersonalFragment", "加载数据");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.unloginRl, R.id.changePwRl})
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.unloginRl:
                showUnloginDialog();
                break;
            case R.id.changePwRl:
                i = new Intent(getActivity(), ChagePwActivity.class);
                startActivity(i);
                break;
        }
    }
    public void showUnloginDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // 先得到构造器
        builder.setTitle("退出登录"); // 设置标题
        builder.setMessage("确定退出登录？"); // 设置内容
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() { // 设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss(); // 关闭dialog
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() { // 设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                SharedPreferences autoLoginSp = getActivity().getSharedPreferences("autoLoginSp", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = autoLoginSp.edit();
//                editor.putInt("autoLogin", 0);
//                editor.commit();
//                Intent i = new Intent(getActivity(),LoginActivity.class);
//                i.putExtra("autologin", true);
//                i.putExtra("autologinInt", 1);
//                startActivity(i);

                MyApplication.getInstance().finishActivity(getActivity());
                getActivity().finish();
            }
        });
        builder.create().show();
    }
}
