package test.example.com.counselor.view.service.addcommon;

import android.content.Context;
import android.util.Log;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class AddCommonPresenter {

    private IAddCommonView mIAddCommonView;
    public AddCommonPresenter(Context context, IAddCommonView iAddCommonView) {
        this.mIAddCommonView = iAddCommonView;
    }

    public void addCommonText(int fragmentType,String title,String context_str){
        Log.e("Advice","title:"+title+",context:"+context_str+",fragmentType:"+fragmentType);
        mIAddCommonView.addSuccess();
    }

}
