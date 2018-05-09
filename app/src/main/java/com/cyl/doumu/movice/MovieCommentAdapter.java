package com.cyl.doumu.movice;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyl.doumu.R;
import com.cyl.doumu.bean.MovieEntry;

import java.util.List;

/*
 * @Description: 电影评论列表的适配器
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public class MovieCommentAdapter extends BaseQuickAdapter<MovieEntry,BaseViewHolder> {
    private Context mContext;

    public MovieCommentAdapter(Context context, @Nullable List<MovieEntry> data) {
        super(R.layout.item_movie_info_comment, data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntry item) {

    }
}
