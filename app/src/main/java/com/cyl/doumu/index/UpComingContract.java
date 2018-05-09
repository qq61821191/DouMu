package com.cyl.doumu.index;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;
import com.cyl.doumu.bean.MovieListBean;

/*
 * @Description: 即将上映 契约类
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public interface UpComingContract {
    interface Presenter extends BasePresenter {

        void getUpComingData(int start);


    }

    interface View extends BaseView<Presenter> {
        void showUpcomingListData(MovieListBean data);
    }
}
