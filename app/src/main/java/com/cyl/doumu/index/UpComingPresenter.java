package com.cyl.doumu.index;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.data.DouBanImpl;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hhh on 2018/4/15.
 */

public class UpComingPresenter implements UpComingContract.Presenter {

    @NonNull
    private UpComingContract.View mView;

    @NonNull
    private DouBanImpl mDouBan;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public UpComingPresenter(@NonNull UpComingContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
        mDouBan=DouBanImpl.getInstance();
    }

    @Override
    public void getUpComingData(int start) {
        addSubscription(
        mDouBan.getUpComing(start)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        //mView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(MovieListBean movieListBean) throws Exception {
                        mView.showUpcomingListData(movieListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                }));

    }

    @Override
    public void subscribe() {
        getUpComingData(0);

    }

    @Override
    public void unSubscribe() {
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscription(Disposable s) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(s);
    }
}
