package cn.bisondev.myframework.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Presenter基类
 * Created by Basil on 2016/9/19.
 */
public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
