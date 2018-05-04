package com.cyl.doumu.index;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;
import com.cyl.doumu.bean.MovieListBean;

/**
 * Created by cyl on 2018/4/15.
 */

public interface UpComingContract {
    interface Presenter extends BasePresenter {

        void getUpComingData(int start);


    }

    interface View extends BaseView<Presenter> {
        void showUpcomingListData(MovieListBean data);
    }
}
