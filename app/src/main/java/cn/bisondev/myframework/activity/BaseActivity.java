package cn.bisondev.myframework.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import cn.bisondev.myframework.R;

/**
 * 封装普通的BaseActivity
 * Created by Bison on 2017/4/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    public BaseActivity() { /* compiled code */ }

    protected BaseActivity context;

    protected Toolbar mToolbar;
    protected TextView mTvCenterTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);//通知栏所需颜色
        }*/

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        context = this;
        setContentView(provideContentViewId());     //设置布局文件

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            //先设定一个无标题的Toolbar
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initTitle();
        }

        initView();
        initLogic();
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化逻辑代码
     */
    protected abstract void initLogic();

    /**
     * 引入布局文件的ID
     * @return
     */
    protected abstract int provideContentViewId();//用于引入布局文件

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    protected void initTitle() {
        //  设置Toolbar title文字颜色
        mToolbar.setTitleTextColor(Color.WHITE);
        //设置NavigationIcon
        mToolbar.setNavigationIcon(R.mipmap.navigation_back_white);
        // 设置navigation button 点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTvCenterTitle = (TextView) findViewById(R.id.tv_centerTitle);
    }

    /**
     * 设置居中标题的文字
     * @param string 字符
     */
    public void setCenterTitle(String string) {
        mTvCenterTitle.setText(string);
    }

    /**
     * 设置居中标题的文字
     * @param id 字符的资源Id
     */
    public void setCenterTitle(int id) {
        mTvCenterTitle.setText(id);
    }

    /**
     * 设置状态栏透明状态
     * @param flag 是否设置状态栏透明
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean flag) {
        Window win = getWindow();
        //获取WindowManager.LayoutParams对象
        WindowManager.LayoutParams winParams = win.getAttributes();
        //获取状态栏透明状态的Flag
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (flag) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 显示短Toast
     * @param string 字符
     */
    protected void showShortToast(String string) {
        Toast.makeText(getBaseContext(), string, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示短Toast
     * @param stringId 字符资源ID
     */
    protected void showShortToast(int stringId) {
        String string = getResources().getString(stringId);
        Toast.makeText(getBaseContext(), string, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长Toast
     * @param string 字符
     */
    protected void showLongToast(String string) {
        Toast.makeText(getBaseContext(), string, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示长Toast
     * @param stringId 字符资源Id
     */
    protected void showLongToast(int stringId) {
        String string = getResources().getString(stringId);
        Toast.makeText(getBaseContext(), string, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 界面跳转
     * @param cls 目标Activity
     */
    protected void readyGo(Class<?> cls) {
        readyGo(cls, null);
    }

    /**
     * 跳转界面，传参
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转界面并关闭当前界面
     * @param cls 目标Activity
     */
    protected void readyGoThenKill(Class<?> cls) {
        readyGoThenKill(cls, null);
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> cls, Bundle bundle) {
        readyGo(cls, bundle);
        finish();
    }

    /**
     * startActivityForResult
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}