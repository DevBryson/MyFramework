package cn.bisondev.myframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bison on 2016/9/26.
 */

public class StringUtils {

    /**
     * 提取出城市或者县
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district){
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }

    /**
     * 手机验证
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(14[57])|(17[0])|(17[7])|(18[0,0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * is null or its length is 0 or it is made by space
     * <p/>
     * <pre>
     * isBlank(null) = true;
     * isBlank("") = true;
     * isBlank("  ") = true;
     * isBlank("a") = false;
     * isBlank("a ") = false;
     * isBlank(" a") = false;
     * isBlank("a b") = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return
     * true, else return false.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }
}
