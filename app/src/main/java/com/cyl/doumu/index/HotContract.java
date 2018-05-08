package com.cyl.doumu.index;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;
import com.cyl.doumu.bean.MovieListBean;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 11:13$
 */
public interface HotContract {
    interface View extends BaseView<Presenter> {

        void showHotListData(MovieListBean lists);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void getHotData(int page);


    }
}
