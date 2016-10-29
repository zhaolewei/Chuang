package com.zlw.mymodel.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 123 on 2016/6/28 0028.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initView();
        initData();
        bindEvent();
    }

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 绑定组件
     */
    protected abstract void initView();


}
