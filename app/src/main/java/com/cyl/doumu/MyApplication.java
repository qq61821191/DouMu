package com.cyl.doumu;

import android.app.Application;
import android.content.Context;

/**
 * @description: Application
 * @author: Cyl
 * @date: 2018-03-20  17:04
 * @version: V1.0
 */
public class MyApplication extends Application {
    private Context mContext;
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        instance=this;
    }

    public Context getContext() {
        return mContext;
    }

    public static MyApplication getInstance(){

        return instance;
    }
}
