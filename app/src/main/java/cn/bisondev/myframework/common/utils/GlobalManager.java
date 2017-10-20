package cn.bisondev.myframework.common.utils;

/**
 * 存有建表语句的终极类
 * Created by Bison on 2016/9/13.
 */
public final class GlobalManager {

    public GlobalManager() {
    }

    /**
     * Users的终极类，拥有建表语句和键名
     */
    public static abstract class UserInfo {

        public static final String DATABASENAME = "userdatabse";        //数据库名
        public static final String TABLENAME = "usertable";             //用户表名

        public static final String ID = "id";                           //用户id
        public static final String NAME = "name";                       //用户名
        public static final String PHONE = "phone";                     //手机号
        public static final String PASSWORD = "password";               //密码
        public static final String TIME = "time";                       //时间戳
        public static final String LIVE = "live";
        public static final String SIGNATURE = "signature";
        public static final String TOKEN = "token";                     //本地令牌
        public static final String ONLYKEY = "key";

        public static final String CREATE_TABLE= "create table "+ TABLENAME +"("
                + ID + " integer primary key autoincrement,"
                + PHONE + " text,"
                + NAME + " text,"
                + PASSWORD +" text,"
                + TIME + " integer,"
                + TOKEN + " text,"
                + LIVE + " text,"
                + SIGNATURE + " text,"
                + ONLYKEY + " text)";
    }
}
