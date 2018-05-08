package com.cyl.doumu.top;

import com.cyl.doumu.base.BasePresenter;
import com.cyl.doumu.base.BaseView;
import com.cyl.doumu.bean.MovieListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
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
