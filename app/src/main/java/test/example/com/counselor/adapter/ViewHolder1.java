package test.example.com.counselor.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Sli.D on 2017/6/19.
 */

public class ViewHolder1 {
    private SparseArray<View> mView;//存储要获取组件的数组
    private int position;
    private View mContertView;//装载组件的容器
    public ViewHolder1(Context context, int position, ViewGroup parent, int layoutId){

        this.position = position;
        this.mView = new SparseArray<View>();
        mContertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mContertView.setTag(this);
    }

    public static <T extends View> ViewHolder1 getViewHolder(Context context, int position, View convertView,
                                                             ViewGroup parent, int layoutId){
        /*
         * 仅适用于同布局
         * 判断是否首次调用getViewHolder
         */
        if(convertView==null){
            return new ViewHolder1(context,position,parent,layoutId);
        }else{
            return (ViewHolder1) convertView.getTag();
        }
    }
    //获取item内组件
    public <T extends View> T getView(int viedId){
        View view = mView.get(viedId);
        if(view==null){
            view = mContertView.findViewById(viedId);
            mView.put(viedId,view);
        }
        return (T)view;
    }
    //返回已加载mContertView实例
    public View getContertView(){
        return mContertView;
    }
}
