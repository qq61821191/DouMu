package com.cyl.doumu.index;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseFragment;
import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.index.adapter.HotListAdapter;
import com.cyl.doumu.index.adapter.UpComingListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/18.
 */

public class UpComingFragment extends BaseFragment implements UpComingContract.View {
    @BindView(R.id.rv)
    RecyclerView rv;
    private UpComingListAdapter mAdapter;
    private UpComingContract.Presenter mPresenter;

    @Override
    public void showUpcomingListData(MovieListBean data) {
        mAdapter=new UpComingListAdapter(getActivity(),data.getSubjects());
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(UpComingContract.Presenter presenter) {
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
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        ButterKnife.bind(this,view);
        mPresenter=new UpComingPresenter(this);
        return view;
    }

    @Override
    public void initData() {
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
