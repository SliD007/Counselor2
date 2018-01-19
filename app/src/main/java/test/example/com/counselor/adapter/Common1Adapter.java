package test.example.com.counselor.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

import test.example.com.counselor.base.MyLvClickListener;

import static test.example.com.counselor.adapter.ViewHolder1.getViewHolder;

/**
 * Created by Sli.D on 2018/1/2.
 */

public abstract class Common1Adapter<T> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int layoutId;
    //这里声明了两种回调响应
    //OnItemClickListenerListView自带属性
    AdapterView.OnItemClickListener onItemClickListener;
    //MyClickListener自定义响应接口（也可使用抽象类回调）
    MyLvClickListener mClickListener; //暂时用不到

    public Common1Adapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }

    public Common1Adapter(Context context, List<T> datas, int layoutId,AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.onItemClickListener = onItemClickListener;

    }

    public Common1Adapter(Context context, List<T> datas, int layoutId, MyLvClickListener myClickListener) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.mClickListener = myClickListener;
    }

    public Common1Adapter(Context context, List<T> datas, int layoutId,
                          MyLvClickListener myClickListener,AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.onItemClickListener = onItemClickListener;
        this.mClickListener = myClickListener;
    }

    @Override
    public int getCount() {
        return datas == null ? 1 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void initView(){

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取mViewHolder实例
        ViewHolder1 mViewHolder = getViewHolder(context,position,convertView,parent,layoutId);
//        ViewHolder1 mViewHolder = new ViewHolder1(context,position,parent,layoutId);
        T t = getItem(position);
        convertView(mViewHolder,convertView, t, position);
        return mViewHolder.getContertView();
    }
    /**
     * 需要去实现的对item中的view的设置操作
     * @param item
     * @param t
     */
    protected abstract void convertView(ViewHolder1 mViewHolder,View item, T t, int position);
}
