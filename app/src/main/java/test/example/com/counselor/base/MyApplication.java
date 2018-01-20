package test.example.com.counselor.base;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.LinkedList;
import java.util.List;

import test.example.com.counselor.view.login.LoginEntity;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Sli.D on 2017/12/19.
 */

public class MyApplication extends Application {
    private static List<Activity> activityList = new LinkedList<Activity>();
    private static MyApplication instance;

    public LoginEntity getLoginEntity() {
        return loginEntity;
    }

    public void setLoginEntity(LoginEntity loginEntity) {
        this.loginEntity = loginEntity;
    }

    public LoginEntity loginEntity;// 当前的用户信息
    public boolean refresh = false;
    public boolean show_star_dialog = true;

    private RequestQueue queues;
    public MyApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Volley框架
        queues = Volley.newRequestQueue(getApplicationContext());
        //Crash 上报
        CrashReport.initCrashReport(getApplicationContext(), "69d7035114", true);
        //解决Android 7.0 FileUriExposedException
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        //okgo框架
        initOkGo();
    }

    private void initOkGo() {

        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        params.put("commonParamsKey2", "这里支持中文参数");
        //-----------------------------------------------------------------------------------//

        //必须调用初始化
        OkGo.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())          //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
//                    .setCertificates()                                  //方法一：信任所有证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

                    //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                    //这两行同上,不需要就不要传
                    .addCommonHeaders(headers)                                         //设置全局公共头
                    .addCommonParams(params);                                          //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
        Log.e("activityList",activityList.toString());
    }

    //销毁栈顶activity
    public void finishActivity(Activity activity){
        activityList.remove(activity);
        Log.e("activityList",activityList.toString());
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            Log.i("-aa_aa-", "destroy all activity: " + activity.toString() + " !!!");
            activity.finish();
        }
        System.exit(0);
    }


    // 用于返回全局RequestQueue对象，如果为空则创建它
    public RequestQueue getRequestQueue() {
        if (queues == null) {
            queues = Volley.newRequestQueue(getApplicationContext());
        }
        return queues;
    }

    /**
     * 将Request对象添加进RequestQueue，由于Request有*StringRequest,JsonObjectResquest...
     * 等多种类型，所以需要用到*泛型。同时可将*tag作为可选参数以便标示出每一个不同请求
     */

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // 如果tag为空的话，就是用默认TAG
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    // 通过各Request对象的Tag属性取消请求
    public void cancelPendingRequests(Object tag) {
        if (getRequestQueue() != null) {
            getRequestQueue().cancelAll(tag);
        }
    }

}
