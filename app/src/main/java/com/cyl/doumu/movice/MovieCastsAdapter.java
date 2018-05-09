package com.cyl.doumu.movice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyl.doumu.R;
import com.cyl.doumu.bean.Cast;

import java.util.List;

/*
 * @Description: 电影主演列表的适配器
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public class MovieCastsAdapter extends BaseQuickAdapter<Cast,BaseViewHolder> {
    private Context mContext;

    public MovieCastsAdapter(Context context, @Nullable List<Cast> data) {
        super(R.layout.item_movie_info_casts, data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Cast item) {
        helper.setText(R.id.tv_casts_name,item.getName());
        if(item.getAvatar()!=null){
            Glide.with(mContext)
                    .load(item.getAvatar().getSmall())
                    .into((ImageView)helper.getView(R.id.iv_casts_img));
        }

    }
}
