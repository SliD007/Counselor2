package test.example.com.counselor.view.service;

import test.example.com.counselor.base.BasePresenter;

/**
 * Created by Sli.D on 2017/12/25.
 */

public class ServicePresenter extends BasePresenter {

    IServiceView mIServiceView;
    public ServicePresenter(IServiceView serviceView) {
        this.mIServiceView = serviceView;
    }

    public void requestData(int index,boolean need_request){
        String[] str1 = new String[0];
        String[] str2 = new String[0];
        String[] str3 = new String[0];

        /*
        need_request获取模式，在okgo里：
            true则使用FIRST_CACHE_THEN_REQUEST先缓存仔请求
            false则使用IF_NONE_CACHE_REQUEST缓存不在才请求
         */
        if (need_request){  //需要进行网络请求
            switch (index){
                case 0:
                    str1 = new String[]{"工作日志r", "工作日志r", "工作日志r"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 1:
                    str1 = new String[]{"关于预防金融诈骗的建议r", "关于预防虚假网络贷款的建议", "关于预防老年人被诈骗的建议"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 2:
                    str1 = new String[]{"关于预防金融诈骗的典型案件r", "关于预防虚假网络贷款的典型案件", "关于预防老年人被诈骗的典型案件"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 3:
                    str1 = new String[]{"11月总结r", "10月总结", "9月总结"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
            }
        }else { // 在本地获取
            switch (index){
                case 0:
                    str1 = new String[]{"工作日志", "工作日志", "工作日志"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 1:
                    str1 = new String[]{"关于预防金融诈骗的建议", "关于预防虚假网络贷款的建议", "关于预防老年人被诈骗的建议"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 2:
                    str1 = new String[]{"关于预防金融诈骗的典型案件", "关于预防虚假网络贷款的典型案件", "关于预防老年人被诈骗的典型案件"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
                case 3:
                    str1 = new String[]{"11月总结", "10月总结", "9月总结"};
                    str2 = new String[]{"报送至：长沙县司法局", "报送至：长沙县司法局", "报送至：长沙县司法局"};
                    str3 = new String[]{"2017/12/01", "2017/11/01", "2017/10/01"};
                    break;
            }
        }
        mIServiceView.setTabSelection(index,str1,str2,str3);
    }




    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
}
