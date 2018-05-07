package com.cyl.doumu.movice;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyl.doumu.R;
import com.cyl.doumu.bean.MovieEntry;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */

public class MovieCommentAdapter extends BaseQuickAdapter<MovieEntry,BaseViewHolder> {
    private Context mContext;

    public MovieCommentAdapter(Context context, @Nullable List<MovieEntry> data) {
        super(R.layout.item_top_type, data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntry item) {

    }
}
