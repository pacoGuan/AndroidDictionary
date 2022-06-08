package com.maniu.maniuproxy;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class BaseActivity extends Activity {
//动态代理技术     IOC  ---》事件

//    IOC     控制反转   ----》会 动态代理
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjetUtils.inject(this);
    }
}
