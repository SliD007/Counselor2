package test.example.com.counselor.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sli.D on 2018/1/9.
 */

public class TimeUtil {

    public static String DataTime = "yyyy-MM-dd HH:mm:ss";
    public static String Time = "yyyy-MM-dd HH:mm";
    public static String Data = "yyyy-MM-dd";

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time, String pattern) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = sf.parse(time);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

}
