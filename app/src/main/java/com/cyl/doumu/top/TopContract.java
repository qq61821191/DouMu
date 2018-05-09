package com.cyl.doumu.top;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;
import com.cyl.doumu.bean.MovieListBean;

import java.util.List;

/*
 * @Description: 榜单契约类
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public interface TopContract {
    interface Presenter extends BasePresenter {
        void getType();

        void getTop250(int start);

        void getUsBox(int start);

        void getWeekly(int start);

        void getBestNew(int start);
    }

    interface View extends BaseView<Presenter>{
        void showType(List<String> data);

        void showData(MovieListBean data);
    }
}
