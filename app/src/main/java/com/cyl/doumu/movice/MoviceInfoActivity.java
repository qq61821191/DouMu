package com.cyl.doumu.movice;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseActivity;
import com.cyl.doumu.bean.Cast;
import com.cyl.doumu.bean.Images;
import com.cyl.doumu.bean.MovieEntry;
import com.cyl.doumu.utils.Blur;
import com.cyl.doumu.utils.UIUtils;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
 * @Description: 电影详情界面
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/7
 */
public class MoviceInfoActivity extends BaseActivity implements MoviceInfoContract.View{
    @BindView(R.id.iv_details_img)
    ImageView iv_img;
    @BindView(R.id.iv_details_bg)
    ImageView iv_bg;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingLayout;
    @BindView(R.id.expand_text_view)
    ExpandableTextView etvContent;
    @BindView(R.id.rv_movie_info_casts)
    RecyclerView rv_casts;

    @BindView(R.id.tv_movie_info_original_name)
    TextView tvOriginalName;
    @BindView(R.id.tv_movie_info_country)
    TextView tvCountryAndYear;
    @BindView(R.id.tv_movie_info_types)
    TextView tvTypes;
    @BindView(R.id.tv_movie_info_collection_num)
    TextView tvCollectionNum;
    @BindView(R.id.tv_movie_info_score)
    TextView tvScore;
    @BindView(R.id.tv_movie_info_score_num)
    TextView tvScoreNum;
    @BindView(R.id.ll_movie_info_data)
    LinearLayout llData;

    private MovieEntry mEntry;
    private MoviceInfoContract.Presenter mPresenter;
    private MovieCastsAdapter mCastsAdapter;
    private List<Cast> casts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movice_info);
        ButterKnife.bind(this);
        initView();
        mPresenter=new MovieInfoPresenter(this);

    }

    //初始化界面
    private void initView(){
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationIcon(AppCompatResources.getDrawable(this, R.drawable.ic_action_navigation_arrow_back));
        mEntry=(MovieEntry) getIntent().getSerializableExtra("movieBean");
        mCollapsingLayout.setTitle(mEntry.getTitle());
        Bitmap movice_img = getIntent().getParcelableExtra("img");
        if(movice_img!=null){
            iv_img.setImageBitmap(movice_img);
            iv_bg.setImageBitmap(Blur.apply(movice_img));
            iv_bg.setAlpha(0.9f);
        }else{
            MovieEntry entry=new MovieEntry();
            Images images=new Images();
            images.setLarge("https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp");
            entry.setImages(images);
            Glide.with(this)
                    .asBitmap()
                    .load(entry.getImages().getLarge())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            iv_img.setImageBitmap(resource);
                            iv_bg.setImageBitmap(Blur.apply(resource));
                            iv_bg.setAlpha(0.9f);
                        }
                    });
        }
        mCastsAdapter=new MovieCastsAdapter(this,casts);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_casts.setLayoutManager(layoutManager);
        rv_casts.setItemAnimator(new DefaultItemAnimator());
        rv_casts.setAdapter(mCastsAdapter);
    }

    //数据展现
    @Override
    public void showMovieInfo(MovieEntry info) {
        llData.setVisibility(View.VISIBLE);
        etvContent.setText(info.getSummary());
        tvOriginalName.setText("原名："+info.getOriginal_title());
        tvCollectionNum.setText(info.getCollect_count()+" 人收藏");
        StringBuilder countries=new StringBuilder();
        for (String a:info.getCountries()){
            countries.append(a).append("、");
        }
        countries.deleteCharAt(countries.length()-1);
        tvCountryAndYear.setText(info.getYear()+" / "+countries);
        StringBuilder types=new StringBuilder();
        for (String a:info.getGenres()){
            types.append(a).append("、");
        }
        types.deleteCharAt(types.length()-1);
        tvTypes.setText(types+"");
        tvScore.setText(info.getRating().getAverage()+"");
        tvScoreNum.setText(info.getRatings_count()+" 人");
        mCastsAdapter.setNewData(info.getCasts());
    }

    //初始化数据
    @Override
    public void initData() {
        mPresenter.getMovieInfo(mEntry.getId());
    }

    @Override
    public void setPresenter(MoviceInfoContract.Presenter presenter) {
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
        UIUtils.toastData("数据获取失败");
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.unSubscribe();
    }
}
