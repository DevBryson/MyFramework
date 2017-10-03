package cn.bisondev.myframework.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限相关工具类
 *
 * Created by Bison on 2017/5/31.
 */

public final class PermissionUtils {

    private static int mRequestCode = -1;

    private static OnPermissionListener mOnPermissionListener;

    public interface OnPermissionListener {

        //权限已授权
        void onPermissionGranted();

        //权限已被拒绝
        void onPermissionDenied(String[] deniedPermissions);
    }

    /**
     * 对权限进行UI界面的解释说明的处理辅助类
     */
    public abstract static class RationaleHandler {
        private Context context;
        private int requestCode;
        private String[] permissions;

        /**
         * 对权限进行解释说明的处理方法
         */
        protected abstract void showRationale();

        /**
         * 对权限进行解释说明的处理方法
         * @param context Activity的Context对象
         * @param requestCode 申请码
         * @param permissions 权限的数组
         */
        void showRationale(Context context, int requestCode, String[] permissions) {
            this.context = context;
            this.requestCode = requestCode;
            this.permissions = permissions;
            showRationale();
        }

        /**
         * 再次申请权限
         */
        @TargetApi(Build.VERSION_CODES.M)
        public void requestPermissionsAgain() {
            ((Activity) context).requestPermissions(permissions, requestCode);
        }
    }

    /**
     * 申请权限
     * @param context Activity的Context对象
     * @param requestCode 申请码
     * @param permissions 需要申请的权限数组
     * @param listener 权限申请结果的监听类
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(Context context, int requestCode
            , String[] permissions, OnPermissionListener listener) {
        requestPermissions(context, requestCode, permissions, listener, null);
    }

    /**
     * 申请权限
     * @param context Activity的Context对象
     * @param requestCode 申请码
     * @param permissions 需要申请的权限数组
     * @param listener 权限申请结果的监听类
     * @param handler
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(Context context, int requestCode
            , String[] permissions, OnPermissionListener listener, RationaleHandler handler) {
        if (context instanceof Activity) {
            mRequestCode = requestCode;
            mOnPermissionListener = listener;
            String[] deniedPermissions = getDeniedPermissions(context, permissions);
            if (deniedPermissions.length > 0) {
                //获取用户是否拒绝过此权限的申请（true:用户拒绝过（需要向用户说明）;false:用户没拒绝，直接申请）
                boolean rationale = shouldShowRequestPermissionRationale(context, deniedPermissions);
                if (rationale && handler != null) {
                    handler.showRationale(context, requestCode, deniedPermissions);
                } else {
                    ((Activity) context).requestPermissions(deniedPermissions, requestCode);
                }
            } else {
                if (mOnPermissionListener != null)
                    //权限已全部授权
                    mOnPermissionListener.onPermissionGranted();
            }
        } else {
            throw new RuntimeException("Context must be an Activity");
        }
    }

    /**
     * 请求权限结果，对应Activity中onRequestPermissionsResult()方法。
     * @param context Activity的Context对象
     * @param requestCode 申请码
     * @param permissions 权限的数组
     * @param grantResults 申请的结果
     */
    public static void onRequestPermissionsResult(Activity context, int requestCode,
                                                  String[] permissions, int[] grantResults) {
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mOnPermissionListener != null) {
                String[] deniedPermissions = getDeniedPermissions(context, permissions);
                if (deniedPermissions.length > 0) {
                    mOnPermissionListener.onPermissionDenied(deniedPermissions);
                } else {
                    mOnPermissionListener.onPermissionGranted();
                }
            }
        }
    }

    /**
     * 获取需要申请的权限
     * @param context Activity的Context对象
     * @param permissions 权限数组
     * @return 系统中还没有授权的权限数组
     */
    private static String[] getDeniedPermissions(Context context, String[] permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) ==
                    PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions.toArray(new String[deniedPermissions.size()]);
    }

    /**
     * 是否彻底拒绝了某项权限
     */
    public static boolean hasAlwaysDeniedPermission(Context context, String... deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRequestPermissionRationale(context, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否需要对申请的权限进行UI界面的提示说明
     * @param context Activity的Context对象
     * @param deniedPermissions 还没获得授权的权限数组
     * @return
     */
    private static boolean shouldShowRequestPermissionRationale(Context context, String... deniedPermissions) {
        //6.0以下无需UI界面的解释说明
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return false;
        boolean rationale;
        for (String permission : deniedPermissions) {
            rationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
            if (rationale)
                return true;
        }
        return false;
    }
}