package cn.bisondev.myframework.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.facebook.stetho.Stetho;

import cn.bisondev.myframework.BuildConfig;
import cn.bisondev.myframework.common.utils.FileUtils;
import cn.bisondev.myframework.common.wrapper.AppLog;

/**
 * 初始化Application的辅助服务
 * Author: Bison
 * Date: 2017/6/11
 * Email: bisonqin@gmail.com
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT_WHEN_APP_CREATE = "cn.bisondev.myframework.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (null == intent)
            return;
        String action = intent.getAction();
        if (TextUtils.equals(action, ACTION_INIT_WHEN_APP_CREATE))
            performInit();
    }

    private void performInit() {
        AppLog.init();

        // 检查创建文件夹
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getExternalFilesDirs(null);
        }
        FileUtils.createOrExistsDir(Environment.getExternalStorageDirectory() + "/" + getPackageName());

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
