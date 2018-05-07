package com.cyl.doumu.movice;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;
import com.cyl.doumu.bean.MovieEntry;

/**
 * Created by Administrator on 2018/4/20.
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
