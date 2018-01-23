package test.example.com.counselor.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

import test.example.com.counselor.base.MyApplication;
import test.example.com.counselor.view.schedule.IScheduleModel;
import test.example.com.counselor.view.schedule.ScheduleEntity;
import test.example.com.counselor.view.schedule.ScheduleModel;

/**
 * Created by Sli.D on 2018/1/13.
 */

public class LocaltionUtil {
    Context mContext;
    ILocaltionModel mILocaltionModel;
    IScheduleModel mIScheduleModel;
    List<ScheduleEntity> scheduleEntities;
    public LocaltionUtil(Context context,List<ScheduleEntity> scheduleEntities){
        this.mContext = context;
        this.mILocaltionModel = localtionModel;
        this.mIScheduleModel = new ScheduleModel();
        this.scheduleEntities = scheduleEntities;
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
        mOption.setInterval(10000);//可选，设置定位间隔。默认为2秒
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

    private void clock(double x, double y){
        String village = "";
        double Vx = 0;
        double Vy = 0;
        //不在工作时间不能打卡
        //获取现在时（精度：天）
        long nowTime=System.currentTimeMillis();
        nowTime = TimeUtil.getStringToDate(TimeUtil.getDateToString(nowTime,TimeUtil.Data),TimeUtil.Data);
//        Log.e("NowTime",""+nowTime);
        //获取工作安排,判断顾问应该在哪个村社服务（只有在三个页面有效）
        if(scheduleEntities==null){
            Log.e("scheduleEntities","null");
        }
        for(int i=0;i<scheduleEntities.size();i++){
            long workTime = scheduleEntities.get(i).getPlacementTime();
//            Log.e("workTime",""+workTime);
            if(workTime==nowTime)
                village = scheduleEntities.get(i).getVillage();
        }

        //获取当前服务村社经纬度
        if(village.equals(MyApplication.getInstance().loginEntity.getVillageA())){
            Vx = MyApplication.getInstance().loginEntity.getCommunityA().getDouble("longitude");
            Vy = MyApplication.getInstance().loginEntity.getCommunityA().getDouble("latitude");
            MyApplication.getInstance().clockVillage = MyApplication.getInstance().loginEntity.getVillageAId();
        }else if(village.equals(MyApplication.getInstance().loginEntity.getVillageB())){
            Vx = MyApplication.getInstance().loginEntity.getCommunityB().getDouble("longitude");
            Vy = MyApplication.getInstance().loginEntity.getCommunityB().getDouble("latitude");
            MyApplication.getInstance().clockVillage = MyApplication.getInstance().loginEntity.getVillageAId();
        }

        //判断距离
        LatLng latLng1 = new LatLng(y,x);
        LatLng latLng2 = new LatLng(Vy,Vx);

        float distance = AMapUtils.calculateLineDistance(latLng1,latLng2);
//        Log.e("distance",""+distance);
        if(distance<=5000){
            MyApplication.getInstance().clock = true;
            locationClient.stopLocation();
        }

    }

    ILocaltionModel localtionModel = new ILocaltionModel() {
        @Override
        public void getLocaltionSuccess(AMapLocation location) {

//            Log.e("定位","返回code:"+location.getErrorCode());
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            switch (location.getErrorCode()){
                case 0:
                    Log.e("定位成功",""
//                            "定位类型: " + location.getLocationType() + "\n"+
//                                    "经    度    : " + location.getLongitude() + "\n"+
//                                    "纬    度    : " + location.getLatitude() + "\n"+
//                                    "精    度    : " + location.getAccuracy() + "米" + "\n"+
//                                    "提供者    : " + location.getProvider()+ "\n" +
//                                    "地    址    : " +location.getAddress() + "\n"
//                                "国家信息    : " +location.getCountry() +
//                                "省    : " +location.getProvince() +
//                                "市    : " +location.getCity() +
//                                "县区    : " +location.getDistrict() +
//                                "镇街    : " +location.getStreet() +
//                                "门牌号    : " +location.getStreetNum()
                    );
                    clock(location.getLongitude(),location.getLatitude());
                    break;

            }
        }

        @Override
        public void getLocaltionFailed() {

        }
    };
}
