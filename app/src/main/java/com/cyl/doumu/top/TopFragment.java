package com.cyl.doumu.top;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseFragment;
import com.cyl.doumu.bean.MovieEntry;
import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.movice.MoviceInfoActivity;
import com.cyl.doumu.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * @Description: 榜单界面
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public class TopFragment extends BaseFragment implements TopContract.View  {

    @BindView(R.id.rv_top_type)
     RecyclerView rv_type;

    @BindView(R.id.rv_top_content)
     RecyclerView rv_content;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    private TopTypeListAdapter mTypeAdapter;

    private TopContract.Presenter mPresenter;

    private TopDataListAdapter mListAdapter;

    private List<MovieEntry> mDatas=new ArrayList<>();

    private int curTypePosition=0;

    private int page=1;

    //显示分类列表
    @Override
    public void showType(List<String> data) {
        if(mTypeAdapter==null){
            mTypeAdapter=new TopTypeListAdapter(getContext(),data);
            mTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mTypeAdapter.setCurPos(position);
                    curTypePosition=position;
                    mRefreshLayout.setRefreshing(true);
                    refreshData();
                }
            });
            rv_type.setAdapter(mTypeAdapter);
            return;
        }
        mTypeAdapter.setNewData(data);
    }

    //数据展现
    @Override
    public void showData(MovieListBean data) {
        mListAdapter.setEnableLoadMore(true);
        mRefreshLayout.setRefreshing(false);
        if(data==null){
            mListAdapter.loadMoreFail();
            return;
        }
        if(data.getSubjects()==null || data.getSubjects().size()==0){
            UIUtils.toastData("没有更多数据");
            mListAdapter.loadMoreEnd(false);
            return;
        }
        if(page==1){
            mDatas.clear();
            mListAdapter.setNewData(data.getSubjects());
        }else{
            mListAdapter.addData(data.getSubjects());
        }
        mDatas.addAll(data.getSubjects());
        mListAdapter.loadMoreComplete();
        page++;
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
        mRefreshLayout.setRefreshing(false);
        mListAdapter.loadMoreFail();
        UIUtils.toastData("数据获取失败");
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_top,container,false);
        ButterKnife.bind(this,view);
        mPresenter=new TopPresenter(this);
        initView();
        return view;
    }

    private void initView(){
        rv_type.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_type.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        rv_content.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_content.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mListAdapter=new TopDataListAdapter(getContext(),mDatas);
        rv_content.setAdapter(mListAdapter);
        mListAdapter.setOnItemClickListener(itemClick);
        mListAdapter.setOnLoadMoreListener(requestLoadMoreListener);
        mRefreshLayout.setOnRefreshListener(onRefreshListener);
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }

    @Override
    public void initData() {
        mRefreshLayout.setRefreshing(true);
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    //加载更多监听事件
    BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener=new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            switch (curTypePosition){
                case 0:
                    mPresenter.getTop250(page);
                    break;
                case 1:
                    mPresenter.getWeekly(page);
                    break;
                case 2:
                    mPresenter.getBestNew(page);
                    break;
                case 3:
                    mPresenter.getUsBox(page);
                    break;
            }
        }
    };

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

    //刷新事件
    SwipeRefreshLayout.OnRefreshListener onRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
        refreshData();
        }
    };

    //刷新具体实现
    private void refreshData(){
        mListAdapter.setEnableLoadMore(false);
        page=1;
        switch (curTypePosition){
            case 0:
                mPresenter.getTop250(page);
                break;
            case 1:
                mPresenter.getWeekly(page);
                break;
            case 2:
                mPresenter.getBestNew(page);
                break;
            case 3:
                mPresenter.getUsBox(page);
                break;
        }
    }
}
