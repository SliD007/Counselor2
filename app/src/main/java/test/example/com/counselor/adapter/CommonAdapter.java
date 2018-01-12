package test.example.com.counselor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import test.example.com.counselor.base.MyLvClickListener;

/**
 * Created by jianghejie on 15/11/26.
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {

    private Context context;
    private List<T> datas;
    private int layoutId;
    //这里声明了两种回调响应
    //OnItemClickListenerListView自带属性
    AdapterView.OnItemClickListener onItemClickListener;
    //MyClickListener自定义响应接口（也可使用抽象类回调）
    MyLvClickListener mClickListener; //暂时用不到


    public CommonAdapter() {
    }

    public CommonAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }

    public CommonAdapter(Context context, List<T> datas, int layoutId, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.onItemClickListener = onItemClickListener;

    }

    public CommonAdapter(Context context, List<T> datas, int layoutId, MyLvClickListener myClickListener) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.mClickListener = myClickListener;
//        Log.e("CommonAdapter","5");
    }

    public CommonAdapter(Context context, List<T> datas, int layoutId,
                          MyLvClickListener myClickListener, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.onItemClickListener = onItemClickListener;
        this.mClickListener = myClickListener;
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
//        Log.e("CommonAdapter","onBindViewHolder");
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        if (datas==null){
//            Log.e("datas","null");
            return 0;
        }else {
//            Log.e("datas",""+datas.size());
            return datas.size();
        }
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;//存储要获取组件的数组

        public ViewHolder(View view) {
            super(view);
            this.mView = view;
        }

        public <T extends View> T getView(int viedId) {
            return (T) this.mView.findViewById(viedId);
        }
    }
}





















