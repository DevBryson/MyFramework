package cn.bisondev.myframework;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import cn.bisondev.myframework.receiver.NetStateReceiver;
import cn.bisondev.myframework.common.utils.CrashHandler;
import cn.bisondev.myframework.service.InitializeService;

/**
 * Created by Basil on 2016/9/18.
 */
public class MyApplication extends Application {

    public static Handler mHandler;
    public static int mainThreadID;

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

        mHandler = new Handler();            //handler
        mainThreadID = Process.myTid();     //主线程id

        InitializeService.start(this);
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

    public static Handler getHandler() {
        return mHandler;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadID() {
        return mainThreadID;
    }
}
