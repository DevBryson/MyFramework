package cn.bisondev.myframework;

import android.os.Environment;

/**
 * Created by Bison on 2017/3/28.
 */

public class Config {

    //Crash文件存放路径
    public static final String CRASH_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashInfo/";
    //Crash文件后缀
    public static final String CRASH_FILE_NAME_SUFFIX = ".txt";
}
