package com.cyl.doumu.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseFragment;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/22 0022$ 16:45$
 */
public class TopFragment extends BaseFragment {
    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_top,container,false);
        return view;
    }
}
