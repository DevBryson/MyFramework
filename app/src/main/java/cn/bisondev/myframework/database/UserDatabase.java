package cn.bisondev.myframework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.bisondev.myframework.utils.GlobalManager;

/**
 * SQLiteOpenHelper的继承子类，建立数据库
 * Created by Basil on 2016/9/13.
 */
public class UserDatabase extends SQLiteOpenHelper {

    public UserDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(GlobalManager.UserInfo.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
