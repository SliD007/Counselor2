package test.example.com.counselor.view.service.addchargecase;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddChargeCasePersenter {

    private IAddChargeCaseView mIAddChargeCaseView;
    public AddChargeCasePersenter(Context context, IAddChargeCaseView iAddChargeCaseView) {
        this.mIAddChargeCaseView = iAddChargeCaseView;
    }

    public void addGroupCase(String[] str){
        for (int i=0;i<str.length;i++){
            Log.e("addWorkLog",""+str[i]);
        }
        mIAddChargeCaseView.addSuccess();
    }

}
