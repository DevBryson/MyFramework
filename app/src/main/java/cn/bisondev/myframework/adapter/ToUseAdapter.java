package cn.bisondev.myframework.adapter;

import android.content.Context;

import java.util.List;

/**
 * 演示怎么用BaseRecyclerViewAdapter
 * Created by Bison on 2017/4/3.
 */

public class ToUseAdapter extends BaseRecyclerViewAdapter<String>{

    private List<String> datas;

    public ToUseAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    protected void bindData(BaseViewHolder holder, String data, int position) {
        //只要通过ViewHolder获取对应的空间就行了
        //TextView tv = holder.getView(R.id.item_tv);
        //tv.setText(data);
    }
}
