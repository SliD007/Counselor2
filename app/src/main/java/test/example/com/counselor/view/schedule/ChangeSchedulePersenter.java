package test.example.com.counselor.view.schedule;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/26.
 */

public class ChangeSchedulePersenter {

    Context mContext;
    IChangeSchedule mIChangeSchedule;
    public ChangeSchedulePersenter(Context context,IChangeSchedule iChangeSchedule) {
        this.mContext = context;
        this.mIChangeSchedule = iChangeSchedule;
    }

    public void changeSchedule(String time){
        Log.e("NewTime:",""+time);
        mIChangeSchedule.changeSuccess();
    }
}
