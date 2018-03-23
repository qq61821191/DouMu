package com.cyl.doumu.index;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 11:13$
 */
public interface HomeContract {
    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void showHotListData();

        /**
         * 即将上映
         */
        void showUpcoming();

        void showEmpty();

        void showError();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void getHotData();

        void getUpcomingData();

        void searchData(String keyword);
    }
}
