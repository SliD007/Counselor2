package test.example.com.counselor.view.schedule.chage;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/26.
 */

public class ChangeSchedulePersenter {


    private Context mContext;
    private IChangeSchedule mIChangeSchedule;
    public ChangeSchedulePersenter(Context context,IChangeSchedule iChangeSchedule) {
        this.mContext = context;
        this.mIChangeSchedule = iChangeSchedule;
    }

    public void changeSchedule(int id,String time,String workWay){
        Log.e("changeSchedule:","id:"+id+"time:"+time+"workWay:"+workWay);
        mIChangeSchedule.changeSuccess();

    }
}
