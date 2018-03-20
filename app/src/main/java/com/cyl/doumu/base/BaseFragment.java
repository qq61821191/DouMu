package com.cyl.doumu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * @description: Fragment基类
 * @author: Cyl
 * @date: 2018-03-20  15:33
 * @version: V1.0
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null) {
            mRootView = inflateView(inflater, container, savedInstanceState);
        }
        ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
        if(viewGroup != null) {
            viewGroup.removeView(mRootView);
        }
        return mRootView;
    }

    public abstract View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
