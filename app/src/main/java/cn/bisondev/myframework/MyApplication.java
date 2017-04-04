package cn.bisondev.myframework;

import android.app.Application;
import android.content.Context;

import cn.bisondev.myframework.receiver.NetStateReceiver;
import cn.bisondev.myframework.utils.CrashHandler;

/**
 * Created by Basil on 2016/9/18.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 通过继承Thread.UncaughtExceptionHandler，记录App发生异常的信息
         */
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        /**
         * 注册监听网络变化的BroadcastReceiver
         */
        NetStateReceiver.registerNetworkStateReceiver(this);
        mContext = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        /**
         * 当系统内存不足时，取消网络状态的广播的注册
         */
        NetStateReceiver.unRegisterNetworkStateReceiver(this);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static Context getContext() {
        return mContext;
    }
}
