package cn.bisondev.myframework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 封装的RecyclerViewAdapter类
 * Created by Bison on 2017/3/24.
 */

public abstract class BaseRecyclerViewAdapter<D> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<D> datas;
    private int layoutId;
    protected OnItemClickListner ItemClickListner;//单击事件
    protected OnItemLongClickListner ItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    /**
     *
     * @param context
     * @param datas RecyclerView所承载的信息
     * @param layoutId RecyclerView的Item的布局
     */
    public BaseRecyclerViewAdapter(Context context, List<D> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new BaseViewHolder(inflater.inflate(layoutId, parent, false));      //解析Item的布局文件
        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        bindData(holder, datas.get(position), position);                                   //视图和控件绑定

        //设置单击的监听
        if(ItemClickListner != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemClickListner.onItemClickListner(holder.itemView, position);
                }
            });
        }

        if(ItemLongClickListner != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ItemLongClickListner.onItemLongClickListner(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected abstract void bindData(BaseViewHolder holder, D data, int position);      //绑定数据的抽象方法

    public void setItemClickListner(OnItemClickListner itemClickListner) {
        this.ItemClickListner = itemClickListner;
    }

    public void setItemLongClickListner(OnItemLongClickListner itemLongClickListner) {
        this.ItemLongClickListner = itemLongClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}
