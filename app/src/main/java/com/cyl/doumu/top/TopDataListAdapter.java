package com.cyl.doumu.top;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyl.doumu.R;
import com.cyl.doumu.bean.MovieEntry;

import java.util.List;

/*
 * @Description: 榜单列表的适配器
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/31 0031$ 09:40$
 */
public class TopDataListAdapter extends BaseQuickAdapter<MovieEntry,BaseViewHolder>{
    private Context mContext;
    public TopDataListAdapter(Context context, @Nullable List<MovieEntry> data) {
        super(R.layout.item_hot_list,data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntry item) {


        StringBuilder genres=new StringBuilder();
        if(item.getGenres()!=null && item.getGenres().length>0){
            for (int i=0;i<item.getGenres().length;i++){
                genres.append(item.getGenres()[i]).append("、");
            }
            genres.deleteCharAt(genres.length()-1);
        }


        StringBuilder directors=new StringBuilder();
        if(item.getDirectors()!=null && item.getDirectors().size()>0){
            for (int i=0;i<item.getDirectors().size();i++){
                directors.append(item.getDirectors().get(i).getName()).append("、");
            }
            directors.deleteCharAt(directors.length()-1);
        }

        helper.setText(R.id.tv_item_hot_list_casts,"类别："+genres)
                .setText(R.id.tv_item_hot_list_directors,"导演："+directors)
                .setText(R.id.tv_item_hot_list_rating,"评分："+(item.getRating()==null?"0.0":item.getRating().getAverage()))
                .setText(R.id.tv_item_hot_list_title,item.getTitle());
        if(item.getImages()!=null && item.getImages().getSmall()!=null && !item.getImages().getSmall().equals("")){
            Glide.with(mContext)
                    .load(item.getImages().getSmall())
                    .into((ImageView)helper.getView(R.id.iv_item_hot_list_img));
        }


    }
}
