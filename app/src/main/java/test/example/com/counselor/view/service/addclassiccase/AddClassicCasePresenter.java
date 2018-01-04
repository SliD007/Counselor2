package test.example.com.counselor.view.service.addclassiccase;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddClassicCasePresenter {

    private IAddClassicCaseView mIAddClassicCaseView;
    public AddClassicCasePresenter(Context context, IAddClassicCaseView iAddClassicCaseView) {
        this.mIAddClassicCaseView = iAddClassicCaseView;
    }

    public void addClassicCase(String title,String context_str){
        Log.e("Advice","title:"+title+",context:"+context_str);
        mIAddClassicCaseView.addSuccess();
    }

}
