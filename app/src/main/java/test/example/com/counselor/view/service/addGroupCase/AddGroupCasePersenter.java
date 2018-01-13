package test.example.com.counselor.view.service.addGroupCase;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddGroupCasePersenter {

    private IAddGroupCaseView mIAddGroupCaseView;
    public AddGroupCasePersenter(Context context, IAddGroupCaseView iAddGroupCaseView) {
        this.mIAddGroupCaseView = iAddGroupCaseView;
    }

    public void addGroupCase(String[] str){
        for (int i=0;i<str.length;i++){
            Log.e("addWorkLog",""+str[i]);
        }
        mIAddGroupCaseView.addSuccess();
    }

}
