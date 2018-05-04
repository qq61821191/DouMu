package com.cyl.doumu.index;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/22 0022$ 16:45$
 */
public class HotFragment extends BaseFragment implements HotContract.View{
    @BindView(R.id.rv)
    RecyclerView rv;
    private HotContract.Presenter mPresenter;
    private HotListAdapter mAdapter;
    private List<MovieEntry> mDatas;

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        ButterKnife.bind(this,view);
        mPresenter=new HotPresenter(this);

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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showHotListData(MovieListBean lists) {
        mAdapter=new HotListAdapter(getActivity(),lists.getSubjects());
        mDatas=lists.getSubjects();
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(itemClick);
    }

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
}
