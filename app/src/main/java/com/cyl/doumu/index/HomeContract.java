package com.cyl.doumu.index;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;

/*
 * @Description: 首页的契约类
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 11:13$
 */
public interface HomeContract {
    interface View extends BaseView<Presenter> {



        void showHotListData();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void getHotData();

    }
}
