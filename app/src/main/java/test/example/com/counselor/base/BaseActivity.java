package test.example.com.counselor.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

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
        handler消息传递
     */
    protected void setIconText() {

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


}
