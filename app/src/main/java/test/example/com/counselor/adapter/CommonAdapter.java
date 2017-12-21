package test.example.com.counselor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import test.example.com.counselor.entity.ListEntity;

import static test.example.com.counselor.adapter.ViewHolder.getViewHolder;

/**
 * Created by Sli.D on 2017/12/21.
 */

public abstract class CommonAdapter extends BaseAdapter {
    //自定义适配器三要素：Viewholder、List<Entity>、Listener
    ViewHolder mViewHolder;
    List<ListEntity> mEntityList;
    ListEntity mEntity;

    LayoutInflater mInflater;
    Context context;
    public CommonAdapter(Context context, List<ListEntity> list){
        this.context = context;
        this.mEntityList = list;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mEntityList.size();
    }

    @Override
    public ListEntity getItem(int position) {
        return mEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取mViewHolder实例
        ViewHolder mViewHolder = getViewHolder(context,position,convertView,parent,getItem(position));
        convert(mViewHolder,getItem(position),position);
        //一定注意这里返回的方式，我就遗忘这里不止一次造成空指针
        return mViewHolder.getContertView();
    }
    //获取item组件的抽象方法，子类继承实现
    public abstract void convert(ViewHolder mViewHolder, ListEntity mEntity, int position);

}
