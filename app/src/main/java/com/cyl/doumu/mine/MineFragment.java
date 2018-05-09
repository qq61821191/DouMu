package com.cyl.doumu.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/9.
 */

public class MineFragment extends BaseFragment {
    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_top,container,false);

        return view;
    }

    @Override
    public void initData() {

    }
}
