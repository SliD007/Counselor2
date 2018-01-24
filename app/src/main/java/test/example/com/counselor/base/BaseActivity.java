package test.example.com.counselor.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.lang.reflect.Method;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;
import test.example.com.counselor.util.Urls;


public abstract class BaseActivity extends FragmentActivity implements ViewTreeObserver.OnGlobalLayoutListener{

    private String TAG = "BaseActivity";
    protected boolean hide_status = false;
    protected boolean hide_title = true;
    /** 是否禁止旋转屏幕 **/
    public boolean isAllowScreenRoate = false;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setViewBase();  //默认隐藏标题栏
        initContentView(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        Log.i(TAG,""+this);
        Log.i(TAG,""+this+getNavigationBarHeight(this));

        //虚拟键盘
        content = (FrameLayout) findViewById(android.R.id.content);
        content.post(new Runnable() {
            @Override
            public void run() {
                mLayoutComplete = true;
            }
        });
        content.getViewTreeObserver().addOnGlobalLayoutListener(this);

        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
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

    //打卡
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

    //获取虚拟按键的高度
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }
    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    FrameLayout content;
    private boolean mLayoutComplete = false;

    @Override
    public void onGlobalLayout() {
        if (!mLayoutComplete)
            return;
        onNavigationBarStatusChanged();
//        Log.e(TAG, "content 布局改变");

    }

    protected void onNavigationBarStatusChanged() {
// 子类重写该方法，实现自己的逻辑即可。
    }


}
