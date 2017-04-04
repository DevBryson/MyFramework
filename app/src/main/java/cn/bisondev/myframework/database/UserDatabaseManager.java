package cn.bisondev.myframework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.bisondev.myframework.bean.User;
import cn.bisondev.myframework.utils.GlobalManager;

/**
 *
 * Created by Bison on 2016/9/13.
 */
public class UserDatabaseManager {

    private Context mContext;
    private static UserDatabaseManager mManager;
    private UserDatabase mDatabase;
    private static SQLiteDatabase mSQLiteDatabase;
    private UserDatabaseManager(Context context) {
        mContext = context;
        mDatabase = new UserDatabase(mContext, GlobalManager.UserInfo.DATABASENAME, null, 1);
        mSQLiteDatabase = mDatabase.getWritableDatabase();
    }
    public static UserDatabaseManager newInstance(Context context) {
        if (mManager == null) {
            mManager = new UserDatabaseManager(context);
        }
        return mManager;
    }
    /**
     * 记住User的数据，存入数据库的表
     * @param user
     */
    public static void Remember(User user) {
        mSQLiteDatabase.execSQL("insert into " + GlobalManager.UserInfo.TABLENAME +
                "(" + GlobalManager.UserInfo.PHONE + ","
                + GlobalManager.UserInfo.NAME + ","
                + GlobalManager.UserInfo.PASSWORD + ","
                + GlobalManager.UserInfo.TIME + ","
                + GlobalManager.UserInfo.TOKEN + ") values('"
                + user.getPhone() + "','"
                + user.getName() + "','"
                + user.getPassword() + "','"
                + user.getTime() + "','"
                + user.getToken() + "')"
        );
        Log.e("RemenberUser","成功");
    }
    /**
     * 获取最近用户的数据，返回数据库第一个User的数据
     * @return
     */
    public static User getRecent() {
        //ASC表示升序排序，DESC表示降序
        Cursor cursor = mSQLiteDatabase.query(GlobalManager.UserInfo.TABLENAME, null, null, null, null, null, "TIME ASC");
        cursor.moveToLast();
        if (cursor.getCount() != 0){
            String phone = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.PHONE));
            if (TextUtils.isEmpty(phone)){
                return null;
            } else {
                User user = new User(phone, "", "", 0, "", "", "");
                return user;
            }
        }
        return null;
    }
    /**
     * 获取用户名
     * @param user
     */
    public static String getName(User user) {
        User mUser = QueryByReccentPhone(user.getPhone());
        return mUser.getName();
    }
    /**
     * 获取密码
     * @param user
     */
    public static String getPassword(User user) {
        User mUser = QueryByReccentPhone(user.getPhone());
        return mUser.getPassword();
    }
    /**
     * 获取时间戳
     * @param user
     */
    public static int getTime(User user) {
        User mUser = QueryByReccentPhone(user.getPhone());
        return mUser.getTime();
    }
    /**
     * 获取Token
     * @param user
     */
    public static String getToken(User user) {
        User mUser = QueryByReccentPhone(user.getPhone());
        return mUser.getToken();
    }
    /**
     * 获取Live
     * @param user
     */
    public static String getLive(User user) {
        User mUser = QueryByReccentPhone(user.getPhone());
        return mUser.getLive();
    }
    /**
     * 获取用户名
     * @param user
     */
    public static String getSignature(User user) {
        User mUser = QueryByReccentPhone(user.getPhone());
        return mUser.getSignature();
    }
    /**
     * 更新User的手机号
     * @param user
     */
    public static void updatePhone(User user,String phone) {
        mSQLiteDatabase.execSQL("UPDATE " + GlobalManager.UserInfo.TABLENAME
                + " SET " + GlobalManager.UserInfo.PHONE + "=" + "'" + user.getPhone() + "'"
                + " WHERE " + GlobalManager.UserInfo.PHONE + "=" + "'" + phone + "'");       //因为修改手机号，所以通过旧的手机号定位
    }
    /**
     * 更新User的用户名
     * @param user
     */
    public static void updateName(User user) {
        mSQLiteDatabase.execSQL("update " + GlobalManager.UserInfo.TABLENAME
                + " set " + GlobalManager.UserInfo.NAME + "=" + "'" + user.getName() + "'"
                + " where " + GlobalManager.UserInfo.PHONE + "=" +  "'" + user.getPhone() + "'");
    }
    /**
     * 更新User的密码
     * @param user
     */
    public static void updatePassword(User user) {
        mSQLiteDatabase.execSQL("UPDATE " + GlobalManager.UserInfo.TABLENAME
                + " SET " + GlobalManager.UserInfo.PASSWORD + "=" +  "'" + user.getPassword() + "'"
                + " WHERE " + GlobalManager.UserInfo.PHONE + "=" + "'" + user.getPhone() + "'");
    }
    /**
     * 更新User的Time
     * @param user
     */
    public static void updateTime(User user) {
        mSQLiteDatabase.execSQL("UPDATE " + GlobalManager.UserInfo.TABLENAME
                + " SET " + GlobalManager.UserInfo.TIME + "=" +  "'" + user.getTime() + "'"
                + " WHERE " + GlobalManager.UserInfo.PHONE + "=" +  "'" + user.getPhone() + "'");
    }
    /**
     * 更新User的Token
     * @param user
     */
    public static void updateToken(User user) {
        mSQLiteDatabase.execSQL("UPDATE " + GlobalManager.UserInfo.TABLENAME
                + " SET " + GlobalManager.UserInfo.TOKEN + "=" + "'" + user.getToken() + "'"
                + " WHERE " + GlobalManager.UserInfo.PHONE + "=" + "'" + user.getPhone() + "'");
    }
    /**
     * 更新User的Live
     * @param user
     */
    public static void updateLive(User user) {
        mSQLiteDatabase.execSQL("UPDATE " + GlobalManager.UserInfo.TABLENAME
                + " SET " + GlobalManager.UserInfo.LIVE + "=" + "'" + user.getLive() + "'"
                + " WHERE " + GlobalManager.UserInfo.PHONE + "=" + "'" + user.getPhone() + "'");
    }
    /**
     * 更新User的Signature
     * @param user
     */
    public static void updateSignature(User user) {
        mSQLiteDatabase.execSQL("UPDATE " + GlobalManager.UserInfo.TABLENAME
                + " SET " + GlobalManager.UserInfo.SIGNATURE + "=" + "'" + user.getSignature() + "'"
                + " WHERE " + GlobalManager.UserInfo.PHONE + "=" + "'" + user.getPhone() + "'");
    }
    /**
     * 删除User的数据
     * @param user
     */
    public static void deleteUser(User user) {
        mSQLiteDatabase.delete(GlobalManager.UserInfo.TABLENAME, GlobalManager.UserInfo.PHONE + " = ?", new String[]{user.getPhone()});
        mSQLiteDatabase.execSQL("DELETE FROM " + GlobalManager.UserInfo.TABLENAME
                + " WHERE " + GlobalManager.UserInfo.PHONE + "=" + "'" + user.getPhone() + "'");
    }
    /**
     * 通过手机号搜索
     * @param phone
     * @return
     */
    public static User QueryByReccentPhone(String phone) {

        //拉到外面，避免重复创建对象
        String name;
        String password;
        int time;
        String token;
        String live;
        String signature;

        User user;

        Cursor cursor = mSQLiteDatabase.query(GlobalManager.UserInfo.TABLENAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            if (cursor.getCount() != 0) {
                do {
                    String mPhone = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.PHONE));
                    if (mPhone.equals(phone)) {
                    /*
                    查找到数据后，填充到User实体类，取出数据
                     */
                        name = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.NAME));
                        password = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.PASSWORD));
                        time = cursor.getInt(cursor.getColumnIndex(GlobalManager.UserInfo.TIME));
                        token = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.TOKEN));
                        live = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.LIVE));
                        signature = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.SIGNATURE));
                        user = new User(mPhone, name, password, time, token, live, signature);
                        return user;
                    }
                } while (cursor.moveToNext());
            }
        }
        return null;
    }
    /**
     * 通过名字搜索
     * @param name
     * @return
     */
    public static User QueryByReccentName(String name) {
        //拉到外面，避免重复创建对象
        String phone;
        String password;
        int time;
        String token;
        String live;
        String signature;

        User user;

        Cursor cursor = mSQLiteDatabase.query(GlobalManager.UserInfo.TABLENAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            if (cursor.getCount() != 0) {
                do {
                    String mPhone = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.NAME));
                    if (mPhone.equals(name)) {
                    /*
                    查找到数据后，填充到User实体类，取出数据
                     */
                        phone = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.PHONE));
                        password = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.PASSWORD));
                        time = cursor.getInt(cursor.getColumnIndex(GlobalManager.UserInfo.TIME));
                        token = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.TOKEN));
                        live = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.LIVE));
                        signature = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.SIGNATURE));
                        user = new User(phone, name, password, time, token, live, signature);
                        return user;
                    }
                } while (cursor.moveToNext());
            }
        }
        return null;
    }
    /**
     * 在User表中是否存在该手机号的账户
     * @param phone
     * @return
     */
    public static boolean isUserPhoneExist(String phone) {
        String mPhone;
        Cursor cursor = mSQLiteDatabase.query(GlobalManager.UserInfo.TABLENAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            if (cursor.getCount() != 0) {
                do {
                    mPhone = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.PHONE));
                    if (mPhone.equals(phone)) {
                        return true;
                    }
                } while (cursor.moveToNext());
            }
        }
        return false;
    }

    /**
     * 查询所有本地数据库的手机号码
     * @return
     */
    public static List<String> searchAllPhone() {
        //创建承接手机号码的列表
        List<String> phoneList = new ArrayList<>();
        String phone;

        //按照时间顺序排列结果
        Cursor cursor = mSQLiteDatabase.query(GlobalManager.UserInfo.TABLENAME, null, null, null, null, null, "TIME ASC");
        if (cursor.moveToFirst()) {
            if (cursor.getCount() != 0) {
                do {
                    //获取当前位置的手机号
                    phone = cursor.getString(cursor.getColumnIndex(GlobalManager.UserInfo.PHONE));
                    //放进集合中存储
                    phoneList.add(phone);
                } while (cursor.moveToNext());
            }
        }
        return phoneList.isEmpty() ? null : phoneList;
    }
}

