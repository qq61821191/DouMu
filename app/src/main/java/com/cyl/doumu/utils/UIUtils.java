package com.cyl.doumu.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.cyl.doumu.MyApplication;
import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseActivity;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Created at 2015/7/27
 */
public class UIUtils {

    public static Context getContext() {
        return MyApplication.getInstance();
    }

    /**
     * 页面跳转
     *
     * @param intent
     */
    public static void startActivity(Intent intent) {
        // 如果不在activity里去打开activity  需要指定任务栈  需要设置标签
        if (BaseActivity.activity == null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            BaseActivity.activity.startActivity(intent);
        }
    }

    public static void toastData(String content){
        Toast.makeText(getContext(),content,Toast.LENGTH_LONG).show();
    }

}
