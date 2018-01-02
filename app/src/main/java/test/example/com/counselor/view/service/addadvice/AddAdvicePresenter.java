package test.example.com.counselor.view.service.addadvice;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddAdvicePresenter {

    private IAddAdviceView mIAddAdviceView;
    public AddAdvicePresenter(Context context, IAddAdviceView iAddAdviceView) {
        this.mIAddAdviceView = iAddAdviceView;
    }

    public void addAdvice(String title,String context_str, int rbId){
        Log.e("Advice","title:"+title+",context:"+context_str+",rbId:"+rbId);
        mIAddAdviceView.addSuccess();
    }

}
