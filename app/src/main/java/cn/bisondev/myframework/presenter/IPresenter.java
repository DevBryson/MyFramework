package cn.bisondev.myframework.presenter;

import android.support.annotation.UiThread;

import cn.bisondev.myframework.view.IView;

/**
 * Created by Bison on 2017/9/17.
 */

public interface IPresenter<V extends IView> {

    @UiThread
    void attachView(V view);

    @UiThread
    void detachView();
}
