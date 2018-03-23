package com.cyl.doumu.index;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 11:55$
 */
public class HomePresenter implements HomeContract.Presenter {

    @NonNull
    private HomeContract.View mHomeView;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public HomePresenter(@NonNull HomeContract.View homeView) {
        mHomeView = homeView;

    }

    @Override
    public void getHotData() {

    }

    @Override
    public void getUpcomingData() {

    }

    @Override
    public void searchData(String keyword) {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
