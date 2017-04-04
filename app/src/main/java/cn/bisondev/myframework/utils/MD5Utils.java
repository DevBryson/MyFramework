package cn.bisondev.myframework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5码生成工具类
 * Created by Bison on 2016/9/27.
 */

public class MD5Utils {

    /**
     * 32位MD5码生成
     * @param str
     * @return
     */
    public static String ecode32(String str) {

        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(str.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 二次32位MD5码加密
     * @param str
     * @return
     */
    public static String ecodeTwice32(String str) {//MD5两次
        return ecode32(ecode32(str));
    }


    /**
     * 16位MD5码加密
     * @param str
     * @return
     */
    public static String ecode16(String str) {
        return (ecode32(str)).substring(8,24);
    }

    /**
     * 二次16位MD5码加密
     * @param str
     * @return
     */
    public static String ecodeTwice16(String str) {
        return ecode16(ecode16(str));
    }
}
