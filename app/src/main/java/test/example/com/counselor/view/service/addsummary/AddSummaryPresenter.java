package test.example.com.counselor.view.service.addsummary;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddSummaryPresenter {

    private IAddSummaryView mIAddSummaryView;
    public AddSummaryPresenter(Context context, IAddSummaryView iAddSummaryView) {
        this.mIAddSummaryView = iAddSummaryView;
    }

    public void addCommonText(String title,String context_str){
        Log.e("Advice","title:"+title+",context:"+context_str);
        mIAddSummaryView.addSuccess();
    }

}
