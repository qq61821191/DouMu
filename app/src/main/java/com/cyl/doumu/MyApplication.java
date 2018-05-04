package com.cyl.doumu;

import android.app.Application;
import android.content.Context;

import com.cyl.doumu.base.BaseActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @description: Application
 * @author: Cyl
 * @date: 2018-03-20  17:04
 * @version: V1.0
 */
public class MyApplication extends Application {
    private Context mContext;
    private static MyApplication instance;
    /**
     * Activity集合，来管理所有的Activity
     */
    private static List<BaseActivity> activities;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        activities = new LinkedList<>();
        instance=this;
    }

    public Context getContext() {
        return mContext;
    }

    public static MyApplication getInstance(){

        return instance;
    }

    /**
     * 添加一个Activity
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    /**
     * 结束一个Activity
     *
     * @param activity
     */
    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    /**
     * 结束当前所有Activity
     */
    public static void clearActivities() {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应运程序
     */
    public static void quiteApplication() {
        clearActivities();
        System.exit(0);
    }
}
