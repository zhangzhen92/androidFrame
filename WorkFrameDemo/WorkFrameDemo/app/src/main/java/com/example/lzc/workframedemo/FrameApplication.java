package com.example.lzc.workframedemo;

import android.app.Application;

/**
 * 类描述：
 * 创建人：zz
 * 创建时间： 2016/11/24 16:18
 */


public class FrameApplication extends Application{
    public static FrameApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
