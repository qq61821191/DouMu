package com.cyl.doumu.movice;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseActivity;
import com.cyl.doumu.bean.Images;
import com.cyl.doumu.bean.MovieEntry;
import com.cyl.doumu.utils.Blur;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.rv_movie_info_comment_list)
    RecyclerView rv_commnet;

    private MovieEntry mEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movice_info);
        ButterKnife.bind(this);
        initView();
        fillData();
    }

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


    }

    @Override
    public void showMovieInfo(MovieEntry info) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setPresenter(MoviceInfoContract.Presenter presenter) {

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

    private void fillData(){
        etvContent.setText("上路的开发九埃里克森打飞机啊两三块等解封拉开司法局卡拉京东方啦暑假待付款撒点击分类开始的就看风景昂克赛拉等解封啦暑假待付款拉屎弗兰克就奥斯卡了的法伤恐龙当家福利卡圣诞节快乐废旧塑料卡解放路卡设计费看来都是就爱看了福建看来都是接口了接口就沙龙的看法卡死来看待分开了sad九分裤拉水电费进阿里放进来看就是拉的咖啡机拉时代峻峰斯科拉法健身卡辣等解封卡萨丁发卡萨放假看了三大就发上来看冯老师复读机啊来说坑爹费卢卡斯家里看打算离开发佳世客烂赌夫了三分离开撒旦教龙口粉丝单反");
    }
}
