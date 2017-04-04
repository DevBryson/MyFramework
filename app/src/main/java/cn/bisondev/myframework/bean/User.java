package cn.bisondev.myframework.bean;

/**
 * User的实体类
 * Created by Bison on 2016/9/13.
 */
public class User {

    private String mPassword;
    private String mName;
    private String mPhone;
    private int mTime;
    private String mToken;
    private String mLive;

    private String mSignature;

    public User(String phone, String name, String password, int time, String token) {
        mPhone = phone;
        mName = name;
        mPassword = password;
        mTime = time;
        mToken = token;
    }
    public User(String phone, String name, String password, int time, String token, String live, String signature) {
        mPhone = phone;
        mName = name;
        mPassword = password;
        mTime = time;
        mToken = token;
        mLive = live;
        mSignature = signature;
    }

    public void setName(String name) {
        this.mName = name;
    }
    public String getName() {
        return mName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int mTime) {
        this.mTime = mTime;
    }

    public String getLive() {
        return mLive;
    }

    public void setLive(String mLive) {
        this.mLive = mLive;
    }

    public String getSignature() {
        return mSignature;
    }

    public void setSignature(String mSignature) {
        this.mSignature = mSignature;
    }
}
