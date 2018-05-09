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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseFragment;
import com.cyl.doumu.bean.MovieEntry;
import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.index.adapter.HotListAdapter;
import com.cyl.doumu.movice.MoviceInfoActivity;
import com.cyl.doumu.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * @Description: 正在热映的fragment
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/22 0022$ 16:45$
 */
public class HotFragment extends BaseFragment implements HotContract.View{
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;


    private HotContract.Presenter mPresenter;
    private HotListAdapter mAdapter;
    private List<MovieEntry> mDatas=new ArrayList<>();
    private int page=1;

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        ButterKnife.bind(this,view);
        initView();
        mPresenter=new HotPresenter(this);

        return view;
    }

    //初始化界面
    private void initView(){
        mAdapter=new HotListAdapter(getContext(),mDatas);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(itemClick);
        mAdapter.setOnLoadMoreListener(requestLoadMoreListener);
        mRefreshLayout.setOnRefreshListener(onRefreshListener);
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }

    //初始化数据
    @Override
    public void initData() {
        mRefreshLayout.setRefreshing(true);
        mPresenter.subscribe();
    }

    //加载更多事件监听
    BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener=new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            mPresenter.getHotData(page);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    //数据绑定
    @Override
    public void showHotListData(MovieListBean lists) {
        mAdapter.setEnableLoadMore(true);
        mRefreshLayout.setRefreshing(false);
        if(lists==null){
            mAdapter.loadMoreFail();
            return;
        }

        if(lists.getSubjects()==null || lists.getSubjects().size()==0){
            UIUtils.toastData("没有更多数据");
            mAdapter.loadMoreEnd(false);
            return;
        }
        if(page==1){
            mDatas.clear();
            mAdapter.setNewData(lists.getSubjects());
        }else{
            mAdapter.addData(lists.getSubjects());
        }
        mDatas.addAll(lists.getSubjects());
        mAdapter.loadMoreComplete();
        page++;
    }

    //item点击事件
    BaseQuickAdapter.OnItemClickListener itemClick=new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            LinearLayout ll=(LinearLayout)view;
            ImageView img=ll.findViewById(R.id.iv_item_hot_list_img);
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

    @Override
    public void showError(String err) {
        mAdapter.loadMoreFail();
        mRefreshLayout.setRefreshing(false);
        UIUtils.toastData("数据获取失败");
    }

    @Override
    public void showEmpty() {

    }



    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(HotContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    //刷新事件
    SwipeRefreshLayout.OnRefreshListener onRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mAdapter.setEnableLoadMore(false);
            page=1;
            mPresenter.getHotData(page);
        }
    };


}
