package test.example.com.counselor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sli.D on 2017/6/19.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private View mView;//存储要获取组件的数组
    public ViewHolder(View view){
        super(view);
        this.mView = view;
    }

    public <T extends View> T getView(int viedId){
        return (T)this.mView.findViewById(viedId);
    }
}
