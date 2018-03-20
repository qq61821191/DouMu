package com.cyl.doumu.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.WindowManager;

import com.cyl.doumu.MyApplication;
import com.cyl.doumu.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;


/**
 * @description: Activity基类
 * @author: Cyl
 * @date: 2018-03-20  16:59
 * @version: V1.0
 */
public class BaseActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected MyApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(false);
        tintManager.setStatusBarTintResource(getStatusBarTintResource());

        application = (MyApplication) getApplication();

    }

    public int getStatusBarTintResource() {
        return R.color.colorPrimary;
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        final int bit = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(on) {
            layoutParams.flags |= bit;
        } else {
            layoutParams.flags &= ~bit;
        }
        getWindow().setAttributes(layoutParams);
    }

    protected void startApplicationSettingActivity() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }


}
