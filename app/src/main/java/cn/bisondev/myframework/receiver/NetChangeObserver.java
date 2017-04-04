package cn.bisondev.myframework.receiver;

import cn.bisondev.myframework.utils.NetStateUtils;

/**
 * 网络改变观察者，观察网络改变后回调的方法
 * Created by Basil on 2016/10/6.
 */

public interface NetChangeObserver {

    /**
     * 网络连接回调 type为网络类型
     */
    void onNetConnected(NetStateUtils.NetType type);

    /**
     * 没有网络
     */
    void onNetDisConnect();
}
