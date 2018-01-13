package test.example.com.counselor.util;

import com.amap.api.location.AMapLocation;

/**
 * Created by Sli.D on 2018/1/13.
 */

public interface ILocaltionModel {
    void getLocaltionSuccess(AMapLocation location);
    void getLocaltionFailed();
}
