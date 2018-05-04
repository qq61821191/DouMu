package com.cyl.doumu.top;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseFragment;
import com.cyl.doumu.bean.MovieListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/19.
 */

public class TopFragment extends BaseFragment implements TopContract.View  {

    @BindView(R.id.rv_top_type)
     RecyclerView rv_type;

    @BindView(R.id.rv_top_content)
     RecyclerView rv_content;

    private TopTypeListAdapter mTypeAdapter;

    private TopContract.Presenter mPresenter;

    private TopDataListAdapter mListAdapter;

    private int curTypePosition=0;

    @Override
    public void showType(List<String> data) {
        if(mTypeAdapter==null){
            mTypeAdapter=new TopTypeListAdapter(getContext(),data);
            mTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mTypeAdapter.setCurPos(position);
                }
            });
            rv_type.setAdapter(mTypeAdapter);
            return;
        }
        mTypeAdapter.setNewData(data);
    }

    @Override
    public void showTop250Data(MovieListBean data) {
        if(mListAdapter==null){
            mListAdapter=new TopDataListAdapter(getContext(),data.getSubjects());
            rv_content.setAdapter(mListAdapter);
            return;
        }
        mListAdapter.setNewData(data.getSubjects());
    }

    @Override
    public void showUsBox(MovieListBean data) {

    }

    @Override
    public void showWeekly(MovieListBean data) {

    }

    @Override
    public void showBestNewData(MovieListBean data) {

    }

    @Override
    public void setPresenter(TopContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_top,container,false);
        ButterKnife.bind(this,view);
        mPresenter=new TopPresenter(this);
        rv_type.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_type.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        rv_content.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_content.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void initData() {
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }


}
