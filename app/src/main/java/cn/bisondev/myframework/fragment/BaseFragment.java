package cn.bisondev.myframework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.bisondev.myframework.R;

/**
 * 封装的Fragment基类
 * Created by Bison on 2017/4/3.
 */

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    public Toolbar mToolbar;
    public TextView mTvCenterTitle;
    public View view;
    //控件是否已经初始化
    private boolean isCreateView = false;

    public BaseFragment() { /* compiled code */ }

    protected AppCompatActivity context;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = (AppCompatActivity) getActivity();

        //如果有Bundle数据传来，则获取数据
        if (null != getArguments()) {
            getBundleExtras(getArguments());
        }

        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = initContentView(inflater, container, savedInstanceState);
        }

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        initTitle(view);

        initView(view);
        isCreateView = true;
        return view;
    }


    //此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreateView) {
            initLogic();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //第一个fragment会调用
        if (getUserVisibleHint())
            initLogic();
    }

    // 初始化UI setContentView
    protected abstract View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                            @Nullable Bundle savedInstanceState);

    /**
     * 初始化View的抽象方法
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 逻辑处理抽象方法
     */
    protected abstract void initLogic();

    /**
     * 获取Bundle信息
     *
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    /**
     * 初始化居中标题的TextView控件
     * @param view
     */
    protected void initTitle(View view) {
        mTvCenterTitle = (TextView) view.findViewById(R.id.tv_centerTitle);
    }

    /**
     * 设置居中标题的文字
     * @param string 字符
     */
    public void setCenterTitle(String string) {
        if(null != mTvCenterTitle) {
            mTvCenterTitle.setText(string);
        }
    }

    /**
     * 设置居中标题的文字
     *
     * @param id 字符资源id
     */
    public void setCenterTitle(int id) {
        if(null != mTvCenterTitle) {
            mTvCenterTitle.setText(id);
        }
    }

    /**
     * 显示短Toast
     *
     * @param string 字符
     */
    protected void showShortToast(String string) {
        if(context != null) {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示短Toast
     *
     * @param stringId 字符资源Id
     */
    protected void showShortToast(int stringId) {
        String string = getResources().getString(stringId);

        if(context != null) {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示长Toat
     *
     * @param string 字符
     */
    protected void showLongToast(String string) {
        if(context != null) {
            Toast.makeText(context, string, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 显示长Toast
     *
     * @param stringId 字符资源Id
     */
    protected void showLongToast(int stringId) {
        String string = getResources().getString(stringId);

        if(context != null) {
            Toast.makeText(context, string, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 启动一个Activity
     *
     * @param cls 目标Activity
     */
    protected void readyGo(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    /**
     * 启动一个Activity并携带Bundle
     *
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 启动一个Activity后finish本Activity
     *
     * @param cls 目标Activity
     */
    protected void readyGoThenKill(Class<?> cls) {
        readyGoThenKill(cls, null);
    }

    /**
     * 启动一个Activity后finish本Activity，并携带Bundle
     *
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> cls, Bundle bundle) {
        readyGo(cls, bundle);
        getActivity().finish();
    }

    /**
     * 启动一个Activity寻求返回值
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 启动一个Activity寻求返回值，并携带Bundle数据
     *
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}
