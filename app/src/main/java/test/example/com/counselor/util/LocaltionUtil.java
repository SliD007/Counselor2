package test.example.com.counselor.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by Sli.D on 2018/1/13.
 */

public class LocaltionUtil {
    Context mContext;
    ILocaltionModel mILocaltionModel;
    public LocaltionUtil(Context context,ILocaltionModel iLocaltionModel){
        this.mContext = context;
        this.mILocaltionModel = iLocaltionModel;
    }

    /**
     * 定位
     */
    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient = null;
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption locationOption = null;
    //声明定位回调监听器
    private AMapLocationListener locationListener = null;

    private void initLocation(){
        //初始化client
        locationClient = new AMapLocationClient(mContext.getApplicationContext());
        //初始化option
        locationOption = getDefaultOption();
        //初始化监听
        locationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (null != location) {
//                    Log.e("定位","返回");
                    mILocaltionModel.getLocaltionSuccess(location);
                } else {
//                    Log.e("定位","无返回");
                    mILocaltionModel.getLocaltionFailed();
                }
            }
        };
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        //设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    public void startLocation(){
        Log.e("开始定位","开始定位");
        //初始化配置
        initLocation();
        // 启动定位
        locationClient.startLocation();
    }

    public void stopLocation(){
        // 停止定位
         Log.e("停止定位","停止定位");
        locationClient.stopLocation();
    }

    //默认的定位参数
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true

        return mOption;
    }


    public String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
