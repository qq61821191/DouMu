package com.cyl.doumu.movice;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;
import com.cyl.doumu.bean.MovieEntry;

/*
 * @Description: 电影详情 契约类
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public interface MoviceInfoContract {
    interface Presenter extends BasePresenter{
        void getMovieInfo(String id);
    }

    interface View extends BaseView<Presenter>{
        void showMovieInfo(MovieEntry info);
        void initData();
    }
}
