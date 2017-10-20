package cn.bisondev.myframework.common.wrapper;

import com.orhanobut.logger.Logger;

import cn.bisondev.myframework.BuildConfig;

/**
 * 封装的Log工具类
 * Author: Bison
 * Date: 2017/6/11
 * Gmail: bisonqin@gmail.com
 */
public class AppLog {

    private static final String TAG = "MyFramework";

    public static void init() {
        Logger.init(TAG);
    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.i(msg);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.d(msg);
        }
    }

    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.w(msg);
        }
    }

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.e(msg);
        }
    }

    public static void e(Throwable e) {
        if (BuildConfig.DEBUG) {
            Logger.e(e, "");
        }
    }
}
