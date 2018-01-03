package test.example.com.counselor.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Lan on 2016/11/3.
 */

public class Constants {

    private static final String parentPath = Environment.getExternalStorageDirectory().getAbsolutePath();

    private static final String APP_FOLDER_NAME = "Falvguwen";
    private static final String IMAGE_FOLDER_NAME = "image";
    private static final String FILE_FOLDER_NAME = "file";
    private static final String LOG_FOLDER_NAME = "log";
    private static final String DOWNLOAD_FOLDER_NAME = "download";
    private static final String CACHE_NAME = "cache";

    // 应用程序文件夹路径
    private static final String APP_FOLDER_PATH = parentPath + File.separator + APP_FOLDER_NAME;
    // 应用程序图片文件夹路径
    private static final String IMAGE_FOLDER_PATH = parentPath + File.separator + APP_FOLDER_NAME + File.separator + IMAGE_FOLDER_NAME;
    // 应用程序文件文件夹路径
    private static final String FILE_FOLDER_PATH = parentPath + File.separator + APP_FOLDER_NAME + File.separator + FILE_FOLDER_NAME;
    // 应用程序文件文件夹路径
    private static final String LOG_FOLDER_PATH = parentPath + File.separator + APP_FOLDER_NAME + File.separator + LOG_FOLDER_NAME;
    // 应用程序下载文件夹路径
    private static final String DOWNLOAD_FOLDER_PATH = parentPath + File.separator + APP_FOLDER_NAME + File.separator + DOWNLOAD_FOLDER_NAME;
    // 应用缓存数据文件夹路径
    private static final String CACHE_FOLDER_PATH = parentPath + File.separator + APP_FOLDER_NAME + File.separator + CACHE_NAME;

    public static final int SIZE_PER_PAGE = 10;

    public static final String SP_SETTING_IS_REC_MESSAGE = "settings_is_rec_message";
    public static final String SP_SETTING_IS_SOUND = "settings_is_sound";
    public static final String SP_SETTING_IS_SHAKE = "settings_is_shake";


    public static String getAppRootFolder() {
        File f = new File(APP_FOLDER_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
        return APP_FOLDER_PATH;
    }

    public static String getAppImageFolder() {
        File f = new File(IMAGE_FOLDER_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
        return IMAGE_FOLDER_PATH;
    }

    public static String getAppLogFolder() {
        File f = new File(LOG_FOLDER_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
        return LOG_FOLDER_PATH;
    }

    public static String getAppDownloadFolder() {
        File f = new File(DOWNLOAD_FOLDER_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
        return DOWNLOAD_FOLDER_PATH;
    }

    public static String getAppCacheFolder() {
        File f = new File(CACHE_FOLDER_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
//        Log.e("CACHE_FOLDER_PATH",CACHE_FOLDER_PATH);
        return CACHE_FOLDER_PATH;
    }

    public static String getAppFileFolder() {
        File f = new File(FILE_FOLDER_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
        return FILE_FOLDER_PATH;
    }

}
