package cn.bisondev.myframework.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * ScrollView添加滑动监听
 * Created by Basil on 2016/10/8.
 */

public class ListenScrollView extends ScrollView {
    private OnScrollChangedListener mOnScrollChangedListener;

    public ListenScrollView(Context context) {
        super(context);
    }

    public ListenScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }
}
