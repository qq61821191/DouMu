package com.cyl.doumu.index.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyl.doumu.R;
import com.cyl.doumu.bean.MovieEntry;
import com.cyl.doumu.bean.MovieListBean;

import java.util.List;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/31 0031$ 09:40$
 */
public class HotListAdapter extends BaseQuickAdapter<MovieEntry,BaseViewHolder>{
    private Context mContext;
    public HotListAdapter(Context context,@Nullable List<MovieEntry> data) {
        super(R.layout.item_hot_list,data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntry item) {
        StringBuilder casts=new StringBuilder();
        if(item.getCasts()!=null && item.getCasts().size()>0){
            for (int i=0;i<item.getCasts().size();i++){
                casts.append(item.getCasts().get(i).getName()).append("、");
            }
            casts.deleteCharAt(casts.length()-1);
        }


        StringBuilder directors=new StringBuilder();
        if(item.getDirectors()!=null && item.getDirectors().size()>0){
            for (int i=0;i<item.getDirectors().size();i++){
                directors.append(item.getDirectors().get(i).getName()).append("、");
            }
            directors.deleteCharAt(directors.length()-1);
        }

        helper.setText(R.id.tv_item_hot_list_casts,"主演："+casts)
                .setText(R.id.tv_item_hot_list_directors,"导演："+directors)
                .setText(R.id.tv_item_hot_list_rating,"评分："+item.getRating().getAverage())
                .setText(R.id.tv_item_hot_list_title,item.getTitle());
        Glide.with(mContext)
                .load(item.getImages().getSmall())
                .into((ImageView)helper.getView(R.id.iv_item_hot_list_img));

    }
}
