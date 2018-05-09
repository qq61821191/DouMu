package com.cyl.doumu.index;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseFragment;
import com.cyl.doumu.bean.MovieEntry;
import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.index.adapter.HotListAdapter;
import com.cyl.doumu.index.adapter.UpComingListAdapter;
import com.cyl.doumu.movice.MoviceInfoActivity;
import com.cyl.doumu.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/18.
 */

public class UpComingFragment extends BaseFragment implements UpComingContract.View {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    private UpComingListAdapter mAdapter;
    private UpComingContract.Presenter mPresenter;
    private List<MovieEntry> mDatas=new ArrayList<>();
    private int page=1;

    @Override
    public void showUpcomingListData(MovieListBean data) {
        mAdapter.setEnableLoadMore(true);
        mRefreshLayout.setRefreshing(false);
        if(data==null){
            mAdapter.loadMoreFail();
            return;
        }

        if(data.getSubjects()==null || data.getSubjects().size()==0){
            UIUtils.toastData("没有更多数据");
            mAdapter.loadMoreEnd(false);
            return;
        }
        if(page==1){
            mDatas.clear();
            mAdapter.setNewData(data.getSubjects());
        }else{
            mAdapter.addData(data.getSubjects());
        }
        mDatas.addAll(data.getSubjects());
        mAdapter.loadMoreComplete();
        page++;
    }

    private void initView(){
        mAdapter=new UpComingListAdapter(getContext(),mDatas);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(itemClick);
        mAdapter.setOnLoadMoreListener(requestLoadMoreListener);
        mRefreshLayout.setOnRefreshListener(onRefreshListener);
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
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
        mAdapter.loadMoreFail();
        mRefreshLayout.setRefreshing(false);
        UIUtils.toastData("数据获取失败");
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        ButterKnife.bind(this,view);
        initView();
        mPresenter=new UpComingPresenter(this);

        return view;
    }

    @Override
    public void initData() {
        mRefreshLayout.setRefreshing(true);
        mPresenter.subscribe();
    }

    BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener=new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            mPresenter.getUpComingData(page);
        }
    };

    BaseQuickAdapter.OnItemClickListener itemClick=new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            RelativeLayout rl=(RelativeLayout)view;
            ImageView img=rl.findViewById(R.id.iv_item_upcoming_list_img);
            BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
            Bitmap bitmap=bd.getBitmap();
            Intent intent=new Intent(getContext(),MoviceInfoActivity.class);
            Bundle b=new Bundle();
            b.putParcelable("img",bitmap);
            b.putSerializable("movieBean",mDatas.get(position));
            intent.putExtras(b);
            startActivity(intent);

        }
    };

    SwipeRefreshLayout.OnRefreshListener onRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mAdapter.setEnableLoadMore(false);
            page=1;
            mPresenter.getUpComingData(page);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
