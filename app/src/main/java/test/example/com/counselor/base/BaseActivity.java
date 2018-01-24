package test.example.com.counselor.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Urls;


public abstract class BaseActivity extends FragmentActivity {

    private String TAG = "BaseActivity";
    protected boolean hide_status = false;
    protected boolean hide_title = true;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setViewBase();  //默认隐藏标题栏
        initContentView(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        Log.i(TAG,""+this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//解决虚拟键

    }
    /*
    初始化布局
     */
    protected abstract void initContentView(Bundle savedInstanceState);

    /*
    基础页面设置
    */
    protected void setViewBase() {
        //隐藏标题栏
        if(hide_title) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        //隐藏状态栏
        Window window = getWindow();
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        if(hide_status)
            window.setFlags(flag, flag);
    }

    /*
    toast封装
     */
    protected void toast(String str, boolean is_long){
        if(is_long)
            Toast.makeText(getApplicationContext(), str,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), str,Toast.LENGTH_SHORT).show();
    }

    /*
    按两次退出App
     */
    private long exitTime = 0;
    protected boolean allow_quit = true;    //一级菜单时允许退出app，二级菜单屏蔽此功能
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(allow_quit && keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                toast("再按一次退出程序",false);
                exitTime = System.currentTimeMillis();
            } else {
                MyApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void clock(){
        HashMap<String,String> params = new HashMap<>();
        params.put("counselorId",MyApplication.getInstance().loginEntity.getId()+"");
        params.put("villageId", MyApplication.getInstance().clockVillage+"");
        Log.e("clock","params");
        OkGo.post(Urls.ClockURL)
                .params(params)
                .cacheTime(-1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("requestScheduleList","response"+response.toString());
                        Log.e("clock","onSuccess"+s);
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }



}
