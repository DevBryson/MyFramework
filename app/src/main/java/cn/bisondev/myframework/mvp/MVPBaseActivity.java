package cn.bisondev.myframework.mvp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cn.bisondev.myframework.receiver.NetChangeObserver;
import cn.bisondev.myframework.receiver.NetStateReceiver;
import cn.bisondev.myframework.utils.NetStateUtils;
import cn.bisondev.myframework.utils.ToastUtils;

/**
 * Created by Bison on 2016/9/21.
 */

public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    protected Toolbar mToolbar;
    private SwipeRefreshLayout mRefreshLayout;
    /**
     * 网络观察者
     */
    protected NetChangeObserver mNetChangeObserver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetChangeObserver();
        //允许为空，不是所有都要实现MVP模式
        if(createPresenter()!=null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        setContentView(provideContentViewId());//布局

        //TODO to find toolbar
        //mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar); //把Toolbar当做ActionBar给设置
            if (canBack()) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null)
                    actionBar.setDisplayHomeAsUpEnabled(true);//设置ActionBar一个返回箭头，主界面没有，次级界面有
            }
        }

        if (isSetRefresh()) {
            setupSwipeRefresh();
        }
    }

    private void initNetChangeObserver() {
        // 网络改变的一个回掉类
        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetStateUtils.NetType type) {

            }

            @Override
            public void onNetDisConnect() {
                ToastUtils.showToast(getBaseContext(),"网络未连接");
            }
        };

        //开启广播去监听 网络 改变事件
        NetStateReceiver.registerObserver(mNetChangeObserver);
    }

    private void setupSwipeRefresh() {
        //TODO to find SwipeRefreshLayout
        //mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        if(mPresenter!=null) {
            mPresenter.detachView();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 此时android.R.id.home即为返回箭头
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 判断当前 Activity 是否允许返回
     * 主界面不允许返回，次级界面允许返回
     *
     * @return false
     */
    public boolean canBack() {
        return false;
    }

    /**
     * 判断子Activity是否需要刷新功能
     *
     * @return false
     */
    public boolean isSetRefresh() {
        return false;
    }

    protected abstract T createPresenter();

    abstract protected int provideContentViewId();//用于引入布局文件

}