package test.example.com.counselor.view.schedule;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.example.com.counselor.R;
import test.example.com.counselor.adapter.CommonAdapter;
import test.example.com.counselor.adapter.ViewHolder;
import test.example.com.counselor.entity.ListEntity;
import test.example.com.counselor.listener.MyLvClickListener;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class ScheduleAdapter extends CommonAdapter {

    //这里声明了两种回调响应
    //OnItemClickListenerListView自带属性
    AdapterView.OnItemClickListener onItemClickListener;
    //MyClickListener自定义响应接口（也可使用抽象类回调）
    MyLvClickListener mClickListener; //暂时用不到

    public ScheduleAdapter(Context context, List<ListEntity> list) {
        super(context, list);
    }

    public ScheduleAdapter(Context context, List<ListEntity> list, MyLvClickListener myClickListener,
                        AdapterView.OnItemClickListener onItemClickListener) {
        //回调父类构造方法
        super(context, list);
        this.onItemClickListener = onItemClickListener;
        this.mClickListener = myClickListener;
    }
    @Override
    public void convert(ViewHolder mViewHolder, ListEntity mEntity, int position) {
        ImageView leftImg = (ImageView) mViewHolder.getView(R.id.leftImg);
        TextView tv1 = (TextView) mViewHolder.getView(R.id.itemTv1);
        TextView tv2 = (TextView) mViewHolder.getView(R.id.itemTv2);
        TextView tv3 = (TextView) mViewHolder.getView(R.id.itemTv3);
        tv1.setText(mEntity.getName());
        tv2.setText(mEntity.getFrom());
        tv3.setText(mEntity.getTime());
        leftImg.setTag(position);
        leftImg.setOnClickListener(mClickListener);
    }
}
