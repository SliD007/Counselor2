package test.example.com.counselor.view.service.addworklog;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddWorkLogPersenter {

    private IAddWorkLogView mIAddWorkLogView;
    public AddWorkLogPersenter(Context context, IAddWorkLogView iAddWorkLogView) {
        this.mIAddWorkLogView = iAddWorkLogView;
    }

    public void addWorkLog(String[] str){
        for (int i=0;i<str.length;i++){
            Log.e("addWorkLog",""+str[i]);
        }
        mIAddWorkLogView.addSuccess();
    }

}
