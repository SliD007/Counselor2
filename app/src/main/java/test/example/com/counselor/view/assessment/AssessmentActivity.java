package test.example.com.counselor.view.assessment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;
import test.example.com.counselor.base.MyApplication;

public class AssessmentActivity extends BaseActivity {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.assessWv)
    WebView assessWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        super.allow_quit = false;
        titleBarTv.setText("我的考核");
        requestAssessmentWeb();
        super.isAllowScreenRoate=true;
    }

    private void requestAssessmentWeb() {
        assessWv.loadUrl("http://law.d9lab.net/jsp/index/advisorAssement.jsp?justiceId="+
                MyApplication.getInstance().loginEntity.getJustice().getInteger("id")+"&counselorId="+
                MyApplication.getInstance().loginEntity.getId());
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//        assessWv.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // TODO Auto-generated method stub
//                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }


    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_assessment);
    }



    private void initDatas() {
        Log.e("AssessmentActivity", "加载数据");


    }

    @OnClick({R.id.backTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                MyApplication.getInstance().finishActivity(this);
                this.finish();
                break;
        }
    }

}
