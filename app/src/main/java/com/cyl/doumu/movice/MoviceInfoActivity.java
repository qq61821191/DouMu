package com.cyl.doumu.movice;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviceInfoActivity extends BaseActivity {
    @BindView(R.id.iv_details_img)
    ImageView iv_img;
    @BindView(R.id.iv_details_bg)
    ImageView iv_bg;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingLayout;

    private MovieEntry mEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movice_info);
        ButterKnife.bind(this);
        initView();
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
}
