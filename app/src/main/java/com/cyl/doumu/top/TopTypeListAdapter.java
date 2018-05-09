package com.cyl.doumu.top;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyl.doumu.R;

import java.util.List;

/*
 * @Description: 榜单分类适配器
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public class TopTypeListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private int curPos=0;
    private Context mContext;

    public TopTypeListAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.item_top_type,data);
        mContext=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        final int position=helper.getAdapterPosition();
       TextView tvTitle=helper.getView(R.id.tv_item_top_type_title);
        RelativeLayout rl=helper.getView(R.id.rl_item_top_type);
       tvTitle.setText(item);
       if(curPos==position){
           tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
           rl.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_selector_color));
       }else{
           tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.colorText));
           rl.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
       }
    }

    public void setCurPos(int position){
        curPos=position;
        notifyDataSetChanged();
    }
}
